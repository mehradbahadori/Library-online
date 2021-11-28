package ir.mehrad.library.book;

import ir.mehrad.library.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public List<Book> getAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }
    public Book getBookById(long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent())
            return optionalBook.get();
        return new Book();
    }
    public List<Book> getAuthorBooks(long authorId) {
        Iterable<Book> all = bookRepository.findAuthorBooks(authorId);
        return (List<Book>) all;
    }
    public Book saveNewBook(Book book) {
        bookRepository.save(book);
        return book;
    }
}
