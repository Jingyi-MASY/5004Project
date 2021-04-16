package cs5004.animator.view;

import javax.swing.*;

public class InputMessage {

  public static void Message(String message) {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame,message,
            "Input Error",
            JOptionPane.ERROR_MESSAGE);

  }
}
