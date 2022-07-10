package com.system.etecsystem.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.etecsystem.models.Author;
import com.system.etecsystem.repositories.AuthorRepository;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  public List<Author> findAll() {
    return this.authorRepository.findAll();
  }

  public Optional<Author> findById(Long id) {
    return this.authorRepository.findById(id);
  }

  public Optional<Author> findByName(String name) {
    return this.authorRepository.findByName(name);
  }

  public Author saveAuthor(Author author) {
    return this.authorRepository.save(author);
  }

  public Author updateAuthor(Long id, Author author) {
    author.setId(id);
    return this.authorRepository.save(author);
  }

  public void deleteAuthor(Long id) {
    this.authorRepository.deleteById(id);
  }
}
