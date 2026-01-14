package model;

import java.util.List;

public class Content {

    List<String> chapters;

    public void add(int chapter, String contentOfChapter) {
        if (chapter > chapters.size()) {
            chapters.add(contentOfChapter);
        } else {
            chapters.set(chapter, contentOfChapter);
        }
    }

}
