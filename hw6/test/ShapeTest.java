import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class ShapeTest {
  private IShape o1;
  private IShape c1;
  private IShape r1;

  @Before
  public void setup() {
    c1 = new Circle("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 30, 50, 10);
    o1 = new Oval("o1", ShapeType.OVAL, new Point2D(15, -15), Color.WHITE, 5, 5, 4, 6);
    r1 = new Rectangle("r1", ShapeType.TRIANGLE, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20);
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

    //negative time
    try {
      new Oval("c1", ShapeType.OVAL, new Point2D(0,0), Color.BLUE, -10, 10, 20, 15);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

    //Check null
    try {
      new Rectangle("c1", null, new Point2D(0,0), Color.BLACK, 10, 20, 10, 20);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new Circle(null, ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 10, 20, 200);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new Circle("c1", ShapeType.CIRCLE, null, Color.BLACK, 10, 20, 50);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Circle("c1", ShapeType.CIRCLE, new Point2D(0,0), null, 10, 20, 50);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    //Circle parameter
    try {
      new Circle("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 10, 20, 0);
      fail("Accepting invalid radius");
    } catch (IllegalArgumentException ignored) {
    }

    //Triangle parameter
    try {
      new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(0,0), Color.BLACK, 10, 20, 0, 5);
      fail("Accepting invalid WH");
    } catch (IllegalArgumentException ignored) {
    }

    //Oval parameter
    try {
      new Oval("r1", ShapeType.OVAL, new Point2D(0,0), Color.BLACK, 10, 20, 0, 5);
      fail("Accepting invalid Oval radius");
    } catch (IllegalArgumentException ignored) {
    }

//    //Wrong Shape Type
//    try {
//      new Rectangle("r1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 10, 20, 5, 5);
//      fail("Accepting wrong type");
//    } catch (IllegalArgumentException ignored) {
//    }
  }

  @Test
  public void testGet() {
    IShape c1 = new Circle("c1", ShapeType.CIRCLE, new Point2D(0,0), Color.BLACK, 30, 50, 10);
    IShape o1 = new Oval("o1", ShapeType.OVAL, new Point2D(15, -15), Color.WHITE, 5, 5, 4, 6);
    IShape r1 = new Rectangle("r1", ShapeType.TRIANGLE, new Point2D(50, 50), Color.WHITE, 0, 20, 30, 20);
    assertEquals("c1", c1.getName());
    assertEquals(ShapeType.RECTANGLE, r1.getType());
  }

  @Test
  public void testPosition() {
    //getPosition
    assertEquals(new Point2D(50, 50), r1.getPositionAt(10));
    try {
      r1.getPositionAt(30);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      r1.getPositionAt(-5);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testColor() {
    //getColor
    assertEquals(Color.BLACK, c1.getColorAt(30));
    try {
      c1.getColorAt(20);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      c1.getColorAt(-5);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

    c1.addChangeColor(Color.WHITE, 30, 35);


  }




}
