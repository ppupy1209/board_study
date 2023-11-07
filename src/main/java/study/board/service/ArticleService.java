package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.Article;
import study.board.domain.type.SearchType;
import study.board.dto.ArticleDto;
import study.board.dto.ArticlePatchDto;
import study.board.dto.ArticlePostDto;
import study.board.dto.ArticleWithCommentsDto;
import study.board.repository.ArticleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String keyword, Pageable pageable) {

        if (keyword==null || keyword.isEmpty()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        Page<ArticleDto> articleDtos = Page.empty();
        switch (searchType) {
            case TITLE:
               articleDtos = articleRepository.findByTitleContaining(keyword, pageable).map(ArticleDto::from);
            break;
            case CONTENT:
               articleDtos = articleRepository.findByContentContaining(keyword, pageable).map(ArticleDto::from);
            break;
            case HASHTAG:
                articleDtos = articleRepository.findByHashtag("#" + keyword, pageable).map(ArticleDto::from);
            break;
        }
        return articleDtos;
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto searchArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public  void saveArticle(ArticlePostDto articlePostDto) {
        articleRepository.save(articlePostDto.toEntity());
    }

    public void updateArticle(ArticlePatchDto articlePatchDto, Long articleId) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            if(articlePatchDto.getTitle() != null) {article.setTitle(articlePatchDto.getTitle());}
            if (articlePatchDto.getContent() != null) {article.setContent(articlePatchDto.getContent());}
            article.setHashtag(articlePatchDto.getHashtag());
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패.");
        }

    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

    public Page<ArticleDto> searchArticlesViaHashtag(String hashtag, Pageable pageable) {
        if (hashtag == null || hashtag.isBlank()) {
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtag("#" + hashtag, pageable).map(ArticleDto::from);
    }

    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
    }
}
