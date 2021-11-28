package ir.mehrad.library.rentBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {
    @Autowired
    RentRepository rentRepository;

    public void saveRent(BookRental bookRental){
        rentRepository.save(bookRental);
    }
}
