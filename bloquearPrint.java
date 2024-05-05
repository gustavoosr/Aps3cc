
package cronometroaps;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class bloquearPrint implements KeyEventDispatcher{
    
        @Override
        public boolean dispatchKeyEvent(KeyEvent e){
            int tecladesativar = KeyEvent.VK_PRINTSCREEN;
            if(e.getKeyCode()== tecladesativar){
                e.consume();
                JOptionPane.showMessageDialog(null,"Não foi possível capturar a tela");
                return false;
            }
            return false;
        }
    }
