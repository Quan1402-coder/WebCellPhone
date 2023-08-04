package com.charles.website.model.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String body;
    private Long productId;
}
