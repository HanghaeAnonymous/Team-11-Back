package com.anonymous.mentalcare.repository;

import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
    List<Post> findAllByUser(User user);
}
