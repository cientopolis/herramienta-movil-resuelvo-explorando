package spu.aprendizajemovil.model.positionLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.activityLayer.Task;

public class PositionedTask implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2471433917079560911L;
	private Position position;
	private Task task;
	private Collection<PositionedElement> availables;

	public PositionedTask()
	{
		this.setAvailables(new ArrayList<PositionedElement>());
	}

	private PositionedTask(Task task)
	{
		this();
		this.setTask(task);
	}

	public PositionedTask(Task task, Position position)
	{
		this(task);
		this.setPosition(position);
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public Task getTask()
	{
		return task;
	}

	public void setTask(Task task)
	{
		this.task = task;
	}

	public Collection<PositionedElement> getAvailables()
	{
		return availables;
	}

	public void setAvailables(Collection<PositionedElement> availables)
	{
		this.availables = availables;
	}

	public boolean addAvailable(PositionedElement positionedElement)
	{
		return this.getAvailables().add(positionedElement);
	}

	public String getDescription()
	{
		return this.getTask().getDescription();
	}

	public String getName()
	{
		return this.getTask().getName();
	}

	public PositionedElement pickElement(String result)
	{
		PositionedElement element = this.getElement(result);
		if (element != null)
		{
			this.getAvailables().remove(element);
		}
		return element;
	}

	public boolean pickElement(PositionedElement element)
	{
		return this.getAvailables().remove(element);
	}

	public boolean hasElement(String result)
	{
		return this.getTask().elementsContain(result);
	}

	public PositionedElement getElement(String code)
	{
		for (PositionedElement each : this.getAvailables())
		{
			if (each.hasIdentification(code))
			{
				return each;
			}
		}
		return null;
	}

	// public boolean isElementAvailable(String result)
	// {
	// return this.getElement(result) != null;
	// }

	public boolean hasIdentification(String code)
	{
		return this.getTask().hasIdentification(code);
	}

	public boolean elementIsAvailable(PositionedElement element)
	{
		return this.getAvailables().contains(element);
	}

	public String toString()
	{
		return this.getTask().toString();
	}

	public void returnElement(PositionedElement element)
	{
		this.addAvailable(element);
		element.setPosition(this.getPosition());
	}

	public boolean isElementValid(PositionedElement element)
	{
		return this.getTask().validElementsContains(element.getElement());
	}

	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (o == this)
		{
			return true;
		}
		if (this.getClass().equals(o.getClass()))
		{
			PositionedTask aux = (PositionedTask) o;
			return this.getTask().equals(aux.getTask());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = hash * 7 + this.getTask().hashCode();
		return hash;
	}

}
