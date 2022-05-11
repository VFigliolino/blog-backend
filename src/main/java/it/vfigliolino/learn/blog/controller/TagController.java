package it.vfigliolino.learn.blog.controller;

import io.swagger.annotations.Api;
import it.vfigliolino.learn.blog.dto.TagDto;
import it.vfigliolino.learn.blog.service.TagService;
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

@RequestMapping("/tag")
@RestController
@Slf4j
@Api("tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated TagDto tagDto) {
        tagService.save(tagDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable("id") Long id) {
        TagDto tag = tagService.findById(id);
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(tagService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        tagService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<TagDto>> pageQuery(TagDto tagDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TagDto> tagPage = tagService.findByCondition(tagDto, pageable);
        return ResponseEntity.ok(tagPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated TagDto tagDto, @PathVariable("id") Long id) {
        tagService.update(tagDto, id);
        return ResponseEntity.ok().build();
    }
}