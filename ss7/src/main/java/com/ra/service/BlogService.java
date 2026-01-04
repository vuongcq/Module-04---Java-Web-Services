package com.ra.service;

import com.ra.model.dto.blog.BlogRequestDTO;
import com.ra.model.dto.blog.BlogResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<BlogResponseDTO> findAll(Pageable pageable);
    Page<BlogResponseDTO> searchByTitle(String title, Pageable pageable);
    BlogResponseDTO findById(Long id);
    BlogResponseDTO addBlog(BlogRequestDTO blogRequestDTO);
    BlogResponseDTO updateBlog(Long id, BlogRequestDTO blogRequestDTO);
    BlogResponseDTO deleteBlog(Long id);
}
