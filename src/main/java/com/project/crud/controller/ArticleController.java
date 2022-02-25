package com.project.crud.controller;

import com.project.crud.model.Article;
import com.project.crud.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/menu")
    public String menu(Model model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "menu";
    }

    @GetMapping("/create")
    public String createArticleForm(Article article) {
        return "create";
    }

    @PostMapping("/create")
    public String createArticle(Article article) {
        articleRepository.save(article);
        return "redirect:/menu";
    }

    @GetMapping("article-delete/{id}")
    public String deleteArticle(@PathVariable("id") Integer id) {
        articleRepository.deleteById(id);
        return "redirect:/menu";
    }

    @GetMapping("/article-update/{id}")
    public String updateArticleForm(@PathVariable("id") Integer id, Model model) {
        Optional<Article> article = articleRepository.findById(id);
        model.addAttribute("article", article);
        return "/article-update";
    }

    @PostMapping("/article-update")
    public String updateArticle(Article article) {
        articleRepository.save(article);
        return "redirect:/menu";
    }

}
