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

import com.system.etecsystem.models.Genre;
import com.system.etecsystem.services.GenreService;

@RestController
@RequestMapping("/api")
public class GenreController {

  @Autowired
  private GenreService genreService;

  @GetMapping("generos")
  public ResponseEntity<Object> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.genreService.findAll());
  }

  @GetMapping("generos/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    var genreOptional = this.genreService.findById(id);
    if (genreOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Genre not found!' }");
    return ResponseEntity.status(HttpStatus.OK).body(genreOptional.get());
  }

  @PostMapping("generos")
  public ResponseEntity<Object> saveGenre(@RequestBody Genre genre) {
    var genreOptional = this.genreService.findByName(genre.getName());
    if (genreOptional.isPresent())
      return ResponseEntity.status(HttpStatus.CONFLICT).body("{ error: 'Genre already exists!' }");
    return ResponseEntity.status(HttpStatus.CREATED).body(this.genreService.saveGenre(genre));
  }

  @PutMapping("generos/{id}")
  public ResponseEntity<Object> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
    if (this.genreService.findById(id).isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Genre not found!' }");
    else if (this.genreService.findByName(genre.getName()).isPresent())
      return ResponseEntity.status(HttpStatus.CONFLICT).body("{ error: 'Genre already exists!' }");
    return ResponseEntity.status(HttpStatus.OK).body(this.genreService.updateGenre(id, genre));
  }

  @DeleteMapping("generos/{id}")
  public ResponseEntity<Object> deleteGenre(@PathVariable Long id) {
    var genreOptional = this.genreService.findById(id);
    if (genreOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Genre not found!' }");
    this.genreService.deleteGenre(id);
    return ResponseEntity.status(HttpStatus.OK).body("{ data: 'Genre deleted successfully!' }");
  }
}
