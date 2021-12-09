package com.anonymous.mentalcare.models;

import com.anonymous.mentalcare.dto.PostDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<ReadingPost> readingPostList = new ArrayList<>();;

    public void addComment(Comment comment) {
        comment.setPost(this);
        this.getCommentList().add(comment);
    }

    public Post(PostDto.PostWrittenRequestDto postWrittenRequestDto, User user){
        this.user = user;
        this.title = postWrittenRequestDto.getTitle();
        this.content = postWrittenRequestDto.getContent();
    }

    public void updatePost(PostDto.PostUpdateRequestDto postUpdateRequestDto){
        this.content = postUpdateRequestDto.getContent();
    }
}
