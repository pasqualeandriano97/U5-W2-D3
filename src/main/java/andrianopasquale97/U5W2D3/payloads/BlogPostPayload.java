package andrianopasquale97.U5W2D3.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BlogPostPayload {

    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
    private int authorId;


    public BlogPostPayload(String category, String title, String cover, String content, double readingTime, int authorId) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.authorId = authorId;
    }
}
