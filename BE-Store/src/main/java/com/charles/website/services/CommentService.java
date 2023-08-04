package com.charles.website.services;

import com.charles.website.entity.Comment;
import com.charles.website.entity.Order;
import com.charles.website.model.request.CommentRequest;
import com.charles.website.model.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void add(CommentRequest request);

    void update(Long id, CommentRequest request);

    void delete(Long id);

    List<Comment> getList();

    Comment getDetail();
}
