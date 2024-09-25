package com.example.demo.Repository;

import com.example.demo.Models.Book;
import com.example.demo.Models.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RatingRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RatingRepository ratingRepository;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setTitle("Test Book");
        testBook.setAuthor("Test Author");
        testBook.setGenre("Fiction");
        testBook.setPageCount(100);
        testBook.setReleaseYear(2024);
        bookRepository.save(testBook);
    }

    @Test
    @Rollback(value = false)
    public void testFindAverageRatingsForAllBooks() {
        Rating rating1 = new Rating();
        rating1.setUserId(1);
        rating1.setBook(testBook);
        rating1.setRating(4);
        ratingRepository.save(rating1);

        Rating rating2 = new Rating();
        rating2.setUserId(2);
        rating2.setBook(testBook);
        rating2.setRating(5);
        ratingRepository.save(rating2);

        List<Object[]> averageRatings = ratingRepository.findAverageRatingsForAllBooks();

        assertThat(averageRatings).hasSize(1);
        Object[] result = averageRatings.get(0);
        assertThat(result[0]).isEqualTo(testBook.getId());
        assertThat((Double) result[1]).isEqualTo(4.5);
    }
}
