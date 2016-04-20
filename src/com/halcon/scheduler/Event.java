package com.halcon.scheduler;

import java.util.Collection;
import java.util.Collections;

public class Event {
	public final int duration;
	public final Collection<Requirement> requirements;
	
	public Event(int duration, Collection<Requirement> requirements) {
		this.duration = duration;
		this.requirements = Collections.unmodifiableCollection(requirements);
	}
}
