package com.example.demo.Controller;

import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.RatingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String author) {

        List<Book> books;

        if (genre != null) {
            books = bookRepository.findByGenre(genre);
        } else if (releaseYear != null) {
            books = bookRepository.findByReleaseYear(releaseYear);
        } else if (author != null) {
            books = bookRepository.findByAuthor(author);
        } else {
            books = bookRepository.findAll();
        }

        List<Object[]> ratingsData = bookRepository.findAllBooksWithRatings();

        Map<Long, Double> averageRatingsMap = new HashMap<>();
        for (Object[] data : ratingsData) {
            Book book = (Book) data[0];
            Double averageRating = (Double) data[1];
            averageRatingsMap.put(book.getId(), averageRating);
        }

        for (Book book : books) {
            book.setAverageRating(averageRatingsMap.getOrDefault(book.getId(), 0.0));
        }

        return books;
    }


}



