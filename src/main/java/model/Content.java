package model;

import java.util.List;

public class Content {

    String chapters;

    public Content(String chapters) {
        this.chapters = chapters;
    }

    public void add(String newText) {
        this.chapters = chapters + newText;
    }


}
