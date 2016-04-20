package com.halcon.scheduler;

import java.util.Collection;
import java.util.HashSet;

public class Scheduler {
	private final Schedule schedule;
	
	public Scheduler(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public boolean schedule(Event event) {
		int startTime = 0;
		do {
			Collection<Resource> resources = schedule.resourcesAvailableDuringPeriod(startTime, event.duration);
			Collection<Resource> grants = new HashSet<>();
			
			for(Requirement requirement : event.requirements) {
				Collection<Resource> tmp = new HashSet<>(requirement.acceptableResources);
				tmp.retainAll(resources);
				if(tmp.isEmpty()) break;
				Resource grant = tmp.iterator().next();
				grants.add(grant);
				resources.remove(grant);
			}
			
			if(grants.size() == event.requirements.size()) {
				schedule.addEvent(event, startTime, grants);
				return true;
			}
			
			startTime++;
		} while(schedule.canScheduleEventAt(startTime, event.duration));
		
		return false;
	}
}
