package com.anonymous.mentalcare.util;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;

import java.util.List;

public class LookUpService {
    public static void lookUpSignUpProc(User user){
        System.out.println("회원가입 요청---------");
        System.out.println("username : " + user.getUserId());
        System.out.println("password : " + user.getPassword());
        System.out.println("회원가입 처리 완료-----");
    }

    public static void lookUpReadCommentDetail(Comment comment, User user) {
        System.out.println("----내가 작성한 댓글의 위치 조회----");
        System.out.println("request user Id : " + user.getId());
        System.out.println("comment owner Id : " + comment.getUser().getId());
        System.out.println("isValid : " + (comment.getUser().getId().equals(user.getId())));
        System.out.println("comment content : " + comment.getComment());
        System.out.print("comment List : [");
        for (Comment comments : comment.getPost().getCommentList()) {
            System.out.print(comments.getComment() + ", ");
        }
        System.out.println("]");
        System.out.println("-------comment 조회 종료--------");
    }

    public static void lookUpMyPostDetail(Post post) {
        System.out.println("----내가 작성한 글 조회----");
        System.out.println("postId : " + post.getPostId());
        System.out.println("userId : " + post.getUser().getId());
        System.out.println("content : " + post.getContent());
        System.out.print("comment List : [");
        for (Comment comments : post.getCommentList()) {
            System.out.print(comments.getComment() + ", ");
        }
        System.out.println("]");
        System.out.println("--------조회 종료--------");
    }

    public static void getFeed(List<Post> postList, List<Comment> commentList) {
        System.out.println("postList Size : " + postList.size());
        System.out.println("commentList Size : " + commentList.size());
    }
}
