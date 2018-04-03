
package com.demo.spa.boot.mvc.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spa.boot.mvc.data.entity.Event;
import com.demo.spa.boot.mvc.domain.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(tags={"api-main"})
public class ApiController {

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private EventService eventService = null;

	@RequestMapping(value="/events", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get all the event entities.", notes="Get all the event entities.")
	public List<Event> getAllEvents() {
		log.info("readAllEvents() is calling ...");
		return eventService.readAllEvents();
	}

	@RequestMapping(value="/events/{id}", method=RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get the event entity detail by Id.", notes="Get the event entity detail by Id.")
	public Event getEventDetailById(@ApiParam(value="the id of the existing event entity.", required=true) @PathVariable int id) {
		Event result = eventService.readEventDetailById(id);
		return result;
	}

	@RequestMapping(value="/events/by", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get the event entities by category.", notes="Get the event entities by category.")
	public List<Event> getEventsByCategory(@ApiParam(value="the event's category (i.e., all, Opening, Closed)", required=true) @RequestParam(required=true, name="category") String category) {
		log.info("readEventsByCategory() is calling ...");
		return eventService.readEventsByCategory(category);
	}

	@RequestMapping(value="/eventdetail/bytitle", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get the event entity by title.", notes="Get the event entity by title.")
	public Event getEventDetailByTitle(@ApiParam(value="the event's attribute - title", required=true) @RequestParam(required=true, name="title") String title) {
		return eventService.readEventDetailByTitle(title);
	}

	@RequestMapping(value="/events/{id}", method=RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Change the event details (Update)", notes="Change the event details (Update)")
	public Event modifyEventById(@ApiParam(value="the id of the existing event entity.", required=true) @PathVariable int id, @RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	@RequestMapping(value="/events/{id}/closed", method=RequestMethod.PUT, produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Close the event by Id (Update detail for status)", notes="Close the event by Id (Update detail for status)")
	public Event closeEvent(@ApiParam(value="the id of the existing event entity.", required=true) @PathVariable int id) {
		Event result = eventService.readEventDetailById(id);
		result.setStatus("Closed");
		eventService.updateEvent(result);
		return result;
	}

	@RequestMapping(value="/events", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Create a new event entity.", notes="Create a new event entity.")
	public Event addNewEvent(@RequestBody Event event) {
		return eventService.createEvent(event);
	}

	@RequestMapping(value="/events/{id}", method=RequestMethod.DELETE, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Delete an event entity.", notes="Delete an event entity.")
	public Event removeEventById(@ApiParam(value="the id of the existing event entity.", required=true) @PathVariable int id) {
		return eventService.deleteEvent(id);
	}

	@RequestMapping(value="/events/{id}/delete", method=RequestMethod.DELETE, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Delete an event entity by other way.", notes="Delete an event entity by other way.")
	public Event deleteEventById(@ApiParam(value="the id of the existing event entity.", required=true) @PathVariable int id) {
		return eventService.deleteEvent(id);
	}

}
