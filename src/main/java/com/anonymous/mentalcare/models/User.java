package com.anonymous.mentalcare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User  {
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

    @Builder
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User update(String userId) {
        this.userId = userId;
        return this;
    }


}
