package com.photoexchange.controller;

import com.photoexchange.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.photoexchange.model.Photo;
import com.photoexchange.service.CommentService;
import com.photoexchange.service.PhotoServiceImpl;
import com.photoexchange.service.UserServiceImpl;


@Controller
@RequestMapping("/photo")
public class PhotoController {
	
	@Autowired
	private PhotoServiceImpl photoService;
	
	@Autowired
	private CommentService commentService;	
	
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/add")
	public String addPhotoPage(Model model) {
		model.addAttribute("photoForm", new Photo());
		model.addAttribute("allUsers", userService.getAll());
        return "addPhoto";
	}
	
	@PostMapping("/add")
	public String create(Photo photo, Model model) {
		photoService.create(photo);
		model.addAttribute("photoForm", new Photo());
		model.addAttribute("allUsers", userService.getAll());
		return "addPhoto";
	}
	
	@GetMapping("/edit")
	public String editPhotoPage(@RequestParam long id, Model model) {
		Photo photo = photoService.getById(id);
		model.addAttribute("photoForm", photo);
		model.addAttribute("commentForm", new Comment(photo));
		model.addAttribute("allUsers", userService.getAll());
		model.addAttribute("isCreator", userService.getCurrentUser().equals(photo.getCreator()));
        return "editPhoto";
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam Photo photo, Model model) {		
		photoService.update(photo);
		
		model.addAttribute("allUsers", userService.getAll());
    	model.addAttribute("photos", photoService.getPhotoByUser());
		return "welcome";
	}
	
	@PostMapping("/editWithComment")
	public String edit(Comment comment, Model model) {	
		commentService.create(comment);
		
		model.addAttribute("allUsers", userService.getAll());
    	model.addAttribute("photos", photoService.getPhotoByUser());
		return "welcome";
	}
	
	@GetMapping("/getFotoByUser")
    public String getFotoByUser(@RequestParam(value="id") long id, Model model) {
		model.addAttribute("allUsers", userService.getAll());
    	model.addAttribute("photos", photoService.getPhotoByUser(id));  // get all photo by user shared 
        return "welcome";
	}
}
