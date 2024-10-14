package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GestionLog {
    static String filtro;
    static ArrayList<LineaLog> lineas = new ArrayList<>();
    public static void leerLog(){
        try{
            Path path = Paths.get("src/log_ejemplo.log");
             List<String> l = Files.readAllLines(path);
        String[] partes;

        for(String linea:l){
            partes=linea.split(" ");

            String fecha = partes[0]+" "+partes[1];
            String nivel = partes[2];
            String mensaje="";
            for(int i=3;i< partes.length;i++) {
                mensaje =mensaje + (partes[i] + " ");
            }
            lineas.add(new LineaLog(fecha,nivel,mensaje));
        }
        String[] quitarCorchetes;
        String[] quitarCorchetes2;
            for(LineaLog lin: lineas){
            quitarCorchetes= lin.getFecha().split("");
            String fecha="";
            for(int i=1;i< quitarCorchetes.length-1;i++){
                fecha=fecha+quitarCorchetes[i];
            }
            quitarCorchetes2= lin.getNivel().split("");
            String nivel="";
            for(int j=1;j< quitarCorchetes2.length-1;j++){
                nivel=nivel+quitarCorchetes2[j];
            }
               lin.setFecha(fecha);
               lin.setNivel(nivel);
        }
            for(LineaLog lin: lineas){
                System.out.println(lin);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void elegirNivel(){
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;

        while(!salir) {
            try {
                menuNiveles();
                int nivelelegido = teclado.nextInt();


                    switch (nivelelegido){
                        case 1:
                            for(LineaLog lin: lineas){
                                System.out.println(lin);
                            }
                            filtro="NINGUNO";
                            salir = true;
                            break;
                        case 2:
                            for(LineaLog lin: lineas){
                               if(lin.getNivel().equalsIgnoreCase("INFO")) {
                                   System.out.println(lin);
                               }
                            }
                            filtro="INFO";
                            salir = true;
                            break;
                        case 3:
                            for(LineaLog lin: lineas){
                                if(lin.getNivel().equalsIgnoreCase("WARNING")) {
                                    System.out.println(lin);
                                }
                            }
                            filtro="WARNING";
                            salir = true;
                            break;
                        case 4:
                            for(LineaLog lin: lineas){
                                if(lin.getNivel().equalsIgnoreCase("ERROR")) {
                                    System.out.println(lin);
                                }
                            }
                            filtro="ERROR";
                            salir = true;
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

    public static void menuNiveles(){
        System.out.println("Elige el nivel por el que quieras filtrar\n1-NINGUNO(muestra todos)\n2-INFO\n3-WARNING\n4-ERROR\n5-SALIR");

    }
}
