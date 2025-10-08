package org.example.blogcrud.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.blogcrud.dto.comment.CommentRequest;
import org.example.blogcrud.dto.comment.CommentResponse;
import org.example.blogcrud.dto.post.PostRequest;
import org.example.blogcrud.dto.post.PostResponse;
import org.example.blogcrud.dto.post.PostUpdateRequest;
import org.example.blogcrud.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse response = postService.createPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> responses = postService.getAllPosts();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
        @PathVariable Long postId,
        @RequestBody PostUpdateRequest request) {
        PostResponse postResponse = postService.updatePost(postId, request);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
        @PathVariable Long postId,
        @RequestBody CommentRequest request) {
        CommentResponse response = postService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
