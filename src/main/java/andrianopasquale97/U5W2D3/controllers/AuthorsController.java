package andrianopasquale97.U5W2D3.controllers;

import andrianopasquale97.U5W2D3.entities.Author;
import andrianopasquale97.U5W2D3.exceptions.BadRequestExcepition;
import andrianopasquale97.U5W2D3.payloads.AuthorDTO;
import andrianopasquale97.U5W2D3.services.AuthorsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public AuthorDTO saveAuthor(@RequestBody @Validated AuthorDTO body, BindingResult result){
        if (result.hasErrors()) {
            throw new BadRequestExcepition(result.getAllErrors());
        }
        System.out.println(body);
        return authorsService.save(body);
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping("")
    public Page<Author> getAllAuthor(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorsService.getAuthors(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) throws Exception {
        return authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public AuthorDTO findAndUpdate(@PathVariable int authorId, @RequestBody AuthorDTO body) throws Exception {
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }

    @PostMapping("/upload/{authorId}")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image,@PathVariable int authorId) throws IOException {
         this.authorsService.uploadAuthorImage(image, authorId);
        return this.authorsService.uploadImage(image);

    }
}
