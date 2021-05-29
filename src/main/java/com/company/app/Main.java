package com.company.app;

import com.company.app.controller.Controller;

import java.util.Objects;
import java.util.Scanner;

/**
 * Main class with a program entry point.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.process("-fillCatalog");

        Scanner input = new Scanner(System.in);
        boolean flag = true;

        System.out.println(controller.intro());

        while (flag) {
            String answer = "";
            answer = input.nextLine();

            String result = controller.process(answer);

            if (Objects.equals(result, "false")) {
                flag = false;
            } else {
                System.out.println(result);
            }
        }
    }
}
