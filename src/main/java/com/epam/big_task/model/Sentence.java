package com.epam.big_task.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements Comparable<Sentence> {

    private String value;
    private List<Word> words;
    private List<Mark> marks;

    public Sentence(String value) {
        this.value = value.replaceAll("\\s+", " ").trim();
        setWords();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        setWords();
    }

    public List<Word> getWords() {
        return words;
    }

    private void setWords() {
        List<Word> words = new ArrayList<>();
        String[] words1 = value.split("\\s");
        for (String s : words1) {
            words.add(new Word(s.trim()));
        }
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
        return "Sentence{'" + value + '\'' +
                '}';
    }

    @Override
    public int compareTo(Sentence o) {
        if (this.getWords().size() > o.getWords().size()) {
            return 1;
        } else if (this.getWords().size() < o.getWords().size()) {
            return -1;
        }
        return 0;
    }
}
