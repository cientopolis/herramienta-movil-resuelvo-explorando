package spu.aprendizajemovil.model.userLayer.userStates;

import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;

public class GoingToDeposit extends UserState
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1752053948939872244L;
	private PositionedDeposit deposit;

	public PositionedDeposit getDeposit()
	{
		return deposit;
	}

	public void setDeposit(PositionedDeposit deposit)
	{
		this.deposit = deposit;
	}

}
