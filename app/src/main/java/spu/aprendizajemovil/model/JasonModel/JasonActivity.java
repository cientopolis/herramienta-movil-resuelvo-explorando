package spu.aprendizajemovil.model.JasonModel;


import java.util.List;

public class JasonActivity {
    private String Goal;
    private List<String> taskToRecolect;
    private String Name;
    private String Code;

    public JasonActivity(String goal, List<String> taskToRecolect, String name, String code) {
        Goal = goal;
        this.taskToRecolect = taskToRecolect;
        Name = name;
        Code = code;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
    }

    public List<String> getTaskToRecolect() {
        return taskToRecolect;
    }

    public void setTaskToRecolect(List<String> taskToRecolect) {
        this.taskToRecolect = taskToRecolect;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
