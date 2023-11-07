package study.board.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.board.domain.Article;
import study.board.domain.ArticleComment;
import study.board.repository.ArticleCommentRepository;
import study.board.repository.ArticleRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @PostConstruct
    public void init() {
        Article article1 = Article.of("1번","내용 1","#spring");
        Article article2 = Article.of("2번","내용 2","#java");
        Article article3 = Article.of("3번","내용 3","#python");
        Article article4 = Article.of("4번","내용 4","#c++");
        Article article5 = Article.of("5번","내용 5","#kotlin");
        Article article6 = Article.of("5번","내용 5","#kotlin");

        articleRepository.saveAll(List.of(article1,article2,article3,article4,article5,article6));

        ArticleComment articleComment1 = ArticleComment.of(article1,"Answer 1");
        ArticleComment articleComment2 = ArticleComment.of(article2,"Answer 2");
        ArticleComment articleComment3 = ArticleComment.of(article3,"Answer 3");
        ArticleComment articleComment4 = ArticleComment.of(article4,"Answer 4");
        ArticleComment articleComment5 = ArticleComment.of(article5,"Answer 5");

        articleCommentRepository.saveAll(List.of(articleComment1,articleComment2,articleComment3,articleComment4,articleComment5));
    }
}
