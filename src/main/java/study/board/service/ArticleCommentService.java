package study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.Article;
import study.board.dto.CommentPostDto;
import study.board.repository.ArticleCommentRepository;
import study.board.repository.ArticleRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    public void saveComment(CommentPostDto request) {
        Article article = articleRepository.getReferenceById(request.getArticleId());
        articleCommentRepository.save(request.toEntity(article,request.getContent()));
    }

    public void deleteComment(Long commentId) {
        articleCommentRepository.deleteById(commentId);
    }
}
