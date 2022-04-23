
package net.mconcoba.models;

import java.util.Date;

/**
 *
 * @author mconcoba
 */
public class StudentModel {
    int alumno_id;
    String nombres;
    String apellidos;
    float bimestre_uno;
    float bimestre_dos;
    float bimestre_tres;
    float bimestre_cuatro;
    float promedio;
    Date fecha_creacion;
    Date fecha_actualizacion;

    public StudentModel() {
    }
    
    

    public StudentModel(int alumno_id, String nombres, String apellidos, float bimestre_uno, float bimestre_dos, float bimestre_tres, float bimestre_cuatro, float promedio, Date fecha_creacion, Date fecha_actualizacion) {
        this.alumno_id = alumno_id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.bimestre_uno = bimestre_uno;
        this.bimestre_dos = bimestre_dos;
        this.bimestre_tres = bimestre_tres;
        this.bimestre_cuatro = bimestre_cuatro;
        this.promedio = promedio;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public float getBimestre_uno() {
        return bimestre_uno;
    }

    public void setBimestre_uno(float bimestre_uno) {
        this.bimestre_uno = bimestre_uno;
    }

    public float getBimestre_dos() {
        return bimestre_dos;
    }

    public void setBimestre_dos(float bimestre_dos) {
        this.bimestre_dos = bimestre_dos;
    }

    public float getBimestre_tres() {
        return bimestre_tres;
    }

    public void setBimestre_tres(float bimestre_tres) {
        this.bimestre_tres = bimestre_tres;
    }

    public float getBimestre_cuatro() {
        return bimestre_cuatro;
    }

    public void setBimestre_cuatro(float bimestre_cuatro) {
        this.bimestre_cuatro = bimestre_cuatro;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
    
    
}


