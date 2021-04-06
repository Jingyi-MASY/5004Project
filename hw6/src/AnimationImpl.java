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

  @Override
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
    if (targetColor == null) {
      throw new IllegalArgumentException("target color is invalid");
    }
    boolean found = false;
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        found = true;
        x.addChangeColor(targetColor, startTime, endTime);
      }
    }
    if (!found) {
      throw new IllegalArgumentException("not found shape name");
    }
  }

  @Override
  public void scale(String name, int scalingFactor, int startTime, int endTime) {
    if (scalingFactor <= 0) {
      throw new IllegalArgumentException("invalid scaling factor");
    }
    boolean found = false;
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        found = true;
        x.addScale(scalingFactor, startTime, endTime);
      }
    }
    if (!found) {
      throw new IllegalArgumentException("not found shape name");
    }


  }

  @Override
  public void move(String name, Point2D targetPosition, int startTime, int endTime) {
    if (targetPosition == null) {
      throw new IllegalArgumentException("invalid target position");
    }
    boolean found = false;
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        found = true;
        x.addMove(targetPosition, startTime, endTime);
      }
    }
    if (!found) {
      throw new IllegalArgumentException("not found shape name");
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
    for (int i = 0; i < listOfShapes.size(); i++) {
      //filter all shapes that are appeared at moment time
      if (listOfShapes.get(i).getCopy(time) != null) {
        //copy a shape at moment time to result list
        listOfShapes.add(listOfShapes.get(i).getCopy(time));
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
    for (int i = 0; i < listOfShapes.size(); i++) {
      if(time >= listOfShapes.get(i).getAppearTime()
              && time < listOfShapes.get(i).getDisappearTime()) {
        str.append(listOfShapes.get(i).getCopy(time).toString());
      }
    }
    return str.toString();
  }

  @Override
  public String showAllShapes() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < listOfShapes.size(); i++) {
      str.append(listOfShapes.get(i).toString());
    }
    return str.toString();
  }
}
