package com.dauphine.blogger.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dauphine.blogger.exception.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getAllLikeTitle(String title) {
        return repository.findAllLikeTitle(title);
    }

    @Override
    public List<Post> getAllLikeContent(String content) {
        return repository.findAllLikeContent(content);
    }

    @Override
    public Post getById(UUID id) throws PostNotFoundByIdException{
        return repository.findById(id).orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        // get the category by id in the database
        Category category = new Category();
        category.setId(categoryId);
        // create a new post
        Post newPost = new Post(title, content, category);
        // save the post in the database
        return repository.save(newPost);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            return repository.save(post);
        }
        return null; // Post not found
    }

    @Override
    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false; // Post not found
    }

    @Override
    public List<Post> getByCategory(UUID categoryId) {
        return repository.findByCategory(categoryId);
    }

}