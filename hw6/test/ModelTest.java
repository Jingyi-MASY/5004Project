import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class ModelTest {
  private AnimationImpl anime;

  @Before
  public void setup() {
    anime = new AnimationImpl();
  }

  @Test
  public void testAddShape() {
    assertEquals("", anime.showAllShapes());
    anime.addShape(new Rectangle("r1", ShapeType.TRIANGLE, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20));
    try{
      anime.addShape(null);
      fail("Accepting Null");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals(" ", anime.showAllShapes());
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

  @Test
  public void testGetMovement() {

  }

  @Test
  public void testDisplay() {

  }

}
