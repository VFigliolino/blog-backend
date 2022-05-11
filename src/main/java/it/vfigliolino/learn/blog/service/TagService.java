package it.vfigliolino.learn.blog.service;

import cn.hutool.core.bean.BeanUtil;
import it.vfigliolino.learn.blog.domain.Tag;
import it.vfigliolino.learn.blog.dto.TagDto;
import it.vfigliolino.learn.blog.mapper.TagMapper;
import it.vfigliolino.learn.blog.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TagService {
    private final TagRepository repository;
    private final TagMapper tagMapper;

    public TagService(TagRepository repository, TagMapper tagMapper) {
        this.repository = repository;
        this.tagMapper = tagMapper;
    }

    public TagDto save(TagDto tagDto) {
        Tag entity = tagMapper.toEntity(tagDto);
        return tagMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public TagDto findById(Long id) {
        return tagMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<TagDto> findByCondition(TagDto tagDto, Pageable pageable) {
        Page<Tag> entityPage = repository.findAll(pageable);
        List<Tag> entities = entityPage.getContent();
        return new PageImpl<>(tagMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public TagDto update(TagDto tagDto, Long id) {
        TagDto data = findById(id);
        Tag entity = tagMapper.toEntity(tagDto);
        BeanUtil.copyProperties(data, entity);
        return save(tagMapper.toDto(entity));
    }
}