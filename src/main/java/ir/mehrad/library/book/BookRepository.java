package ir.mehrad.library.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book , Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM book where author_id = ?1")
    public List<Book> findAuthorBooks(long id);
    Book findByNameAndAuthorName(String bookName, String authorName);
}
