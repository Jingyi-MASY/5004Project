package cs5004.animator.view;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Movement;
import cs5004.animator.model.ShapeType;

public class TextView implements IView{
  private PrintStream out;
  private int speed;

  public TextView(PrintStream out) {
    if (out == null) {
      out = System.out;
    }
    this.out = out;
  }

  public void showShapeInitials(List<IShape> listOfShapes) {
    //PrintStream result = new PrintStream(filePath);
    for (IShape shape : listOfShapes) {
      out.print("Create " + shape.getColorAt(shape.getAppearTime()).toString() + " ");
      out.print(shape.getType().toString() + " " + shape.getName() + " ");
      if (shape.getType() == ShapeType.RECTANGLE) {//when shape is a rectangle
        out.print("with corner at " + shape.getPositionAt(shape.getAppearTime()).toString() + ", ");
        out.print("width " + shape.getPara1At(shape.getAppearTime()));
        out.println("and height " + shape.getPara2At(shape.getAppearTime()));
      } else { //when shape is an ellipse
        out.print("with center at " + shape.getPositionAt(shape.getAppearTime()) + ", ");
        out.print("radius " + shape.getPara1At(shape.getAppearTime()));
        out.println(" and " + shape.getPara2At(shape.getAppearTime()));
      }
    }
  }

  public void showShapeAppearance(List<IShape> listOfShapes) {
    for (IShape shape : listOfShapes) {
      out.print(shape.getName() + " appears at time t=" + shape.getAppearTime());
      out.println(" and disappears at time t=" + shape.getDisappearTime());
    }
  }

  public void showMotions(List<IShape> listOfShapes) {
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
    this.showShapeAppearance(animation.getListOfShapes());
    this.showMotions(animation.getListOfShapes());
  }

  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    LinkedList<IShape> oneShape = new LinkedList<>();
    for (IShape x : animation.getListOfShapes()) {
      if (x.equals(shapeName)) {
        oneShape.add(x);
        break;
      }
    }
    this.showShapeInitials(oneShape);
    this.showShapeInitials(oneShape);
    this.showMotions(oneShape);
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  public PrintStream getOutput() {
    return out;
  }
}
