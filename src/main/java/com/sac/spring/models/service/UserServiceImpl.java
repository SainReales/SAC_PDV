package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.dao.IUserDao;
import com.sac.spring.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public List<User> getUsers() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.findById(id).orElse(null);
	}

}
