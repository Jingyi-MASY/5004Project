import java.awt.*;
import java.util.LinkedList;

public interface IAnimation {
  public void addCircle(String name, ShapeType type, Point2D position, Color color, int appearTime,
                                     int disappearTime, int radius) throws IllegalArgumentException;

  public void addOval(String name, ShapeType type, Point2D position, Color color, int appearTime,
                        int disappearTime, int xRadius, int yRadius) throws IllegalArgumentException;

  public void addRectangle(String name, ShapeType type, Point2D position, Color color, int appearTime,
                      int disappearTime, int width, int height) throws IllegalArgumentException;

  public void changeColor(IShape shape, Color targetColor, int startTime, int endTime);

  public void scale(IShape shape, int scalingFactor, int startTime, int endTime);

  public void Move(IShape shape, Point2D targetPosition, int startTime, int endTime);

  public LinkedList<Movement> getAllMovement();

  public LinkedList<IShape> getShapeStatusAtTime(int time);

}
