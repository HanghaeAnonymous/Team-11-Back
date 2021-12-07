package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.CommentRequestDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void comment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Optional<Post> post = postRepository.findByPostId(postId);

        if(!post.isPresent()){
            throw new NullPointerException("유효하지 않거나 이미 삭제된 게시글입니다.");
        }else if(!Objects.equals(post.get().getUser().getId(), userDetails.getUserId())){
            throw new IllegalArgumentException("당신의 댓글이 아닙니다!");
        }

        commentRepository.save(new Comment(commentRequestDto, userDetails.getUser(), post.get()));
    }
}
