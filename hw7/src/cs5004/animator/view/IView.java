package cs5004.animator.view;

import cs5004.animator.model.IAnimation;

/**
 * this is the View interface for this project. In includes all the operations that a view should
 * support.
 */
public interface IView {

  /**
   * This methods shows all in the passed in animation, it includes the headers, shapes, motions.
   * @param animation the passed in animation to show in this view.
   */
  void showAll(IAnimation animation);

  /**
   * This methods show all the motions in the passed in animation that are related to the passed in
   * shape name.
   * @param animation  the animation to show.
   * @param shapeName  the name of the shape to show in the animation.
   */
  void showOneShape(IAnimation animation, String shapeName);

  /**
   * this set gets the speed of this view.
   * @return  speed of this view, int type.
   */
  int getSpeed();

  //PrintStream getOutput();
}
