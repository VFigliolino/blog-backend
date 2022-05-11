package it.vfigliolino.learn.blog.controller;

import io.swagger.annotations.Api;
import it.vfigliolino.learn.blog.dto.ImageDto;
import it.vfigliolino.learn.blog.service.ImageService;
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

@RequestMapping("/image")
@RestController
@Slf4j
@Api("image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ImageDto imageDto) {
        imageService.save(imageDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> findById(@PathVariable("id") Long id) {
        ImageDto image = imageService.findById(id);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(imageService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        imageService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ImageDto>> pageQuery(ImageDto imageDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ImageDto> imagePage = imageService.findByCondition(imageDto, pageable);
        return ResponseEntity.ok(imagePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ImageDto imageDto, @PathVariable("id") Long id) {
        imageService.update(imageDto, id);
        return ResponseEntity.ok().build();
    }
}