package com.system.etecsystem.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.system.etecsystem.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

  public Optional<Genre> findByName(String name);

}
