package org.example;

public class LineaLog {
    String fecha;
    String nivel;
    String mensaje;


    public LineaLog(String fecha, String nivel, String mensaje) {
        this.fecha = fecha;
        this.nivel = nivel;
        this.mensaje = mensaje;

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "["+fecha+"]"+" "+"["+nivel+"]"+" "+ mensaje;
    }
}
