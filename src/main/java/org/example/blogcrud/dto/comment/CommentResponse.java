package org.example.blogcrud.dto.comment;

import lombok.Builder;
import lombok.Getter;
import org.example.blogcrud.entity.Comment;

@Getter
@Builder
public class CommentResponse {

    private final Long id;
    private final String content;
    private final String author;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .author(comment.getAuthor())
            .build();
    }

}
