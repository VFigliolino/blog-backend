package it.vfigliolino.learn.blog.service;

import cn.hutool.core.bean.BeanUtil;
import it.vfigliolino.learn.blog.domain.Vote;
import it.vfigliolino.learn.blog.dto.VoteDto;
import it.vfigliolino.learn.blog.mapper.VoteMapper;
import it.vfigliolino.learn.blog.repository.VoteRepository;
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
public class VoteService {
    private final VoteRepository repository;
    private final VoteMapper voteMapper;

    public VoteService(VoteRepository repository, VoteMapper voteMapper) {
        this.repository = repository;
        this.voteMapper = voteMapper;
    }

    public VoteDto save(VoteDto voteDto) {
        Vote entity = voteMapper.toEntity(voteDto);
        return voteMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public VoteDto findById(Long id) {
        return voteMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<VoteDto> findByCondition(VoteDto voteDto, Pageable pageable) {
        Page<Vote> entityPage = repository.findAll(pageable);
        List<Vote> entities = entityPage.getContent();
        return new PageImpl<>(voteMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public VoteDto update(VoteDto voteDto, Long id) {
        VoteDto data = findById(id);
        Vote entity = voteMapper.toEntity(voteDto);
        BeanUtil.copyProperties(data, entity);
        return save(voteMapper.toDto(entity));
    }
}