package com.skilldistillery.skillswap.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Comment;
import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.repositories.CommentRepository;
import com.skilldistillery.skillswap.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository commentRepo;

	@Override
	public Set<Comment> index(String username) {
		return commentRepo.findByUser_Username(username);
	}

	@Override
	public Comment show(String username, int commentId) {
		Comment comment = null;
		comment = commentRepo.findByIdAndUserId(commentId, userRepo.findByUsername(username).getId());
		return comment;
	}

	@Override
	public Comment create(String username, Comment comment, Project project) {
		User user = userRepo.findByUsername(username);
		Project project = 
		if(user != null) {
			comment.setUser(user);
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}
	
	@Override
	public Comment update(String username, int commentId, Comment comment) {
		Comment existing = show(username, commentId);
		existing.setComment(comment.getComment());
		return commentRepo.save(existing);
	}

	@Override
	public boolean destroy(String username, int commentId) {
		commentRepo.deleteById(commentId);
		return !commentRepo.existsById(commentId);
	}
	

}
