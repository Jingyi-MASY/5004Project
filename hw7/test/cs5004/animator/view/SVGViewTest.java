package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;

public class SVGViewTest {
  private IView view1;

  @Before
  public void setUp() {
    IAnimation animation;
    animation = new AnimationImpl(100, 100, 800, 800);
    animation.declareShape("r1", "rectangle");
    animation.addMotion("r1", 0, 100, 100, 100, 200, 50, 50, 50,
            100, 200, 200, 50, 100, 100, 100, 100);
    animation.declareShape("r2", "rectangle");
    animation.addMotion("r2", 50, 100, 200, 200, 50, 100, 100, 100,
            150, 100, 400, 50, 200, 200, 200, 200);
    animation.declareShape("r3", "rectangle");
    animation.declareShape("e1", "ellipse");
    animation.declareShape("e2", "ellipse");
    animation.declareShape("e3", "ellipse");
    view1.setSpeed(100);
  }

  @Test
  public void showAll() {

  }

  @Test
  public void showOneShape() {
  }

  @Test
  public void setSpeed() {
  }

  @Test
  public void getSpeed() {
  }
}