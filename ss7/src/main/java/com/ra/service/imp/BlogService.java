package com.ra.service.imp;

import com.ra.model.dto.blog.BlogRequestDTO;
import com.ra.model.dto.blog.BlogResponseDTO;
import com.ra.model.entity.Blog;
import com.ra.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogService implements com.ra.service.BlogService {
    @Autowired
    private BlogRepository blogRepository;

    // findAll nay la hien thi danh sach blog co ca phan trang
    @Override
    public Page<BlogResponseDTO> findAll(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        return blogs.map(blog ->
                BlogResponseDTO.builder()
                        .id(blog.getId())
                        .title(blog.getTitle())
                        .content(blog.getContent())
                        .image(blog.getImage())
                        .build());
    }

    @Override
    public Page<BlogResponseDTO> searchByTitle(String title, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByTitleContainingIgnoreCase(title, pageable);
        return blogs.map(blog -> BlogResponseDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .image(blog.getImage()).build());
    }

    @Override
    public BlogResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public BlogResponseDTO addBlog(BlogRequestDTO blogRequestDTO) {
        return null;
    }

    @Override
    public BlogResponseDTO updateBlog(Long id, BlogRequestDTO blogRequestDTO) {
        return null;
    }

    @Override
    public BlogResponseDTO deleteBlog(Long id) {
        return null;
    }
}
