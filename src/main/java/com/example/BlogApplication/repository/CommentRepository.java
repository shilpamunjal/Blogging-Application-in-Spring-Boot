package com.example.BlogApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BlogApplication.entity.Comment;
import com.example.BlogApplication.entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	List<Comment>findByPostId(Long postId);
	
}
