package com.anonymous.mentalcare.repository;

import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.ReadingPost;
import com.anonymous.mentalcare.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
    Optional<Post> findByPostIdAndUser(Long postId, User user);
    List<Post> findAllByUser(User user);

    Page<Post> findAllByUserNot(User user, Pageable pageable);
    Page<Post> findAllByUserNotAndPostIdNotIn(User user, List<Long> readingPostIdList, Pageable pageable);

    long countByUserNot(User user);
    long countByUserNotAndPostIdNotIn(User user, List<Long> readingPostIdList);
}
