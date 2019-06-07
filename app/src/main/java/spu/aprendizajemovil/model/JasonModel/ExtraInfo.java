package spu.aprendizajemovil.model.JasonModel;

import java.io.Serializable;

public class ExtraInfo implements Serializable {
    public String title;
    public String content;

    public ExtraInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
