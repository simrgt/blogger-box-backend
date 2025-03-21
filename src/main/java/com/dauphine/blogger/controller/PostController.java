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

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final List<Post> temporaryPosts;

    public PostController() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "my first post", "my first post content", null, null));
        temporaryPosts.add(new Post(UUID
            .randomUUID(), "my second post", "my second post content", null, null));
        temporaryPosts.add(new Post(UUID.randomUUID(),
            "my third post", "my third post content", null, null));
    }

    @GetMapping
    public List<Post> getPosts() {
        return temporaryPosts;
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody Post updatedPost) {
        for (int i = 0; i < temporaryPosts.size(); i++) {
            Post post = temporaryPosts.get(i);
            if (post.getId().equals(id)) {
                // Keep the original ID but update other fields
                Post updated = new Post(
                    id,
                    updatedPost.getTitle(),
                    updatedPost.getContent(),
                    updatedPost.getCreatedAt(),
                    updatedPost.getCategory()
                );
                temporaryPosts.set(i, updated);
                return updated;
            }
        }
        return null; // Post not found
    }
    
    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable UUID id) {
        for (int i = 0; i < temporaryPosts.size(); i++) {
            if (temporaryPosts.get(i).getId().equals(id)) {
                temporaryPosts.remove(i);
                return true;
            }
        }
        return false; // Post not found
    }

    @GetMapping("/latest")
    public List<Post> getLatestPosts() {
        List<Post> sortedPosts = new ArrayList<>(temporaryPosts);
        // Sort by creation date in descending order (newest first)
        sortedPosts.sort((post1, post2) -> {
            // Handle null createdAt values (place them at the end)
            if (post1.getCreatedAt() == null) return 1;
            if (post2.getCreatedAt() == null) return -1;
            // Sort in descending order (most recent first)
            return post2.getCreatedAt().compareTo(post1.getCreatedAt());
        });
        return sortedPosts;
    }

    @GetMapping("/category/{category}")
    public List<Post> getPostsByCategory(@PathVariable String category) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : temporaryPosts) {
            if (post.getCategory() != null && post.getCategory().equals(category)) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }
}
