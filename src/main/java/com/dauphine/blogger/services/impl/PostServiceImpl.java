package com.dauphine.blogger.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;

@Service
public class PostServiceImpl implements PostService {
    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "my first post", "my first post content", null, null));
        temporaryPosts.add(new Post(UUID
            .randomUUID(), "my second post", "my second post content", null, null));
        temporaryPosts.add(new Post(UUID.randomUUID(),
            "my third post", "my third post content", null, null));
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts;
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post newPost = new Post(UUID.randomUUID(), title, content, null, null);
        temporaryPosts.add(newPost);
        return newPost;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        for (int i = 0; i < temporaryPosts.size(); i++) {
            Post post = temporaryPosts.get(i);
            if (post.getId().equals(id)) {
                Post updated = new Post(
                    id,
                    title,
                    content,
                    post.getCreatedAt(),
                    post.getCategory()
                );
                temporaryPosts.set(i, updated);
                return updated;
            }
        }
        return null; // Post not found
    }

    @Override
    public boolean delete(UUID id) {
        for (int i = 0; i < temporaryPosts.size(); i++) {
            if (temporaryPosts.get(i).getId().equals(id)) {
                temporaryPosts.remove(i);
                return true;
            }
        }
        return false; // Post not found
    }

    @Override
    public List<Post> getByCategory(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(post -> post.getCategory() != null && post.getCategory().getId().equals(categoryId))
                .toList();
    }

}