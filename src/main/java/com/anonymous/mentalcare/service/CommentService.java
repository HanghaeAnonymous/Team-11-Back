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

import javax.validation.Valid;
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

    public void commentDelete(Long commentId, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(!comment.isPresent()){
            throw new NullPointerException("존재하지 않는 댓글입니다.");
        }else if(!comment.get().getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("댓글의 작성자만 삭제가 가능합니다.");
        }

        commentRepository.deleteById(commentId);
    }
}
