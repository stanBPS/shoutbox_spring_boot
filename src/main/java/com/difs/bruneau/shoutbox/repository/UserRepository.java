package com.difs.bruneau.shoutbox.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.difs.bruneau.shoutbox.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	
	@RestResource(path="chercher-un-utilisateur")
	User findByUserPseudo(@Param("user_pseudo") String userPseudo);
	
	@Query(value="SELECT * FROM user_table u WHERE u.user_pseudo = :user_pseudo", nativeQuery=true)
	User getUserByPseudo(@Param("user_pseudo")String user_pseudo);
	
	User findById(@Param("idPseudo")int idPseudo);
	
	Collection<User> findAll();

}
