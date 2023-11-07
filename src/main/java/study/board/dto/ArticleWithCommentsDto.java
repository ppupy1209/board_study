package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.board.domain.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ArticleWithCommentsDto {

    private Long articleId;

    private String title;
    private String content;
    private String hashtag;

    private String createdBy;

    private LocalDateTime createdAt;

    private List<ArticleCommentDto> comments;



    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toList()));
    }

}
