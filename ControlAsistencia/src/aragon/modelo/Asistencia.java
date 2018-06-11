/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aragon.modelo;

/**
 *
 * @author eduardo
 */
public class Asistencia {

    private String fecha;
    private Boolean asistencia;

    public Asistencia() {
    }

    public Asistencia(String fecha, Boolean asistencia) {
        this.fecha = fecha;
        this.asistencia = asistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public String toString() {
        String asis = (asistencia.equals(true)) ? "S" : "N";
        return "" + fecha + "-" + asis;

    }

}