package spu.aprendizajemovil.model.activityLayer;

import java.io.Serializable;

public class Deposit implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7671738260353408037L;
	private String description;
	private String name;
	private String code;

	public Deposit(String name, String des, String code)
	{
		this.setDescription(des);
		this.setName(name);
		this.setCode(code);
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	public boolean hasIdentification(String result)
	{
		return this.getCode().equals(result);
	}

	public String toString()
	{
		return this.getName()
				;
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
			Deposit aux = (Deposit) o;
			return this.getName().equals(aux.getName());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 17;
		hash = hash * 17 + this.getName().hashCode();
		hash = hash * 17 + this.getDescription().hashCode();
		hash = hash * 17 + this.getCode().hashCode();
		return hash;
	}

}
