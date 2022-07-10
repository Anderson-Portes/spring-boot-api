package com.system.etecsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.etecsystem.models.Genre;
import com.system.etecsystem.repositories.GenreRepository;

@Service
public class GenreService {

  @Autowired
  private GenreRepository genreRepository;

  public List<Genre> findAll() {
    return this.genreRepository.findAll();
  }

  public Optional<Genre> findById(Long id) {
    return this.genreRepository.findById(id);
  }

  public Optional<Genre> findByName(String name) {
    return this.genreRepository.findByName(name);
  }

  public Genre saveGenre(Genre genre) {
    return this.genreRepository.save(genre);
  }

  public Genre updateGenre(Long id, Genre genre) {
    genre.setId(id);
    return this.genreRepository.save(genre);
  }

  public void deleteGenre(Long id) {
    this.genreRepository.deleteById(id);
  }
}
