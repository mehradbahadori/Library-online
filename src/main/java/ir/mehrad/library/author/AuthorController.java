package ir.mehrad.library.author;

import ir.mehrad.library.book.Book;
import ir.mehrad.library.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;

    @GetMapping("authors")
    public String authors(){
        return "authors";
    }

    @GetMapping("authors/list")
    public String showAuthorsList(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authorslist";
    }

    @GetMapping("authors/{id}")
    public String showAuthorPage(@PathVariable long id , Model model){
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author" , author );
        List<Book> books=bookService.getAuthorBooks(id);
        model.addAttribute("book" ,books);
        return "authordetails";
    }

    @GetMapping("authors/create")
    public String showAuthorForm(Model model){
        Author author = new Author();
        model.addAttribute("author" , author);
        return "newauthor";
    }

    @PostMapping("authors/create")
    public @ResponseBody String createBook(@ModelAttribute(value = "author") Author author){
         authorService.saveNewAuthor(author);
        return "Author created";
    }
}
