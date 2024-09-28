package cache_stampede.persistence;

import jakarta.persistence.*;

@Entity
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    private String author;

    private String contents;

    private int views;

    protected Article() {
    }

    public Article(final String title, final String author, final String contents, final int views) {
        this.title = title;
        this.author = author;
        this.contents = contents;
        this.views = views;
    }

    public static Article toEntity(final String title, final String author, final String contents) {
        return new Article(title, author, contents, 0);
    }

    // getter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public int getViews() {
        return views;
    }
}
