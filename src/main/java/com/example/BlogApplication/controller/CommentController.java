package com.example.BlogApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.service.CommentService;
import com.example.BlogApplication.service.PostService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/")

@CrossOrigin
public class CommentController {
	
	@Autowired
 private CommentService service;
	
	@PostMapping("comments/create")
	public ResponseEntity createPost(@RequestParam Long postid,@RequestParam String postedBy,@RequestBody String content) {
		try {
			return ResponseEntity.ok(service.createComment(postid, postedBy, content));
		}
		
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}

	}
	
	@GetMapping("comments/{postid}")
	public ResponseEntity getCommentsByPostId(@PathVariable Long postid) {
		try {
			
			return ResponseEntity.ok(service.getCommentByPostId(postid));
		}
		
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	
	
	

}
