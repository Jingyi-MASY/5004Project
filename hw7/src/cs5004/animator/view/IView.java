package cs5004.animator.view;

import java.io.PrintStream;

import cs5004.animator.model.IAnimation;

public interface IView {
  void showAll(IAnimation animation);
  void showOneShape(IAnimation animation, String shapeName);
  void setSpeed(int speed);
  int getSpeed();
  PrintStream getOutput();
}
