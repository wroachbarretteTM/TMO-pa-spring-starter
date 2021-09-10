package com.galvanize.tmo.paspringstarter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("api/books")
public class LibraryController {

    @Autowired
    private LibraryService service;

    

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
       
        List<Book> bookList = service.readAll();
       // Collections.sort(bookList, new TitleComparator());
        

        
        return ResponseEntity.ok(bookList);
       
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> read(@PathVariable("id") Long id) {
        Book foundBook = service.read(id);
        if (foundBook == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundBook);
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Book book)
    throws URISyntaxException{
        Book createdBook = service.create(book);
        ObjectMapper mapper = new ObjectMapper();
    if (createdBook == null) {
        return ResponseEntity.notFound().build();
    } else {
        String outputString = new String();
        try {
            outputString = mapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(createdBook.getId())
          .toUri();

        return ResponseEntity.created(uri)
          .body(outputString);
    }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable Long id) {
        Book updatedBook = service.update(id, book);
        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedBook);
        }
    }

    @DeleteMapping
    public ResponseEntity<Book> deleteAllBooks() {
        service.deleteAll();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public void health() {

    }
    
}
