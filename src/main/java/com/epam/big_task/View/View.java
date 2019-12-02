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

    public View() throws IOException {
        controller = new ControllerImpl();
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - Вивести всі речення.");
        menu.put("2", " 2 - Знайти найбільшу кількість речень тексту, в яких є однакові слова.");
        menu.put("3", " 3 - Вивести всі речення заданого тексту у порядку зростання кількості слів у\n" +
                "кожному з них");
        menu.put("4", " 4 - Знайти таке слово у першому реченні, якого немає ні в одному з інших\n" +
                "речень(список слів)");
        menu.put("5", " 5 - У всіх запитальних реченнях тексту знайти і надрукувати без повторів\n" +
                "слова заданої довжини.");
        menu.put("6", " 6 - У кожному реченні тексту поміняти місцями перше слово, що починається\n" +
                "на голосну букву з найдовшим словом");
        menu.put("7", " 7 - Надрукувати слова тексту в алфавітному порядку по першій букві. Слова,\n" +
                "що починаються з нової букви, друкувати з абзацного відступу");
        menu.put("8", " 8 - З тексту видалити всі слова заданої довжини, що починаються на\n" +
                "приголосну букву");
        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::pressButton1);
        methodsMenu.put("2", this::pressButton2);
        methodsMenu.put("3", this::pressButton3);
        methodsMenu.put("4", this::pressButton4);
        methodsMenu.put("5", this::pressButton5);
        methodsMenu.put("6", this::pressButton6);
        methodsMenu.put("7", this::pressButton7);
        methodsMenu.put("8", this::pressButton8);
    }

    private void pressButton1() {
        List<Sentence> sentences = new ArrayList<>(controller.getSentences());
        System.out.println("\n========================= all sentences =============================");
        for (Sentence s : sentences) {
            System.out.println(s.getValue());
        }
    }

    private void pressButton2() {
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

    private void pressButton3() {
        List<Sentence> sortedList = controller.getSortedSentenceList();
        System.out.println("\n========================= sorted sentences =============================");
        for (Sentence s : sortedList) {
            System.out.println(s.getValue());
        }
    }

    private void pressButton4() {
        List<Word> uniqueWords = controller.getUniqueWords();
        System.out.println("\n===================== unique words in 1st sentence ========================");
        System.out.println(uniqueWords.toString());
    }

    private void pressButton5() {
        System.out.println("\n============= words of a given length of question sentences =====================");
        Map<Sentence, List<Word>> wordMap = controller.getWordsOfQuestion(5);
        for (Map.Entry<Sentence, List<Word>> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + "\nWords: " + entry.getValue());
        }
    }

    private void pressButton6() {
        controller.replaceLongestVowel();
        System.out.println("\n========================== replace longest and vowel =============================");
        for (Sentence s : controller.getSentences()) {
            System.out.println("aaa" + s.toString());
        }
    }

    private void pressButton7() {
        System.out.println("\n========================== sorted words =============================");
        controller.printWordsByAlphabet();
    }

    private void pressButton8() {
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
