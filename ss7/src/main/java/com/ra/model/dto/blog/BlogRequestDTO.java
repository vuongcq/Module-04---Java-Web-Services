package com.ra.model.dto.blog;

import com.ra.model.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogRequestDTO {

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private MultipartFile   image;

    @Column(name = "content")
    private String content;
}
