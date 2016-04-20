package com.halcon.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Schedule {
	public final int duration;
	private final Collection<Resource> availableResources;
	private final List<Collection<Event>> events;
	private final Map<Event, Collection<Resource>> grants = new HashMap<>();
	
	
	public Schedule(int duration, Collection<Resource> availableResources) {
		this.duration = duration;
		this.availableResources = Collections.unmodifiableCollection(availableResources);
		
		events = new ArrayList<>();
		for(int t = 0; t < duration; t++) events.add(t, new HashSet<Event>());
	}
	
	public boolean canScheduleEventAt(int startTime, int duration) {
		return startTime >= 0 && startTime + duration <= this.duration;
	}
	
	public Collection<Resource> resourcesAvailableDuringPeriod(int startTime, int duration) {
		Collection<Resource> resources = new HashSet<>(availableResources);
		
		for(int i = 0; i < duration; i++) {
			for(Event event : events.get(startTime + i)) {
				resources.removeAll(grants.get(event));
			}
		}
			
		return resources;
	}
	
	public void addEvent(Event event, int startTime, Collection<Resource> requiredResources) {
		for(int i = 0; i < event.duration; i++) {
			events.get(startTime + i).add(event);
		}
		
		grants.put(event, requiredResources);
	}
}
