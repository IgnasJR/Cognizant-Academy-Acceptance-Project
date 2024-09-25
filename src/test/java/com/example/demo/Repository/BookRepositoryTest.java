package com.example.demo.Repository;

import com.example.demo.Models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByGenre() {
        Book book = new Book();
        book.setAuthor("Test Author");
        book.setGenre("Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Test Book");

        entityManager.merge(book);
        List<Book> found = bookRepository.findByGenre("Test Genre");
        assertThat(found).hasSize(1).extracting(Book::getGenre).containsOnly("Test Genre");
    }

    @Test
    public void testFindByTitle() {
        Book book = new Book();
        book.setAuthor("Test Author");
        book.setGenre("Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Test Book");

        entityManager.merge(book);
        List<Book> found = bookRepository.findByTitle("Test Book");
        assertThat(found).hasSize(1).extracting(Book::getTitle).containsOnly("Test Book");
    }

    @Test
    public void testFindByAuthor() {
        Book book = new Book();
        book.setAuthor("Test Author");
        book.setGenre("Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Test Book");

        entityManager.merge(book);
        List<Book> found = bookRepository.findByAuthor("Test Author");
        assertThat(found).hasSize(1).extracting(Book::getAuthor).containsOnly("Test Author");
    }

    @Test
    public void testFindByReleaseYear() {
        Book book = new Book();
        book.setAuthor("Test Author");
        book.setGenre("Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Test Book");

        entityManager.merge(book);
        List<Book> found = bookRepository.findByReleaseYear(2020);
        assertThat(found).hasSize(1).extracting(Book::getReleaseYear).containsOnly(2020);
    }

    @Test
    public void testNotFoundByGenre() {
        Book book = new Book();
        book.setAuthor("Test Author");
        book.setGenre("Other Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Test Book");
        entityManager.merge(book);
        List<Book> found = bookRepository.findByGenre("Test Genre");
        assertThat(found).isEmpty();
    }

    @Test
    public void testNotFoundByTitle() {
        Book book = new Book();
        book.setAuthor("Test Author");
        book.setGenre("Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Other Test Book");
        entityManager.merge(book);
        List<Book> found = bookRepository.findByTitle("Test Book");
        assertThat(found).isEmpty();
    }

    @Test
    public void testNotFoundByAuthor() {
        Book book = new Book();
        book.setAuthor("Other Test Author");
        book.setGenre("Test Genre");
        book.setPageCount(300);
        book.setReleaseYear(2020);
        book.setTitle("Test Book");
        entityManager.merge(book);
        List<Book> found = bookRepository.findByAuthor("Test Author");
        assertThat(found).isEmpty();
    }

}