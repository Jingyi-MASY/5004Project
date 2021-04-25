package cs5004.animator.view;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Movement;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.ShapeType;

/**
 * This TextView represents a view class that displays animations in text format. This class
 * includes all fields and methods that belong to a TextView.
 */
public class TextView implements IView {
  private PrintStream out;
  private int speed;

  /**
   * This construct a TextView based on the passed in output.
   * @param out the passed in output used in this view, PrintStream class.
   */
  public TextView(PrintStream out) {
    if (out == null) {
      out = System.out;
    }
    this.out = out;
  }

  private void showShapeInitials(List<IShape> listOfShapes) {
    for (IShape shape : listOfShapes) {
      int r = shape.getColorAt(shape.getAppearTime()).getRed();
      int g = shape.getColorAt(shape.getAppearTime()).getGreen();
      int b = shape.getColorAt(shape.getAppearTime()).getBlue();
      out.print("Create Color(r=" + r + ",g=" + g + ",b=" + b + ") ");
      out.print(shape.getType().toString() + " " + shape.getName() + " ");
      if (shape.getType() == ShapeType.RECTANGLE) { //when shape is a rectangle
        out.print("with corner at " + shape.getPositionAt(shape.getAppearTime()).toString() + ", ");
        out.print("width " + shape.getPara1At(shape.getAppearTime()));
        out.println(" and height " + shape.getPara2At(shape.getAppearTime()));
      } else { //when shape is an ellipse
        int xRadius = shape.getPara1At(shape.getAppearTime()) / 2;
        int yRadius = shape.getPara2At(shape.getAppearTime()) / 2;
        Point2D tLCorner = shape.getPositionAt(shape.getAppearTime());
        Point2D center = new Point2D(tLCorner.getX() + xRadius, tLCorner.getY() + yRadius);
        out.print("with center at " + center + ", ");
        out.print("radius " + xRadius);
        out.println(" and " + yRadius);
      }
    }
  }

  private void showShapeAppearance(List<IShape> listOfShapes) {
    for (IShape shape : listOfShapes) {
      out.print(shape.getName() + " appears at time t=" + shape.getAppearTime());
      out.println(" and disappears at time t=" + shape.getDisappearTime());
    }
  }

  private void showMotions(List<IShape> listOfShapes) {
    LinkedList<Movement> motionList = new LinkedList<>();
    for (IShape x : listOfShapes) {
      if (x.getMovementList() != null) {
        motionList.addAll(x.getMovementList());
      }
    }
    motionList.sort(Comparator.comparingInt(Movement::getEndTime));

    for (Movement m : motionList) {
      out.println(m.display());
    }
  }

  @Override
  public void showAll(IAnimation animation) {
    this.showShapeInitials(animation.getListOfShapes());
    out.println("");
    this.showShapeAppearance(animation.getListOfShapes());
    out.println("");
    this.showMotions(animation.getListOfShapes());
  }

  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    if (animation == null || shapeName == null) {
      throw new IllegalArgumentException("invalid input");
    }
    boolean found = false;
    for (IShape x : animation.getListOfShapes()) {
      if (x.getName().equals(shapeName)) {
        found = true;
      }
    }
    if (!found) {
      throw new IllegalArgumentException("shape not found");
    }
    for (IShape x : animation.getListOfShapes()) {
      //show initials
      int r = x.getColorAt(x.getAppearTime()).getRed();
      int g = x.getColorAt(x.getAppearTime()).getGreen();
      int b = x.getColorAt(x.getAppearTime()).getBlue();
      out.print("Create Color(r=" + r + ",g=" + g + ",b=" + b + ") ");
      out.print(x.getType().toString() + " " + x.getName() + " ");
      if (x.getType() == ShapeType.RECTANGLE) { //when shape is a rectangle
        out.print("with corner at " + x.getPositionAt(x.getAppearTime()).toString() + ", ");
        out.print("width " + x.getPara1At(x.getAppearTime()));
        out.println(" and height " + x.getPara2At(x.getAppearTime()));
      } else { //when shape is an ellipse
        int xRadius = x.getPara1At(x.getAppearTime()) / 2;
        int yRadius = x.getPara2At(x.getAppearTime()) / 2;
        Point2D tLCorner = x.getPositionAt(x.getAppearTime());
        Point2D center = new Point2D(tLCorner.getX() + xRadius, tLCorner.getY() + yRadius);
        out.print("with center at " + center + ", ");
        out.print("radius " + xRadius);
        out.println(" and " + yRadius);
      }
      //show appearance
      if (x.getName().equals(shapeName)) {
        out.print(x.getName() + " appears at time t=" + x.getAppearTime());
        out.println(" and disappears at time t=" + x.getDisappearTime());
        //show motions
        if (x.getMovementList() != null) {
          x.getMovementList().sort(Comparator.comparingInt(Movement::getEndTime));
          for (Movement m : x.getMovementList()) {
            out.println(m.display());
          }
        }
      }
    }
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }
}
