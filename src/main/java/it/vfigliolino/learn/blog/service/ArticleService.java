package it.vfigliolino.learn.blog.service;

import it.vfigliolino.learn.blog.domain.Article;
import it.vfigliolino.learn.blog.dto.ArticleDto;
import it.vfigliolino.learn.blog.mapper.ArticleMapper;
import it.vfigliolino.learn.blog.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository repository;
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleRepository repository, ArticleMapper articleMapper) {
        this.repository = repository;
        this.articleMapper = articleMapper;
    }

    public ArticleDto save(ArticleDto articleDto) {
        Article entity = articleMapper.toEntity(articleDto);
        return articleMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ArticleDto findById(Long id) {
        return articleMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ArticleDto> findByCondition(ArticleDto articleDto, Pageable pageable) {
        Page<Article> entityPage = repository.findAll(pageable);
        List<Article> entities = entityPage.getContent();
        return new PageImpl<>(articleMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ArticleDto update(ArticleDto articleDto, Long id) {
        ArticleDto data = findById(id);
        Article entity = articleMapper.toEntity(articleDto);
        BeanUtils.copyProperties(data, entity);
        return save(articleMapper.toDto(entity));
    }
}