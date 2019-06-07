package spu.aprendizajemovil.utils;

import java.io.Serializable;

import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;

public class CollectedElement implements Comparable<CollectedElement>,
		Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6861378033372822267L;
	private PositionedElement element;
	private PositionedTask task;

	public CollectedElement(PositionedElement element, PositionedTask task)
	{
		this.setElement(element);
		this.setTask(task);
	}

	public PositionedElement getElement()
	{
		return element;
	}

	public void setElement(PositionedElement element)
	{
		this.element = element;
	}

	public PositionedTask getTask()
	{
		return task;
	}

	public void setTask(PositionedTask task)
	{
		this.task = task;
	}

	public String toString()
	{
		return this.getElement().toString();
	}

	@Override
	public int compareTo(CollectedElement another)
	{
		int comparedTask = this.getTask().getName()
				.compareToIgnoreCase(another.getTask().getName());
		if (comparedTask == 0)
		{
			return this.getElement().getName()
					.compareToIgnoreCase(another.getElement().getName());
		}
		return comparedTask;
	}

}
