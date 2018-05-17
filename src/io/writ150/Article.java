package io.writ150;
/*
 * Created by david on 4/1/2018.
 * Copyright ISOTOPE Studio
 */


import java.util.List;

class Article {
    private final int year;
    private final List<String> text;
    private final String type;

    public Article(int year, List<String> text, String type) {
        this.year = year;
        this.text = text;
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public List<String> getText() {
        return text;
    }

    public String getType() {
        return type;
    }
}