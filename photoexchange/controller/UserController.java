/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoexchange.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.photoexchange.model.User;
import com.photoexchange.service.PhotoServiceImpl;
import com.photoexchange.service.SecurityServiceImpl;
import com.photoexchange.service.UserServiceImpl;
import com.photoexchange.validator.UserValidator;

/**
 *
 * @author Filip
 */
@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	PhotoServiceImpl photoService;
	
	 @Autowired
	    private SecurityServiceImpl securityService;

	    @Autowired
	    private UserValidator userValidator;

	    @RequestMapping(value = "/registration", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new User());

	        return "registration";
	    }
	    

	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
	        userValidator.validate(userForm, bindingResult);

	        if (bindingResult.hasErrors()) {
	            return "registration";
	        }

	        userService.save(userForm);

	        securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());

	        return "redirect:/welcome";
	    }

	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(Model model, String error, String logout) {
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "login";
	    }

	    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	    public String welcome(Model model) {
	    	model.addAttribute("allUsers", userService.getAll());
	    	model.addAttribute("photos", photoService.getPhotoByUser());
	        return "welcome";
	    }
	    
	    
	    
	    // -------------------------------------------------------------------------
	

  /**
   * GET /create  --> Create a new user and save it in the database.
 * @throws ParseException 
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name, String surname, String password) throws ParseException {
    
    return "User succesfully created with id = " + userService.create(email, name, surname, password);
  }
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
	  userService.delete(id);
    return "User succesfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    return "The user id is: " + userService.getIdByEmail(email);
  }
  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    userService.updateUser(id, email, name);
    return "User succesfully updated!";
  }

  // Private fields

 
  
}