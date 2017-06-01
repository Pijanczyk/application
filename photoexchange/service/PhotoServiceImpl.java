package com.photoexchange.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photoexchange.dao.PhotoDao;
import com.photoexchange.model.Photo;
import com.photoexchange.model.User;

@Service
public class PhotoServiceImpl {
	
	@Autowired
	private UserServiceImpl userService;
	  
	@Autowired
	private PhotoDao photoDao;

	public void create(Photo photo) {
		User creator = userService.getCurrentUser();
		photo.setCreator(creator);
		photo.setDate(new Date());
		photoDao.save(photo);
	}

	public List<Photo> getPhotoByUser() {
		User creator = userService.getCurrentUser();
		return photoDao.findByCreator(creator);
	}
	
	public List<Photo> getPhotoByUser(long id) {
		User creator = userService.getById(id);
		User currentUser = userService.getCurrentUser();
		return photoDao.findByCreatorAndForCurrentUser(creator, currentUser);
	}

	public void update(Photo photo) {
		Photo existingPhoto = getById(photo.getId());
		User currentUser = userService.getCurrentUser();
		if (currentUser.equals(existingPhoto.getCreator())) {
			existingPhoto.setSharedUsers(photo.getSharedUsers());
		}			
		
		photoDao.save(photo);
	}

	public Photo getById(long id) {		
		return photoDao.findOne(id);
	}
}
