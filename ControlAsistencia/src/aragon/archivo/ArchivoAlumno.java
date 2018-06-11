/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aragon.archivo;

import aragon.modelo.Alumno;
import aragon.modelo.Asistencia;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class ArchivoAlumno {
    
    private ArrayList<Alumno> alumnos = new ArrayList();
    private Alumno buscado = new Alumno();
    private File f = null;
    private FileWriter fw = null;
    private PrintWriter pw = null;
    private JFileChooser jfc = new JFileChooser();
    private String path = new String();
    
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }
    
    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    public Alumno getBuscado() {
        return buscado;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
    public void generaArchivo(ArrayList<Alumno> arrAl) throws IOException {
        
        ArrayList<Alumno> temp = arrAl;
        jfc.setDialogTitle("Guardar...");
        jfc.showSaveDialog(null);
        
        fw = new FileWriter(f, false);
        
        pw = new PrintWriter(fw);
        
        for (int i = 0; i < temp.size(); i++) {
            pw.println(temp.get(i).getNumeroDeCuenta() + ","
                    + temp.get(i).getApellidoPaterno() + ","
                    + temp.get(i).getApellidoMaterno() + ","
                    + temp.get(i).getAsistencias().toString());
        }
        pw.close();
        if (f != null) {
            fw.close();
        }
        if (pw != null) {
            pw.close();
        }
        
    }
    
    public Boolean leerArchivo() {
        alumnos.clear();
        
        Boolean flag = null;
        
        FileReader lectorDeArchivos = null;
        BufferedReader buff = null;
        
        try {
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                f = jfc.getSelectedFile();
                path = f.getPath();
                
                if (f.exists()) {
                    lectorDeArchivos = new FileReader(f);
                    String cad;
                    while ((cad = buff.readLine()) != null) {
                        
                        String[] cadena = cad.split(",");
                        
                        String numeroDeCuenta = cadena[0];
                        String nombre = cadena[1];
                        String apPat = cadena[2];
                        String apMat = cadena[3];
                        ArrayList<Asistencia> asis = new ArrayList<>();
                        for (int i = 4; i < cadena.length; i++) {
                            Boolean b = (cadena[i].charAt(9) == 'S');
                            asis.add(new Asistencia(cadena[i].substring(0, 7), b));
                        }
                        
                        Alumno tempA = new Alumno(numeroDeCuenta, nombre, apPat, apMat, asis);
                        
                        alumnos.add(tempA);
                    }
                    flag = true;
                    JOptionPane.showMessageDialog(null, "Sea ha cargado el archivo" + f.getName(), "Archivo cargado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No esta el archivo", "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
                    flag = false;
                }
                
            }
        } catch (HeadlessException | IOException e) {
        } finally {
            try {
                if (lectorDeArchivos != null) {
                    lectorDeArchivos.close();
                }
                if (buff != null) {
                    buff.close();
                }
            } catch (IOException e) {
            }
        }
        return flag;
    }
    
    public Boolean buscarNumeroDeCuenta(String ndc) {
        String numeroDeCta = ndc;
        if (alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vacia", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            
            for (int i = 0; i < alumnos.size(); i++) {
                if (numeroDeCta.equals(alumnos.get(i).getNumeroDeCuenta())) {
                    this.buscado = alumnos.get(i);
                    return true;
                } else if (i + 1 == alumnos.size()) {
                    return false;
                }
            }
        }
        return true;
    }
}
