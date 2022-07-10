package com.system.etecsystem.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.system.etecsystem.models.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

  public Optional<Publisher> findByName(String name);

}
