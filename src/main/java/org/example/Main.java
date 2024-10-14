package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.GestionLog.elegirNivel;
import static org.example.GestionLog.leerLog;


public class Main {
    public static void main(String[] args) {
            leerLog();
            elegirNivel();
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        while(!salir) {
            try {
                menu();
                int opcion = teclado.nextInt();


                switch (opcion){
                    case 1:
                        leerLog();
                        break;
                    case 2:
                        elegirNivel();

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("El numero escogido no corresponde con ninguna opcion");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("El dato introducido no es correcto debes introducir un número\n");
                teclado.nextLine();
            }
        }
        }
    public static void menu(){
        System.out.println("\n1-Carga del archivo .log y carga de trazas en memoria. \n2-Filtrado de trazas por nivel de log.\n3-Exportación de logs filtrados a XML.\n4-Exportación de logs filtrados a JSON.\n5-Resetear filtrado (quita el filtrado previo)\n");

    }
    }
