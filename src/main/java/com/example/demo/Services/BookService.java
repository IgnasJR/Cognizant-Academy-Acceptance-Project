package com.example.demo.Services;

import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooksWithAverageRatings() {
        List<Object[]> results = bookRepository.findAllBooksWithRatings();
        List<Book> booksWithRatings = new ArrayList<>();

        for (Object[] result : results) {
            String title = (String) result[0];
            Double averageRating = (Double) result[1];

            Book book = findBookByTitle(title);
            if (book != null) {
                book.setAverageRating(averageRating);
                booksWithRatings.add(book);
            }
        }
        return booksWithRatings;
    }

    private Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title).stream().findFirst().orElse(null);
    }
}

