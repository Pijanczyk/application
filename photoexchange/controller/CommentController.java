package com.photoexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.photoexchange.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping("/create")
	public String create(Model model, String text, String userId, String photoId) {
		model.addAttribute("name", "Added Comment : " + text);
		commentService.create(text, userId, photoId);
		return "comment";
		  
	  }
}
