package spu.aprendizajemovil.model.positionLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.activityLayer.Deposit;

public class PositionedDeposit implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8055439744607446495L;
	private Position position;
	private Collection<PositionedElement> depositedElements;
	private Deposit deposit;

	public PositionedDeposit()
	{
		this.setDepositedElements(new ArrayList<PositionedElement>());
	}

	public PositionedDeposit(Deposit deposit, Position pos)
	{
		this();
		this.setPosition(pos);
		this.setDeposit(deposit);
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public Collection<PositionedElement> getDepositedElements()
	{
		return depositedElements;
	}

	public void setDepositedElements(
			Collection<PositionedElement> depositedElements)
	{
		this.depositedElements = depositedElements;
	}

	public boolean depositElement(PositionedElement element)
	{
		boolean added = this.getDepositedElements().add(element);
		if (added)
		{
			element.setPosition(this.getPosition());
		}
		return added;
	}

	public Deposit getDeposit()
	{
		return deposit;
	}

	public void setDeposit(Deposit deposit)
	{
		this.deposit = deposit;
	}

	public String getDescription()
	{
		return this.getDeposit().getDescription();
	}

	public String getName()
	{
		return this.getDeposit().getName();
	}

	public boolean hasIdentification(String result)
	{
		return this.getDeposit().hasIdentification(result);
	}

	public String toString()
	{
		return this.getDeposit().toString();
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
			PositionedDeposit aux = (PositionedDeposit) o;
			return this.getDeposit().equals(aux.getDeposit());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 13;
		hash = hash * 13 + this.getDeposit().hashCode();
		return hash;
	}

}
