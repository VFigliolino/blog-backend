package it.vfigliolino.learn.blog.service;

import cn.hutool.core.bean.BeanUtil;
import it.vfigliolino.learn.blog.domain.Image;
import it.vfigliolino.learn.blog.dto.ImageDto;
import it.vfigliolino.learn.blog.mapper.ImageMapper;
import it.vfigliolino.learn.blog.repository.ImageRepository;
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
public class ImageService {
    private final ImageRepository repository;
    private final ImageMapper imageMapper;

    public ImageService(ImageRepository repository, ImageMapper imageMapper) {
        this.repository = repository;
        this.imageMapper = imageMapper;
    }

    public ImageDto save(ImageDto imageDto) {
        Image entity = imageMapper.toEntity(imageDto);
        return imageMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ImageDto findById(Long id) {
        return imageMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ImageDto> findByCondition(ImageDto imageDto, Pageable pageable) {
        Page<Image> entityPage = repository.findAll(pageable);
        List<Image> entities = entityPage.getContent();
        return new PageImpl<>(imageMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ImageDto update(ImageDto imageDto, Long id) {
        ImageDto data = findById(id);
        Image entity = imageMapper.toEntity(imageDto);
        BeanUtil.copyProperties(data, entity);
        return save(imageMapper.toDto(entity));
    }
}