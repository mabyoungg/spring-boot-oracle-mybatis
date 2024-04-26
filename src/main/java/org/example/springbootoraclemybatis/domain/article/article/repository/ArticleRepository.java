package org.example.springbootoraclemybatis.domain.article.article.repository;

import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

    Optional<Article> findFirstByOrderByIdDesc();

    List<Article> findTop3ByOrderByIdDesc();
}
