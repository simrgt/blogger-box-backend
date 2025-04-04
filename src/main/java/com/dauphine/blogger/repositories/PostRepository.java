package com.dauphine.blogger.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dauphine.blogger.models.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
        SELECT post
        FROM Post post
        WHERE UPPER(post.title) LIKE CONCAT('%', UPPER(:title), '%')
    """)
    List<Post> findAllLikeTitle(@Param("title") String title);

    @Query("""
        SELECT post
        FROM Post post
        WHERE UPPER(post.content) LIKE CONCAT('%', UPPER(:content), '%')
    """)
    List<Post> findAllLikeContent(@Param("content") String content);

    @Query("""
        SELECT post
        FROM Post post
        WHERE post.category.id = :categoryId
    """)
    List<Post> findByCategory(@Param("categoryId") UUID categoryId);

}
