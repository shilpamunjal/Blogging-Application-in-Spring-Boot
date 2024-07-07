package com.example.BlogApplication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired 
	private PostRepository postrepository;
	//Hibernate: insert into blogs (content,date,img,likecount,name,posted_by,tags,viewcount) values (?,?,?,?,?,?,?,?)
	public Post savePost(Post post) {
		post.setLikecount(0);
		post.setViewcount(0);
		post.setDate(new Date());
		
		return (Post) postrepository.save(post);
	}
	
	public List<Post> getAllPosts(){
		return postrepository.findAll();
		
	}
	
	public Post getPostById(Long postid) {
		Optional<Post> post1=postrepository.findById(postid);
		if(post1.isPresent()) {
			Post post=post1.get();
			post.setViewcount(post.getViewcount()+1);
			return postrepository.save(post);
		}
		else {
			throw new EntityNotFoundException();
		}
		
	}
	
	public void likePost(Long postId) {
		Optional<Post> post1=postrepository.findById(postId);
		if(post1.isPresent()) {
			Post post=post1.get();
			post.setLikecount(post.getLikecount()+1);
			 postrepository.save(post);
		}
		
		else {
			throw new EntityNotFoundException();
		}
	}
	//Hibernate: select p1_0.id,p1_0.content,p1_0.date,p1_0.img,p1_0.likecount,p1_0.name,p1_0.posted_by,p1_0.tags,p1_0.viewcount from blogs p1_0 where p1_0.name=?
	public List<Post> searchByName(String name){
		return postrepository.findAllByName(name);
	}
	
	

}
