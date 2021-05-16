package com.difs.bruneau.shoutbox.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.difs.bruneau.shoutbox.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	
	@Query("SELECT r FROM Role r WHERE r.idRole = :id_role")
	public Role getRoleById(@Param("id_role") int id_role);
	
	@Query("SELECT r FROM Role r WHERE r.libelleRole = :libelleRole")
	public Role getIdByRole(@Param("libelleRole") String libelleRole);

}
