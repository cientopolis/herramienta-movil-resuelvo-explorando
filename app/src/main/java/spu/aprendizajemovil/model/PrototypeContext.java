package spu.aprendizajemovil.model;

import java.io.Serializable;
import java.util.ArrayList;

import spu.aprendizajemovil.model.positionLayer.PositionedActivity;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;
import spu.aprendizajemovil.model.userLayer.User;

public class PrototypeContext implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1614506418712819435L;
	private PositionedActivity positionedActivity;
	private User user;
	private ArrayList<PositionedDeposit> positionedDeposits;
	private String currentPosition;
	private ArrayList<PositionedElement> positionedElements;
	private ArrayList<DepositEvaluator> depositEvaluators;
	private ArrayList<TaskEvaluator> taskEvaluators;

	public PrototypeContext()
	{
		this.setUser(new User("Usuario"));
		this.setCurrentPosition("goal");

		this.setPositionedDeposits(new ArrayList<PositionedDeposit>());
		this.setPositionedElements(new ArrayList<PositionedElement>());
		this.setDepositEvaluators(new ArrayList<DepositEvaluator>());
		this.setTaskEvaluators(new ArrayList<TaskEvaluator>());

		// Deposit depositoPapel = new Deposit("Cart?n/Papel", "Cart?n/Papel",
		// "depositoPapel");
		// PositionedDeposit positionedDepositoPapel = new
		// PositionedDeposit(depositoPapel,
		// null);
		// this.addPositionedDeposit(positionedDepositoPapel);
		//
		// Deposit depositoPlastico = new Deposit("Pl?stico", "Pl?stico",
		// "depositoPlastico");
		// PositionedDeposit positionedDepositoPlastico = new
		// PositionedDeposit(depositoPlastico,
		// null);
		// this.addPositionedDeposit(positionedDepositoPlastico);
		//
		// Deposit depositoMetal = new Deposit("Metal", "Metal",
		// "depositoMetal");
		// PositionedDeposit positionedDepositoMetal = new
		// PositionedDeposit(depositoMetal,
		// null);
		// this.addPositionedDeposit(positionedDepositoMetal);
		//
		// Element cajaDeCereal = new Element("Caja de Cereal",
		// "Caja de cart?n tipo cereales o similar", "cajaDeCereal");
		// cajaDeCereal.addDeposit(depositoPapel);
		// PositionedElement cajaDeCerealPositioned = new PositionedElement(
		// cajaDeCereal, null);
		// cajaDeCerealPositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(cajaDeCerealPositioned);
		//
		// Element hojaDeCuaderno = new Element("Hoja de Cuaderno",
		// "Hoja de cuaderno escolar usada -escrita-", "hojaDeCuaderno");
		// hojaDeCuaderno.addDeposit(depositoPapel);
		// PositionedElement hojaDeCuadernoPositioned = new PositionedElement(
		// hojaDeCuaderno, null);
		// hojaDeCuadernoPositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(hojaDeCuadernoPositioned);
		//
		// Element sachetDeLeche = new Element("Sachet de Leche",
		// "Sachet de leche", "sachetDeLeche");
		// sachetDeLeche.addDeposit(depositoPlastico);
		// PositionedElement sachetDeLechePositioned = new PositionedElement(
		// sachetDeLeche, null);
		// sachetDeLechePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(sachetDeLechePositioned);
		//
		// Element envaseDeYogurth = new Element("Envase de Yogurth",
		// "Envase de yogurth sin etiqueta", "envaseDeYogurth");
		// envaseDeYogurth.addDeposit(depositoPlastico);
		// PositionedElement envaseDeYogurthPositioned = new PositionedElement(
		// envaseDeYogurth, null);
		// envaseDeYogurthPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(envaseDeYogurthPositioned);
		//
		// Element tuboDeRolloDePapel = new Element("Tubo de Rollo de Papel",
		// "Rollo de papel cocina", "tuboDeRolloDePapel");
		// tuboDeRolloDePapel.addDeposit(depositoPapel);
		// PositionedElement tuboDeRolloDePapelPositioned = new
		// PositionedElement(
		// tuboDeRolloDePapel, null);
		// tuboDeRolloDePapelPositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(tuboDeRolloDePapelPositioned);
		//
		// Element envaseDeGaseosa = new Element("Envase de Gaseosa",
		// "Lata de aluminio de gaseosa", "envaseDeGaseosa");
		// envaseDeGaseosa.addDeposit(depositoMetal);
		// PositionedElement envaseDeGaseosaPositioned = new PositionedElement(
		// envaseDeGaseosa, null);
		// envaseDeGaseosaPositioned.addDeposit(positionedDepositoMetal);
		// this.addElement(envaseDeGaseosaPositioned);
		//
		// Element perchaGrande = new Element("Percha Grande",
		// "Percha de pl?stico", "perchaGrande");
		// perchaGrande.addDeposit(depositoPlastico);
		// PositionedElement perchaGrandePositioned = new PositionedElement(
		// perchaGrande, null);
		// perchaGrandePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(perchaGrandePositioned);
		//
		// Element vasoGrande = new Element("Vaso Grande", "Vaso de metal",
		// "vasoGrande");
		// vasoGrande.addDeposit(depositoMetal);
		// PositionedElement vasoGrandePositioned = new PositionedElement(
		// vasoGrande, null);
		// vasoGrandePositioned.addDeposit(positionedDepositoMetal);
		// this.addElement(vasoGrandePositioned);
		//
		// Element vasoChico = new Element("Vaso Chico",
		// "Vaso descartable chico",
		// "vasoChico");
		// vasoChico.addDeposit(depositoPlastico);
		// PositionedElement vasoChicoPositioned = new PositionedElement(
		// vasoChico, null);
		// vasoChicoPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(vasoChicoPositioned);
		//
		// Element cajaParaHuevos = new Element("Caja para Huevos",
		// "Caja contenedora de huevos por 6 unidades", "cajaParaHuevos");
		// cajaParaHuevos.addDeposit(depositoPapel);
		// PositionedElement cajaParaHuevosPositioned = new PositionedElement(
		// cajaParaHuevos, null);
		// cajaParaHuevosPositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(cajaParaHuevosPositioned);
		//
		// Element botellaDeGaseosa = new Element("Botella de Gaseosa",
		// "Botella de pl?stico de 600cm3 transparente",
		// "botellaDeGaseosa");
		// botellaDeGaseosa.addDeposit(depositoPlastico);
		// PositionedElement botellaDeGaseosaPositioned = new PositionedElement(
		// botellaDeGaseosa, null);
		// botellaDeGaseosaPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(botellaDeGaseosaPositioned);
		//
		// Element botellaDeLeche = new Element("Botella de Leche",
		// "Botella pl?stica de leche de 1 litro", "botellaDeLeche");
		// botellaDeLeche.addDeposit(depositoPlastico);
		// PositionedElement botellaDeLechePositioned = new PositionedElement(
		// botellaDeLeche, null);
		// botellaDeLechePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(botellaDeLechePositioned);
		//
		// Element cajaDeZapatos = new Element("Caja de Zapatos",
		// "Caja de cart?n de zapatos", "cajaDeZapatos");
		// cajaDeZapatos.addDeposit(depositoPapel);
		// PositionedElement cajaDeZapatosPositioned = new PositionedElement(
		// cajaDeZapatos, null);
		// cajaDeZapatosPositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(cajaDeZapatosPositioned);
		//
		// Element platoDescartable = new Element("Plato Descartable",
		// "Plato pl?stico mediano", "platoDescartable");
		// platoDescartable.addDeposit(depositoPlastico);
		// PositionedElement platoDescartablePositioned = new PositionedElement(
		// platoDescartable, null);
		// platoDescartablePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(platoDescartablePositioned);
		//
		// Element coladorDePasta = new Element("Colador de Pasta",
		// "Colador pl?stico mediano", "coladorDePasta");
		// coladorDePasta.addDeposit(depositoPlastico);
		// PositionedElement coladorDePastaPositioned = new PositionedElement(
		// coladorDePasta, null);
		// coladorDePastaPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(coladorDePastaPositioned);
		//
		// Element botellaChica = new Element("Botella Chica",
		// "Botella pl?stica de agua", "botellaChica");
		// botellaChica.addDeposit(depositoPlastico);
		// PositionedElement botellaChicaPositioned = new PositionedElement(
		// botellaChica, null);
		// botellaChicaPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(botellaChicaPositioned);
		//
		// Element rolloGrande = new Element("Rollo Grande",
		// "Rollo de cart?n de film", "rolloGrande");
		// rolloGrande.addDeposit(depositoPapel);
		// PositionedElement rolloGrandePositioned = new PositionedElement(
		// rolloGrande, null);
		// rolloGrandePositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(rolloGrandePositioned);
		//
		// Element rolloChico = new Element("Rollo Chico",
		// "Rollo de cart?n de papel higi?nico", "rolloChico");
		// rolloChico.addDeposit(depositoPapel);
		// PositionedElement rolloChicoPositioned = new PositionedElement(
		// rolloChico, null);
		// rolloChicoPositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(rolloChicoPositioned);
		//
		// Element sobreGrande = new Element("Sobre Grande",
		// "Sobre de papel madera", "sobreGrande");
		// sobreGrande.addDeposit(depositoPapel);
		// PositionedElement sobreGrandePositioned = new PositionedElement(
		// sobreGrande, null);
		// sobreGrandePositioned.addDeposit(positionedDepositoPapel);
		// this.addElement(sobreGrandePositioned);
		//
		// Element reglaGrande = new Element("Regla Grande",
		// "Regla de pl?stico",
		// "reglaGrande");
		// reglaGrande.addDeposit(depositoPlastico);
		// PositionedElement reglaGrandePositioned = new PositionedElement(
		// reglaGrande, null);
		// reglaGrandePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(reglaGrandePositioned);
		//
		// Element vasoMedidorGrande = new Element("Vaso Medidor Grande",
		// "Vaso pl?stico medidor de l?quidos y s?lidos",
		// "vasoMedidorGrande");
		// vasoMedidorGrande.addDeposit(depositoPlastico);
		// PositionedElement vasoMedidorGrandePositioned = new
		// PositionedElement(
		// vasoMedidorGrande, null);
		// vasoMedidorGrandePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(vasoMedidorGrandePositioned);
		//
		// Element transportador = new Element("Transportador",
		// "Transportador pl?stico que no tenga regla en cent?metros",
		// "transportador");
		// transportador.addDeposit(depositoPlastico);
		// PositionedElement transportadorPositioned = new PositionedElement(
		// transportador, null);
		// transportadorPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(transportadorPositioned);
		//
		// Element escuadra = new Element("Escuadra", "Escuadra pl?stica",
		// "escuadra");
		// escuadra.addDeposit(depositoPlastico);
		// PositionedElement escuadraPositioned = new
		// PositionedElement(escuadra,
		// null);
		// escuadraPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(escuadraPositioned);
		//
		// Element botellaGrande = new Element("Botella Grande",
		// "Botella grande de pl?stico de agua", "botellaGrande");
		// botellaGrande.addDeposit(depositoPlastico);
		// PositionedElement botellaGrandePositioned = new PositionedElement(
		// botellaGrande, null);
		// botellaGrandePositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(botellaGrandePositioned);
		//
		// Element botellaMedianaBlanca = new Element("Botella Mediana Blanca",
		// "Botella de pl?stico blanca", "botellaMedianaBlanca");
		// botellaMedianaBlanca.addDeposit(depositoPlastico);
		// PositionedElement botellaMedianaBlancaPositioned = new
		// PositionedElement(
		// botellaMedianaBlanca, null);
		// botellaMedianaBlancaPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(botellaMedianaBlancaPositioned);
		//
		// Element botellaMedianaRoja = new Element("Botella Mediana Roja",
		// "Botella de pl?stico roja", "botellaMedianaRoja");
		// botellaMedianaRoja.addDeposit(depositoPlastico);
		// PositionedElement botellaMedianaRojaPositioned = new
		// PositionedElement(
		// botellaMedianaRoja, null);
		// botellaMedianaRojaPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(botellaMedianaRojaPositioned);
		//
		// Element lataDeTomate = new Element("Lata de Tomates",
		// "Lata met?lica sin etiqueta", "lataDeTomate");
		// lataDeTomate.addDeposit(depositoMetal);
		// PositionedElement lataDeTomatePositioned = new PositionedElement(
		// lataDeTomate, null);
		// lataDeTomatePositioned.addDeposit(positionedDepositoMetal);
		// this.addElement(lataDeTomatePositioned);
		//
		// Element envasePostreChico = new Element("Envase de Postre Chico",
		// "Envase de Danonino rojo", "envaseDePostreChico");
		// envasePostreChico.addDeposit(depositoPlastico);
		// PositionedElement envasePostreChicoPositioned = new
		// PositionedElement(
		// envasePostreChico, null);
		// envasePostreChicoPositioned.addDeposit(positionedDepositoPlastico);
		// this.addElement(envasePostreChicoPositioned);
		//
		// //
		// // Task 1
		// //
		//
		// Task task1 = new Task(
		// "task1",
		// "Deber?n recolectar todos los elementos que se puedan usar para hacer papel reciclado.",
		// "task1");
		// PositionedTask positionedTask1 = new PositionedTask(task1, null);
		// task1.addElements(cajaDeCereal);
		// positionedTask1.addAvailable(cajaDeCerealPositioned);
		// task1.addValidElements(cajaDeCereal);
		//
		// task1.addElements(hojaDeCuaderno);
		// positionedTask1.addAvailable(hojaDeCuadernoPositioned);
		// task1.addValidElements(hojaDeCuaderno);
		//
		// task1.addElements(sachetDeLeche);
		// positionedTask1.addAvailable(sachetDeLechePositioned);
		//
		// task1.addElements(envaseDeYogurth);
		// positionedTask1.addAvailable(envaseDeYogurthPositioned);
		//
		// task1.addElements(tuboDeRolloDePapel);
		// positionedTask1.addAvailable(tuboDeRolloDePapelPositioned);
		// task1.addValidElements(tuboDeRolloDePapel);
		//
		// task1.addElements(envaseDeGaseosa);
		// positionedTask1.addAvailable(envaseDeGaseosaPositioned);
		//
		// task1.addElements(perchaGrande);
		// positionedTask1.addAvailable(perchaGrandePositioned);
		//
		// task1.addElements(vasoGrande);
		// positionedTask1.addAvailable(vasoGrandePositioned);
		//
		// task1.addElements(vasoChico);
		// positionedTask1.addAvailable(vasoChicoPositioned);
		//
		// task1.addElements(cajaParaHuevos);
		// positionedTask1.addAvailable(cajaParaHuevosPositioned);
		// task1.addValidElements(cajaParaHuevos);
		//
		// //
		// // Task 2
		// //
		//
		// Task task2 = new Task(
		// "task2",
		// "Deber?n recolectar todos los elementos que se puedan usar para envasar l?quidos.",
		// "task2");
		// PositionedTask positionedTask2 = new PositionedTask(task2, null);
		//
		// task2.addElements(botellaDeGaseosa);
		// positionedTask2.addAvailable(botellaDeGaseosaPositioned);
		// task2.addValidElements(botellaDeGaseosa);
		//
		// task2.addElements(botellaDeLeche);
		// positionedTask2.addAvailable(botellaDeLechePositioned);
		// task2.addValidElements(botellaDeLeche);
		//
		// task2.addElements(cajaDeZapatos);
		// positionedTask2.addAvailable(cajaDeZapatosPositioned);
		//
		// task2.addElements(platoDescartable);
		// positionedTask2.addAvailable(platoDescartablePositioned);
		//
		// task2.addElements(coladorDePasta);
		// positionedTask2.addAvailable(coladorDePastaPositioned);
		//
		// task2.addElements(envaseDeGaseosa);
		// positionedTask2.addAvailable(envaseDeGaseosaPositioned);
		// task2.addValidElements(envaseDeGaseosa);
		//
		// task2.addElements(botellaChica);
		// positionedTask2.addAvailable(botellaChicaPositioned);
		// task2.addValidElements(botellaChica);
		//
		// task2.addElements(rolloGrande);
		// positionedTask2.addAvailable(rolloGrandePositioned);
		//
		// task2.addElements(rolloChico);
		// positionedTask2.addAvailable(rolloChicoPositioned);
		//
		// task2.addElements(sobreGrande);
		// positionedTask2.addAvailable(sobreGrandePositioned);
		//
		// //
		// // Task 3
		// //
		//
		// Task task3 = new Task(
		// "task3",
		// "Deber?n recolectar todos los elementos que se puedan usar para medir distancia en cent?metros.",
		// "task3");
		// PositionedTask positionedTask3 = new PositionedTask(task3, null);
		//
		// task3.addElements(reglaGrande);
		// positionedTask3.addAvailable(reglaGrandePositioned);
		// task3.addValidElements(reglaGrande);
		//
		// task3.addElements(vasoMedidorGrande);
		// positionedTask3.addAvailable(vasoMedidorGrandePositioned);
		//
		// task3.addElements(transportador);
		// positionedTask3.addAvailable(transportadorPositioned);
		//
		// task3.addElements(vasoGrande);
		// positionedTask3.addAvailable(vasoGrandePositioned);
		//
		// task3.addElements(botellaDeGaseosa);
		// positionedTask3.addAvailable(botellaDeGaseosaPositioned);
		//
		// task3.addElements(escuadra);
		// positionedTask3.addAvailable(escuadraPositioned);
		// task3.addValidElements(escuadra);
		//
		// task3.addElements(botellaGrande);
		// positionedTask3.addAvailable(botellaGrandePositioned);
		//
		// task3.addElements(botellaMedianaBlanca);
		// positionedTask3.addAvailable(botellaMedianaBlancaPositioned);
		//
		// task3.addElements(botellaMedianaRoja);
		// positionedTask3.addAvailable(botellaMedianaRojaPositioned);
		//
		// task3.addElements(lataDeTomate);
		// positionedTask3.addAvailable(lataDeTomatePositioned);
		//
		// task3.addElements(envasePostreChico);
		// positionedTask3.addAvailable(envasePostreChicoPositioned);
		//
		// task3.addElements(vasoChico);
		// positionedTask3.addAvailable(vasoChicoPositioned);
		//
		// EducationalActivity educationalActivity = new EducationalActivity(
		// "?Aprendo Jugando!",
		// "El objetivo del juego es recolectar elementos de acuerdo a una tarea recibida, para luego depositarlos seg?n el tipo de material con el que est?n hechos.",
		// "E1");
		// PositionedActivity positionedActivity = new PositionedActivity(
		// educationalActivity, null);
		// educationalActivity.addTask(task1);
		// positionedActivity.addPositionedTasks(positionedTask1);
		// educationalActivity.addTask(task2);
		// positionedActivity.addPositionedTasks(positionedTask2);
		// educationalActivity.addTask(task3);
		// positionedActivity.addPositionedTasks(positionedTask3);
		//
		// this.setPositionedActivity(positionedActivity);
	}

	public PrototypeContext(PositionedActivity positionedActivity, ArrayList<PositionedDeposit> positionedDeposits, ArrayList<PositionedElement> positionedElements) {
		this();
		this.positionedActivity = positionedActivity;
		this.positionedDeposits = positionedDeposits;
		this.positionedElements = positionedElements;
	}

	public PositionedActivity getPositionedActivity()
	{
		return positionedActivity;
	}

	public void setPositionedActivity(PositionedActivity positionedActivity)
	{
		this.positionedActivity = positionedActivity;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public PositionedTask getUserCurrentTask()
	{
		return this.getUser().getCurrentTask();
	}

	public PositionedActivity getUserCurrentActivity()
	{
		return this.getUser().getCurrentActivity();
	}

	public ArrayList<PositionedDeposit> getPositionedDeposits()
	{
		return positionedDeposits;
	}

	public void setPositionedDeposits(ArrayList<PositionedDeposit> deposits)
	{
		this.positionedDeposits = deposits;
	}

	public boolean addPositionedDeposit(PositionedDeposit deposit)
	{
		return this.getPositionedDeposits().add(deposit);
	}

	public PositionedDeposit existsDeposit(String result)
	{
		// se deberia hacer
		for (PositionedDeposit each : this.getPositionedDeposits())
		{
			if (each.hasIdentification(result))
			{
				return each;
			}
		}
		return null;
	}

	public String getCurrentPosition()
	{
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition)
	{
		this.currentPosition = currentPosition;
	}

	public ArrayList<PositionedElement> getPositionedElements()
	{
		return positionedElements;
	}

	public void setPositionedElements(ArrayList<PositionedElement> elements)
	{
		this.positionedElements = elements;
	}

	public boolean addElement(PositionedElement element)
	{
		return this.getPositionedElements().add(element);
	}

	public PositionedElement hasElement(String code)
	{
		for (PositionedElement each : this.getPositionedElements())
		{
			if (each.hasIdentification(code))
			{
				return each;
			}
		}
		return null;
	}

	public ArrayList<DepositEvaluator> getDepositEvaluators()
	{
		return depositEvaluators;
	}

	public void setDepositEvaluators(
			ArrayList<DepositEvaluator> depositEvaluators)
	{
		this.depositEvaluators = depositEvaluators;
	}

	public ArrayList<TaskEvaluator> getTaskEvaluators()
	{
		return taskEvaluators;
	}

	public void setTaskEvaluators(ArrayList<TaskEvaluator> taskEvaluators)
	{
		this.taskEvaluators = taskEvaluators;
	}

	public boolean addDepositEvaluator(DepositEvaluator depositEvaluator)
	{
		return this.getDepositEvaluators().add(depositEvaluator);
	}

	public boolean addTaskEvaluator(TaskEvaluator taskEvaluator)
	{
		return this.getTaskEvaluators().add(taskEvaluator);
	}

	public TaskEvaluator getTaskEvaluator ( PositionedTask tarea){
		for (TaskEvaluator taskEvaluator:this.getTaskEvaluators()) {
			if(taskEvaluator.getTask().equals(tarea)){
				return taskEvaluator;
			}
		}
		return null;
	}

	public DepositEvaluator getDepositEvaluator(PositionedDeposit deposit)
	{
		// obtiene el DepositEvaluator de la coleccion, o lo crea (y lo retorna)
		// si no exista
		for (DepositEvaluator each : this.getDepositEvaluators())
		{
			if (each.getDeposit().equals(deposit))
			{
				return each;
			}
		}
		DepositEvaluator evaluator = new DepositEvaluator(deposit);
		this.addDepositEvaluator(evaluator);
		return evaluator;
	}

	public boolean evaluateDeposit(PositionedDeposit deposit,
			PositionedElement element)
	{
		DepositEvaluator evaluator = this.getDepositEvaluator(deposit);
		if (element.isDepositValid(deposit))
		{
			evaluator.addCorrectElement(element);
		} else
		{
			evaluator.addIncorrectElement(element);
		}
		return false;
	}

	public boolean evaluateTask(PositionedTask task, User user)
	{
		// el obj taskEvaluator se crea con los datos (elementos correctos e
		// incorrectos cargados)
		TaskEvaluator evaluator = new TaskEvaluator(task, user);
		return this.addTaskEvaluator(evaluator);
	}

}
