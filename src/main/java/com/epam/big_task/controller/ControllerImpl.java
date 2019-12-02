package com.epam.big_task.controller;

import com.epam.big_task.model.BusinessLogic;
import com.epam.big_task.model.Model;
import com.epam.big_task.model.Sentence;
import com.epam.big_task.model.Word;

import java.util.List;
import java.util.Map;

public class ControllerImpl implements Controller {

    Model model;

    public ControllerImpl() {
        model = new BusinessLogic();
    }

    @Override
    public List<Sentence> getSortedSentenceList(List<Sentence> sentences) {
        return model.getSortedSentenceList(sentences);
    }

    @Override
    public List<Word> getUniqueWords(Sentence sentence, List<Sentence> otherSentences) {
        return model.getUniqueWords(sentence, otherSentences);
    }

    @Override
    public Map<Sentence, Integer> getSortedByRepeating(List<Sentence> sentences) {
        return model.getSortedByRepeating(sentences);
    }

    @Override
    public Map<Sentence, List<Word>> getWordsOfQuestion(List<Sentence> sentences, int length) {
        return model.getWordsOfQuestion(sentences, length);
    }

    @Override
    public void replaceLongestVowel(List<Sentence> sentences) {
        model.replaceLongestVowel(sentences);
    }

    @Override
    public void printWordsByAlphabet(List<Sentence> sentences) {
        model.printWordsByAlphabet(sentences);
    }

    @Override
    public void deleteWordsStartConsonant(List<Sentence> sentences, int length) {
        model.deleteWordsStartConsonant(sentences, length);
    }
}
