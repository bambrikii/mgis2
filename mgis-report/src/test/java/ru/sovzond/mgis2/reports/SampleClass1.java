package ru.sovzond.mgis2.reports;

import java.util.ArrayList;
import java.util.List;

public class SampleClass1 {
    private int id;
    private String name;
    private String comment;
    private List<SampleClass2> list = new ArrayList<SampleClass2>();


    public SampleClass1(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<SampleClass2> getList() {
        return list;
    }

    public void setList(List<SampleClass2> list) {
        this.list = list;
    }

}
