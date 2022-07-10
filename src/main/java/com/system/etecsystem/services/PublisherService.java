package com.system.etecsystem.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.etecsystem.repositories.PublisherRepository;
import com.system.etecsystem.models.Publisher;

@Service
public class PublisherService {

  @Autowired
  private PublisherRepository publisherRepository;

  public List<Publisher> findAll() {
    return this.publisherRepository.findAll();
  }

  public Optional<Publisher> findById(Long id) {
    return this.publisherRepository.findById(id);
  }

  public Optional<Publisher> findByName(String name) {
    return this.publisherRepository.findByName(name);
  }

  public Publisher savePublisher(Publisher publisher) {
    return this.publisherRepository.save(publisher);
  }

  public Publisher updatePublisher(Long id, Publisher publisher) {
    publisher.setId(id);
    return this.publisherRepository.save(publisher);
  }

  public void deletePublisher(Long id) {
    this.publisherRepository.deleteById(id);
  }

}
