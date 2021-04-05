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
//  private List<String> listOfNames;


  /**
   * This is the constructor of this AnimationImpl class. It initialize an animation implementation
   * by creating two empty lists: listOfShapes and listOfNames.
   */
  public AnimationImpl() {
    this.listOfShapes = new LinkedList<>();
//    this.listOfNames = new LinkedList<>();
  }

  /*
   * This is a helper function that helps to verify if the name, type, position, color, appearance
   * time of the shape are valid or not.
   * This method returns true if they are all valid, throw an error otherwise.
   */
//  protected boolean checkShapeValidity(String name, ShapeType type, Point2D position, Color color,
//                                       int appearTime, int disappearTime) {
//    if (name == null) {
//      throw new IllegalArgumentException("invalid name");
//    } else if (type == null) {
//      throw new IllegalArgumentException("invalid shape type");
//    } else if (position == null) {
//      throw new IllegalArgumentException("invalid position");
//    } else if (color == null) {
//      throw new IllegalArgumentException("invalid color");
//    } else if (appearTime < 0 || disappearTime > 100 || disappearTime < appearTime) {
//      throw new IllegalArgumentException("invalid appearance time");
//    }
//    for (String n : listOfNames) {
//      if (n.equals(name)) {
//        throw new IllegalArgumentException("Name of this shape exists.");
//      }
//    }
//    return true;
//  }

  /*
   * this is a helper function that helps to verify if the radius of a circle shape is valid.
   * this methods return true if valid, throws an error otherwise.
   */
//  protected boolean checkCircleRadius(int radius) {
//    if (radius < 0) {
//      throw new IllegalArgumentException("invalid radius");
//    }
//    return true;
//  }
//
//  /*
//   * this is a helper function that helps to verify if the xRadius and yRadius of a oval shape is
//   * valid. this methods return true if valid, throws an error otherwise.
//   */
//  protected boolean checkOvalRadius(int xRadius, int yRadius) {
//    if (xRadius < 0 || yRadius < 0) {
//      throw new IllegalArgumentException("invalid xRadius or yRadius");
//    }
//    return true;
//  }
//
//  /*
//   * this is a helper function that helps to verify if the width and height of a rectangle shape is
//   * valid. this methods return true if valid, throws an error otherwise.
//   */
//  protected boolean checkRectangleWH(int width, int height) {
//    if (width < 0 || height < 0) {
//      throw new IllegalArgumentException("invalid width or height");
//    }
//    return true;
//  }


  public void addShape(String name, IShape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Null shape input.");
    }
    for (IShape n : listOfShapes) {
      if (n.getName().equals(name)) {
        throw new IllegalArgumentException("Name of this shape exists.");
      }
    }
    this.listOfShapes.add(shape);
  }


//  @Override
//  public void addCircle(String name, ShapeType type, Point2D position, Color color, int appearTime,
//                         int disappearTime, int radius) throws IllegalArgumentException {
//    //check validity
//    checkShapeValidity(name, type, position, color, appearTime, disappearTime);
//    checkCircleRadius(radius);
//    this.listOfNames.add(name);
//    this.listOfShapes.add(new Circle(name, type, position, color, appearTime,
//            disappearTime, radius));
//
//  }
//
//  @Override
//  public void addOval(String name, ShapeType type, Point2D position, Color color, int appearTime,
//                      int disappearTime, int xRadius, int yRadius)
//          throws IllegalArgumentException {
//    //check validity
//    checkShapeValidity(name, type, position, color, appearTime, disappearTime);
//    checkOvalRadius(xRadius, yRadius);
//    this.listOfNames.add(name);
//    this.listOfShapes.add(new Oval(name, type, position, color, appearTime, disappearTime,
//            xRadius, yRadius));
//  }
//
//  @Override
//  public void addRectangle(String name, ShapeType type, Point2D position, Color color,
//                           int appearTime, int disappearTime, int width, int height)
//          throws IllegalArgumentException {
//    //check validity
//    checkShapeValidity(name, type, position, color, appearTime, disappearTime);
//    checkRectangleWH(width, height);
//    this.listOfNames.add(name);
//    this.listOfShapes.add(new Oval(name, type, position, color, appearTime, disappearTime,
//            width, height));
//  }

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
