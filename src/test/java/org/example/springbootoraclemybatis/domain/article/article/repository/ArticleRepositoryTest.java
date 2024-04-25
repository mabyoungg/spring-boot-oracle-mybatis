package org.example.springbootoraclemybatis.domain.article.article.repository;

import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @DisplayName("findFirstByOrderByIdDesc")
    @Test
    void t1() {
        Optional<Article> articleOp = articleRepository.findFirstByOrderByIdDesc();

        assertThat(articleOp.isPresent()).isTrue();
    }
}
