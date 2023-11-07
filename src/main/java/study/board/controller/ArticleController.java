package study.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.board.domain.type.SearchType;
import study.board.dto.ArticleDto;
import study.board.dto.ArticlePatchDto;
import study.board.dto.ArticlePostDto;
import study.board.dto.ArticleWithCommentsDto;
import study.board.service.ArticleService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Page<ArticleDto> searchArticles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable
            ) {
        return articleService.searchArticles(searchType,keyword,pageable);
    }

    @GetMapping("/{article-id}")
    public ArticleWithCommentsDto getArticle(@PathVariable("article-id") Long articleId) {
        return articleService.searchArticle(articleId);
    }

    @PostMapping
    public void postArticle(@RequestBody ArticlePostDto articlePostDto) {
        articleService.saveArticle(articlePostDto);
    }

    @PatchMapping("/{article-id}")
    public void patchArticle(@PathVariable("article-id") Long articleId,
                             @RequestBody ArticlePatchDto articlePatchDto) {
        articleService.updateArticle(articlePatchDto,articleId);
    }

    @GetMapping("/search-hashtag")
    public List<String> getHashtags() {
        return articleService.getHashtags();
    }

    @GetMapping("/via-hashtag")
    public Page<ArticleDto> searchArticlesViaHashtag(
            @RequestParam(required = false) String hashtag,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return articleService.searchArticlesViaHashtag(hashtag,pageable);
    }

}
