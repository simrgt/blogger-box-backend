package com.dauphine.blogger.services;

import java.util.List;
import java.util.UUID;

import com.dauphine.blogger.models.Post;

public interface PostService {

    List<Post> getByCategory(UUID categoryId);

    List<Post> getAll();

    Post getById(UUID id);

    Post create(String title, String content, UUID categoryId);

    Post update(UUID id, String title, String content);

    boolean delete(UUID id);

}
