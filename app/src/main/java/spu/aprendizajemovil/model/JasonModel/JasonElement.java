package spu.aprendizajemovil.model.JasonModel;


import java.util.ArrayList;
import java.util.Collection;

import spu.aprendizajemovil.model.activityLayer.Deposit;
import spu.aprendizajemovil.model.activityLayer.Element;

public class JasonElement {
    private String Name;
    private String Description;
    private Collection<String> Deposits;
    private String Code;

    public JasonElement(String name, String description, Collection<String> deposits, String code) {
        Name = name;
        Description = description;
        Deposits = deposits;
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Collection<String> getDeposits() {
        return Deposits;
    }

    public void setDeposits(Collection<String> deposits) {
        Deposits = deposits;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
