/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.mconcoba.contollers;

import com.mysql.jdbc.PreparedStatement;
import java.util.ArrayList;
import net.mconcoba.db.Conexion;
import net.mconcoba.models.StudentModel;
import java.sql.ResultSet;


/**
 *
 * @author mconcoba
 */
public class StudentController {
    
    
    StudentModel registro = new StudentModel();
    
    // forma / dato de retor / nombre (datos a solicitar)
    
    public ArrayList<Object[]> consultar(){
        ArrayList<Object[]> data = new ArrayList<>();
        try{
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstancia().getConexion().prepareCall("SELECT * from Alumnos");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                Object[] fila = new Object[8];
                for(int i = 0; i < 8; i++){
                    fila[i] = resultado.getObject(i+1);
                
                }
                data.add(fila);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
    
    public void guardar(Object obj){
        registro = (StudentModel) obj;
        try{
            java.sql.PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("insert into Alumnos(nombres, apellidos, bimestre_uno, bimestre_dos, bimestre_tres, bimestre_cuatro)" +
"		values(?, ?, ?, ?, ?, ?);");
            procedimiento.setString(1, registro.getNombres());
            procedimiento.setString(2, registro.getApellidos());
            procedimiento.setFloat(3, registro.getBimestre_uno());
            procedimiento.setFloat(4, registro.getBimestre_dos());
            procedimiento.setFloat(5, registro.getBimestre_tres());
            procedimiento.setFloat(6, registro.getBimestre_cuatro());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void editar(Object obj){
        registro = (StudentModel) obj;
        try{
            java.sql.PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("update Alumnos set nombres = ?, apellidos = ?, bimestre_uno = ?, "
                    + "bimestre_dos = ?, bimestre_tres = ?, bimestre_cuatro = ?  WHERE alumno_id = ?;");
            procedimiento.setString(1, registro.getNombres());
            procedimiento.setString(2, registro.getApellidos());
            procedimiento.setFloat(3, registro.getBimestre_uno());
            procedimiento.setFloat(4, registro.getBimestre_dos());
            procedimiento.setFloat(5, registro.getBimestre_tres());
            procedimiento.setFloat(6, registro.getBimestre_cuatro());
            procedimiento.setInt(7, registro.getAlumno_id());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void eliminar(Object obj){
        registro = (StudentModel) obj;
        try{
            java.sql.PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("DELETE from Alumnos WHERE alumno_id = ?;");
            procedimiento.setInt(1, registro.getAlumno_id());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
