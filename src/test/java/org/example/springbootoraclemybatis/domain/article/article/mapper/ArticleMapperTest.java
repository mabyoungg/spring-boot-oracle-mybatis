package org.example.springbootoraclemybatis.domain.article.article.mapper;

import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;

    @DisplayName("findFirstByOrderByIdDesc")
    @Test
    void t1() {
        Optional<Article> articleOp = articleMapper.findFirstByOrderByIdDesc();

        assertThat(articleOp.isPresent()).isTrue();
    }

    @DisplayName("findTop3ByOrderByIdDesc")
    @Test
    void t2() {
        List<Article> articles = articleMapper.findTop3ByOrderByIdDesc();

        Article latestArticle = articleMapper.findFirstByOrderByIdDesc().get();

        long idLast = latestArticle.getId();
        long idSecondLast = idLast - 1;
        long idThirdLast = idLast - 2;

        assertThat(articles)
                .hasSize(3)
                .extracting(Article::getId)
                .containsExactly(idLast, idSecondLast, idThirdLast);
    }

    @DisplayName("search")
    @Test
    void t3() {
        int page = 1;
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(sorts));

        Page<Article> articlePage = articleMapper.search(
                List.of("title", "content"),
                "제목",
                pageable
        );

        assertThat(articlePage.getContent())
                .extracting(Article::getTitle)
                .containsSubsequence("제목 4", "제목 3", "제목 2", "제목 1");
    }
}
