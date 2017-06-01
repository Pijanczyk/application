package com.photoexchange.service;

import java.text.ParseException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.photoexchange.dao.RoleDao;
import com.photoexchange.dao.UserDao;
import com.photoexchange.model.User;


@Service
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SecurityServiceImpl securityService;

	public String create(String email, String name, String surname, String password) throws ParseException {

		User user = new User(email, name, surname, password);
		userDao.save(user);
		return String.valueOf(user.getId());

	}



	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleDao.findAll()));
		userDao.save(user);
	}

	public User findByUsername(String email) {
		return userDao.findByEmail(email);
	}

	public void delete(long id) {
		User user = new User(id);
		userDao.delete(user);
	}

	public String getIdByEmail(String email) {
		User user = userDao.findByEmail(email);
		return String.valueOf(user.getId());
	}
	
	public User getByEmail(String email) {
		return userDao.findByEmail(email);
	}


	public void updateUser(long id, String email, String name) {
		User user = userDao.findOne(id);
		user.setEmail(email);
		user.setName(name);
		userDao.save(user);
	}

	public Iterable<User> getAll(){
		return userDao.findAll();
	}



	public User getCurrentUser() {	
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return getByEmail(user.getUsername());
	}
	
	
	
	public User getById(long id) {
		
		return userDao.findOne(id);
	}

	
	
	public SecurityServiceImpl getSecurityService() {
		return securityService;
	}



	public void setSecurityService(SecurityServiceImpl securityService) {
		this.securityService = securityService;
	}
}
