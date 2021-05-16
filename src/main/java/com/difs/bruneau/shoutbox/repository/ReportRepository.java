package com.difs.bruneau.shoutbox.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.difs.bruneau.shoutbox.model.Report;
import com.difs.bruneau.shoutbox.model.User;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
	Report findById(int idReport);
	
	@Query(value="SELECT * FROM message_report WHERE id_message = :id_message", nativeQuery=true)
	User getReportByIdMessage(@Param("id_message")int id_message);
	
	@Query(value="DELETE FROM message_report WHERE id_gestion = :id_gestion", nativeQuery=true)
	Report deleteReport(@Param("id_gestion")int id_gestion);
}
