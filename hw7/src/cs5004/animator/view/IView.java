package cs5004.animator.view;

import cs5004.animator.IAnimation;

public interface IView {
  void showAll(IAnimation animation);
  void showOneShape(IAnimation animation, String shapeName);
}
