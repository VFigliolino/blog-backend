package it.vfigliolino.learn.blog.controller;

//import com.sun.tools.javac.util.DefinedBy.Api;
import io.swagger.annotations.Api;
import it.vfigliolino.learn.blog.domain.Comment;
import it.vfigliolino.learn.blog.dto.CommentDto;
import it.vfigliolino.learn.blog.mapper.CommentMapper;
import it.vfigliolino.learn.blog.service.CommentService;
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

@RequestMapping("/comment")
@RestController
@Slf4j
@Api("comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated CommentDto commentDto) {
        commentService.save(commentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable("id") Long id) {
        CommentDto comment = commentService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(commentService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<CommentDto>> pageQuery(CommentDto commentDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommentDto> commentPage = commentService.findByCondition(commentDto, pageable);
        return ResponseEntity.ok(commentPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CommentDto commentDto, @PathVariable("id") Long id) {
        commentService.update(commentDto, id);
        return ResponseEntity.ok().build();
    }
}