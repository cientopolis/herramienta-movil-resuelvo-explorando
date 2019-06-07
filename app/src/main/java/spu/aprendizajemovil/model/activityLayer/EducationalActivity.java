package spu.aprendizajemovil.model.activityLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class EducationalActivity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1379403391653353665L;
	private String goal;
	private ArrayList<Task> tasks;
	private String name;
	private String code;

	public EducationalActivity()
	{
		this.setTasks(new ArrayList<Task>());
	}

	public EducationalActivity(String name, String goal, String code)
	{
		this();
		this.setName(name);
		this.setGoal(goal);
		this.setCode(code);
	}

	public String getGoal()
	{
		return goal;
	}

	public void setGoal(String goal)
	{
		this.goal = goal;
	}

	public ArrayList<Task> getTasks()
	{
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks)
	{
		this.tasks = tasks;
	}

	public boolean addTask(Task task)
	{
		return this.getTasks().add(task);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}
