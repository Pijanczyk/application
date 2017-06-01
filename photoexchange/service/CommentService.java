package com.photoexchange.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photoexchange.dao.CommentDao;
import com.photoexchange.dao.PhotoDao;
import com.photoexchange.dao.UserDao;
import com.photoexchange.model.Comment;
import com.photoexchange.model.Photo;
import com.photoexchange.model.User;

@Service
public class CommentService {

	@Autowired
	private UserDao userDao;
	  
	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private UserServiceImpl userService;
	
	public void create(Comment comment) {
		User creator = userService.getCurrentUser();
		comment.setUser(creator);
		comment.setDate(new Date());
		commentDao.save(comment);
	}
	
	public void create(String text, String userId, String photoId) {
		User user = userDao.findOne(Long.parseLong(userId));
		Photo photo  = photoDao.findOne(Long.parseLong(photoId));
		Comment comment = new Comment(text, user, photo);
		commentDao.save(comment);
	}
	
}
