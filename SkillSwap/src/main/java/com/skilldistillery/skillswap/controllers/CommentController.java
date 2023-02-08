package com.skilldistillery.skillswap.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillswap.entities.Comment;
import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.repositories.CommentRepository;
import com.skilldistillery.skillswap.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("projects/{pid}/comments")
	public Set<Comment> index(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") int projectId) {
		return commentService.index(principal.getName());
		
	}	
	
	@PostMapping("projects/{id}/comments")
	public Comment create(Principal principal, HttpServletRequest req, HttpServletResponse res, @RequestBody Comment comment, @PathVariable ("id") int projectId) {
		
		try {
			commentService.create(principal.getName(), comment, projectId);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}
	
	@PutMapping("projects/{pid}/comments/{cid}")
	public Comment update(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") int projectId, @PathVariable("cid") int commentId, @RequestBody Comment comment) {
		
		try {
			comment = commentService.update(principal.getName(), commentId, comment, projectId);
			if(comment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		
		return comment;
	}
	
	@DeleteMapping("projects/{pid}/comments/{cid}")
	public boolean destroy(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") int projectId, @PathVariable("cid") int commentId) {
		commentRepo.deleteById(commentId);
		return !commentRepo.existsById(commentId);
	}

}
