package ir.mehrad.library.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.mehrad.library.user.User;
import ir.mehrad.library.user.UserService;
import ir.mehrad.library.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String userManagement(){
        return "usermanagement";
    }
    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> users = userService.getAlUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("users/{id}")
    public String showUserPage(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userpage";
    }

    @GetMapping("newuser")
    public String showNewUserForm(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "newuser";
    }

    @PostMapping("newuser")
    public String addNewUser(@ModelAttribute(value = "user") User user) {
        userService.saveUser(user);
        return "login";
    }

    @GetMapping("login")
    public String login(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute(value = "user") User user) {
        User u = userService.getUsernameAndPassword
                (user.getUsername(), user.getPassword());
        Util util=new Util();
        if (u != null) {
            String payload = String.format("{\"message\" : %d }", u.getId());
            String token = Jwts.builder()
                    .setPayload(payload)
                    .signWith(SignatureAlgorithm.HS256,util.getSecret())
                    .compact();
            u.setToken(token);
            userService.saveUser(u);
            return "lists";
        } else {
            return "not found";
        }
    }
}
