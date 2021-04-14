package cs5004.animator.view;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import cs5004.animator.IShape;
import cs5004.animator.Movement;
import cs5004.animator.ShapeType;

public class TextView implements IView{
  private PrintStream out;

  public TextView(PrintStream out) {
    this.out = out;
  }

  @Override
  public void showShapeInitials(List<IShape> listOfShapes) {
    for (IShape shape : listOfShapes) {
      out.print("Create " + shape.getColor().toString() + " " + shape.getType().toString() + " ");
      out.print(shape.getName() + " ");
      if (shape.getType() == ShapeType.RECTANGLE) {//when shape is a rectangle
        out.print("with corner at " + shape.getPosition().toString() + ", ");
        out.print("width " + shape.getPara1());
        out.println("and height " + shape.getPara2());
      } else { //when shape is a circle or oval
        out.print("with center at " + shape.getPosition() + ", ");
        if (shape.getType() == ShapeType.CIRCLE) { //shape is a circle
          out.println("radius " + shape.getPara());
        } else { // shape is a ellipse
          out.println("radius " + shape.getPara1() + " " + shape.getPara2());
        }
      }
    }
  }

  @Override
  public void showShapeAppearance(List<IShape> listOfShapes) {
    for (IShape shape : listOfShapes) {
      out.print(shape.getName() + " appears at time t=" + shape.getAppearTime());
      out.println(" and disappears at time t=" + shape.getDisappearTime());
    }

  }

  @Override
  public void showMotions(List<IShape> listOfShapes) {
    LinkedList<Movement> motionList = new LinkedList<>();
    for (IShape x : listOfShapes) {
      if (x.getMovementList() != null) {
        motionList.addAll(x.getMovementList());
      }
    }
    motionList.sort(Comparator.comparingInt(Movement::getStartTime));

    for (Movement m : motionList) {
      out.print(m.display());
    }
  }
}
