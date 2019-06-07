package spu.aprendizajemovil.model.userLayer.userStates;

import spu.aprendizajemovil.model.positionLayer.PositionedTask;

public class GoingToTask extends UserState
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4611407716542742441L;
	private PositionedTask positionedTask;

	public PositionedTask getPositionedTask()
	{
		return positionedTask;
	}

	public void setPositionedTask(PositionedTask positionedTask)
	{
		this.positionedTask = positionedTask;
	}

}
