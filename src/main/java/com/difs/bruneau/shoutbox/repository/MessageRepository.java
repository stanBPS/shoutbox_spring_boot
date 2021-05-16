package com.difs.bruneau.shoutbox.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.difs.bruneau.shoutbox.model.Message;


@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

	@RestResource(path="chercher-par-id")
	Message findByIdPseudo(@Param("id_pseudo") int idPseudo);
	
	Message findById(@Param("idMessage")int idMessage);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Message m SET m.messageSubject = :messageSubject WHERE m.idMessage = :idMessage")
	void updateTextMessage(@Param("messageSubject")String messageSubject,@Param("idMessage") int idMessage);
	
	Collection<Message> findAll();
}
