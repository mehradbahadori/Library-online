package ir.mehrad.library.user;

import ir.mehrad.library.user.User;
import ir.mehrad.library.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAlUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(long id) {
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()) {
            return userId.get();
        } else {
            return new User();
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<Object> findUserById(long id) {
        return findUserById(id);
    }
}
