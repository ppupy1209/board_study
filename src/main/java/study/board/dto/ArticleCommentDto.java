package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.board.domain.ArticleComment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleCommentDto {

    private String content;

    private String createdBy;

    private LocalDateTime createdAt;

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getContent(),
                articleComment.getCreatedBy(),
                articleComment.getCreatedAt()
        );
    }

}
