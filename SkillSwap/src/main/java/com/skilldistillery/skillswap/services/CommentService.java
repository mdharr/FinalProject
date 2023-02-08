package com.skilldistillery.skillswap.services;

import java.util.Set;

import com.skilldistillery.skillswap.entities.Comment;
import com.skilldistillery.skillswap.entities.Project;

public interface CommentService {
	
	public Set<Comment> index(String username);
	
	public Comment show(String username, int commentId);
	
	public Comment create(String username, Comment comment, int projectId);
	
	public Comment update(String username, int commentId, Comment comment);
	
	public boolean destroy(String username, int commentId);

}
