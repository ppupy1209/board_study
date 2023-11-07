package study.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.board.dto.CommentPostDto;
import study.board.service.ArticleCommentService;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping
    public void postNewArticleComment(@RequestBody CommentPostDto request) {
            articleCommentService.saveComment(request);
    }

    @DeleteMapping("/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long commentId) {
        articleCommentService.deleteComment(commentId);
    }
}
