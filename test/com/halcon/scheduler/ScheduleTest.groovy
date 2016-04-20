package com.halcon.scheduler

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

class ScheduleTest {

	@Test
	public void testCanScheduleEvent() {
		def schedule = new Schedule(1, [])
		
		assertThat(schedule.canScheduleEventAt(-1, 1), is(false))
		assertThat(schedule.canScheduleEventAt(0, 1), is(true))
		assertThat(schedule.canScheduleEventAt(1, 1), is(false))

		assertThat(schedule.canScheduleEventAt(-1, 2), is(false))
		assertThat(schedule.canScheduleEventAt(0, 2), is(false))
		assertThat(schedule.canScheduleEventAt(1, 2), is(false))
	}
	
	@Test
	public void resourcesAvailableDuringPeriod() {
		def resource = new Resource()
		def schedule = new Schedule(1, [resource])
		
		assertThat(schedule.resourcesAvailableDuringPeriod(0, 1).size(), is(1))
	}
	
	@Test
	public void resourcesAddEvent() {
		def resource = new Resource()
		def schedule = new Schedule(1, [resource])
		def event = new Event(1, [new Requirement([resource])])
		
		schedule.addEvent(event, 0, [resource])
		 
		assertThat(schedule.resourcesAvailableDuringPeriod(0, 1).size(), is(0))
	}
}
