
package com.demo.spa.boot.mvc.domain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spa.boot.mvc.data.dao.EventDao;
import com.demo.spa.boot.mvc.data.entity.Event;

@Service("eventService")
public class EventService {

	private static final Logger log = LoggerFactory.getLogger(EventService.class);

	@Autowired
	private EventDao eventDao = null;

	@Transactional(readOnly=true)
	public List<Event> readAllEvents() {
		List<Event> eventList = eventDao.findAll();
		log.debug("getAllEvents : size = " + eventList.size());
		return eventList;
	}

	@Transactional(readOnly=true)
	public List<Event> readEventsByCategory(String category) {
		List<Event> res = null;
		if (category.equals("all")) {
			res = eventDao.findAll();
		} else {
			res = eventDao.findByStatus(category);
		}
		log.debug("getEventsByCategory : category = " + category + ", size = " + res.size());
		return res;
	}

	@Transactional(readOnly=true)
	public Event readEventDetailByTitle(String title) {
		return eventDao.findByTitle(title);
	}

	@Transactional(readOnly=true)
	public Event readEventDetailById(int id) {
		Event event = eventDao.findById(new Integer(id)).get();
		return event;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public Event updateEvent(Event event) {
		log.debug("Before updating Event record from web request : " + event);
		eventDao.saveAndFlush(event);
		log.debug("After being updated Event record into DB : " + event);
		return event;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public Event createEvent(Event event) {
		log.debug("Before creating Event record from web request : " + event);
		event.setStatus("Opening");
		eventDao.save(event);
		log.debug("After being created Event record into DB : " + event);
		return event;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public Event deleteEvent(int id) {
		Event event = eventDao.findById(new Integer(id)).get();
		log.debug("Before deleting Event record in DB : " + event);
		eventDao.delete(event);
		log.debug("After being deleted Event record from DB : " + event);
		return event;
	}

}
