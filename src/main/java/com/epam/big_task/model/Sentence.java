package com.epam.big_task.model;

import java.util.List;
import java.util.Objects;

public class Sentence {

    private String value;
    private List<Word> words;
    private List<Mark> marks;

    public Sentence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(value, sentence.value) &&
                Objects.equals(words, sentence.words) &&
                Objects.equals(marks, sentence.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, words, marks);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "value='" + value + '\'' +
                ", words=" + words +
                ", marks=" + marks +
                '}';
    }
}