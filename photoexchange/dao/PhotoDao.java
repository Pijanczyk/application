package com.photoexchange.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.photoexchange.model.Photo;
import com.photoexchange.model.User;

/**
 * @author Filip
 */
@Transactional
public interface PhotoDao extends CrudRepository<Photo, Long> {

	public List<Photo> findByCreator(User creator);

	@Query("SELECT p FROM Photo p WHERE p.creator = :creator and :currentUser MEMBER OF p.sharedUsers")
	public List<Photo> findByCreatorAndForCurrentUser(@Param("creator") User creator, @Param("currentUser") User currentUser);

}
