package study.board.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import study.board.domain.Article;
import study.board.domain.ArticleComment;

@Getter
public class CommentPostDto {
    Long articleId;
    String content;

    public ArticleComment toEntity(Article article,String content) {
        return ArticleComment.of(article,content);
    }
}
