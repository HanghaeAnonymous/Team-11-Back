package com.anonymous.mentalcare.repository;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUser(User user);
    
        
    Optional<Comment> findByCommentId(Long commentId);.
}
