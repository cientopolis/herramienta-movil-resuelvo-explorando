package spu.aprendizajemovil.model.JasonModel;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import spu.aprendizajemovil.model.activityLayer.Deposit;
import spu.aprendizajemovil.model.activityLayer.EducationalActivity;
import spu.aprendizajemovil.model.activityLayer.Element;
import spu.aprendizajemovil.model.activityLayer.Task;

public class EActivityFromJson {

    String languaje;
    JasonActivity educationalActivity;
    List <JasonTask> taskToRecolectDefinition;
    List <Deposit> taskToDeposit;
    List<JasonElement> elements;

    public EActivityFromJson(String path){

    }

    public EActivityFromJson(String languaje, JasonActivity educationalActivity, List<JasonTask> taskToRecolectDefinition, List<Deposit> taskToDeposit, List<JasonElement> elements) {
        this.languaje = languaje;
        this.educationalActivity = educationalActivity;
        this.taskToRecolectDefinition = taskToRecolectDefinition;
        this.taskToDeposit = taskToDeposit;
        this.elements = elements;
    }

    public EducationalActivity getEducationalActivity() {
        EducationalActivity newEA = new EducationalActivity( this.educationalActivity.getName(), this.educationalActivity.getGoal(), this.educationalActivity.getCode());
        List <String> EA = this.educationalActivity.getTaskToRecolect();
        for (String taskCode: EA) {
            newEA.getTasks().add(this.getTaskByCode(taskCode));

        }

        return newEA;
    }

    public void setEducationalActivity(JasonActivity educationalActivity) {
        this.educationalActivity = educationalActivity;
    }

    public ArrayList<Task> getTaskToRecolectDefinition() {
        ArrayList <Task> newTasks = new ArrayList<Task>();
        for (JasonTask jTask: this.taskToRecolectDefinition) {
            Task tAux = new Task( jTask.getName(),jTask.getDescription(), jTask.getCode(),jTask.getConsigna(),jTask.getExtras());
            Collection <String> validElements =  jTask.getValidElements();
            Collection <String> elements = jTask.getElements();
            for (String eachValidElements : validElements) {
                tAux.getValidElements().add(this.getElementByCode(eachValidElements));
            }
            for (String eachElements : elements) {
                tAux.getElements().add(this.getElementByCode( eachElements));
            }
            newTasks.add(tAux);
        }
        return newTasks;
    }

    public void setTaskToRecolectDefinition(List<JasonTask> taskToRecolectDefinition) {
        this.taskToRecolectDefinition = taskToRecolectDefinition;
    }

    public List<Deposit> getTaskToDeposit() {
        return taskToDeposit;
    }

    public void setTaskToDeposit(List<Deposit> taskToDeposit) {
        this.taskToDeposit = taskToDeposit;
    }

    public ArrayList<Element> getElements() {
        ArrayList<Element> newElements = new ArrayList<Element>();
        for (JasonElement jElem : this.elements) {
            Element eAux = new Element(jElem.getName(),jElem.getDescription(),jElem.getCode());
            Collection<String> depositsCodes = jElem.getDeposits();
            for (String eachDep : depositsCodes) {
                eAux.getDeposits().add(this.getDepositByCode(eachDep));
            }
            newElements.add(eAux);
        }
        return newElements;
    }

    private Deposit getDepositByCode (String code){
        Deposit depoAux = null;
        for (Deposit depo : this.taskToDeposit) {
            if (depo.getCode().equals(code)){
                depoAux=depo;
            }
        }
        return depoAux;
    }
    private Element getElementByCode (String code){
        Element elemAux = null;
        ArrayList <Element> miListaDeElementos = this.getElements();
        for (Element elem : miListaDeElementos) {
            if (elem.getCode().equals(code)){
                elemAux = elem;
            }
        }
        return elemAux;
    }
    private Task getTaskByCode (String code){
        Task taskAux = null;
        ArrayList <Task> miListaDeTareas = this.getTaskToRecolectDefinition();
        for (Task task : miListaDeTareas) {
            if (task.getCode().equals(code)){
                taskAux = task;
            }
        }
        return taskAux;
    }

    public void setElements(List<JasonElement> elements) {
        this.elements = elements;
    }

    public String getLanguaje() {
        return languaje;
    }

    public void setLanguaje(String languaje) {
        this.languaje = languaje;
    }

}
