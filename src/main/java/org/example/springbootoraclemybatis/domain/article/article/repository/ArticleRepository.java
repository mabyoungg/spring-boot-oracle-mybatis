package org.example.springbootoraclemybatis.domain.article.article.repository;

import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findFirstByOrderByIdDesc();
}
