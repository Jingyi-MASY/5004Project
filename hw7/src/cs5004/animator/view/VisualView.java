package cs5004.animator.view;

import javax.swing.*;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Collections;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;

/**
 *
 */
public class VisualView extends JFrame implements IView {
  private ViewPanel viewPanel;
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
    viewPanel = new ViewPanel(listOfShapes, speed);
    setContentPane(viewPanel);
    JScrollPane scrollPane = new JScrollPane(viewPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//    JToggleButton playPause = new JToggleButton("Play");
//
//    playPause.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        if(playPause.isSelected()){
//          playPause.setText("Pause");
//          viewPanel.timer.stop();
//        } else{
//          playPause.setText("Play");
//          viewPanel.timer.stop();
//        }
//      }
//    });
//
//    viewPanel.add(playPause);

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

  @Override
  public PrintStream getOutput() {
    return null;
  }

//  public static void main(String[] args) {
//    IAnimation animation = new AnimationImpl(100, 100, 1000, 1000);
//    IView view = new VisualView(System.out, 5);
//    animation.declareShape("r1", "rectangle");
//    animation.addMotion("r1", 1, 0, 0, 100, 200, 0, 128, 128,
//            100, 200, 200, 50, 100, 200, 150, 100);
//    view.showAll(animation);
//  }
}
