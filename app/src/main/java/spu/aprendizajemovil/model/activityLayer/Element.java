package spu.aprendizajemovil.model.activityLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Element implements Serializable
{

	private static final long serialVersionUID = -8606203431971971334L;
	private String name;
	private String description;
	private Collection<Deposit> deposits;
	private String code;

	public Element()
	{
		this.setDeposits(new ArrayList<Deposit>());
	}

	public Element(String name, String desc, String code)
	{
		this();
		this.setName(name);
		this.setDescription(desc);
		this.setCode(code);
	}

	public Element(String name, String desc, Collection<Deposit> dep)
	{
		this.setName(name);
		this.setDescription(desc);
		this.setDeposits(dep);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Collection<Deposit> getDeposits()
	{
		return deposits;
	}

	public void setDeposits(Collection<Deposit> deposits)
	{
		this.deposits = deposits;
	}

	public boolean addDeposit(Deposit deposit)
	{
		return this.getDeposits().add(deposit);
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public boolean hasIdentification(String code)
	{
		return this.getCode().equals(code);// o como sea que se lo identifique
	}

	public boolean isDepositValid(Deposit deposit)
	{
		return this.getDeposits().contains(deposit);
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
			Element aux = (Element) o;
			return this.getName().equals(aux.getName())
					&& this.getDescription().equals(aux.getDescription());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 19;
		hash = hash * 19 + this.getName().hashCode();
		hash = hash * 19 + this.getDescription().hashCode();
		hash = hash * 17 + this.getCode().hashCode();
		return hash;
	}

}
