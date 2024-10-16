package org.example;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                        boolean salir4 = false;
                        String ficherolog="";
                        while (salir4 == false) {
                            try {
                                System.out.println("Dime el nombre del archivo log que quieras cargar");
                                ficherolog = teclado.next();
                                Path log = Paths.get(ficherolog);
                                if (Files.exists(log)) {
                                    GestionLog.leerLog(ficherolog);
                                    salir4=true;
                                } else if (Files.notExists(log)) {
                                    throw new ArchivoException("El archivo introducido no existe.");
                                }

                            } catch (ArchivoException e) {
                                e.printStackTrace();
                                System.out.println("El archivo no existe, vuelva a introducirlo de nuevo");


                            }
                        }
                        break;
                    case 2:
                        GestionLog.elegirNivel();
                        break;
                    case 3:
                        boolean salir2 = false;
                        String archivoxml="";
                        while (salir2 == false) {
                            try {
                                System.out.println("Dime un nombre para el archivo xml(el .xml no hace falta que lo pongas)");
                                archivoxml = teclado.next();
                                Path xml = Paths.get(archivoxml+".xml");
                                if (Files.exists(xml)) {
                                    throw new ArchivoException("El archivo introducido ya existe.");
                                } else if (Files.notExists(xml)) {
                                    GestionLog.volcarLogXml(GestionLog.filtro, archivoxml + ".xml");
                                    salir2 = true;
                                }

                            } catch (ArchivoException e) {
                                e.printStackTrace();
                                System.out.println("El archivo ya existe, vuelva a introducirlo de nuevo");


                            }
                        }
                        break;
                    case 4:
                        boolean salir3 = false;
                        String archivojson="";
                        while (salir3 == false) {
                            try {
                                System.out.println("Dime un nombre para el archivo json(el .json no hace falta que lo pongas)");
                                archivojson= teclado.next();
                                Path json = Paths.get(archivojson+".json");
                                if (Files.exists(json)) {
                                    throw new ArchivoException("El archivo introducido ya existe.");
                                } else if (Files.notExists(json)) {
                                    GestionLog.volcarLogJson(GestionLog.filtro, archivojson);
                                    salir3=true;
                                }

                            } catch (ArchivoException e) {
                                e.printStackTrace();
                                System.out.println("El archivo ya existe, vuelva a introducirlo de nuevo");


                            }
                        }
                        break;
                    case 5:
                        GestionLog.resetearFiltro(GestionLog.filtro);
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
