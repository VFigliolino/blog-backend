package it.vfigliolino.learn.blog.controller;

import io.swagger.annotations.Api;
import it.vfigliolino.learn.blog.dto.VoteDto;
import it.vfigliolino.learn.blog.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/vote")
@RestController
@Slf4j
@Api("vote")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated VoteDto voteDto) {
        voteService.save(voteDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteDto> findById(@PathVariable("id") Long id) {
        VoteDto vote = voteService.findById(id);
        return ResponseEntity.ok(vote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(voteService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        voteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<VoteDto>> pageQuery(VoteDto voteDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<VoteDto> votePage = voteService.findByCondition(voteDto, pageable);
        return ResponseEntity.ok(votePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated VoteDto voteDto, @PathVariable("id") Long id) {
        voteService.update(voteDto, id);
        return ResponseEntity.ok().build();
    }
}