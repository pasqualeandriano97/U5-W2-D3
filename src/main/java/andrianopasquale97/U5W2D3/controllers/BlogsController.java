package andrianopasquale97.U5W2D3.controllers;


import andrianopasquale97.U5W2D3.entities.Blogpost;
import andrianopasquale97.U5W2D3.payloads.BlogPostDTO;
import andrianopasquale97.U5W2D3.services.AuthorsService;
import andrianopasquale97.U5W2D3.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost blogpost(@RequestBody @Validated  BlogPostDTO body) throws Exception {

        Blogpost blogpost1 = new Blogpost(body.category(), body.title(),body.cover(), body.content(),body.readingTime(), authorsService.findById(body.authorId()));

         blogsService.save(blogpost1);
         return blogpost1;
    }

    // 2. - GET http://localhost:3001/blogs
    @GetMapping("")
    public Page<Blogpost> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogsService.getPosts(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/blogs/{id}
    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        return blogsService.findById(blogId);
    }

    // 4. - PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable int blogId, @RequestBody BlogPostDTO body) {
        return blogsService.findByIdAndUpdate(blogId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int blogId) {
        blogsService.findByIdAndDelete(blogId);
    }
}
