package com.epam.big_task.View;

import com.epam.big_task.controller.Controller;
import com.epam.big_task.controller.ControllerImpl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    private Controller controller;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public View() throws IOException {
        controller = new ControllerImpl();
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - print all sentences");
        menu.put("2", " 2 - print a list sorted by power");
        menu.put("3", " 3 - print the total power of connected appliances");
        menu.put("4", " 4 - print TVsets only");
        menu.put("5", " 5 - print vacuum cleaners only");
        menu.put("6", " 6 - print laptops only");
        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::pressButton1);
        methodsMenu.put("2", this::pressButton2);
        methodsMenu.put("3", this::pressButton3);
        methodsMenu.put("4", this::pressButton4);
        methodsMenu.put("5", this::pressButton5);
        methodsMenu.put("6", this::pressButton6);
    }

    private void pressButton1() {
        controller.getApplianceList().forEach(System.out::println);
    }

    private void pressButton2() {
        controller.getSortedByPower().forEach(System.out::println);
    }

    private void pressButton3() {
        System.out.println(controller.getConnectionPower());
    }

    private void pressButton4() {
        controller.getTVsets().forEach(System.out::println);
    }

    private void pressButton5() {
        controller.getVacuumCleaners().forEach(System.out::println);
    }

    private void pressButton6() {
        controller.getLaptops().forEach(System.out::println);
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
