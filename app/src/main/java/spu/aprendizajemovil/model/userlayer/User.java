package spu.aprendizajemovil.model.userLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import spu.aprendizajemovil.model.positionLayer.Position;
import spu.aprendizajemovil.model.positionLayer.PositionedActivity;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.model.userLayer.userStates.UserState;
import spu.aprendizajemovil.utils.CollectedElement;

public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603650164596028430L;
	private String name;
	private Position position;
	private PositionedActivity currentActivity;
	private EventHistory eventHistory;
	private PositionedTask currentTask;
	private UserState currentState;
	private HashMap<PositionedTask, ArrayList<PositionedElement>> bagOfElements;

	private User()
	{
		this.setEventHistory(new EventHistory());
		this.setBagOfElements(new HashMap<PositionedTask, ArrayList<PositionedElement>>());
		this.setCurrentActivity(null);
		this.setCurrentTask(null);
	}

	public User(String userName)
	{
		this();
		this.setName(userName);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public PositionedActivity getCurrentActivity()
	{
		return currentActivity;
	}

	public void setCurrentActivity(PositionedActivity currentActivity)
	{
		this.currentActivity = currentActivity;
	}

	public EventHistory getEventHistory()
	{
		return eventHistory;
	}

	public void setEventHistory(EventHistory eventHistory)
	{
		this.eventHistory = eventHistory;
	}

	public UserState getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(UserState currentState)
	{
		this.currentState = currentState;
	}

	public PositionedTask getCurrentTask()
	{
		return currentTask;
	}

	public void setCurrentTask(PositionedTask currentTask)
	{
		this.currentTask = currentTask;
	}

	public void nextTask()
	{
		PositionedTask task = this.getCurrentTask();
		if (task == null)
		{
			this.setCurrentTask(this.getCurrentActivity().getPositionedTasks()
					.get(0));
		} else
		{
			int pos = this.getCurrentActivity().getPositionedTasks()
					.indexOf(currentTask);
			if (pos + 1 < this.getCurrentActivity().getPositionedTasks().size())
			{

				PositionedTask nextTask = this.getCurrentActivity()
						.getPositionedTasks().get(pos + 1);
				this.setCurrentTask(nextTask);
			} else
			{
				this.setCurrentTask(null);
			}
		}
	}

	public boolean addToBagOfElements(PositionedElement element,
			PositionedTask task)
	{
		ArrayList<PositionedElement> elements = new ArrayList<PositionedElement>();
		if (this.getBagOfElements().containsKey(task))
		{
			elements = this.getBagOfElements().get(task);
		} else
		{
			this.getBagOfElements().put(task, elements);
		}
		return elements.add(element);
	}

	// public boolean canPickElementFromTask(String result)
	// {
	// PositionedElement element = this.getCurrentTask().getElement(result);
	// if (element != null)
	// {
	// return !this.hasElementInBag(element, this.getCurrenttask());
	// }
	// return false;
	// }

	public boolean hasElementInBag(PositionedElement element,
			PositionedTask task)
	{
		return this.bagOfElementsContains(element, task);
	}

	public boolean pickElementFromCurrentTask(PositionedElement element)
	{
		if (this.getCurrentTask().pickElement(element))
		{
			element.setPosition(this.getPosition());
			this.addToBagOfElements(element, this.getCurrentTask());
			return true;
		}
		return false;
	}

	public String depositElement(PositionedElement element,
			PositionedTask task, PositionedDeposit deposit)
	{
		if (this.hasElementInBag(element, task))
		{
			this.takeElementFromBag(element, task);
			deposit.depositElement(element);
			return element.getName();
		}
		return "";
	}

	// public PositionedElement takeElementFromBag(String result)
	// {
	// PositionedElement element = null;
	// for (PositionedElement each : this.getBagOfElements())
	// {
	// if (each.hasIdentification(result))
	// {
	// element = each;
	// }
	// }
	// if (element != null)
	// {
	// this.getBagOfElements().remove(element);
	// }
	// return element;
	// }

	public boolean takeElementFromBag(PositionedElement element,
			PositionedTask task)
	{
		return this.removeFromBagOfElements(element, task);
	}

	public boolean takeElementFromBag(CollectedElement collectedElement)
	{
		return this.removeFromBagOfElements(collectedElement);
	}

	// public boolean hasElementInBag(String element)
	// {
	// for (PositionedElement each : this.getBagOfElements())
	// {
	// if (each.hasIdentification(element))
	// {
	// return true;
	// }
	// }
	// return false;
	// }

	public HashMap<PositionedTask, ArrayList<PositionedElement>> getBagOfElements()
	{
		return bagOfElements;
	}

	public boolean isBagEmpty()
	{
		return this.getBagOfElements().isEmpty();
	}

	public ArrayList<CollectedElement> getCollectedElements()
	{
		ArrayList<CollectedElement> result = new ArrayList<CollectedElement>();
		for (PositionedTask eachTask : this.getBagOfElements().keySet())
		{
			for (PositionedElement eachElement : this.getBagOfElements().get(
					eachTask))
			{
				result.add(new CollectedElement(eachElement, eachTask));
			}
		}
		Collections.sort(result);
		return result;
	}

	public ArrayList<CollectedElement> getCollectedElementsForTask(
			PositionedTask task)
	{
		ArrayList<CollectedElement> result = new ArrayList<CollectedElement>();
		if (this.getBagOfElements().containsKey(task))
		{
			for (PositionedElement eachElement : this.getBagOfElements().get(
					task))
			{
				result.add(new CollectedElement(eachElement, task));
			}
			// Collections.sort(result);
		}

		return result;
	}

	public void setBagOfElements(
			HashMap<PositionedTask, ArrayList<PositionedElement>> bagOfElements)
	{
		this.bagOfElements = bagOfElements;
	}

	public boolean bagOfElementsContains(PositionedElement element,
			PositionedTask task)
	{
		if (this.getBagOfElements().containsKey(task))
		{
			return this.getBagOfElements().get(task).contains(element);
		}
		return false;
	}

	public boolean removeFromBagOfElements(PositionedElement element,
			PositionedTask task)
	{
		boolean removed = false;
		if (this.getBagOfElements().containsKey(task))
		{
			removed = this.getBagOfElements().get(task).remove(element);
			if (removed)
			{
				if (this.getBagOfElements().get(task).isEmpty())
				{
					this.getBagOfElements().remove(task);
				}
			}
		}
		return removed;
	}

	public boolean removeFromBagOfElements(CollectedElement collectedElement)
	{
		boolean removed = false;
		if (this.getBagOfElements().containsKey(collectedElement.getTask()))
		{
			removed = this.getBagOfElements().get(collectedElement.getTask())
					.remove(collectedElement.getElement());
			if (removed)
			{
				if (this.getBagOfElements().get(collectedElement.getTask())
						.isEmpty())
				{
					this.getBagOfElements().remove(collectedElement.getTask());
				}
			}
		}
		return removed;
	}

	public PositionedTask getNextTask()
	{
		PositionedTask task = this.getCurrentTask();
		if (task == null)
		{
			return this.getCurrentActivity().getPositionedTasks().get(0);
		} else
		{
			int pos = this.getCurrentActivity().getPositionedTasks()
					.indexOf(currentTask);
			if (pos + 1 < this.getCurrentActivity().getPositionedTasks().size())
			{

				PositionedTask nextTask = this.getCurrentActivity()
						.getPositionedTasks().get(pos + 1);
				return nextTask;
			} else
			{
				return null;
			}
		}
	}

	public Boolean hasNextTask()
	{
		return (this.getNextTask() != null);
	}
}
