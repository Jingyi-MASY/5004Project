package cs5004.animator.model;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import cs5004.animator.util.AnimationBuilder;

/**
 * This class is an implementation of the Animation interface, it implements all operations from the
 * Animation interface. The fields of this AnimationImpl includes a listOfShapes that includes all
 * shapes that have ever appeared in this animation, and a listOfNames that includes all names of
 * all shapes that is used to verify if a name of certain new shape to be added to this animation is
 * valid.
 */
public class AnimationImpl implements IAnimation {
  private List<IShape> listOfShapes;
  private int[] bounds;


  /**
   * This is the constructor of this AnimationImpl class. It initialize an animation implementation
   * by creating two empty lists: listOfShapes and listOfNames.
   */
  public AnimationImpl() {
    this.listOfShapes = new LinkedList<>();
    this.bounds = new int[]{0, 0, 1000, 1000}; //default bounds value
  }

  /**
   * This is the constructor of this AnimationImpl class. It initialize an animation implementation
   * by creating two empty lists: listOfShapes and listOfNames.
   */
  public AnimationImpl(int x, int y, int width, int height) {
    this.listOfShapes = new LinkedList<>();
    this.bounds = new int[]{x, y, width, height}; //default bounds value
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.bounds[0] = x;
    this.bounds[1] = y;
    this.bounds[2] = width;
    this.bounds[3] = height;
  }

  @Override
  public int[] getBounds() {
    return this.bounds;
  }

  @Override
  public List<IShape> getListOfShapes() {
    return this.listOfShapes;
  }

  @Override
  public void declareShape(String name, String type) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("invalid string parameters");
    }
    for (IShape n : listOfShapes) {
      if (n.getName().equals(name)) {
        throw new IllegalArgumentException("Name of this shape exists.");
      }
    }
    if (type.toLowerCase().equals("rectangle")) {
      this.listOfShapes.add(new Rectangle(name, ShapeType.RECTANGLE));
    } else if (type.toLowerCase().equals("ellipse")) {
      this.listOfShapes.add(new Ellipse(name, ShapeType.ELLIPSE));
    } else {
      throw new IllegalArgumentException("invalid shape type");
    }

  }

  @Override
  public void addMotion(String name,
                        int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                        int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    //check for valid name input
    if (name == null) {
      throw new IllegalArgumentException("invalid name");
    }
    //check for valid time range
    if (t2 < t1) {
      throw new IllegalArgumentException("invalid time range");
    }
    //check if the shape with passed in name has been created in the animation
    boolean found = false;
    for (IShape x : listOfShapes) {
      if (x.getName().equals(name)) {
        found = true;
        x.updateAppearance(t1, t2); //update the appearance time of this shape

        //if color changed: addChangeColor, add to motionList; else: update color
        Color color1 = new Color(r1, g1, b1);
        Color color2 = new Color(r2, g2, b2);
        if (r1 == r2 && g1 == g2 && b1 == b2) {
          x.updateColor(color1, t1, t2);
        } else {
          x.addChangeColor(color1, color2, t1, t2);
        }

        //if position changed: addChangePosition, add to motionList; else: update position
        Point2D position1 = new Point2D(x1, y1);
        Point2D position2 = new Point2D(x2, y2);
        if (x1 == x2 && y1 == y2) {
          x.updatePosition(position1, t1, t2);
        } else {
          x.addMove(position1, position2, t1, t2);
        }

        //if para1 changed: addScalePara1, add to motionList; else: update para1
        //para1 is width for a rectangle, and a xDiameter for an ellipse
        if (w1 == w2) {
          x.updateWidth(w1, t1, t2);
        } else {
          x.addWidthScale(w1, w2, t1, t2);
        }
        //if para2 changed: addScalePara2, add to motionList; else: update para2
        //para2 is height for a rectangle, and yDiameter for an ellipse
        if (h1 == h2) {
          x.updateHeight(h1, t1, t2);
        } else {
          x.addHeightScale(h1, h2, t1, t2);
        }
        break;
      }
    }
    if (!found) {
      throw new IllegalArgumentException("not found shape name");
    }
  }


  /*
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
   */

  /*
  @Override
  public String showAllShapes() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < listOfShapes.size(); i++) {
      str.append(listOfShapes.get(i).toString());
    }
    return str.toString();
  }

  @Override
  public LinkedList<Movement> getAllMovement() {
    LinkedList<Movement> result = new LinkedList<>();
    for (IShape x : listOfShapes) {
      if (x.getMovementList() != null) {
        result.addAll(x.getMovementList());
      }
    }
    result.sort(Comparator.comparingInt(Movement::getEndTime));
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
    LinkedList<IShape> result = new LinkedList<>();
    for (int i = 0; i < listOfShapes.size(); i++) {
      //filter all shapes that are appeared at moment time
      if (time >= listOfShapes.get(i).getAppearTime()
              && time < listOfShapes.get(i).getDisappearTime()) {
        //copy a shape at moment time to result list
        result.add(listOfShapes.get(i).getCopy(time));
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
      if (time >= listOfShapes.get(i).getAppearTime()
              && time < listOfShapes.get(i).getDisappearTime()) {
        str.append(listOfShapes.get(i).getCopy(time).toString());
      }
    }
    return str.toString();
  }

   */




  public static final class Builder implements AnimationBuilder<IAnimation> {
    IAnimation animation;

    public Builder() {
      this.animation = new AnimationImpl();
    }

    @Override
    public IAnimation build() {
      return this.animation;
    }

    @Override
    public AnimationBuilder<IAnimation> setBounds(int x, int y, int width, int height) {
      animation.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> declareShape(String name, String type) {
      if (!(type.toLowerCase().equals("rectangle") || type.toLowerCase().equals("ellipse"))) {
        throw new IllegalArgumentException("invalid shape type");
      }
      this.animation.declareShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      return this;
    }

  }
}
