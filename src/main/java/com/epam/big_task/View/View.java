package com.epam.big_task.View;

import com.epam.big_task.controller.Controller;
import com.epam.big_task.controller.ControllerImpl;
import com.epam.big_task.model.Sentence;
import com.epam.big_task.model.Word;

import java.io.IOException;
import java.util.*;

public class View {
    private Controller controller;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);
    private Locale locale;
    private ResourceBundle bundle;

    public View() throws IOException {
        controller = new ControllerImpl();
        locale = new Locale("uk");
        bundle = ResourceBundle.getBundle("MyMenu",locale);
        setMenu();
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::internationalizeMenuUkr);
        methodsMenu.put("2", this::internationalizeMenuEn);
        methodsMenu.put("3", this::pressButton3);
        methodsMenu.put("4", this::pressButton4);
        methodsMenu.put("5", this::pressButton5);
        methodsMenu.put("6", this::pressButton6);
        methodsMenu.put("7", this::pressButton7);
        methodsMenu.put("8", this::pressButton8);
        methodsMenu.put("9", this::pressButton9);
        methodsMenu.put("10", this::pressButton10);
    }

    private void setMenu(){
        menu = new LinkedHashMap<>();
        menu.put("1", bundle.getString("1"));
        menu.put("2", bundle.getString("2"));
        menu.put("3", bundle.getString("3"));
        menu.put("4", bundle.getString("4"));
        menu.put("5", bundle.getString("5"));
        menu.put("6", bundle.getString("6"));
        menu.put("7", bundle.getString("7"));
        menu.put("8", bundle.getString("8"));
        menu.put("9", bundle.getString("9"));
        menu.put("10", bundle.getString("10"));
        menu.put("Q", bundle.getString("Q"));
    }

    private void internationalizeMenuUkr(){
        locale = new Locale("uk");
        bundle = ResourceBundle.getBundle("MyMenu",locale);
        setMenu();
        show();
    }

    private void internationalizeMenuEn(){
        locale = new Locale("en");
        bundle = ResourceBundle.getBundle("MyMenu",locale);
        setMenu();
        show();
    }

    private void pressButton3() {
        List<Sentence> sentences = new ArrayList<>(controller.getSentences());
        System.out.println("\n========================= all sentences =============================");
        for (Sentence s : sentences) {
            System.out.println(s.getValue());
        }
    }

    private void pressButton4() {
        System.out.println("\n=================== with most repeated words ==========================");
        Map<Sentence,Integer> sortedByRepeating = controller.getSortedByRepeating();

        System.out.println(sortedByRepeating.keySet().stream().findFirst().get());
        System.out.println(sortedByRepeating.values().toArray()[0]);
        System.out.println("------------");

        Iterator<Map.Entry<Sentence, Integer>> iterator = sortedByRepeating.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Sentence, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + "\nNumber of repeats: " + entry.getValue());
        }
    }

    private void pressButton5() {
        List<Sentence> sortedList = controller.getSortedSentenceList();
        System.out.println("\n========================= sorted sentences =============================");
        for (Sentence s : sortedList) {
            System.out.println(s.getValue());
        }
    }

    private void pressButton6() {
        List<Word> uniqueWords = controller.getUniqueWords();
        System.out.println("\n===================== unique words in 1st sentence ========================");
        System.out.println(uniqueWords.toString());
    }

    private void pressButton7() {
        System.out.println("\n============= words of a given length of question sentences =====================");
        Map<Sentence, List<Word>> wordMap = controller.getWordsOfQuestion(5);
        for (Map.Entry<Sentence, List<Word>> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + "\nWords: " + entry.getValue());
        }
    }

    private void pressButton8() {
        controller.replaceLongestVowel();
        System.out.println("\n========================== replace longest and vowel =============================");
        for (Sentence s : controller.getSentences()) {
            System.out.println("aaa" + s.toString());
        }
    }

    private void pressButton9() {
        System.out.println("\n========================== sorted words =============================");
        controller.printWordsByAlphabet();
    }

    private void pressButton10() {
        System.out.println("\n========================== delete word consonant =============================");
        controller.deleteWordsStartConsonant(6);
        for (Sentence s : controller.getSentences()) {
            System.out.println(s.getValue());
        }
    }

    private void outputMenu() {
        System.out.println("\n==================== MENU ====================");
        for (String str : menu.values()) {
            System.out.println(str);
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("----------------------------------------------");
            System.out.println("Enter the menu point:");
            keyMenu = input.nextLine().toUpperCase();
            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }
}
