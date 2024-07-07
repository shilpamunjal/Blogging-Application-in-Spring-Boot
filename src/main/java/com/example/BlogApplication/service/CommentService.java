package com.example.BlogApplication.service;

import java.util.List;

import com.example.BlogApplication.entity.Comment;
import com.example.BlogApplication.entity.Post;

public interface CommentService {
	Comment createComment(Long postid,String postedBy,String content);
	List<Comment> getCommentByPostId(Long postId);
}
