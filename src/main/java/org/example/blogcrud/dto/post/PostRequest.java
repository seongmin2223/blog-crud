package org.example.blogcrud.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequest {
    private final String title;
    private final String content;
    private final String author;
}
