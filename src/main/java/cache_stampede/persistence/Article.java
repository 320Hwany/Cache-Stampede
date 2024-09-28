package cache_stampede.persistence;

import jakarta.persistence.*;

@Entity
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    private String contents;

    private int views;

    protected Article() {
    }

    private Article(final String title, final String contents, final int views) {
        this.title = title;
        this.contents = contents;
        this.views = views;
    }

    public static Article toEntity(final String title, final String contents) {
        return new Article(title, contents, 0);
    }
}
