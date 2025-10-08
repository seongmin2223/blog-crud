package org.example.blogcrud.dto.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.example.blogcrud.dto.comment.CommentResponse;
import org.example.blogcrud.entity.Post;

@Getter
@Builder
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final List<CommentResponse> comments;

    // 엔티티 -> DTO로 변환하는 정적 메소드
    public static PostResponse from(Post post) {
        return PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .author(post.getAuthor())
            .comments(post.getComments().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList()))
            .build();
    }
}
