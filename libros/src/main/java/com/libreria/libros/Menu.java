package com.libreria.libros;

import java.util.Scanner;

public class Menu {
    
    int opcion = 0;
    Scanner teclado = new Scanner(System.in);
    
    public void menu () {
        do {
            System.out.println("""
                    1.
                    2.
                    3.
                    4.
                    5.
                    6.
                    7.Salir
                    """);
            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    break;

                case 7:
                    opcion = 7;
                    break;

                default:
                    System.out.println("opcion no valido");
                    break;
            }
        } while (opcion != 7);
    }

}
