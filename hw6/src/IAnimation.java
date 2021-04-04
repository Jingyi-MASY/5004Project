import java.awt.*;
import java.util.LinkedList;

public interface IAnimation {
  public LinkedList<IShape> addShape(String name, ShapeType type, Point2D position, Color color, int appearTime,
                                     int disappearTime);

  public void changeColor(IShape shape, Color targetColor, int startTime, int endTime);

  public void scale(IShape shape, int scalingFactor, int startTime, int endTime);

  public void Move(IShape shape, Point2D targetPosition, int startTime, int endTime);

  public LinkedList<Movement>[] getAnimationTimeline();

  public LinkedList<IShape>[] getShapeStatusTimeline();

  public LinkedList<Shape> getShapeStatusAtTime();

}
