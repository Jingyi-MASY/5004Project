import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.fail;

public class ShapeTest {

  @Before
  public void setup(){

  }

  @Test
  public void testShapeConstructor() {
    IShape c1 = new Circle("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 30, 50, 10);
    IShape o1 = new Oval("o1", ShapeType.OVAL, new Point2D(15, -15), Color.WHITE, 5, 5, 4, 6);
    IShape r1 = new Rectangle("r1", ShapeType.TRIANGLE, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20);

    //appear after disappear
    try {
      new Circle("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 20, 10, 5);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

//    //negative time
//    try {
//      new Oval("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, -10, 10);
//      fail("Accepting invalid time");
//    } catch (IllegalArgumentException ignored) {
//    }
//
//    //same appear and disappear time
//    try {
//      anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 5, 5);
//      fail("Accepting invalid time");
//    } catch (IllegalArgumentException ignored) {
//    }
//
//
//    //Check null
//    try {
//      anime.addCircle("c1", null, new Point2D(0,0), Color.BLACK, 20, 10, 10);
//      fail("Accepting null");
//    } catch (IllegalArgumentException ignored) {
//    }
//
//    try {
//      anime.addShape(null, ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 20, 10);
//      fail("Accepting null");
//    } catch (IllegalArgumentException ignored) {
//    }
//
//    try {
//      anime.addShape("c1", ShapeType.CIRCLE, null, Color.BLACK, 20, 10);
//      fail("Accepting null");
//    } catch (IllegalArgumentException ignored) {
//    }
//    try {
//      anime.addShape("c1", ShapeType.CIRCLE, new Point2D(0,0), null, 20, 10);
//      fail("Accepting null");
//    } catch (IllegalArgumentException ignored) {
//    }
  }

}
