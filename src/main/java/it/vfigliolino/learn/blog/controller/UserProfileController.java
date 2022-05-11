package it.vfigliolino.learn.blog.controller;

import io.swagger.annotations.Api;
import it.vfigliolino.learn.blog.dto.UserProfileDto;
import it.vfigliolino.learn.blog.service.UserProfileService;
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

@RequestMapping("/profile")
@RestController
@Slf4j
@Api("profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated UserProfileDto userProfileDto) {
        userProfileService.save(userProfileDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> findById(@PathVariable("id") Long id) {
        UserProfileDto profile = userProfileService.findById(id);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(userProfileService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        userProfileService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<UserProfileDto>> pageQuery(UserProfileDto userProfileDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserProfileDto> profilePage = userProfileService.findByCondition(userProfileDto, pageable);
        return ResponseEntity.ok(profilePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated UserProfileDto userProfileDto, @PathVariable("id") Long id) {
        userProfileService.update(userProfileDto, id);
        return ResponseEntity.ok().build();
    }
}