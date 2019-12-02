package com.epam.big_task.model;

import java.util.*;

public class BusinessLogic {

    public List<Sentence> getSortedSentenceList(List<Sentence> sentences) {
        List<Sentence> sortedList = new ArrayList<>(sentences);
        Collections.sort(sortedList);
        return sortedList;
    }



}




