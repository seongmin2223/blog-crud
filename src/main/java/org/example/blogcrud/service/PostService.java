package org.example.blogcrud.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.blogcrud.dto.comment.CommentRequest;
import org.example.blogcrud.dto.comment.CommentResponse;
import org.example.blogcrud.dto.post.PostRequest;
import org.example.blogcrud.dto.post.PostResponse;
import org.example.blogcrud.dto.post.PostUpdateRequest;
import org.example.blogcrud.entity.Comment;
import org.example.blogcrud.entity.Post;
import org.example.blogcrud.repository.CommentRepository;
import org.example.blogcrud.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostResponse createPost(PostRequest postRequest) {
        Post post = Post.builder()
            .title(postRequest.getTitle())
            .content(postRequest.getContent())
            .author(postRequest.getAuthor())
            .build();
        Post savedPost = postRepository.save(post);
        return null;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
            .map(PostResponse::from)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다 : " + postId));
        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다: " + postId));

        post.update(request.getTitle(), request.getContent());
        return PostResponse.from(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다: " + postId));

        postRepository.delete(post);
    }

    @Transactional
    public CommentResponse createComment(Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다: " + postId));

        Comment comment = Comment.builder()
            .content(request.getContent())
            .author(request.getAuthor())
            .post(post)
            .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.from(savedComment);
    }
}
