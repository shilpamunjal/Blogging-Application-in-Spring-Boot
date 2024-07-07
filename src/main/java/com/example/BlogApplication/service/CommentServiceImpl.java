package com.example.BlogApplication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogApplication.entity.Comment;
import com.example.BlogApplication.entity.Post;
import com.example.BlogApplication.repository.CommentRepository;
import com.example.BlogApplication.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired 
	private CommentRepository commentrepository;
	@Autowired 
	private PostRepository postrepository;
	//Hibernate: select p1_0.id,p1_0.content,p1_0.date,p1_0.img,p1_0.likecount,p1_0.name,p1_0.posted_by,p1_0.tags,p1_0.viewcount from blogs p1_0 where p1_0.id=?
			//Hibernate: insert into comment (content,created_at,post_id,posted_by) values (?,?,?,?)
	public Comment createComment(Long postid,String postedBy,String content) {
		Optional<Post> optionalPost=postrepository.findById(postid);
		if(optionalPost.isPresent()) {
			Comment c=new Comment();
			c.setPost(optionalPost.get());
			c.setContent(content);
			c.setPostedBy(postedBy);
			c.setCreatedAt(new Date());
			return commentrepository.save(c);
			}
		return null;
		}
	
	
	public List<Comment> getCommentByPostId(Long postId){
		System.out.println("comments service");
		List<Comment> post1=commentrepository.findByPostId(postId);
		System.out.println(post1);
		for(int i=0;i<post1.size();i++) {
			System.out.println(post1.get(i).getPostedBy());	
			System.out.println(post1.get(i).getCreatedAt());
			System.out.println(post1.get(i).getContent());
		}
		return commentrepository.findByPostId(postId);
	}
	}
		