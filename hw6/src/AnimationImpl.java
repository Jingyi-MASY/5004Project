import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AnimationImpl implements IAnimation{
  private List<IShape> listOfShapes;
  //private LinkedList<Movement>[] animationTimeline;
  //private LinkedList<IShape>[] shapeStatusTimeline;
  private List<IShape> statusAtTime;

  public AnimationImpl() {
    this.listOfShapes = new LinkedList<IShape>();
    //this.animationTimeline = new LinkedList<Movement>(); // [[m1, m10, m11], [m2, m4], m3, ...]
    //this.shapeStatusTimeline = ;//[[R1, R2, O1],[R1, R2, O1, O2],...]; [100][]
    statusAtTime = new LinkedList<IShape>();
  }

  @Override
  public LinkedList<IShape> addShape(String name, ShapeType type, Point2D position, Color color,
                                     int appearTime, int disappearTime) {
    return null;
  }

  @Override
  public void changeColor(IShape shape, Color targetColor, int startTime, int endTime) {

  }

  @Override
  public void scale(IShape shape, int scalingFactor, int startTime, int endTime) {

  }

  @Override
  public void Move(IShape shape, Point2D targetPosition, int startTime, int endTime) {

  }

  @Override
  public LinkedList<Movement>[] getAnimationTimeline() {
    return new LinkedList[0];
  }

  @Override
  public LinkedList<IShape>[] getShapeStatusTimeline() {
    return new LinkedList[0];
  }

  @Override
  public LinkedList<Shape> getShapeStatusAtTime() {
    return null;
  }
}
