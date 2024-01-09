package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;
import com.bookrental.bookrental.service.author.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Tag(name = ModuleNameConstants.AUTHOR)
public class AuthorController extends MyBaseController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
        this.module = ModuleNameConstants.AUTHOR;
    }

    @PostMapping
    public ResponseEntity<GlobalApiResponse> createUpdate(@Valid @RequestBody AuthorRequestPojo authorRequestPojo) {
        authorService.createUpdateAuthor(authorRequestPojo);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module),
                null));
    }

    @GetMapping
    public ResponseEntity<GlobalApiResponse> getAll() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), authorService.getAllAuthor()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getAuthor(@PathVariable Integer id) {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module),
                authorService.getAuthorById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> delete(@PathVariable Integer id) {
        authorService.deleteById(id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.DELETE.getCode(), module),
                null));
    }
}