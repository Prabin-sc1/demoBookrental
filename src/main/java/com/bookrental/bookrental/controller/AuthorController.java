package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;
import com.bookrental.bookrental.pojo.author.AuthorResponsePojo;
import com.bookrental.bookrental.service.author.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorRequestPojo> createUpdate(@Valid @RequestBody AuthorRequestPojo authorRequestPojo) {
        this.authorService.createUpdateAuthor(authorRequestPojo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponsePojo>> getAll() {
        return ResponseEntity.ok(this.authorService.getAllAuthor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponsePojo> getAuthor(@PathVariable Integer id) {
        return ResponseEntity.ok(this.authorService.getAuthorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable Integer id) {
        this.authorService.deleteById(id);
        return null;
    }

}