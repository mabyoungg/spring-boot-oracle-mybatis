package org.example.springbootoraclemybatis.domain.article.article.repository;

import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
