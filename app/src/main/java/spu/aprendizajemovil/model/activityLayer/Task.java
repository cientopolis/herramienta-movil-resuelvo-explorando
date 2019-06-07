package spu.aprendizajemovil.model.activityLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.JasonModel.ExtraInfo;

public class Task implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8088029887919446603L;
	private Collection<Element> elements;
	private Collection<Element> validElements;
	private String description;
	private String name;
	private String code;
	private String consigna;
	private ArrayList <ExtraInfo> extras;

	public ArrayList<ExtraInfo> getExtras() {
		return extras;
	}

	public void setExtras(ArrayList<ExtraInfo> extras) {
		this.extras = extras;
	}



	public Task()
	{
		this.setElements(new ArrayList<Element>());
		this.setValidElements(new ArrayList<Element>());
	}

	public Task(String name, String desc, String code)
	{
		this();
		this.setDescription(desc);
		this.setName(name);
		this.setCode(code);
	}

	public String getConsigna() {
		return consigna;
	}

	public void setConsigna(String consigna) {
		this.consigna = consigna;
	}

	public Task(String name, String desc, String code, String consigna, ArrayList<ExtraInfo> extras)
	{
		this();
		this.setDescription(desc);
		this.setName(name);
		this.setCode(code);
		this.setConsigna(consigna);
		this.extras = extras;
	}

	public Task(String name, String desc, String code,
			Collection<Element> elem, Collection<Element> rightElem)
	{
		this(name, desc, code);
		this.setElements(elem);
		this.setValidElements(rightElem);
	}

	public Collection<Element> getElements()
	{
		return elements;
	}

	public void setElements(Collection<Element> elements)
	{
		this.elements = elements;
	}

	public boolean addElements(Element element)
	{
		return this.getElements().add(element);
	}

	public Collection<Element> getValidElements()
	{
		return validElements;
	}

	public void setValidElements(Collection<Element> rightElements)
	{
		this.validElements = rightElements;
	}

	public boolean addValidElements(Element element)
	{
		return this.getValidElements().add(element);
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

	public boolean elementsContain(String result)
	{
		for (Element each : this.getElements())
		{
			if (each.hasIdentification(result))
			{
				return true;
			}
		}
		return false;
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
		return this.getCode().equals(code);
	}

	public boolean validElementsContains(Element element)
	{
		return this.getValidElements().contains(element);
	}

	public String toString()
	{
		return this.getDescription();
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
			Task aux = (Task) o;
			return this.getName().equals(aux.getName())
					&& this.getDescription().equals(aux.getDescription());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = hash * 3 + this.getName().hashCode();
		hash = hash * 3 + this.getDescription().hashCode();
		hash = hash * 3 + this.getCode().hashCode();
		return hash;
	}

}
