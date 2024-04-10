package andrianopasquale97.U5W2D3.services;


import andrianopasquale97.U5W2D3.entities.Author;
import andrianopasquale97.U5W2D3.entities.Blogpost;
import andrianopasquale97.U5W2D3.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import andrianopasquale97.U5W2D3.repositories.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class BlogsService {

    @Autowired
    private BlogPostDAO blogPostDAO;

    public void save(Blogpost blogpost) {

        blogpost.setCover("https://picsum.photos/200/300");
        this.save(blogpost);

    }

    public Page<Blogpost> getPosts(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostDAO.findAll(pageable);
    }

    public Blogpost findById(int authorId) {
        return this.blogPostDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        this.blogPostDAO.delete(found);
    }

    public Blogpost findByIdAndUpdate(int id, Blogpost body) {
        Blogpost found = this.findById(id);
        found.setCover(body.getCover());
        found.setTitle(body.getTitle());
        found.setCategory(body.getCategory());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());
        return found;

    }
}
