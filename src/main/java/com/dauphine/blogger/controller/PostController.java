package com.dauphine.blogger.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dauphine.blogger.exception.PostNotFoundByIdException;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
        summary = "Get all posts",
        description = "Returns all posts or filtered by title"
    )
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false) String title, 
                               @RequestParam(required = false) String content) {
        List<Post> posts = title == null || title.isBlank()
            ? content == null || content.isBlank() 
                ? service.getAll() 
                : service.getAllLikeContent(content)
            : service.getAllLikeTitle(title);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update post",
        description = "Updates a post by id"
    )
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody Post updatedPost) throws PostNotFoundByIdException {
        Post post = service.update(id, updatedPost.getTitle(), updatedPost.getContent());
        return ResponseEntity.ok(post);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete post",
        description = "Deletes a post by id"
    )
    public ResponseEntity<Boolean> deletePost(@PathVariable UUID id) {
        boolean deleted = service.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/latest")
    @Operation(
        summary = "Get latest posts",
        description = "Returns the latest posts sorted by creation date"
    )
    public ResponseEntity<List<Post>> getLatestPosts() {
        List<Post> posts = service.getAll();
        posts.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/category/{category}")
    @Operation(
        summary = "Get posts by category",
        description = "Returns posts filtered by category"
    )
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable String category) {
        List<Post> posts = service.getByCategory(UUID.fromString(category));
        return ResponseEntity.ok(posts);
    }
}
