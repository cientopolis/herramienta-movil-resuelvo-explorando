package spu.aprendizajemovil.model;

import java.io.Serializable;
import java.util.ArrayList;

import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;

public class DepositEvaluator implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -752912257491213179L;
	private PositionedDeposit deposit;
	private ArrayList<PositionedElement> correctElements;
	private ArrayList<PositionedElement> incorrectElements;

	public DepositEvaluator()
	{
		this.setCorrectElements(new ArrayList<PositionedElement>());
		this.setIncorrectElements(new ArrayList<PositionedElement>());
	}

	public DepositEvaluator(PositionedDeposit deposit)
	{
		this();
		this.setDeposit(deposit);
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

	public PositionedDeposit getDeposit()
	{
		return deposit;
	}

	public void setDeposit(PositionedDeposit deposit)
	{
		this.deposit = deposit;
	}

	public String toString()
	{
		return this.getDeposit().toString();
	}
}
