package cs5004.animator.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Collections;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;

/**
 * This class represents the visual view that display an animation using swing. The field of
 * this class includes a speed.
 */
public class VisualView extends JFrame implements IView {
  private int speed;

  /**
   * Constructor of a visual view.
   *
   * @param out   Appendable output file
   * @param speed tick per sec
   */
  public VisualView(PrintStream out, int speed) {
    super("Canvas");
    this.speed = speed;

    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

  }


  private void show(java.util.List<IShape> listOfShapes) {
    ViewPanel viewPanel = new ViewPanel(listOfShapes, speed);
    setContentPane(viewPanel);
    JScrollPane scrollPane = new JScrollPane(viewPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    setContentPane(scrollPane);

    setVisible(true);
  }


  @Override
  public void showAll(IAnimation animation) {
    show(animation.getListOfShapes());
  }


  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    for (IShape shape : animation.getListOfShapes()) {
      if (shape.getName().equalsIgnoreCase(shapeName)) {
        show(Collections.singletonList(shape));
        return;
      }
    }
  }


  @Override
  public int getSpeed() {
    return speed;
  }

}
