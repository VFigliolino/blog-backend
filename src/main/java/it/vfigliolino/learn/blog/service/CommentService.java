package it.vfigliolino.learn.blog.service;

import it.vfigliolino.learn.blog.domain.Comment;
import it.vfigliolino.learn.blog.dto.CommentDto;
import it.vfigliolino.learn.blog.mapper.CommentMapper;
import it.vfigliolino.learn.blog.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository repository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository repository, CommentMapper commentMapper) {
        this.repository = repository;
        this.commentMapper = commentMapper;
    }

    public CommentDto save(CommentDto commentDto) {
        Comment entity = commentMapper.toEntity(commentDto);
        return commentMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CommentDto findById(Long id) {
        return commentMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<CommentDto> findByCondition(CommentDto commentDto, Pageable pageable) {
        Page<Comment> entityPage = repository.findAll(pageable);
        List<Comment> entities = entityPage.getContent();
        return new PageImpl<>(commentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CommentDto update(CommentDto commentDto, Long id) {
        CommentDto data = findById(id);
        Comment entity = commentMapper.toEntity(commentDto);
        BeanUtils.copyProperties(data, entity);
        return save(commentMapper.toDto(entity));
    }
}