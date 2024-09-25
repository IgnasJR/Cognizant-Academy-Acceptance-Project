package com.example.demo.Repository;

import com.example.demo.Models.Book;
import com.example.demo.Models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT r.book.id, AVG(r.rating) FROM Rating r GROUP BY r.book.id")
    List<Object[]> findAverageRatingsForAllBooks();

    @Query("SELECT r FROM Rating r WHERE r.userId = :userId AND r.book.id = :bookId")
    Rating findByUserIdAndBookId(@Param("userId")Integer userId, Long bookId);
    boolean existsByUserIdAndBookId(Integer userId, Long bookId);


}


