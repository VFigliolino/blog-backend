package it.vfigliolino.learn.blog.service;

import cn.hutool.core.bean.BeanUtil;
import it.vfigliolino.learn.blog.domain.UserProfile;
import it.vfigliolino.learn.blog.dto.UserProfileDto;
import it.vfigliolino.learn.blog.mapper.UserProfileMapper;
import it.vfigliolino.learn.blog.repository.UserProfileRepository;
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
public class UserProfileService {
    private final UserProfileRepository repository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileService(UserProfileRepository repository, UserProfileMapper userProfileMapper) {
        this.repository = repository;
        this.userProfileMapper = userProfileMapper;
    }

    public UserProfileDto save(UserProfileDto userProfileDto) {
        UserProfile entity = userProfileMapper.toEntity(userProfileDto);
        return userProfileMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public UserProfileDto findById(Long id) {
        return userProfileMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<UserProfileDto> findByCondition(UserProfileDto userProfileDto, Pageable pageable) {
        Page<UserProfile> entityPage = repository.findAll(pageable);
        List<UserProfile> entities = entityPage.getContent();
        return new PageImpl<>(userProfileMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public UserProfileDto update(UserProfileDto userProfileDto, Long id) {
        UserProfileDto data = findById(id);
        UserProfile entity = userProfileMapper.toEntity(userProfileDto);
        BeanUtil.copyProperties(data, entity);
        return save(userProfileMapper.toDto(entity));
    }
}