package com.skilldistillery.skillswap.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Comment;
import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.repositories.CommentRepository;
import com.skilldistillery.skillswap.repositories.ProjectRepository;
import com.skilldistillery.skillswap.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ProjectRepository projectRepo;

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
	public Comment create(String username, Comment comment, int projectId) {
		User user = userRepo.findByUsername(username);
		Project project = null;
		System.out.println("-------------------------------------------------------------------" + user);
		Optional<Project> projectOpt = projectRepo.findById(projectId);
		if (projectOpt.isPresent()) {
			project = projectOpt.get();
		}
		if(user != null) {
			comment.setUser(user);
			comment.setProject(project);
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}
	
	@Override
	public Comment update(String username, int commentId, Comment comment, int projectId) {
		Comment existing = show(username, commentId);
		existing.setComment(comment.getComment());
		return commentRepo.save(existing);
	}

	@Override
	public boolean destroy(String username, int commentId, int projectId) {
		commentRepo.deleteById(commentId);
		return !commentRepo.existsById(commentId);
	}
	

}
