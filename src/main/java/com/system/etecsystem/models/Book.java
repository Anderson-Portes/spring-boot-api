package com.system.etecsystem.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_livros")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nm_livro", nullable = false, length = 50)
  private String name;

  @Column(nullable = false, unique = true, length = 20)
  private String isbn;

  @Column(name = "nr_tombo", nullable = false, length = 5)
  private int tombo;

  @Column(name = "nr_AnoLivro", nullable = false, length = 4)
  private String yearBook;

  @Column(name = "status_livro", nullable = false)
  private char bookStatus;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "id_autor")
  @JsonIgnoreProperties("bookList")
  private Author author;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "id_editora")
  @JsonIgnoreProperties("bookList")
  private Publisher publisher;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "id_genero")
  @JsonIgnoreProperties("bookList")
  private Genre genre;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getTombo() {
    return tombo;
  }

  public void setTombo(int tombo) {
    this.tombo = tombo;
  }

  public String getYearBook() {
    return yearBook;
  }

  public void setYearBook(String yearBook) {
    this.yearBook = yearBook;
  }

  public char getBookStatus() {
    return bookStatus;
  }

  public void setBookStatus(char bookStatus) {
    this.bookStatus = bookStatus;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

}
