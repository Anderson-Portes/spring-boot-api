package com.system.etecsystem.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.system.etecsystem.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  public Optional<Author> findByName(String name);

}
