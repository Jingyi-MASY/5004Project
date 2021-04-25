package cs5004.animator.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for Easy Animator.
 */
public class EasyAnimatorTest {
  private EasyAnimator controller1;
  private EasyAnimator controller2;
  private IView view;
  private Readable in;
  private PrintStream out;

  @Before
  public void setUp() throws Exception {
    IAnimation model = new AnimationImpl();
    this.view = new SVGView(out, 2);
    this.controller1 = new EasyAnimator(in, out, model, view);
  }

  @Test
  public void testMain() {
    String[] x = new String[8];
    x[0] = "in";
    x[1] = "toh-3.txt";
    x[2] = "speed";
    x[3] = "3";
    x[4] = "view";
    x[5] = "text";
    x[6] = "out";
    x[7] = "test.txt";
    controller1.main(x);
    assertEquals(3, view.getSpeed());
    assertTrue(view instanceof TextView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeTestMain1() {
    EasyAnimator.main(null);
  }

  @Test(expected = NullPointerException.class)
  public void negativeTestMain2() {
    String[] x = new String[]{"-speed", "2", "view", "text", "out", "test.txt"};
    EasyAnimator.main(x);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeTestMain3() {
    String[] x = new String[8];
    x[0] = "in";
    x[1] = "toh-3.txt";
    x[2] = "speed";
    x[3] = "-10";
    x[4] = "view";
    x[5] = "text";
    x[6] = "out";
    x[7] = "test.txt";
    controller1.main(x);
  }
}