import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.fail;

public class ModelTest {
  private AnimationImpl anime = new AnimationImpl();

  @Test
  public void testAddShape() {
    anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 30, 50);

    try {
      anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 20, 10);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, -10, 10);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 5, 5);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }


    //Check null
    try {
      anime.addShape("c1", null, new Point2D(0,0), Color.BLACK, 20, 10);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      anime.addShape(null, ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 20, 10);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      anime.addShape("c1", ShapeType.CIRCLE, null, Color.BLACK, 20, 10);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), null, 20, 10);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testColor(){

  }

  @Test
  public void testMove(){

  }

  @Test
  public void testScale(){

  }
}