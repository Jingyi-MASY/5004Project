package cs5004.animator.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * this class represents the input message.
 */
public class InputMessage {

  /**
   * this constructor creates an InputMessage based on the passed in message.
   * @param message passed in message to create the input message, String type.
   */
  public static void message(String message) {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame,message,
            "Input Error",
            JOptionPane.ERROR_MESSAGE);

  }
}
