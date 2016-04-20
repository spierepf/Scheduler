package com.halcon.scheduler

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

class SchedulerTest {

	@Test
	public void testShortSchedule() {
		def resource1 = new Resource()
		def schedule = new Schedule(1, [resource1])
		def scheduler = new Scheduler(schedule)

		def event1 = new Event(1, [new Requirement([resource1])])
		def event2 = new Event(1, [new Requirement([resource1])])
		
		assertThat(scheduler.schedule(event1), is(true))
		assertThat(scheduler.schedule(event2), is(false))
	}

	@Test
	public void testLongSchedule() {
		def resource1 = new Resource()
		def schedule = new Schedule(2, [resource1])
		def scheduler = new Scheduler(schedule)

		def event1 = new Event(1, [new Requirement([resource1])])
		def event2 = new Event(1, [new Requirement([resource1])])
		
		assertThat(scheduler.schedule(event1), is(true))
		assertThat(scheduler.schedule(event2), is(true))
	}
}
