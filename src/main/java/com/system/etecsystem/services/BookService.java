package com.system.etecsystem.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.etecsystem.models.Book;
import com.system.etecsystem.repositories.BookRepository;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> findAll() {
    return this.bookRepository.findAll();
  }

  public Optional<Book> findById(Long id) {
    return this.bookRepository.findById(id);
  }

  public Optional<Book> findByIsbn(String isbn) {
    return this.bookRepository.findByIsbn(isbn);
  }

  public Book saveBook(Book book) {
    return this.bookRepository.save(book);
  }

  public Book updateBook(Book book, Long id) {
    book.setId(id);
    return this.bookRepository.save(book);
  }

  public void deleteById(Long id) {
    this.bookRepository.deleteById(id);
  }
}
