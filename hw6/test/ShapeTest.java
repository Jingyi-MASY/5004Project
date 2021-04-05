import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * A junit test for IShape Interface and all its subclass.
 */
public class ShapeTest {
  private IShape o1;
  private IShape c1;
  private IShape r1;

  @Before
  public void setup() {
    c1 = new Circle("c1", ShapeType.CIRCLE, new Point2D(0, 0),
            Color.BLACK, 30, 50, 10);
    o1 = new Oval("o1", ShapeType.OVAL, new Point2D(15, -15),
            Color.WHITE, 5, 5, 4, 6);
    r1 = new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(50, 50),
            Color.WHITE, 0, 20, 30, 20);
  }

  @Test
  public void testShapeConstructor() {
    IShape c1 = new Circle("c1", ShapeType.CIRCLE, new Point2D(0, 0),
            Color.BLACK, 30, 50, 10);
    IShape o1 = new Oval("o1", ShapeType.OVAL, new Point2D(15, -15),
            Color.WHITE, 5, 5, 4, 6);
    IShape r1 = new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(50, 50),
            Color.WHITE, 0, 20, 30, 20);

    //appear after disappear
    try {
      new Circle("c1", ShapeType.CIRCLE, new Point2D(0, 0),
              Color.BLACK, 20, 10, 5);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

    //negative time
    try {
      new Oval("c1", ShapeType.OVAL, new Point2D(0, 0),
              Color.BLUE, -10, 10, 20, 15);
      fail("Accepting invalid time");
    } catch (IllegalArgumentException ignored) {
    }

    //Check null
    try {
      new Rectangle("c1", null, new Point2D(0, 0),
              Color.BLACK, 10, 20, 10, 20);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new Circle(null, ShapeType.CIRCLE, new Point2D(0, 0),
              Color.BLACK, 10, 20, 200);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new Circle("c1", ShapeType.CIRCLE, null,
              Color.BLACK, 10, 20, 50);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Circle("c1", ShapeType.CIRCLE, new Point2D(0, 0),
              null, 10, 20, 50);
      fail("Accepting null");
    } catch (IllegalArgumentException ignored) {
    }

    //Circle parameter
    try {
      new Circle("c1", ShapeType.CIRCLE, new Point2D(0, 0),
              Color.BLACK, 10, 20, 0);
      fail("Accepting invalid radius");
    } catch (IllegalArgumentException ignored) {
    }

    //Triangle parameter
    try {
      new Rectangle("r1", ShapeType.RECTANGLE, new Point2D(0, 0),
              Color.BLACK, 10, 20, 0, 5);
      fail("Accepting invalid WH");
    } catch (IllegalArgumentException ignored) {
    }

    //Oval parameter
    try {
      new Oval("r1", ShapeType.OVAL, new Point2D(0, 0),
              Color.BLACK, 10, 20, 0, 5);
      fail("Accepting invalid Oval radius");
    } catch (IllegalArgumentException ignored) {
    }

    //Wrong Shape Type
    try {
      new Rectangle("r1", ShapeType.CIRCLE, new Point2D(0, 0),
              Color.BLACK, 10, 20, 5, 5);
      fail("Accepting wrong type");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testGet() {
    assertEquals("c1", c1.getName());
    assertEquals(ShapeType.RECTANGLE, r1.getType());
    assertEquals(30, c1.getAppearTime());
    assertEquals(5, o1.getDisappearTime());
    assertEquals("c1", c1.getCopy(40).getName());
    assertEquals(new Point2D(30, 50), c1.getCopy(40).getPositionAt(40));
    try {
      c1.getCopy(40).getPositionAt(30);
      fail("Out of copy time boundary");
    } catch (IllegalArgumentException ignored) {
    }

    assertEquals("Name: o1" + System.lineSeparator() +
            "Type: oval" + System.lineSeparator() +
            "Lower left corner: (15, -15), xRadius: 4, yRadius: 26 "
            + "Color: java.awt.Color[r=255,g=255,b=255]"
            + System.lineSeparator() +
            "Appears at: t=5" + System.lineSeparator() +
            "Disappears at t=5" + System.lineSeparator(), o1.toString());

    //Check Model test
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
    assertEquals(Color.WHITE, c1.getColorAt(35));
    assertEquals(Color.BLACK, c1.getColorAt(36));
    //Valid input test in ModelTest
  }

  @Test
  public void testScale() {
    c1.addScale(2, 40, 50);
    try {
      c1.addScale(3, 40, 50);
      fail("Conflict Scale");
    } catch (IllegalStateException ignored) {
    }
    try {
      c1.addScale(0, 38, 39);
      fail("Accepting Invalid scale factor");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals("scales from Radius: 10 to Radius: 15",
            c1.getDimensionChange(45, 2));

  }

  @Test
  public void testMove() {
    IShape r1Copy = r1.getCopy(5);
    r1.addMove(new Point2D(10, 10), 11, 16);
    r1.addMove(new Point2D(70, 90), 0, 10);
    try {
      r1.addMove(new Point2D(30, 30), 5, 15);
      fail("Conflict Move");
    } catch (IllegalStateException ignored) {
    }
    try {
      r1.addMove(new Point2D(30, 30), 30, 40);
      fail("Accepting Invalid scale factor");
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals(new Point2D(50, 50), r1Copy.getPositionAt(5));
    assertEquals(new Point2D(60, 70), r1.getPositionAt(5));
    assertEquals(new Point2D(50, 50), r1.getPositionAt(18));
    assertEquals("Shape r1 moves from (50, 50) to (70, 90) from t= 0 to t= 10"
            + System.lineSeparator()
            + "Shape r1 moves from (50, 50) to (10, 10) from t= 11 to t= 16"
            + System.lineSeparator(), c1.getMovementList().get(0).display());
  }
}
