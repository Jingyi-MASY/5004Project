package cs5004.animator.controller;

import cs5004.animator.model.IAnimation;
import cs5004.animator.view.IView;

public class MockView implements IView {
  private StringBuilder log;
  private int uniqueCode;

  public MockView(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void showAll(IAnimation animation) {
    log.append("received animation: ").append(animation);
  }

  @Override
  public void showOneShape(IAnimation animation, String shapeName) {
    log.append("received animation: ").append(animation).append(" ");
    log.append("received shapeName: ").append(shapeName);
  }

  @Override
  public int getSpeed() {
    return uniqueCode;
  }
}
