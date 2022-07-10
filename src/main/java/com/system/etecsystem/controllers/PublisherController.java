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

import com.system.etecsystem.models.Publisher;
import com.system.etecsystem.services.PublisherService;

@RequestMapping("/api")
@RestController
public class PublisherController {

  @Autowired
  private PublisherService publisherService;

  @GetMapping("editoras")
  public ResponseEntity<Object> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.publisherService.findAll());
  }

  @GetMapping("editoras/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    var publisherOptional = this.publisherService.findById(id);
    if (publisherOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Publisher not found!' }");
    return ResponseEntity.status(HttpStatus.OK).body(publisherOptional.get());
  }

  @PostMapping("editoras")
  public ResponseEntity<Object> savePublisher(@RequestBody Publisher publisher) {
    var publisherOptional = this.publisherService.findByName(publisher.getName());
    if (publisherOptional.isPresent())
      return ResponseEntity.status(HttpStatus.CONFLICT).body("{ error: 'Publisher already exists!' }");
    return ResponseEntity.status(HttpStatus.CREATED).body(this.publisherService.savePublisher(publisher));
  }

  @DeleteMapping("editoras/{id}")
  public ResponseEntity<Object> deletePublisher(@PathVariable Long id) {
    var publisherOptional = this.publisherService.findById(id);
    if (publisherOptional.isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Publisher not found!' }");
    this.publisherService.deletePublisher(id);
    return ResponseEntity.status(HttpStatus.OK).body("{ data: 'Publisher deleted successfully' }");
  }

  @PutMapping("editoras/{id}")
  public ResponseEntity<Object> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
    if (this.publisherService.findById(id).isEmpty())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Publisher not found!' }");
    else if (this.publisherService.findByName(publisher.getName()).isPresent())
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ error: 'Publisher already exists!' }");
    return ResponseEntity.status(HttpStatus.OK).body(this.publisherService.updatePublisher(id, publisher));
  }

}
