package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.CommentDetailResponseDto;
import com.anonymous.mentalcare.dto.CommentRequestDto;
import com.anonymous.mentalcare.dto.CommentResponseDto;
import com.anonymous.mentalcare.dto.FeedCommentResponseDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<CommentResponseDto> comment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Optional<Post> post = postRepository.findByPostId(postId);

        if(!post.isPresent()){
            throw new NullPointerException("유효하지 않거나 이미 삭제된 게시글입니다.");
        }

        Comment comment = commentRepository.save(new Comment(commentRequestDto, userDetails.getUser(), post.get()));
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    //댓글로 게시글 조회
    public CommentDetailResponseDto readCommentsDetail(Long commentId, User user) {
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);

        if (!comment.isPresent()) {
            throw new NullPointerException("댓글작성자가 아닙니다.");
        }
        List<FeedCommentResponseDto> commentList = new ArrayList<>();
        for (Comment comments : comment.get().getPost().getCommentList()) {
            commentList.add(new FeedCommentResponseDto(comments));
            System.out.print(comments.getComment() + ", ");
        }

        lookUpReadCommentDetail(comment, user, commentList);
        return new CommentDetailResponseDto(comment.get(), commentList);
    }

    private void lookUpReadCommentDetail(Optional<Comment> comment, User user, List<FeedCommentResponseDto> commentList) {
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
