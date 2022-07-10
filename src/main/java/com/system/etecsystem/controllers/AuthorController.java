package com.system.etecsystem.controllers;

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
import com.system.etecsystem.models.Author;
import com.system.etecsystem.services.AuthorService;

@RequestMapping("/api")
@RestController
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @GetMapping("autores")
  public ResponseEntity<Object> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.authorService.findAll());
  }

  @GetMapping("autores/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    var authorOptional = this.authorService.findById(id);
    if (authorOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[]");
    return ResponseEntity.status(HttpStatus.OK).body(authorOptional.get());
  }

  @PostMapping("autores")
  public ResponseEntity<Object> saveAuthor(@RequestBody Author author) {
    var authorOptional = this.authorService.findByName(author.getName());
    if (authorOptional.isPresent())
      return ResponseEntity.status(HttpStatus.CONFLICT).body("{ error: 'Author is already exists.' }");
    return ResponseEntity.status(HttpStatus.CREATED).body(this.authorService.saveAuthor(author));
  }

  @DeleteMapping("autores/{ìd}")
  public ResponseEntity<Object> deleteAuthor(@PathVariable Long id) {
    var authorOptional = this.authorService.findById(id);
    if (authorOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Author not found!' }");
    this.authorService.deleteAuthor(id);
    return ResponseEntity.status(HttpStatus.OK).body("{ data: 'Author deleted successfully!' }");
  }

  @PutMapping("autores/{id}")
  public ResponseEntity<Object> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
    var authorOptional = this.authorService.findById(id);
    if (authorOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Author not found!' }");
    else if (this.authorService.findByName(author.getName()).isPresent())
      return ResponseEntity.status(HttpStatus.CONFLICT).body("{ error: 'Author is already exists.' }");
    return ResponseEntity.status(HttpStatus.OK).body(this.authorService.updateAuthor(id, author));
  }
}
