package com.dbc.mm.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.mm.model.User;
import com.dbc.mm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers()
	{
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findOne(Long id)
	{
		User user = userRepository.findOne(id);
		return user;
	}


}
