
package com.demo.spa.boot.mvc.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.spa.boot.mvc.data.entity.Event;

public abstract class AbstractEventServiceTests {

	@Autowired
	protected EventService eventService = null;

	@Test
    public void testForGet2ndEventDetailById() {
		Event event = eventService.readEventDetailById(2);
		assertThat(event.getId()).isEqualTo(2);
		assertThat(event.getTitle()).isEqualTo("SQL Statement Error");
		assertThat(event.getDescription()).startsWith("Select syntax");
		assertThat(event.getOwner()).isEqualTo("Jim");
		assertThat(event.getStatus()).isEqualTo("Opening");
    }

	@Test
    public void testForGet1stEventDetailByTitle() {
		Event event = eventService.readEventDetailByTitle("UI Issue");
		assertThat(event.getId()).isEqualTo(1);
		assertThat(event.getDescription()).startsWith("Checkbox not");
		assertThat(event.getOwner()).isEqualTo("Tom");
		assertThat(event.getStatus()).isEqualTo("Opening");
    }

	@Test
    public void testForClose1stEventById() {
		Event event = eventService.readEventDetailById(1);
		assertThat(event.getId()).isEqualTo(1);
		assertThat(event.getStatus()).isEqualTo("Opening");
		event.setStatus("Closed");
		eventService.updateEvent(event);
		Event event1 = eventService.readEventDetailById(1);
		assertThat(event1.getId()).isEqualTo(1);
		assertThat(event1.getStatus()).isEqualTo("Closed");
    }

}
