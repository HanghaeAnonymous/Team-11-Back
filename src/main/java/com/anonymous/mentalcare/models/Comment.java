package com.anonymous.mentalcare.models;

import com.anonymous.mentalcare.dto.comment.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto commentRequestDto, User user, Post post) {
        this.post = post;
        this.user = user;
        this.comment = commentRequestDto.getComment();
    }
}
