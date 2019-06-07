package spu.aprendizajemovil.model.userLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.userLayer.events.Event;

public class EventHistory implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -326759251950432537L;
	private Collection<Event> events;

	public EventHistory()
	{
		this.setEvents(new ArrayList<Event>());
	}

	public Collection<Event> getEvents()
	{
		return events;
	}

	public void setEvents(Collection<Event> events)
	{
		this.events = events;
	}

	public boolean addEvent(Event event)
	{
		return this.getEvents().add(event);
	}

}
