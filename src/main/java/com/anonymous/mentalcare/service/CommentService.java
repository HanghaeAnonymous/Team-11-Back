package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.comment.CommentRequestDto;
import com.anonymous.mentalcare.dto.comment.CommentResponseDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.util.ValidateChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto comment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        if(commentRequestDto.getComment().trim().equals("")){
            throw new IllegalArgumentException("내용을 작성해주세요.");
        }

        Optional<Post> post = postRepository.findByPostId(postId);
        if (!post.isPresent()) {
            throw new NullPointerException("유효하지 않거나 이미 삭제된 게시글입니다.");
        }

        Comment comment = commentRepository.save(new Comment(commentRequestDto, user, post.get()));

        return new CommentResponseDto(comment);
    }
}
