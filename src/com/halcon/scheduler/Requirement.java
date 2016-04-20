package com.halcon.scheduler;

import java.util.Collection;
import java.util.Collections;

public class Requirement {
	public final Collection<Resource> acceptableResources;
	
	public Requirement(Collection<Resource> acceptableResources) {
		this.acceptableResources = Collections.unmodifiableCollection(acceptableResources);
	}
}
