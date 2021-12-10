package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.comment.CommentDetailResponseDto;
import com.anonymous.mentalcare.dto.feed.*;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.util.LookUpService;
import com.anonymous.mentalcare.util.ValidateChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public FeedResponseDto getFeed(UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        List<Post> postList = postRepository.findAllByUser(user);
        List<Comment> commentList = commentRepository.findAllByUser(user);

        // println 해주는 기능.
        LookUpService.getFeed(postList, commentList);

        return new FeedResponseDto(postList, commentList);
    }

    // 내가 작성한 게시글 조회
    public MyPostResponseDto getMyPost(Long postId, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Optional<Post> post = postRepository.findByPostIdAndUser(postId, user);
        if (!post.isPresent()) {
            throw new NullPointerException("당신의 글이 아니거나 없는 글입니다!");
        }
        // println 찍어주는 기능.
        LookUpService.lookUpMyPostDetail(post.get());

        return new MyPostResponseDto(post.get());
    }

    // 내가 작성한 댓글이 있는 게시글 조회
    public CommentDetailResponseDto readCommentsDetail(Long commentId, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Optional<Comment> comment = commentRepository.findByCommentId(commentId);
        if (!comment.isPresent()) {
            throw new NullPointerException("유효하지 않은 댓글입니다.");
        }
        // println 찍어주는 기능.
        LookUpService.lookUpReadCommentDetail(comment.get(), user);

        // comment 의 주인과 요청한 사용자가 같은 사람 = 내가 쓴 댓글일 때만 열람 가능.
        if (!comment.get().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("댓글 작성자가 아닙니다.");
        }

        return new CommentDetailResponseDto(comment.get());
    }
}
