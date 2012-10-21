package com.dbc.mm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dbc.mm.model.User;

@Transactional(propagation=Propagation.REQUIRED)
public interface UserRepository extends JpaRepository<User, Long>
{
	public User findById(Long id);
}
