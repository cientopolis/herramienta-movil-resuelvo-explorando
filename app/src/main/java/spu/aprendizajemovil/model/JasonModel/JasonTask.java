package spu.aprendizajemovil.model.JasonModel;


import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.activityLayer.Element;
import spu.aprendizajemovil.model.activityLayer.Task;

public class JasonTask {
    private ArrayList<String> elements;
    private ArrayList<String> validElements;
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

    public String getConsigna() {
        return consigna;
    }

    public void setConsigna(String consigna) {
        this.consigna = consigna;
    }

    public JasonTask(ArrayList<String> elements, ArrayList<String> validElements, ArrayList <ExtraInfo> extras, String description, String name, String code, String consigna) {
        this.elements = elements;
        this.validElements = validElements;
        this.description = description;
        this.consigna = consigna;
        this.extras = extras;

        this.name = name;
        this.code = code;
    }

    public ArrayList<String> getElements() {
        return elements;
    }

    public void setElements(ArrayList<String> elements) {
        this.elements = elements;
    }

    public ArrayList<String> getValidElements() {
        return validElements;
    }

    public void setValidElements(ArrayList<String> validElements) {
        this.validElements = validElements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
