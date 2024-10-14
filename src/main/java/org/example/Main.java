package org.example;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        while(!salir) {
            try {
                menu();
                int opcion = teclado.nextInt();


                switch (opcion){
                    case 1:
                        GestionLog.leerLog();
                        break;
                    case 2:
                        GestionLog.elegirNivel();

                        break;
                    case 3:
                        System.out.println("Dime un nombre para el archivo xml(el .xml no hace falta que lo pongas)");
                        String archivoxml = teclado.next();
                        GestionLog.volcarLog(GestionLog.filtro,"src/"+archivoxml+".xml");
                        break;
                    case 4:
                        System.out.println("Dime un nombre para el archivo xml(el .xml no hace falta que lo pongas)");
                        String archivojson= teclado.next();
                        GestionLog.volcarLogJson(GestionLog.filtro, archivojson);
                        break;
                    case 5:

                        break;
                        case 6:
                            salir = true;
                            break;
                    default:
                        System.out.println("El numero escogido no corresponde con ninguna opcion");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("El dato introducido no es correcto debes introducir un número\n");
                teclado.nextLine();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        }
    public static void menu(){
        System.out.println("\n1-Carga del archivo .log y carga de trazas en memoria. \n2-Filtrado de trazas por nivel de log.\n3-Exportación de logs filtrados a XML.\n4-Exportación de logs filtrados a JSON.\n5-Resetear filtrado (quita el filtrado previo)\n6-SALIR DEL PROGRAMA");

    }
    }
