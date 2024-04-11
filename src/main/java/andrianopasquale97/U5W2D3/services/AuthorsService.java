package andrianopasquale97.U5W2D3.services;

import andrianopasquale97.U5W2D3.entities.Author;

import andrianopasquale97.U5W2D3.exceptions.BadRequestExcepition;
import andrianopasquale97.U5W2D3.exceptions.NotFoundException;
import andrianopasquale97.U5W2D3.payloads.AuthorDTO;
import andrianopasquale97.U5W2D3.repositories.AuthorDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import java.io.IOException;


@Service
public class AuthorsService {
    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;



    public AuthorDTO save(AuthorDTO newAuthor) {
        this.authorDAO.findByEmail(newAuthor.email()).ifPresent(
                author -> {
                    throw new BadRequestExcepition("L'email " + newAuthor.email() + " è già in uso!");
                }
        );

        Author newAuthorEntity = new Author(newAuthor.name(), newAuthor.surname(), newAuthor.email(), newAuthor.dateOfBirth(), newAuthor.avatar());

        newAuthorEntity.setAvatar("https://ui-avatars.com/api/?name="+ newAuthor.name() + "+" + newAuthor.surname());
        this.authorDAO.save(newAuthorEntity);
        return newAuthor;
    }

    public Page<Author> getAuthors(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }


    public Author findById(int authorId) {
      return this.authorDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        this.authorDAO.delete(found);
    }

    public AuthorDTO findByIdAndUpdate(int id, AuthorDTO modifiedAuthor) {
        Author found = this.findById(id);
        found.setName(modifiedAuthor.name());
        found.setSurname(modifiedAuthor.surname());
        found.setEmail(modifiedAuthor.email());
        found.setDateOfBirth(modifiedAuthor.dateOfBirth());
        found.setAvatar("https://ui-avatars.com/api/?name="+ modifiedAuthor.name() + "+" + modifiedAuthor.surname());
         this.authorDAO.save(found);
         return modifiedAuthor;

    }
    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
    public Author uploadAuthorImage (MultipartFile image, int authorId) throws IOException {
        Author found = this.findById(authorId);
        found.setAvatar(this.uploadImage(image));
        this.authorDAO.save(found);
        return found;
    }
}
