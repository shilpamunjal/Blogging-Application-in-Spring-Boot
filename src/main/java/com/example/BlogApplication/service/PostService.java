package com.example.BlogApplication.service;

import java.util.List;

import com.example.BlogApplication.entity.Post;

public interface PostService {
	Post savePost(Post post);
	List<Post> getAllPosts();
	Post getPostById(Long postid);
	void likePost(Long postId);
	List<Post> searchByName(String name);
}
