/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoexchange.dao;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.photoexchange.model.User;

/**
 *
 * @author Filip
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automatically generated from its signature by Spring Data JPA.
   */
  public User findByEmail(String email);
  

}
