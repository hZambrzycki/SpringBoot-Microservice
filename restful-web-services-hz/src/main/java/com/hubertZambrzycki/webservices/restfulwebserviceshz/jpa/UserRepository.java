package com.hubertZambrzycki.webservices.restfulwebserviceshz.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hubertZambrzycki.webservices.restfulwebserviceshz.user.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
