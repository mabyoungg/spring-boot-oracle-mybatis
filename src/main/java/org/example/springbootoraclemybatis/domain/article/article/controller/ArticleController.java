package org.example.springbootoraclemybatis.domain.article.article.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.example.springbootoraclemybatis.domain.article.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "domain/article/article/list";
    }
}
