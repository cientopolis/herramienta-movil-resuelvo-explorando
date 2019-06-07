package spu.aprendizajemovil.model.positionLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.activityLayer.Element;

public class PositionedElement implements Serializable,
		Comparable<PositionedElement>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 286397533798871400L;
	private Position position;
	private Element element;
	private Collection<PositionedDeposit> deposits;

	public PositionedElement()
	{
		this.setDeposits(new ArrayList<PositionedDeposit>());
	}

	private PositionedElement(Element element)
	{
		this();
		this.setElement(element);
	}

	public PositionedElement(Element element, Position position)
	{
		this(element);
		this.setPosition(position);
	}

	public PositionedElement(Element element, Position position,
			Collection<PositionedDeposit> deposits)
	{
		this(element, position);
		this.setDeposits(deposits);
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public Element getElement()
	{
		return element;
	}

	public void setElement(Element element)
	{
		this.element = element;
	}

	public Collection<PositionedDeposit> getDeposits()
	{
		return deposits;
	}

	public void setDeposits(Collection<PositionedDeposit> deposits)
	{
		this.deposits = deposits;
	}

	public boolean addDeposit(PositionedDeposit deposit)
	{
		return this.getDeposits().add(deposit);
	}

	public String getDescription()
	{
		return this.getElement().getDescription();
	}

	public String getName()
	{
		return this.getElement().getName();
	}

	public String getCode()
	{
		return this.getElement().getCode();
	}

	public boolean hasIdentification(String code)
	{
		return this.getElement().hasIdentification(code);
	}

	public String toString()
	{
		return this.getName();
	}

	@Override
	public int compareTo(PositionedElement another)
	{
		return this.getName().compareToIgnoreCase(another.getName());
	}

	public boolean isDepositValid(PositionedDeposit deposit)
	{
		return this.getElement().isDepositValid(deposit.getDeposit());
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
			PositionedElement aux = (PositionedElement) o;
			return this.getElement().equals(aux.getElement());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 29;
		hash = hash * 29 + this.getElement().hashCode();
		return hash;
	}

}
