package com.system.etecsystem.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.etecsystem.dtos.BookDto;
import com.system.etecsystem.models.Book;
import com.system.etecsystem.services.AuthorService;
import com.system.etecsystem.services.BookService;
import com.system.etecsystem.services.GenreService;
import com.system.etecsystem.services.PublisherService;

@RestController
@RequestMapping("/api")
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private GenreService genreService;

  @Autowired
  private AuthorService authorService;

  @Autowired
  private PublisherService publisherService;

  @GetMapping("livros")
  public ResponseEntity<Object> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.bookService.findAll());
  }

  @GetMapping("livros/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    var bookOptional = this.bookService.findById(id);
    if (bookOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Book not found!' }");
    return ResponseEntity.status(HttpStatus.OK).body(bookOptional.get());
  }

  @PostMapping("livros")
  public ResponseEntity<Object> saveBook(@RequestBody BookDto bookDto) {
    var optionalAuthor = this.authorService.findByName(bookDto.getAuthor());
    var optionalGenre = this.genreService.findByName(bookDto.getGenre());
    var optionalPublisher = this.publisherService.findByName(bookDto.getPublisher());
    var optionalBook = this.bookService.findByIsbn(bookDto.getIsbn());
    if (optionalAuthor.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Author not found!' }");
    else if (optionalGenre.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Genre not found!' }");
    else if (optionalPublisher.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Publisher not found!' }");
    else if (optionalBook.isPresent())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Book already exists!' }");
    Book book = new Book();
    BeanUtils.copyProperties(bookDto, book);
    book.setAuthor(optionalAuthor.get());
    book.setGenre(optionalGenre.get());
    book.setPublisher(optionalPublisher.get());
    return ResponseEntity.status(HttpStatus.OK).body(this.bookService.saveBook(book));
  }

  @DeleteMapping("livros/{id}")
  public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
    var bookOptional = this.bookService.findById(id);
    if (bookOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Book not found!' }");
    this.bookService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body("{ data: 'Book deleted successfully' }");
  }

  @PutMapping("livros/{id}")
  public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
    var optionalAuthor = this.authorService.findByName(bookDto.getAuthor());
    var optionalGenre = this.genreService.findByName(bookDto.getGenre());
    var optionalPublisher = this.publisherService.findByName(bookDto.getPublisher());
    var optionalBook = this.bookService.findById(id);
    var optionalBookByIsbn = this.bookService.findByIsbn(bookDto.getIsbn());
    if (optionalAuthor.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Author not found!' }");
    else if (optionalGenre.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Genre not found!' }");
    else if (optionalPublisher.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Publisher not found!' }");
    else if (optionalBook.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Book not found!' }");
    else if (optionalBookByIsbn.isPresent() && optionalBookByIsbn.get().getId() != id)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Isbn already exists!' }");
    Book book = new Book();
    BeanUtils.copyProperties(bookDto, book);
    book.setAuthor(optionalAuthor.get());
    book.setGenre(optionalGenre.get());
    book.setPublisher(optionalPublisher.get());
    return ResponseEntity.status(HttpStatus.OK).body(this.bookService.updateBook(book, id));
  }
}
