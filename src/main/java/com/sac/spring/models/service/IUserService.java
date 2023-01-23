package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.entity.User;

public interface IUserService {

	public List<User> getUsers();

	public User getUserById(Long id);

	public void addUser(User user);

	public void deleteUser(Long id);
}
