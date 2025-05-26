package MyArkanoid;
/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
import javax.swing.JOptionPane;

public class ExceptionJogo extends Exception{
    public ExceptionJogo(String msg) {
        super(msg);
    }
    /*public void next(){
        JOptionPane.showMessageDialog(null, getMessage(), "Nivel Concluido!!!", JOptionPane.WARNING_MESSAGE);
        
    }*/
    public void show(){
        JOptionPane.showMessageDialog(null, getMessage(), "Arkanoid Error", JOptionPane.WARNING_MESSAGE);
        
    }
}
