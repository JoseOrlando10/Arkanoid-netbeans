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
    
    public void show(){
            JOptionPane.showMessageDialog(null, getMessage(), "Ficaste sem Vidas", JOptionPane.WARNING_MESSAGE);
        
    }
}
