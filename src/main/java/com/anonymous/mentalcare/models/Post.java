package com.anonymous.mentalcare.models;

import com.anonymous.mentalcare.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post")
    private List<ReadingPost> readingPostList;

    public void addComment(Comment comment) {
        comment.setPost(this);
        this.getCommentList().add(comment);
    }

    public Post(PostDto.PostWrittenReqeustDto postWrittenReqeustDto){
        //난중에 userDetails user 넣어줘야함
        User user = User.builder()
                .userId("jybin96")
                .password("asdasdasd")
                .postList(null)
                .commentList(null)
                .readingPostList(null)
                .build();
        //
        this.user = user;
        this.title = postWrittenReqeustDto.getTitle();
        this.content = postWrittenReqeustDto.getContent();
        this.commentList = null;
        this.readingPostList = null;
    }

    public void updatePost(PostDto.PostUpdateRequestDto postUpdateRequestDto){
        this.content = postUpdateRequestDto.getContent();
    }
}
