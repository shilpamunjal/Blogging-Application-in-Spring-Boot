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
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.service.PostService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/post")

@CrossOrigin(origins="*")
public class PostController {
	
	@Autowired
 private PostService service;
	
	@PostMapping
	public ResponseEntity createPost(@RequestBody Post post) {
		try {
		Post createdPost=service.savePost(post);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity <List<Post>> getAllPosts(){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllPosts());
		
	}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	
	//select p1_0.id,p1_0.content,p1_0.date,p1_0.img,p1_0.likecount,p1_0.name,p1_0.posted_by,p1_0.tags,p1_0.viewcount from blogs p1_0 where p1_0.id=?
			//Hibernate: update blogs set content=?,date=?,img=?,likecount=?,name=?,posted_by=?,tags=?,viewcount=? where id=?
	@GetMapping("/{postId}")
	public ResponseEntity getPostById(@PathVariable Long postId) {
		try {
			
			Post post=service.getPostById(postId);
			return ResponseEntity.ok(post);
			
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
	}
	
	@PutMapping("/{postId}/like")
	public ResponseEntity likePost(@PathVariable Long postId) {
		try {
			service.likePost(postId);
			return ResponseEntity.ok(null);
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
	}
	@GetMapping("/search/{name}")
	public ResponseEntity searchByName(@PathVariable String name) {
		System.out.println("in search");
		return ResponseEntity.status(HttpStatus.OK).body(service.searchByName(name));
	}
	
	

}
