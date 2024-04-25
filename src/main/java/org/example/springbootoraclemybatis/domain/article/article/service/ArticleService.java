package org.example.springbootoraclemybatis.domain.article.article.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.example.springbootoraclemybatis.domain.article.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void write(String title, String content) {
        articleRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build()
        );
    }
}
