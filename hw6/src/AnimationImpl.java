import java.awt.Color;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is an implementation of the Animation interface, it implements all operations from the
 * Animation interface. The fields of this AnimationImpl includes a listOfShapes that includes all
 * shapes that have ever appeared in this animation, and a listOfNames that includes all names of
 * all shapes that is used to verify if a name of certain new shape to be added to this animation is
 * valid.
 */
public class AnimationImpl implements IAnimation {
  private List<IShape> listOfShapes;


  /**
   * This is the constructor of this AnimationImpl class. It initialize an animation implementation
   * by creating two empty lists: listOfShapes and listOfNames.
   */
  public AnimationImpl() {
    this.listOfShapes = new LinkedList<>();
  }


  public void addShape(IShape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Null shape input.");
    }
    for (IShape n : listOfShapes) {
      if (n.getName().equals(shape.getName())) {
        throw new IllegalArgumentException("Name of this shape exists.");
      }
    }
    this.listOfShapes.add(shape);
  }

  @Override
  public void changeColor(String name, Color targetColor, int startTime, int endTime) {
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        x.addChangeColor(targetColor, startTime, endTime);
      }
    }
  }

  @Override
  public void scale(String name, int scalingFactor, int startTime, int endTime) {
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        x.addScale(scalingFactor, startTime, endTime);
      }
    }

  }

  @Override
  public void move(String name, Point2D targetPosition, int startTime, int endTime) {
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        x.addMove(targetPosition, startTime, endTime);
      }
    }
  }

  @Override
  public LinkedList<Movement> getAllMovement() {
    LinkedList<Movement> result = new LinkedList<>();
    for (IShape x : listOfShapes) {
      if (x.getMovementList() != null) {
        result.addAll(x.getMovementList());
      }
    }
    result.sort(Comparator.comparingInt(Movement::getStartTime));
    return result;
  }

  @Override
  public String displayAll() {
    StringBuilder str = new StringBuilder();
    if (getAllMovement() != null) {
      for (Movement m : getAllMovement()) {
        str.append(m.display());
      }
    }
    return str.toString();
  }

  @Override
  public LinkedList<IShape> getShapeStatusAtTime(int time) {
    if (time < 0 || time > 100) {
      throw new IllegalArgumentException("invalid time");
    }
    LinkedList<IShape> result = new LinkedList<>();
    for (IShape x : listOfShapes) {
      //filter all shapes that are appeared at moment time
      if (x.getCopy(time) != null) {
        //copy a shape at moment time to result list
        listOfShapes.add(x.getCopy(time));
      }
    }
    return result;
  }

  @Override
  public String showStatusAt(int time) {
    if (time < 0 || time > 100) {
      throw new IllegalArgumentException("invalid time");
    }
    StringBuilder str = new StringBuilder();
    if (this.getShapeStatusAtTime(time) != null) {
      for (IShape s : getShapeStatusAtTime(time)) {
        str.append(s.toString());
      }
    }
    return str.toString();
  }

  @Override
  public String showAllShapes() {
    StringBuilder str = new StringBuilder();
    if (listOfShapes != null) {
      for (IShape s : listOfShapes) {
        str.append(s.toString());
      }
    }
    return str.toString();
  }
}
