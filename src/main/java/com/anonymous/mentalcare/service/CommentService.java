package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.comment.CommentDetailResponseDto;
import com.anonymous.mentalcare.dto.comment.CommentRequestDto;
import com.anonymous.mentalcare.dto.comment.CommentResponseDto;
import com.anonymous.mentalcare.dto.feed.FeedCommentResponseDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto comment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Optional<Post> post = postRepository.findByPostId(postId);

        if (!post.isPresent()) {
            throw new NullPointerException("유효하지 않거나 이미 삭제된 게시글입니다.");
        }

        Comment comment = commentRepository.save(new Comment(commentRequestDto, userDetails.getUser(), post.get()));

        return new CommentResponseDto(comment);
    }

    //댓글로 게시글 조회
    public CommentDetailResponseDto readCommentsDetail(Long commentId, User user) {
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);

        if (!comment.isPresent()) {
            throw new NullPointerException("유효하지 않은 댓글입니다.");
        } else if (!comment.get().getUser().equals(user)) {
            throw new IllegalArgumentException("댓글 작성자가 아닙니다.");
        }

        List<FeedCommentResponseDto> commentList = new ArrayList<>();
        for (Comment comments : comment.get().getPost().getCommentList()) {
            commentList.add(new FeedCommentResponseDto(comments));
            System.out.print(comments.getComment() + ", ");
        }

        lookUpReadCommentDetail(comment, user);
        return new CommentDetailResponseDto(comment.get(), commentList);
    }

    private void lookUpReadCommentDetail(Optional<Comment> comment, User user) {
        System.out.println("----comment 조회----");
        System.out.println("request user : " + user.getUserId());
        System.out.println("comment owner : " + comment.get().getUser().getUserId());
        System.out.println("comment content : " + comment.get().getComment());

        System.out.print("comment List : [");
        for (Comment comments : comment.get().getPost().getCommentList()) {
            System.out.print(comments.getComment() + ", ");
        }
        System.out.println("]");
    }
}
