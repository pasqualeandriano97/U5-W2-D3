package andrianopasquale97.U5W2D3.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blogPosts")
public class Blogpost {
    @Id
    @GeneratedValue
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blogpost(String category, String title, String cover, String content, double readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }
}
