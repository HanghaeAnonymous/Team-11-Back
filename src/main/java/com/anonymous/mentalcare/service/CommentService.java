package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.CommentRequestDto;
import com.anonymous.mentalcare.dto.FeedCommentResponseDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        }

        commentRepository.save(new Comment(commentRequestDto, userDetails.getUser(), post.get()));
    }
        //댓글 조회 
    @Transactional
    public FeedCommentResponseDto findByCommentId(Long commentId){
       Comment comment = (Comment) commentRepository.findAllByCommentIdOrderByCreatedAtDesc(commentId);

        return new FeedCommentResponseDto(comment);

    }
}
