package org.example.ui;

import org.example.model.Hospital;

public class Main {
    public static void main(String[] args) {
        try {

            Hospital h = new Hospital("XYZ");
            System.out.println(h);

            MenuUI uiMenu = new MenuUI(h);
            uiMenu.run();

            System.out.println(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
