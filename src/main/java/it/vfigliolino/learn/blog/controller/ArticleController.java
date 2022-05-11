package it.vfigliolino.learn.blog.controller;

//import com.sun.tools.javac.util.DefinedBy.Api;
import io.swagger.annotations.Api;
import it.vfigliolino.learn.blog.domain.Article;
import it.vfigliolino.learn.blog.dto.ArticleDto;
import it.vfigliolino.learn.blog.mapper.ArticleMapper;
import it.vfigliolino.learn.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/article")
@RestController
@Slf4j
@Api("article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ArticleDto articleDto) {
        articleService.save(articleDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable("id") Long id) {
        ArticleDto article = articleService.findById(id);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(articleService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        articleService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ArticleDto>> pageQuery(ArticleDto articleDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticleDto> articlePage = articleService.findByCondition(articleDto, pageable);
        return ResponseEntity.ok(articlePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ArticleDto articleDto, @PathVariable("id") Long id) {
        articleService.update(articleDto, id);
        return ResponseEntity.ok().build();
    }
}