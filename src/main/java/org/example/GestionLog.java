package org.example;

import com.google.gson.stream.JsonWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionLog {
    static String filtro;
    static ArrayList<LineaLog> lineas = new ArrayList<>();

    public static void leerLog(String ficherolog) {
        try {
            Path path = Paths.get("src/"+ficherolog);
            List<String> l = Files.readAllLines(path);
            String[] partes;
            int contadorlineasmalas=0;
            String formatoCorrecto = "\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\] \\[(INFO|WARNING|ERROR)\\] .+";
            Pattern pattern = Pattern.compile(formatoCorrecto);
            for (String linea : l) {
                Matcher matcher = pattern.matcher(linea);
                if (!matcher.matches()) {
                    contadorlineasmalas++;
                } else {

                    partes = linea.split(" ");

                    String fecha = partes[0] + " " + partes[1];
                    String nivel = partes[2];
                    String mensaje = "";
                    for (int i = 3; i < partes.length; i++) {
                        mensaje = mensaje + (partes[i] + " ");
                    }
                    lineas.add(new LineaLog(fecha, nivel, mensaje));
                }
            }

                    String[] quitarCorchetes;
                    String[] quitarCorchetes2;
                    for (LineaLog lin : lineas) {
                        quitarCorchetes = lin.getFecha().split("");
                        String fecha = "";
                        for (int i = 1; i < quitarCorchetes.length - 1; i++) {
                            fecha = fecha + quitarCorchetes[i];
                        }
                        quitarCorchetes2 = lin.getNivel().split("");
                        String nivel = "";
                        for (int j = 1; j < quitarCorchetes2.length - 1; j++) {
                            nivel = nivel + quitarCorchetes2[j];
                        }
                        lin.setFecha(fecha);
                        lin.setNivel(nivel);
                    }


            System.out.println("Lineas malas encontradas: "+contadorlineasmalas);
            System.out.println("\nLOG CARGADO CORRECTAMENTE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void elegirNivel() {
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                menuNiveles();
                int nivelelegido = teclado.nextInt();


                switch (nivelelegido) {
                    case 1:
                        for (LineaLog lin : lineas) {
                            System.out.println(lin);
                        }
                        filtro = "NINGUNO";
                        salir = true;
                        break;
                    case 2:
                        for (LineaLog lin : lineas) {
                            if (lin.getNivel().equalsIgnoreCase("INFO")) {
                                System.out.println(lin);
                            }
                        }
                        filtro = "INFO";
                        salir = true;
                        break;
                    case 3:
                        for (LineaLog lin : lineas) {
                            if (lin.getNivel().equalsIgnoreCase("WARNING")) {
                                System.out.println(lin);
                            }
                        }
                        filtro = "WARNING";
                        salir = true;
                        break;
                    case 4:
                        for (LineaLog lin : lineas) {
                            if (lin.getNivel().equalsIgnoreCase("ERROR")) {
                                System.out.println(lin);
                            }
                        }
                        filtro = "ERROR";
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

    public static void menuNiveles() {
        System.out.println("Elige el nivel por el que quieras filtrar\n1-NINGUNO(muestra todos)\n2-INFO\n3-WARNING\n4-ERROR");

    }

    public static void volcarLogXml(String filtro, String ficheroSalida) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element elementoPrincipal = doc.createElement("logAplicacion");
        doc.appendChild(elementoPrincipal);

        Element log;

        for (LineaLog linea : lineas) {
            if (linea.getNivel().equalsIgnoreCase(filtro)) {

                log = doc.createElement("log");
                elementoPrincipal.appendChild(log);

                Element fechaHora = doc.createElement("fechaHora");
                fechaHora.appendChild(doc.createTextNode(linea.getFecha()));
                log.appendChild(fechaHora);

                Element nivel = doc.createElement("nivel");
                nivel.appendChild(doc.createTextNode(linea.getNivel()));
                log.appendChild(nivel);


                Element mensaje = doc.createElement("mensaje");
                mensaje.appendChild(doc.createTextNode(linea.getMensaje()));
                log.appendChild(mensaje);

            }else
                log = doc.createElement("log");
                elementoPrincipal.appendChild(log);

                Element fechaHora = doc.createElement("fechaHora");
                fechaHora.appendChild(doc.createTextNode(linea.getFecha()));
                log.appendChild(fechaHora);

                Element nivel = doc.createElement("nivel");
                nivel.appendChild(doc.createTextNode(linea.getNivel()));
                log.appendChild(nivel);


                Element mensaje = doc.createElement("mensaje");
                mensaje.appendChild(doc.createTextNode(linea.getMensaje()));
                log.appendChild(mensaje);




        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File(ficheroSalida));
        transformer.transform(source, result);

    }

    public static void volcarLogJson(String filtro, String ficheroSalida) throws ParserConfigurationException, TransformerException, IOException {
        try (JsonWriter writer = new JsonWriter(new FileWriter("src/" + ficheroSalida + ".json"))) {
            writer.setIndent("  ");
            writer.beginArray();

            for (LineaLog linea : lineas) {
                if(linea.getNivel().equalsIgnoreCase(filtro)) {
                    writeLineaLog(writer, linea);
                }else
                    writeLineaLog(writer, linea);

            }
            writer.endArray();
            System.out.println("Se ha creado el fichero JSON correctamente.");


        }


    }
    private static void writeLineaLog(JsonWriter writer, LineaLog linea) throws IOException {
        writer.beginObject();
        writer.name("fechaHora").value(linea.getFecha());
        writer.name("Nivel").value(linea.getNivel());
        writer.name("mensaje").value(linea.getMensaje());
        writer.endObject();
    }
    public static void resetearFiltro(String filtro){
        filtro="";
        for(LineaLog linea : lineas){
            System.out.println(linea);
        }

    }
}