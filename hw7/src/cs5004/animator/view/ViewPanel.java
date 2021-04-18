package cs5004.animator.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
  private int tick = 0;

  /**
   * this constructor creates a ViewPanel based on the passed in listOfShapes and a speed.
   * @param listOfShapes  a list of shape that is a field of an animation.
   * @param speed   a speed to display this view, int type.
   */
  public ViewPanel(java.util.List<IShape> listOfShapes, int speed) {
    super();
    this.listOfShapes = listOfShapes;
    timer = new Timer(speed, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tick += speed;
        repaint();
      }
    });
  }

  @Override
  public void paint(Graphics graph) {
    super.paint(graph);
    Graphics2D g = (Graphics2D) graph;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    timer.start();
    for (IShape shape : listOfShapes) {
      if (tick >= shape.getAppearTime() && tick < shape.getDisappearTime()) {
        g.setColor(shape.getColorAt(tick));

        if (shape.getType() == ShapeType.CIRCLE) {
          g.fillOval(shape.getPositionAt(tick).getX(), shape.getPositionAt(tick).getY(),
                  shape.getPara1At(tick), shape.getPara1At(tick));
        } else if (shape.getType() == ShapeType.RECTANGLE) {
          g.fillRect(shape.getPositionAt(tick).getX(), shape.getPositionAt(tick).getY(),
                  shape.getPara1At(tick), shape.getPara2At(tick));
        } else if (shape.getType() == ShapeType.ELLIPSE) {
          g.drawOval(shape.getPositionAt(tick).getX(), shape.getPositionAt(tick).getY(),
                  shape.getPara1At(tick), shape.getPara2At(tick));
        }
      }
    }
  }
}
