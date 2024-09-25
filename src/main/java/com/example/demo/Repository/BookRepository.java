package com.example.demo.Repository;

import com.example.demo.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(String genre);
    List<Book> findByReleaseYear(Integer releaseYear);
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    Book findBookById(Long id);

    @Query("SELECT b, COALESCE(AVG(r.rating), 0) " +
            "FROM Book b LEFT JOIN b.ratings r " +
            "GROUP BY b.id")
    List<Object[]> findAllBooksWithRatings();


}
