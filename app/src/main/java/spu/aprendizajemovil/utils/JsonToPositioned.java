package spu.aprendizajemovil.utils;


import java.util.ArrayList;

import spu.aprendizajemovil.model.JasonModel.EActivityFromJson;
import spu.aprendizajemovil.model.activityLayer.Deposit;
import spu.aprendizajemovil.model.activityLayer.EducationalActivity;
import spu.aprendizajemovil.model.activityLayer.Element;
import spu.aprendizajemovil.model.activityLayer.Task;
import spu.aprendizajemovil.model.positionLayer.PositionedActivity;
import spu.aprendizajemovil.model.positionLayer.PositionedDeposit;
import spu.aprendizajemovil.model.positionLayer.PositionedElement;
import spu.aprendizajemovil.model.positionLayer.PositionedTask;

public class JsonToPositioned {
    ArrayList <PositionedDeposit> positionedDeposits;
    ArrayList <PositionedElement> positionedElements;
    ArrayList <PositionedTask> positionedTasks;
    PositionedActivity positionedActivity;

    public ArrayList<PositionedDeposit> getPositionedDeposits() {
        return positionedDeposits;
    }

    public ArrayList<PositionedElement> getPositionedElements() {
        return positionedElements;
    }

    public ArrayList<PositionedTask> getPositionedTasks() {
        return positionedTasks;
    }

    public PositionedActivity getPositionedActivity() {
        return positionedActivity;
    }

    public JsonToPositioned(EActivityFromJson jsonEA) {
        this.positionedDeposits = new ArrayList<PositionedDeposit>();
        for (Deposit depo : jsonEA.getTaskToDeposit()) {
            this.positionedDeposits.add(this.asPositionedDeposit(depo));
        }
        this.positionedElements = new ArrayList<PositionedElement>();
        for (Element elem : jsonEA.getElements()) {
            this.positionedElements.add(this.asPositionedElement(elem));
        }
        this.positionedTasks = new ArrayList<PositionedTask>();
        for (Task t : jsonEA.getTaskToRecolectDefinition()) {
            this.positionedTasks.add(this.asPostionedTask(t));
        }
        this.positionedActivity = this.asPositionedActivity(jsonEA.getEducationalActivity());
    }

    private PositionedDeposit asPositionedDeposit ( Deposit deposit){
        PositionedDeposit positionedDeposit = new PositionedDeposit(deposit, null);
        positionedDeposit.setDepositedElements(new ArrayList<PositionedElement>());
        return positionedDeposit;

    }

    private PositionedElement asPositionedElement(Element element){
        PositionedElement positionedElement = new PositionedElement(element, null);
        ArrayList <PositionedDeposit> positionedDeposits= new ArrayList<PositionedDeposit>();
        for (Deposit deposit: element.getDeposits()) {
            positionedDeposits.add(this.findPositionedDeposit(deposit));
        }
        positionedElement.setDeposits(positionedDeposits);
        return positionedElement;
    }

    private PositionedDeposit findPositionedDeposit (Deposit deposit){
        PositionedDeposit depAux = null;
        for (PositionedDeposit dep:this.positionedDeposits) {
            if(dep.getDeposit().equals(deposit)){
                depAux = dep;
            }
        }
        return depAux;
    }
    private PositionedTask asPostionedTask (Task task){
        PositionedTask positionedTask = new PositionedTask(task, null);
        ArrayList <PositionedElement> positionedElements = new ArrayList<PositionedElement>();
        for (Element elem: task.getElements()) {
            positionedElements.add(this.findPositionedElement(elem));
        }
        positionedTask.setAvailables(positionedElements);
        return positionedTask;
    }

    private PositionedElement findPositionedElement (Element element) {
        PositionedElement elemAux = null;
        for (PositionedElement elem:this.positionedElements) {
            if(elem.getElement().equals(element)){
                elemAux = elem;
            }
        }
        return elemAux;
    }

    private PositionedActivity asPositionedActivity (EducationalActivity EA){
        PositionedActivity posEA = new PositionedActivity(EA, null);
        ArrayList <PositionedTask> positionedTasks = new ArrayList<PositionedTask>();
        for (Task task: EA.getTasks()) {
            positionedTasks.add(this.findPositionedTask(task));
        }
        posEA.setPositionedTasks(positionedTasks);
        return posEA;
    }

    private PositionedTask findPositionedTask (Task task){
        PositionedTask taskAux = null;
        for (PositionedTask task1: this.positionedTasks) {
            if(task1.getTask().equals(task)){
                taskAux = task1;
            }
        }
        return taskAux;
    }
}
