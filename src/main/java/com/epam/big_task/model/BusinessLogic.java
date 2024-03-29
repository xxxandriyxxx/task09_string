package com.epam.big_task.model;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessLogic implements Model {

    @Override
    public List<Sentence> getSortedSentenceList(List<Sentence> sentences) {
        List<Sentence> sortedList = new ArrayList<>(sentences);
        Collections.sort(sortedList);
        return sortedList;
    }

    @Override
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

    @Override
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

    @Override
    public Map<Sentence, List<Word>> getWordsOfQuestion(List<Sentence> sentences, int length) {
        Map<Sentence, List<Word>> wordMap = new HashMap<>();
        List<Word> words = new ArrayList<>();
        for (Sentence s : sentences) {
            if (s.getValue().matches(".+\\?")) {
                words.clear();
                for (Word w : s.getWords()) {
                    if (w.getValue().length() == length) {
                        words.add(w);
                    }
                }
                words = words.stream()
                        .distinct()
                        .collect(Collectors.toList());
                wordMap.put(s, words);
            }
        }
        return wordMap;
    }

    @Override
    public void replaceLongestVowel(List<Sentence> sentences) {
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
    }

    @Override
    public void printWordsByAlphabet(List<Sentence> sentences) {
        List<Word> allWords = new ArrayList<>();
        char lastChar = 'a';
        for (Sentence s : sentences) {
            allWords.addAll(s.getWords());
        }

        allWords = allWords.stream()
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(allWords);

        for (Word w : allWords) {
            if (w.getValue().charAt(0) != lastChar) {
                System.out.print("\n");
            }
            System.out.print(w.getValue() + " ");
            lastChar = w.getValue().charAt(0);
        }
    }

    @Override
    public void deleteWordsStartConsonant(List<Sentence> sentences, int length) {
        for (Sentence s : sentences) {
            for (Word w : new ArrayList<>(s.getWords())) {
                if ((w.getValue().length() == length) && (w.getValue().matches("^[^aeiouyAEIOUY].*"))) {
                    s.getWords().remove(w);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (Word w : s.getWords()) {
                sb.append(" ").append(w.getValue());
            }
            s.setValue(sb.toString().trim());
        }
    }

}




