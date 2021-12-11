package com.anonymous.mentalcare.repository;

import com.anonymous.mentalcare.models.ReadingPost;
import com.anonymous.mentalcare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingPostRepository extends JpaRepository<ReadingPost, Long> {
    List<ReadingPost> findAllByUser(User user);
}
