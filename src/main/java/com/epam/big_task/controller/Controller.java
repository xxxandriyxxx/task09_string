package com.epam.big_task.controller;

import com.epam.big_task.model.Sentence;
import com.epam.big_task.model.Word;

import java.util.List;
import java.util.Map;

public interface Controller {

    List<Sentence> getSortedSentenceList(List<Sentence> sentences);

    List<Word> getUniqueWords(Sentence sentence, List<Sentence> otherSentences);

    Map<Sentence, Integer> getSortedByRepeating(List<Sentence> sentences);

    Map<Sentence, List<Word>> getWordsOfQuestion(List<Sentence> sentences, int length);

    List<Sentence> replaceLongestVowel(List<Sentence> sentences);

    void printWordsByAlphabet(List<Sentence> sentences);

    List<Sentence> deleteWordsStartConsonant(List<Sentence> sentences, int length);
}
