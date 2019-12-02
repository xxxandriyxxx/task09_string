package com.epam.big_task.model;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessLogic {

    public List<Sentence> getSortedSentenceList(List<Sentence> sentences) {
        List<Sentence> sortedList = new ArrayList<>(sentences);
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<Word> getUniqueWords(Sentence sentence, List<Sentence> otherSentences) {
        List<Word> uniqueWords = new ArrayList<>();
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

    public Map<Sentence, Integer> getSortedByRepeating(List<Sentence> sentences) {
        List<Map<Word, Long>> numberOfWords = new ArrayList<>();
        for (Sentence s : sentences) {
            Map<Word, Long> map = s.getWords().stream()
                    .collect(Collectors.groupingBy(value -> value, Collectors.counting()));
            numberOfWords.add(map);
        }
        Map<Sentence, Integer> sortedByRepeating = new HashMap<>();
        int counter = 0;
        int sentenceCounter = -1;
        for (Map m : numberOfWords) {
//            System.out.println("----");
            counter = 0;
            sentenceCounter++;
            Iterator<Map.Entry<Word, Long>> iterator = m.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Word, Long> entry = iterator.next();
                if (entry.getValue() > 1) {
//                    System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
                    counter += entry.getValue();
                }
            }
            sortedByRepeating.put(sentences.get(sentenceCounter), counter);
//            System.out.println("couter = " + counter);
        }
        sortedByRepeating = sortedByRepeating.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedByRepeating;
    }
    

    public List<Sentence> replaceLongestVowel(List<Sentence> sentences) {
        String longestWord;
        String vowel;
        int indexV;
        int indexL;
        for (Sentence s : sentences) {
            longestWord = "";
            vowel = "";
            indexV = -1;
            indexL = -1;
            for (Word w : s.getWords()) {
                if (w.getValue().matches("[aeiouyAEIOUY].*")) {
                    System.out.println("+++ wovel " + w);
                    vowel = w.getValue();
                    indexV = s.getWords().indexOf(w);
                    for (Word w2 : s.getWords()) {
                        if (w2.getValue().length() > longestWord.length()) {
                            longestWord = w2.getValue();
                            indexL = s.getWords().indexOf(w2);
                            System.out.println("+++ longest " + w2);
                        }
                    }
                    break;
                }
            }
            if (indexV >= 0 && indexL >= 0) {
                s.getWords().set(indexV, new Word(longestWord));
                s.getWords().set(indexL, new Word(vowel));
                StringBuilder sb = new StringBuilder();
                for (Word w : s.getWords()) {
                    sb.append(" ").append(w.getValue());
                }
                s.setValue(sb.toString().trim());
            }
        }
        return sentences;
    }
}




