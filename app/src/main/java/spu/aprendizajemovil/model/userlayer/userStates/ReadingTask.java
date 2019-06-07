package spu.aprendizajemovil.model.userLayer.userStates;

import spu.aprendizajemovil.model.positionLayer.PositionedTask;

public class ReadingTask extends UserState
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8549910116136376504L;
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
