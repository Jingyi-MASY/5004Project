package cs5004.animator.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.Collections;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;

/**
 * This class represents the visual view that display an animation using swing. The field of
 * this class includes a speed.
 */
public class VisualView extends JFrame implements IView {
  private final int speed;

  /**
   * Constructor of a visual view.
   *
   * @param speed tick per sec
   */
  public VisualView(int speed) {

    super("Canvas");
    if (speed <= 0) {
      throw new IllegalArgumentException("invalid speed");
    }
    this.speed = speed;

    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);

  }


  private void show(java.util.List<IShape> listOfShapes, int[] bounds) {
    ViewPanel viewPanel = new ViewPanel(listOfShapes, speed);

    viewPanel.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    setContentPane(viewPanel);
    JScrollPane scrollPane = new JScrollPane(viewPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    setContentPane(scrollPane);

    setVisible(true);
    viewPanel.animate();
  }


  @Override
  public void showAll(IAnimation animation) {
    show(animation.getListOfShapes(), animation.getBounds());
  }


  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    for (IShape shape : animation.getListOfShapes()) {
      if (shape.getName().equalsIgnoreCase(shapeName)) {
        show(Collections.singletonList(shape), animation.getBounds());
        return;
      }
    }
  }


  @Override
  public int getSpeed() {
    return speed;
  }

}