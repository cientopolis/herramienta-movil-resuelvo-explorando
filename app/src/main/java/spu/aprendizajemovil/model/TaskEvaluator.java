package spu.aprendizajemovil.model;

import java.io.Serializable;
import java.util.ArrayList;

import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.model.userLayer.User;

public class TaskEvaluator implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8743894897122865107L;
	private PositionedTask task;
	private ArrayList<PositionedElement> correctElements;
	private ArrayList<PositionedElement> incorrectElements;
	private ArrayList<PositionedElement> leftElements;

	public TaskEvaluator()
	{
		this.setCorrectElements(new ArrayList<PositionedElement>());
		this.setIncorrectElements(new ArrayList<PositionedElement>());
		this.setLeftElements(new ArrayList<PositionedElement>());
	}

	public TaskEvaluator(PositionedTask task, User user)
	{
		this();
		this.setTask(task);
		if (user.getBagOfElements().containsKey(task))
		{
			for (PositionedElement element : user.getBagOfElements().get(task))
			{
				if (task.isElementValid(element))
				{
					this.addCorrectElement(element);
				} else
				{
					this.addIncorrectElement(element);
				}
			}
		}
		for (PositionedElement each : task.getAvailables())
		{
			if (task.isElementValid(each))
			{
				this.addLeftElement(each);
			}
		}
	}

	public PositionedTask getTask()
	{
		return task;
	}

	public void setTask(PositionedTask task)
	{
		this.task = task;
	}

	public ArrayList<PositionedElement> getCorrectElements()
	{
		return correctElements;
	}

	public void setCorrectElements(ArrayList<PositionedElement> correctElements)
	{
		this.correctElements = correctElements;
	}

	public ArrayList<PositionedElement> getIncorrectElements()
	{
		return incorrectElements;
	}

	public void setIncorrectElements(
			ArrayList<PositionedElement> incorrectElements)
	{
		this.incorrectElements = incorrectElements;
	}

	public boolean addCorrectElement(PositionedElement element)
	{
		return this.getCorrectElements().add(element);
	}

	public boolean addIncorrectElement(PositionedElement element)
	{
		return this.getIncorrectElements().add(element);
	}

	public ArrayList<PositionedElement> getLeftElements()
	{
		return leftElements;
	}

	public void setLeftElements(ArrayList<PositionedElement> leftElements)
	{
		this.leftElements = leftElements;
	}

	public boolean addLeftElement(PositionedElement element)
	{
		return this.getLeftElements().add(element);
	}

	public String toString()
	{
		return this.getTask().toString();
	}

}
