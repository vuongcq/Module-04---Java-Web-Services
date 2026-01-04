package com.ra.controller;

import com.ra.model.dto.ResponseWrapper;
import com.ra.model.dto.ResponseWrapper;
import com.ra.model.dto.blog.BlogResponseDTO;
import com.ra.service.imp.BlogService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 2, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        Page<BlogResponseDTO> blogResponseDTO = blogService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .message("Get blogs successfully")
                .dataResponse(blogResponseDTO)
                .build());
    }

    // tim kiem
    @GetMapping("/search")
    public ResponseEntity<?> searchByTitle(@RequestParam(name = "title") String title, @PageableDefault(page = 0, size = 2,
            sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<BlogResponseDTO> blogResponseDTO = blogService.searchByTitle(title, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Search ok")
                        .dataResponse(blogResponseDTO)
                        .build());
    }
}
