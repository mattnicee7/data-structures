package com.github.mattnicee7;

import java.util.Scanner;

// TAD = Tipo Abstrato de Dado (PT-BR)
// ADT = Abstract Data Type (EN-US)
public class TADFirstExample {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final Number number = new Number();

        while (true) {

            System.out.println("0 - End");
            System.out.println("1 - Read value");
            System.out.println("2 - Set value");
            int option = scanner.nextInt();

            if (option == 0)
                break;
            else if (option == 1)
                System.out.println(number.getValue());
            else if (option == 2) {
                System.out.println("forneca o novo valor");
                final float value = scanner.nextFloat();
                number.setValue(value);
            }

        }

    }

    private static final class Number {

        private float value;

        public Number() {
            this.value = 0;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {

            if (value < 0) {
                value = 0;
                System.out.println("Value less than zero, corrected to zero.");
            } else if (value > 40 && value < 60) {

                if (value < 50) {
                    value = 40;
                    System.out.println("Value in unsupported range, corrected to 40.");
                } else {
                    value = 60;
                    System.out.println("Value in insupported range, corrected to 60.");
                }

            } else if (value > 100) {
                value = 100;
                System.out.println("Value greater than 100, corrected to 100.");
            }

            this.value = value;
        }

    }

}
