package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;
import com.bookrental.bookrental.pojo.author.AuthorResponsePojo;
import com.bookrental.bookrental.service.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Create and Update Author",
            description = "This end point used to create and update member",
            responses = {
                    @ApiResponse(responseCode = "200", description = "success",
                            content = {
                                    @Content(schema = @Schema(implementation = AuthorRequestPojo.class))
                            }
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<GlobalApiResponse> createUpdate(@Valid @RequestBody AuthorRequestPojo authorRequestPojo) {
        authorService.createUpdateAuthor(authorRequestPojo);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.SAVE.getCode(), module),
                null));
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all authors",
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content
                            (array = @ArraySchema
                                    (schema = @Schema(implementation = AuthorResponsePojo.class)))},
                            description = "This end point fetch all authors"
                    )
            }
    )
    public ResponseEntity<GlobalApiResponse> getAll() {
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module), authorService.getAllAuthor()));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get author by id",
            description = "This end point can be used for getting author by id",
            responses = @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = AuthorResponsePojo.class))
                    }
            )
    )
    public ResponseEntity<GlobalApiResponse> getAuthor(@PathVariable Integer id) {
        if(authorService.getAuthorById(id) != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(Message.RETRIEVE.getCode(), module),
                    authorService.getAuthorById(id)));
        }else {
            return ResponseEntity.ok(errorResponse(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), module),
                    null));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete author",
            description = "This end point used to delete author",
            responses = @ApiResponse(responseCode = "200")
    )
    public ResponseEntity<GlobalApiResponse> delete(@PathVariable Integer id) {
        authorService.deleteById(id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(Message.DELETE.getCode(), module), null
        ));
    }
}