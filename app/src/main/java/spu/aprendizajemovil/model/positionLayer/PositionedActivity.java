package spu.aprendizajemovil.model.positionLayer;

import java.io.Serializable;
import java.util.ArrayList;

import spu.aprendizajemovil.model.activityLayer.EducationalActivity;

public class PositionedActivity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180616375168627876L;
	private Position position;
	private EducationalActivity educationalActivity;
	private ArrayList<PositionedTask> positionedTasks;



	public PositionedActivity()
	{
		this.setPositionedTasks(new ArrayList<PositionedTask>());
	}

	private PositionedActivity(EducationalActivity educationalActivity)
	{
		this();
		this.setEducationalActivity(educationalActivity);
	}

	public PositionedActivity(EducationalActivity educationalActivity,
			Position position)
	{
		this(educationalActivity);
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

	public EducationalActivity getEducationalActivity()
	{
		return educationalActivity;
	}

	public void setEducationalActivity(EducationalActivity educationalActivity)
	{
		this.educationalActivity = educationalActivity;
	}

	public ArrayList<PositionedTask> getPositionedTasks()
	{
		return positionedTasks;
	}

	public void setPositionedTasks(ArrayList<PositionedTask> positionedTasks)
	{
		this.positionedTasks = positionedTasks;
	}

	public boolean addPositionedTasks(PositionedTask positionedTask)
	{
		return this.getPositionedTasks().add(positionedTask);
	}

	public String getGoal()
	{
		return this.getEducationalActivity().getGoal();
	}

	public String getName()
	{
		return this.getEducationalActivity().getName();
	}

}
