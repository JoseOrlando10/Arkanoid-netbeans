/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyArkanoid;

import javax.swing.JOptionPane;

/**
 *
 * @author jooma
 */
public class ExceptionJogo extends Exception{
    public ExceptionJogo(String msg) {
        super(msg);
    }
    public void show(){
        JOptionPane.showMessageDialog(null, getMessage(), "Arkanoid Error", JOptionPane.WARNING_MESSAGE);
        
    }
}
