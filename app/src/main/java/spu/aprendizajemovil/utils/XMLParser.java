package spu.aprendizajemovil.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import spu.aprendizajemovil.model.PrototypeContext;
import spu.aprendizajemovil.model.activityLayer.Deposit;
import spu.aprendizajemovil.model.activityLayer.EducationalActivity;
import spu.aprendizajemovil.model.activityLayer.Element;
import spu.aprendizajemovil.model.activityLayer.Task;
import spu.aprendizajemovil.model.positionLayer.PositionedActivity;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import android.app.Activity;
import android.util.Log;

public class XMLParser
{
	static final String KEY_ROOT = "PrototypeData";

	static final String KEY_NAME = "Name";
	static final String KEY_DESCRIPTION = "Description";
	static final String KEY_CODE = "Code";

	static final String KEY_EDUCATIONAL_ACTIVITIES = "EducationalActivities";
	static final String KEY_EDUCATIONAL_ACTIVITY = "EducationalActivity";
	static final String KEY_EDUCATIONAL_ACTIVITY_GOAL = "Goal";

	static final String KEY_ELEMENTS = "Elements";
	static final String KEY_ELEMENT = "Element";

	static final String KEY_DEPOSITS = "Deposits";
	static final String KEY_DEPOSIT = "Deposit";

	static final String KEY_TASKS = "Tasks";
	static final String KEY_TASK = "Task";
	static final String KEY_TASK_VALID_ELEMENTS = "ValidElements";

	public boolean parse(Activity act, PrototypeContext context)
	{
		HashMap<String, EducationalActivity> educationalActivities = new HashMap<String, EducationalActivity>();
		HashMap<String, PositionedActivity> positionedActivities = new HashMap<String, PositionedActivity>();

		HashMap<String, Task> tasks = new HashMap<String, Task>();
		HashMap<String, PositionedTask> positionedTasks = new HashMap<String, PositionedTask>();

		HashMap<String, Element> elements = new HashMap<String, Element>();
		HashMap<String, PositionedElement> positionedElements = new HashMap<String, PositionedElement>();

		HashMap<String, Deposit> deposits = new HashMap<String, Deposit>();
		HashMap<String, PositionedDeposit> positionedDeposits = new HashMap<String, PositionedDeposit>();

		Document doc = this.getFileAsDOMDocument("PrototypeData.xml", act);
		NodeList parent = null;

		// carga de Depositos
		parent = doc.getElementsByTagName(KEY_DEPOSITS);
		for (int i = 0; i < parent.getLength(); i++)
		{
			org.w3c.dom.Element elementDeposits = (org.w3c.dom.Element) parent
					.item(i);
			if (elementDeposits.getParentNode().getNodeName().equals(KEY_ROOT))
			{

				NodeList nodeDeposit = elementDeposits
						.getElementsByTagName(KEY_DEPOSIT);
				for (int u = 0; u < nodeDeposit.getLength(); u++)
				{
					org.w3c.dom.Element elementDeposit = (org.w3c.dom.Element) nodeDeposit
							.item(u);

					// nl = elementDeposit.getElementsByTagName(KEY_DEPOSIT);
					// elementDeposit = (org.w3c.dom.Element) nl.item(0);
					String name = this.getValue(elementDeposit, KEY_NAME);
					String description = this.getValue(elementDeposit,
							KEY_DESCRIPTION);
					String code = this.getValue(elementDeposit, KEY_CODE);
					Deposit aux = new Deposit(name, description, code);
					deposits.put(code, aux);
					positionedDeposits.put(code, new PositionedDeposit(aux,
							null));
				}
			}
		}

		// Carga de elementos
		parent = doc.getElementsByTagName(KEY_ELEMENTS);
		for (int i = 0; i < parent.getLength(); i++)
		{
			org.w3c.dom.Element elementElements = (org.w3c.dom.Element) parent
					.item(i);
			// si se trata de la lista de elementos, y no de la lista de
			// elementos para cada tarea
			if (elementElements.getParentNode().getNodeName().equals(KEY_ROOT))
			{

				NodeList nodeElement = elementElements
						.getElementsByTagName(KEY_ELEMENT);
				for (int u = 0; u < nodeElement.getLength(); u++)
				{
					org.w3c.dom.Element elementElement = (org.w3c.dom.Element) nodeElement
							.item(u);

					String name = this.getValue(elementElement, KEY_NAME);
					String description = this.getValue(elementElement,
							KEY_DESCRIPTION);
					String code = this.getValue(elementElement, KEY_CODE);
					Element elementAux = new Element(name, description, code);
					PositionedElement positionedElementAux = new PositionedElement(
							elementAux, null);

					// carga de los depositos vlidos de cada elemento
					NodeList depositsParent = elementElement
							.getElementsByTagName(KEY_DEPOSITS);
					if (depositsParent.getLength() > 0)
					{
						org.w3c.dom.Element elementDepositsParent = (org.w3c.dom.Element) depositsParent
								.item(0);
						NodeList nodeDeposits = elementDepositsParent
								.getElementsByTagName(KEY_DEPOSIT);
						for (int y = 0; y < nodeDeposits.getLength(); y++)
						{
							org.w3c.dom.Element elementDeposit = (org.w3c.dom.Element) nodeDeposits
									.item(y);
							String depositCode = this
									.getElementValue(elementDeposit);
							Deposit deposit = deposits.get(depositCode);
							PositionedDeposit positionedDeposit = positionedDeposits
									.get(depositCode);

							// agrego los depositos al elemento y al
							// PositionedElement correspondiente
							elementAux.addDeposit(deposit);
							positionedElementAux.addDeposit(positionedDeposit);
						}
					}

					elements.put(code, elementAux);
					positionedElements.put(code, positionedElementAux);
				}
			}
		}

		// Carga de tareas
		parent = doc.getElementsByTagName(KEY_TASKS);
		for (int i = 0; i < parent.getLength(); i++)
		{
			org.w3c.dom.Element elementTasks = (org.w3c.dom.Element) parent
					.item(i);
			// si se trata de la lista de tareas, y no de la lista de tareas de
			// la actividad
			if (elementTasks.getParentNode().getNodeName().equals(KEY_ROOT))
			{

				NodeList nodeTask = elementTasks.getElementsByTagName(KEY_TASK);
				for (int u = 0; u < nodeTask.getLength(); u++)
				{
					org.w3c.dom.Element elementTask = (org.w3c.dom.Element) nodeTask
							.item(u);

					String name = this.getValue(elementTask, KEY_NAME);
					String description = this.getValue(elementTask,
							KEY_DESCRIPTION);
					String code = this.getValue(elementTask, KEY_CODE);
					Task taskAux = new Task(name, description, code);
					PositionedTask positionedTaskAux = new PositionedTask(
							taskAux, null);

					// carga de los elementos vlidos de cada tarea
					NodeList nodeValidElementsParent = elementTask
							.getElementsByTagName(KEY_TASK_VALID_ELEMENTS);
					if (nodeValidElementsParent.getLength() > 0)
					{
						org.w3c.dom.Element elementValidElementsParent = (org.w3c.dom.Element) nodeValidElementsParent
								.item(0);
						NodeList nodeElements = elementValidElementsParent
								.getElementsByTagName(KEY_ELEMENT);
						for (int y = 0; y < nodeElements.getLength(); y++)
						{
							org.w3c.dom.Element elementElement = (org.w3c.dom.Element) nodeElements
									.item(y);
							String elementCode = this
									.getElementValue(elementElement);
							Element element = elements.get(elementCode);
							// agrego los elementos validos a la tarea
							taskAux.addValidElements(element);
						}
					}

					// carga de los elementos disponibles de cada tarea
					NodeList nodeElementsParent = elementTask
							.getElementsByTagName(KEY_ELEMENTS);
					if (nodeElementsParent.getLength() > 0)
					{
						org.w3c.dom.Element elementElementsParent = (org.w3c.dom.Element) nodeElementsParent
								.item(0);
						NodeList nodeElements = elementElementsParent
								.getElementsByTagName(KEY_ELEMENT);
						for (int y = 0; y < nodeElements.getLength(); y++)
						{
							org.w3c.dom.Element elementElement = (org.w3c.dom.Element) nodeElements
									.item(y);
							String elementCode = this
									.getElementValue(elementElement);
							Element element = elements.get(elementCode);
							PositionedElement positionedElement = positionedElements
									.get(elementCode);
							// agrego los elementos disponibles a la tarea y a
							// la
							// PositionedTask correspondiente
							taskAux.addElements(element);
							positionedTaskAux.addAvailable(positionedElement);
						}
					}

					tasks.put(code, taskAux);
					positionedTasks.put(code, positionedTaskAux);
				}
			}
		}

		// Carga de las actividades (de momento hay una sola, pero queda hecho
		// por las dudas)

		parent = doc.getElementsByTagName(KEY_EDUCATIONAL_ACTIVITIES);
		for (int i = 0; i < parent.getLength(); i++)
		{
			org.w3c.dom.Element elementActivities = (org.w3c.dom.Element) parent
					.item(i);
			// si se trata de la lista de actividades (de momento no va a haber
			// objetos que contengan actividades, queda hecho por si se quiere
			// modificarlo)
			if (elementActivities.getParentNode().getNodeName()
					.equals(KEY_ROOT))
			{

				
				
				
				NodeList nodeActivity = elementActivities
						.getElementsByTagName(KEY_EDUCATIONAL_ACTIVITY);
				for (int u = 0; u < nodeActivity.getLength(); u++)
				{
					org.w3c.dom.Element elementActivity = (org.w3c.dom.Element) nodeActivity
							.item(u);

					String name = this.getValue(elementActivity, KEY_NAME);
					String goal = this.getValue(elementActivity,
							KEY_EDUCATIONAL_ACTIVITY_GOAL);
					String code = this.getValue(elementActivity, KEY_CODE);
					EducationalActivity activityAux = new EducationalActivity(
							name, goal, code);
					PositionedActivity positionedActivityAux = new PositionedActivity(
							activityAux, null);

					
					
					
//					// carga de los depositos vlidos de cada elemento
//					NodeList depositsParent = elementElement
//							.getElementsByTagName(KEY_DEPOSITS);
//					if (depositsParent.getLength() > 0)
//					{
//						org.w3c.dom.Element elementDepositsParent = (org.w3c.dom.Element) depositsParent
//								.item(0);
//						NodeList nodeDeposits = elementDepositsParent
//								.getElementsByTagName(KEY_DEPOSIT);
//						for (int y = 0; y < nodeDeposits.getLength(); y++)
//						{
//							org.w3c.dom.Element elementDeposit = (org.w3c.dom.Element) nodeDeposits
//									.item(y);
//							String depositCode = this.getElementValue(elementDeposit);
					
					// carga de los tareas de cada actividad
					NodeList nodeTasksParent = elementActivity
							.getElementsByTagName(KEY_TASKS);
					if (nodeTasksParent.getLength() > 0)
					{
						org.w3c.dom.Element elementTaskParent = (org.w3c.dom.Element) nodeTasksParent
								.item(0);
						NodeList nodeTasks = elementTaskParent
								.getElementsByTagName(KEY_TASK);
						
						for (int y = 0; y < nodeTasks.getLength(); y++)
						{
							org.w3c.dom.Element elementTask = (org.w3c.dom.Element) nodeTasks
									.item(y);
							String taskCode = this.getElementValue(elementTask);
							Task task = tasks.get(taskCode);
							PositionedTask positionedTask = positionedTasks
									.get(taskCode);
	
							// agrego las tareas a la actividad y a su
							// PositionedActivity correspondiente
							activityAux.addTask(task);
							positionedActivityAux
									.addPositionedTasks(positionedTask);
						}
					}

					educationalActivities.put(code, activityAux);
					positionedActivities.put(code, positionedActivityAux);
				}
			}
		}

		// seteo los datos de PrototypeContext
		context.setPositionedElements(new ArrayList<PositionedElement>(
				positionedElements.values()));

		context.setPositionedDeposits(new ArrayList<PositionedDeposit>(
				positionedDeposits.values()));

		// si se agregan actividades al XML, hay que modificar esto
		PositionedActivity activity = positionedActivities.values().iterator()
				.next();
		context.setPositionedActivity(activity);

		return true;
	}

	public String getValue(org.w3c.dom.Element item, String str)
	{
		NodeList n = item.getElementsByTagName(str);
		return this.getElementValue(n.item(0));
	}

	public final String getElementValue(Node elem)
	{
		Node child;
		if (elem != null)
		{
			if (elem.hasChildNodes())
			{
				for (child = elem.getFirstChild(); child != null; child = child
						.getNextSibling())
				{
					if (child.getNodeType() == Node.TEXT_NODE)
					{
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}

	public String readFromfile(String fileName, Activity a)
	{
		StringBuilder returnString = new StringBuilder();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try
		{
			fIn = a.getAssets().open(fileName);
			isr = new InputStreamReader(fIn, "iso-8859-1");
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null)
			{
				returnString.append(line);
			}
		} catch (Exception e)
		{
			e.getMessage();
		} finally
		{
			try
			{
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2)
			{
				e2.getMessage();
			}
		}
		return returnString.toString();
	}

	public Document getFileAsDOMDocument(String fileName, Activity a)
	{
		String result = this.readFromfile(fileName, a);

		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(result));
			doc = db.parse(is);

		} catch (ParserConfigurationException e)
		{
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (SAXException e)
		{
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (IOException e)
		{
			Log.e("Error: ", e.getMessage());
			return null;
		}
		// return DOM
		return doc;
	}

}
