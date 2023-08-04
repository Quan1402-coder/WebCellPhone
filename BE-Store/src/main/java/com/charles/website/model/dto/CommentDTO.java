package com.charles.website.model.dto;

import com.charles.website.entity.Comment;
import com.charles.website.model.response.UserResponse;

public class CommentDTO {
    private String body;
    private UserResponse user;

    public CommentDTO(Comment comment) {
        this.body = comment.getBody();
        this.user = new UserResponse(comment.getUser());
    }
}
