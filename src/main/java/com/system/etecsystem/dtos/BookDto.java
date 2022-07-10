package com.system.etecsystem.dtos;

public class BookDto {

  // Java Book Model propreties
  private String name;
  private String isbn;
  private String yearBook;
  private int tombo;
  private char bookStatus;

  // Other propeties
  private String genre;
  private String author;
  private String publisher;

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

  public String getYearBook() {
    return yearBook;
  }

  public void setYearBook(String yearBook) {
    this.yearBook = yearBook;
  }

  public int getTombo() {
    return tombo;
  }

  public void setTombo(int tombo) {
    this.tombo = tombo;
  }

  public char getBookStatus() {
    return bookStatus;
  }

  public void setBookStatus(char bookStatus) {
    this.bookStatus = bookStatus;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

}
