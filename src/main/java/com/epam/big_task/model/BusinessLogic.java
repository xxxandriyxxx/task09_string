package com.epam.big_task.model;

import java.util.*;

public class BusinessLogic {

    public List<Sentence> getSortedSentenceList(List<Sentence> sentences) {
        List<Sentence> sortedList = new ArrayList<>(sentences);
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<Word> getUniqueWords(Sentence sentence, List<Sentence> otherSentences) {
        List<Word> uniqueWords = new ArrayList<Word>();
        boolean found = false;
        for (Word wu : sentence.getWords()) {
            for (Sentence s : otherSentences) {
                if (!found) {
                    for (Word w : s.getWords()) {
                        if (w.equals(wu)) {
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (!found) {
                uniqueWords.add(wu);
            } else {
                found = false;
            }
        }
        return uniqueWords;
    }




}




