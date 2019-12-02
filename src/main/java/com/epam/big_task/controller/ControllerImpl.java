package com.epam.big_task.controller;

import com.epam.big_task.model.BusinessLogic;
import com.epam.big_task.model.Model;
import com.epam.big_task.model.Sentence;
import com.epam.big_task.model.Word;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ControllerImpl implements Controller {

    private Model model;
    private List<Sentence> sentences;

    public ControllerImpl() throws IOException {
        model = new BusinessLogic();
        readFile();
    }

    private void readFile() throws IOException {
        FileReader fr = new FileReader("src/main/resources/Spring_Web_MVC.txt");
        Scanner scan = new Scanner(fr);

        sentences = new ArrayList<>();
        while (scan.hasNextLine()) {
            String sentence = scan.nextLine();
            if (!sentence.trim().equals("")) {
                sentences.add(new Sentence(sentence));
            }
        }
        fr.close();
    }

    @Override
    public List<Sentence> getSortedSentenceList() {
        return model.getSortedSentenceList(sentences);
    }

    @Override
    public List<Word> getUniqueWords() {
        List<Sentence> sentForSearch = new ArrayList<>(sentences);
        Sentence sentence1 = sentForSearch.remove(0);
        return model.getUniqueWords(sentence1, sentForSearch);
    }

    @Override
    public Map<Sentence, Integer> getSortedByRepeating() {
        return model.getSortedByRepeating(sentences);
    }

    @Override
    public Map<Sentence, List<Word>> getWordsOfQuestion(int length) {
        return model.getWordsOfQuestion(sentences, length);
    }

    @Override
    public void replaceLongestVowel() {
        model.replaceLongestVowel(sentences);
    }

    @Override
    public void printWordsByAlphabet() {
        model.printWordsByAlphabet(sentences);
    }

    @Override
    public void deleteWordsStartConsonant(int length) {
        model.deleteWordsStartConsonant(sentences, length);
    }

    @Override
    public List<Sentence> getSentences() {
        return sentences;
    }
}
