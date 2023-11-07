package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.board.domain.Article;

@Getter
public class ArticlePostDto {

    private String title;
    private String content;
    private String hashtag;


    public Article toEntity() {
        return Article.of(
                title,content,hashtag
        );
    }

}
