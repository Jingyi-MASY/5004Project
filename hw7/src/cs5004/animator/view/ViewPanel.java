package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

/**
 * this ViewPanel class represents all the methods and fields that belong to a ViewPanel.
 * The fields of a ViewPanel includes a speed (int), a timer (Timer), a listOfShape(list)
 * and a tick that has a default value of 0.
 */
public class ViewPanel extends JPanel {
  protected Timer timer;
  private java.util.List<IShape> listOfShapes;
  private int speed = 1;
  private int tick = 0;
  private final int framesPerSecond = 60;
  /**
   * this constructor creates a ViewPanel based on the passed in listOfShapes and a speed.
   * @param listOfShapes  a list of shape that is a field of an animation.
   * @param speed   a speed to display this view, int type.
   */
  public ViewPanel(java.util.List<IShape> listOfShapes, int speed) {
    super();
    this.speed = speed;
    this.listOfShapes = listOfShapes;

  }

  /**
   * This animate creates a timer and paint accordingly.
   */
  public void animate() {
    int delay = 1000 / framesPerSecond;

    timer = new Timer(delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tick += speed;
        repaint();

      }
    });
    timer.start();
  }

  @Override
  public void paint(Graphics graph) {
    super.paint(graph);
    Graphics2D g = (Graphics2D) graph;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    for (IShape shape : listOfShapes) {
      if (tick >= shape.getAppearTime() * framesPerSecond
              && tick  < shape.getDisappearTime() * framesPerSecond) {
        int t = tick / framesPerSecond;
        int r = shape.getColorAt(t).getRed();
        int green = shape.getColorAt(t).getGreen();
        int b = shape.getColorAt(t).getBlue();
        int x = shape.getPositionAt(t).getX();
        int y = shape.getPositionAt(t).getY();
        int para1 = shape.getPara1At(t);
        int para2 = shape.getPara2At(t);
        float durR = (float)(shape.getColorAt(t + 1).getRed() - r) / framesPerSecond;
        float durG = (float)(shape.getColorAt(t + 1).getGreen() - green) / framesPerSecond;
        float durB = (float)(shape.getColorAt(t + 1).getBlue() - b) / framesPerSecond;
        float durX = (float)(shape.getPositionAt(t + 1).getX() - x) / framesPerSecond;
        float durY = (float)(shape.getPositionAt(t + 1).getY() - y) / framesPerSecond;
        float durPara1 = (float)(shape.getPara1At(t + 1) - para1) / framesPerSecond;
        float durPara2 = (float)(shape.getPara2At(t + 1) - para2) / framesPerSecond;
        g.setColor(new Color((int)(r + durR * (tick % framesPerSecond)), (int)(green + durG * (tick % framesPerSecond)), (int)(b + durB * (tick % framesPerSecond))));


        if (shape.getType() == ShapeType.RECTANGLE) {
          g.fillRect((int)(x + durX * (tick % framesPerSecond)), (int)(y + durY * (tick % framesPerSecond)),
                  (int)(para1 + durPara1 * (tick % framesPerSecond)), (int)(para2 + durPara2 * (tick % framesPerSecond)));
        } else if (shape.getType() == ShapeType.ELLIPSE) {
          g.drawOval((int)(x + durX * (tick % framesPerSecond)), (int)(y + durY * (tick % framesPerSecond)),
                  (int)(para1 + durPara1 * (tick % framesPerSecond)), (int)(para2 + durPara2 * (tick % framesPerSecond)));
        }
      }
    }
  }
}
