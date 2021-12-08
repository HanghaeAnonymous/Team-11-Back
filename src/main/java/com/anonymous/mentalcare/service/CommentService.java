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

    //댓글로 게시글 조회
    public CommentDetailResponseDto readCommentsDetail(Long commentId, User user) {
        Optional<Post> post = postRepository.findByUser(user);
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);

        if (!Objects.equals(comment.get().getUser().getId(), user.getUserId())) {
            throw new IllegalArgumentException("댓글작성자가 아닙니다.");
        }
        List<FeedCommentResponseDto> commentList = new ArrayList<>();
        for (Comment comments : post.get().getCommentList()) {
            commentList.add(new FeedCommentResponseDto(comments));
        }
            return new CommentDetailResponseDto(post.get(), commentList);

    }
}
