package com.epam.big_task.controller;

import com.epam.big_task.model.Sentence;
import com.epam.big_task.model.Word;

import java.util.List;
import java.util.Map;

public interface Controller {

    List<Sentence> getSortedSentenceList();

    List<Word> getUniqueWords();

    Map<Sentence, Integer> getSortedByRepeating();

    Map<Sentence, List<Word>> getWordsOfQuestion(int length);

    void replaceLongestVowel();

    void printWordsByAlphabet();

    void deleteWordsStartConsonant(int length);
}
