package ir.mehrad.library.book;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import ir.mehrad.library.author.Author;
import ir.mehrad.library.author.AuthorService;
import ir.mehrad.library.rentBook.BookRental;
import ir.mehrad.library.rentBook.RentService;
import ir.mehrad.library.user.User;
import ir.mehrad.library.user.UserService;
import ir.mehrad.library.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Controller
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    AuthorService authorService;
    @Autowired
    RentService rentService;

    @GetMapping("/books")
    public String books() {
        return "books";
    }

    @GetMapping("books/list")
    public String showBooksList(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("book", books);
        return "bookslist";
    }

    @GetMapping("books/{id}")
    public String showBookPage(@PathVariable long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "bookdetails";
    }


    @GetMapping("books/add")
    public String showBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "newbook";
    }

    @PostMapping("books/add")
    public @ResponseBody
    String addNewBook(@ModelAttribute(value = "book") Book book, Author author) {
        book.setAuthor(author.getId());
        bookService.saveNewBook(book);
        return "The Book was added to the Library";
    }

    @GetMapping("books/rent")
    public String rentABook(Model model) {
        Book b = new Book();
        model.addAttribute("book", b);
        return "bookrental";
    }

    @PostMapping("books/rent")
    public String rent(@ModelAttribute(value = "rent") Book book, @RequestHeader String authorization) {
        try {
            Util util = new Util();
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(util.getSecret()).parseClaimsJws(authorization);
            long id = (long) claimsJws.getBody().get("message");
            Book b = bookService.getBookById(book.getId());
            User user = (User) userService.findUserById(id);
            BookRental bookRental = new BookRental();
            if (b != null) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/mm/dd hh:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = dtf.format(now);
                bookRental.setRentDate(currentDate);
                bookRental.setUser(user);
                bookRental.setBook(book);
                rentService.saveRent(bookRental);
                return "the book was rented";
            } else {
                return "not found";
            }
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    return "bookwasrented";
    }
}
