package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IShape;

public interface IView {
  /**
   * this method takes in a list shape and print out in text form of the shape initials.
   * The initials of a shape includes its type, color, name, position, dimensions.
   * @param listOfShapes  a list of IShape objects, possible shapes are rectangle, ellipse and
   *                      circle.
   */
  void showShapeInitials(List<IShape> listOfShapes);
  void showShapeAppearance(List<IShape> listOfShapes);
  void showMotions(List<IShape> listOfShapes);
  //void showOptions();
  //void showStringEntry();
  //void showOptionError();
}
