package com.hubertZambrzycki.webservices.restfulwebserviceshz.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hubertZambrzycki.webservices.restfulwebserviceshz.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>
{

}
