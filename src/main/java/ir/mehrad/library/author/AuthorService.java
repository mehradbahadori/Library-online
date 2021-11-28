package ir.mehrad.library.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAllAuthors(){
        return (List<Author>) authorRepository.findAll();
    }
    public Author getAuthorById(long id){
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent())
            return optionalAuthor.get();
        return new Author();
    }
    public Author findAuthorById(long id){
        Optional<Author> author = authorRepository.findById(id);
        return author.get();
    }
    public Author saveNewAuthor(Author author){
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }
}
