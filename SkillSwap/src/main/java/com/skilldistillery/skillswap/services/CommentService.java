package com.skilldistillery.skillswap.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.skillswap.entities.Comment;
import com.skilldistillery.skillswap.entities.Project;

public interface CommentService {
	
	public Set<Comment> index(String username);
	
	public Comment show(String username, int commentId);
	
	public Comment create(String username, Comment comment, int projectId);
	
	public Comment update(String username, int commentId, Comment comment, int projectId);
	
	public boolean destroy(String username, int commentId, int projectId);

	//------Comments About Users---------
	public Set<Comment> indexOfCommentsAboutUser(String username);

	boolean destroyCommentAboutUser(String username, int commentId, int userId);

	public Comment updateCommentAboutUser(String username, int commentId, Comment comment, int userId);

	public Comment createCommentAboutUser(String username, Comment comment, int userId);

	List<Comment> getAllCommentsForProject(int projectId);

}
