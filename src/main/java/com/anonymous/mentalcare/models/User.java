package com.anonymous.mentalcare.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    private List<ReadingPost> readingPostList;

    public void addPost(Post post) {
        post.setUser(this);
        this.getPostList().add(post);
    }

    public void addComment(Comment comment){
        comment.setUser(this);
        this.getCommentList().add(comment);
    }
}