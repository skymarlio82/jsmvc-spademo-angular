
package com.demo.spa.boot.mvc.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spa.boot.mvc.data.entity.Event;

@Repository("eventDao")
public interface EventDao extends JpaRepository<Event, Integer> {

	@Query("from Event e")
	List<Event> findAll();

	List<Event> findByStatus(String status);

	Event findByTitle(String title);

}
