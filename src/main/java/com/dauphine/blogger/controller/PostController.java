package com.dauphine.blogger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getPosts() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody Post updatedPost) {
        return service.update(id, updatedPost.getTitle(), updatedPost.getContent());
    }
    
    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable UUID id) {
        return service.delete(id);
    }

    @GetMapping("/latest")
    public List<Post> getLatestPosts() {
        List<Post> posts = service.getAll();
        posts.sort((post1, post2) -> post2.getCreatedAt().compareTo(post1.getCreatedAt()));
        return posts;
    }

    @GetMapping("/category/{category}")
    public List<Post> getPostsByCategory(@PathVariable String category) {
        return service.getByCategory(UUID.fromString(category));
    }
}
