package study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.board.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
