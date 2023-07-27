/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EcobarM
 */
public class Utilidades {

    public static void DelimitarNumerosDni(KeyEvent evt, JTextField text) {
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || !Character.isLetterOrDigit(validar)) {

            evt.consume();
        }

        if (text.getText().length() >= 8) {
            evt.consume();

        }
    }

    public static void DelimitarLetras(KeyEvent evt) {
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar) || !Character.isLetterOrDigit(validar)) {

            evt.consume();
        }

    }

    public static void DelimitarNumerosTelefono(KeyEvent evt, JTextField text) {
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || !Character.isLetterOrDigit(validar)) {
            
            evt.consume();
        }
        
        
        if (text.getText().length() >= 10) {
            evt.consume();

        }
    }

    public static boolean ValidadEmail(String Correo) {

        Pattern patron = Pattern.compile("(\\W|^)[\\w.\\-]{0,25}+@(hotmail||gmail||outlook||yahoo)\\.com(\\W|$)+");
        
        //Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher mat = patron.matcher(Correo);
        return mat.find();

    }
    
    

    public static void cargarCombo(List<?> lista, JComboBox combo) {

        DefaultComboBoxModel comboCarga = new DefaultComboBoxModel();
        
        if (!lista.isEmpty()) {
            comboCarga.addElement("[SELECCIONAR]");

            for (Object o : lista) {
                comboCarga.addElement(o);

            }
        }
        combo.setModel(comboCarga);

    }
    
    public static void DelimitarNumeros(KeyEvent evt, JTextField text) {
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar) || !Character.isLetterOrDigit(validar)) {

            evt.consume();
        }

    }
  
}
