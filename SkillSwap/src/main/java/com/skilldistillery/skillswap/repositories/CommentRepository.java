package com.skilldistillery.skillswap.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillswap.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	Set<Comment> findByUser_Username(String username);
	
	Comment findByIdAndUserId(int commentId, int userId);
	
}
