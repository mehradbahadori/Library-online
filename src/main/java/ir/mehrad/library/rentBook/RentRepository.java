package ir.mehrad.library.rentBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends CrudRepository<BookRental,Long> {
}
