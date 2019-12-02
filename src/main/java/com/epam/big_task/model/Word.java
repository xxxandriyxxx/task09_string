package com.epam.big_task.model;

import java.util.Objects;

public class Word implements Comparable<Word> {

    private String value;

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Word{'" + value + '\'' +
                '}';
    }


    @Override
    public int compareTo(Word o) {
        return this.getValue().compareTo(o.getValue());
    }
}
