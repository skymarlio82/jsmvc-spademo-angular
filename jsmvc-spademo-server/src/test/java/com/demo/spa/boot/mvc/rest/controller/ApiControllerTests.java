
package com.demo.spa.boot.mvc.rest.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spa.boot.mvc.data.entity.Event;
import com.demo.spa.boot.mvc.domain.service.EventService;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTests {

	@Autowired
    private MockMvc mvc = null;

	@MockBean
	EventService eventService = null;

	@Test
	public void testForGetEventDetailById() throws Exception {
		Event event = new Event(1, "UI Issue", "Checkbox not pop up", new Date(), new Date(), "Tom", "Opening", false);
		given(eventService.readEventDetailById(1)).willReturn(event);
		mvc.perform(get("/api/events/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.title").value("UI Issue"))
			.andExpect(jsonPath("$.description").value("Checkbox not pop up"))
			.andExpect(jsonPath("$.owner").value("Tom"))
			.andExpect(jsonPath("$.status").value("Opening"));
	}
	
	@Test
	public void testForGetAllEvents() throws Exception {
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(new Event(1, "UI Issue", "Checkbox not pop up", new Date(), new Date(), "Tom", "Opening", false));
		eventList.add(new Event(2, "SQL Statement Error", "Select syntax wrong", new Date(), new Date(), "Jim", "Opening", false));
		eventList.add(new Event(3, "Spring AOP No Dependency", "JAR lib missed", new Date(), new Date(), "Bob", "Opening", false));
		eventList.add(new Event(4, "Java Null Point Error", "Java Object not found", new Date(), new Date(), "Joe", "Opening", false));
		given(eventService.readAllEvents()).willReturn(eventList);
		mvc.perform(get("/api/events").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$", hasSize(4)))
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[0].title").value("UI Issue"))
			.andExpect(jsonPath("$[1].id").value(2))
			.andExpect(jsonPath("$[1].title").value("SQL Statement Error"))
			.andExpect(jsonPath("$[2].id").value(3))
			.andExpect(jsonPath("$[2].title").value("Spring AOP No Dependency"))
			.andExpect(jsonPath("$[3].id").value(4))
			.andExpect(jsonPath("$[3].title").value("Java Null Point Error"));
	}

}
