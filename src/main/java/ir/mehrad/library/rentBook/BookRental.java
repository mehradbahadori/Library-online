package ir.mehrad.library.rentBook;

import ir.mehrad.library.book.Book;
import ir.mehrad.library.user.User;
import org.springframework.data.annotation.Id;

public class BookRental {
    @Id
    private String id;
    private String rentDate;
    private Book book;
    private User user;

    public String getId() {
        return id;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
