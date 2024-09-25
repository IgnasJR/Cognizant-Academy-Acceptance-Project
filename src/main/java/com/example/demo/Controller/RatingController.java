package com.example.demo.Controller;

import com.example.demo.Models.Book;
import com.example.demo.Models.Rating;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;

    public RatingController(RatingRepository ratingRepository, BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody Rating rating) {
        if (rating.getRating() < 0 || rating.getRating() > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Rating must be between 0 and 5.");
        }
        if (rating.getBook() == null || rating.getUserId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Book and User ID cannot be null.");
        }

        if (rating.getBook().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Book ID cannot be null.");
        }

        Book book = bookRepository.findBookById(rating.getBook().getId());
        if (book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Book does not exist.");
        }

        if (ratingRepository.existsByUserIdAndBookId(rating.getUserId(), rating.getBook().getId())) {
            Rating existingRating = ratingRepository.findByUserIdAndBookId(rating.getUserId(), rating.getBook().getId());
            existingRating.setRating(rating.getRating());

            Rating updatedRating = ratingRepository.save(existingRating);
            return ResponseEntity.status(HttpStatus.OK).body(updatedRating);
        }

        rating.setBook(book);
        Rating savedRating = ratingRepository.save(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
    }


}
