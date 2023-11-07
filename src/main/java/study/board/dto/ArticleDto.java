package study.board.dto;

import lombok.Getter;
import study.board.domain.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private String title;
    private String content;
    private String hashtag;

    private String createdBy;

    private LocalDateTime createdAt;

    private ArticleDto(String title, String content, String hashtag, String createdBy, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public static ArticleDto of(String title, String content, String hashtag, String createdBy, LocalDateTime createdAt) {
        return new ArticleDto(title,content,hashtag,createdBy,createdAt);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedBy(),
                entity.getCreatedAt()
        );
    }


}
