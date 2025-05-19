package MyArkanoid;

import javax.swing.JOptionPane;

public class ExceptionJogo extends Exception{
    public ExceptionJogo(String msg) {
        super(msg);
    }
    public void show(){
        JOptionPane.showMessageDialog(null, getMessage(), "Arkanoid Error", JOptionPane.WARNING_MESSAGE);
        
    }
}
