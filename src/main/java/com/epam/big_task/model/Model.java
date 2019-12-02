package com.epam.big_task.model;

import java.util.List;
import java.util.Map;

public interface Model {

    List<Sentence> getSortedSentenceList(List<Sentence> sentences);

    List<Word> getUniqueWords(Sentence sentence, List<Sentence> otherSentences);

    Map<Sentence, Integer> getSortedByRepeating(List<Sentence> sentences);

    Map<Sentence, List<Word>> getWordsOfQuestion(List<Sentence> sentences, int length);

    void replaceLongestVowel(List<Sentence> sentences);

    void printWordsByAlphabet(List<Sentence> sentences);

    void deleteWordsStartConsonant(List<Sentence> sentences, int length);
}
