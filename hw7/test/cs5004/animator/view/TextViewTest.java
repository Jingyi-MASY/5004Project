package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;

import static org.junit.Assert.assertEquals;

/**
 * this is the test class for TextView, in this class it test all public methods of a TextView.
 */
public class TextViewTest {
  private IView view1;
  ByteArrayOutputStream os = new ByteArrayOutputStream();
  private final PrintStream out = new PrintStream(os);
  private IAnimation animation;

  @Before
  public void setUp() throws Exception {
    animation = new AnimationImpl(100, 100, 600, 600);
    view1 = new TextView(out);
    animation.declareShape("r1", "rectangle");
    //this motion should display outside of the canvas
    animation.addMotion("r1", 1, 0, 0, 100, 200, 0, 128, 128,
            100, 200, 200, 50, 100, 200, 150, 100);
    animation.declareShape("r2", "rectangle");
    //this motion should display exactly from the canvas
    animation.addMotion("r2", 50, 100, 200, 200, 50, 128, 100,
            100, 150, 200, 400, 50, 200, 100, 150, 200);
    animation.declareShape("e1", "ellipse");
    //this motion should display from the edge of the canvas
    animation.addMotion("e1", 1, 100, 200, 100, 200, 0, 0, 255,
            20, 250, 280, 200, 100, 255, 0, 255);
    animation.declareShape("r3", "rectangle");
    //this motion should display within the canvas
    animation.addMotion("r3", 50, 1, 300, 60, 80, 128, 128, 56,
            120, 400, 450, 200, 90, 50, 50, 96);
    animation.declareShape("e2", "ellipse");
    //this motion should display from within the canvas and move to the corner by the end
    animation.addMotion("e2", 20, 400, 450, 100, 75, 60, 80, 120,
            120, 700, 700, 60, 45, 100, 200, 186);
    animation.declareShape("r4", "rectangle");
    //this motion starts from within the canvas but move out at the end
    animation.addMotion("r4", 20, 300, 450, 100, 75, 60, 80, 120,
            120, 700, 700, 60, 45, 100, 200, 186);
  }

  @Test
  public void testShowAll() {
    view1.showAll(animation);
    assertEquals("Create Color(r=0,g=128,b=128) rectangle r1 with corner at "
            + "(0, 0), width 100 and height 200\n"
            + "Create Color(r=128,g=100,b=100) rectangle r2 with corner at "
            + "(100, 200), width 200 and height 50\n"
            + "Create Color(r=0,g=0,b=255) ellipse e1 with center at "
            + "(150, 300), radius 50 and 100\n"
            + "Create Color(r=128,g=128,b=56) rectangle r3 with corner at "
            + "(1, 300), width 60 and height 80\n"
            + "Create Color(r=60,g=80,b=120) ellipse e2 with center at "
            + "(450, 487), radius 50 and 37\n"
            + "Create Color(r=60,g=80,b=120) rectangle r4 with corner at "
            + "(300, 450), width 100 and height 75\n"
            + "\n"
            + "r1 appears at time t=1 and disappears at time t=100\n"
            + "r2 appears at time t=50 and disappears at time t=150\n"
            + "e1 appears at time t=1 and disappears at time t=20\n"
            + "r3 appears at time t=50 and disappears at time t=120\n"
            + "e2 appears at time t=20 and disappears at time t=120\n"
            + "r4 appears at time t=20 and disappears at time t=120\n"
            + "\n"
            + "e1 changes from Color(r=0,g=0,b=255) to Color(r=255,g=0,b=255) "
            + "from time t=1 to t=20\n"
            + "e1 moves from (100, 200) to (250, 280) from time t=1 to t=20\n"
            + "e1 changes xDiameter from 100 to 200 from time t=1 to t=20\n"
            + "e1 changes yDiameter from 200 to 100 from time t=1 to t=20\n"
            + "r1 changes from Color(r=0,g=128,b=128) to Color"
            + "(r=200,g=150,b=100) from time t=1 to t=100\n"
            + "r1 moves from (0, 0) to (200, 200) from time t=1 to t=100\n"
            + "r1 changes width from 100 to 50 from time t=1 to t=100\n"
            + "r1 changes height from 200 to 100 from time t=1 to t=100\n"
            + "r3 changes from Color(r=128,g=128,b=56) to Color"
            + "(r=50,g=50,b=96) from time t=50 to t=120\n"
            + "r3 moves from (1, 300) to (400, 450) from time t=50 to t=120\n"
            + "r3 changes width from 60 to 200 from time t=50 to t=120\n"
            + "r3 changes height from 80 to 90 from time t=50 to t=120\n"
            + "e2 changes from Color(r=60,g=80,b=120) to Color"
            + "(r=100,g=200,b=186) from time t=20 to t=120\n"
            + "e2 moves from (400, 450) to (700, 700) from time t=20 to t=120\n"
            + "e2 changes xDiameter from 100 to 60 from time t=20 to t=120\n"
            + "e2 changes yDiameter from 75 to 45 from time t=20 to t=120\n"
            + "r4 changes from Color(r=60,g=80,b=120) to Color"
            + "(r=100,g=200,b=186) from time t=20 to t=120\n"
            + "r4 moves from (300, 450) to (700, 700) from time t=20 to t=120\n"
            + "r4 changes width from 100 to 60 from time t=20 to t=120\n"
            + "r4 changes height from 75 to 45 from time t=20 to t=120\n"
            + "r2 changes from Color(r=128,g=100,b=100) to Color"
            + "(r=100,g=150,b=200) from time t=50 to t=150\n"
            + "r2 moves from (100, 200) to (200, 400) from time t=50 to t=150\n"
            + "r2 changes width from 200 to 50 from time t=50 to t=150\n"
            + "r2 changes height from 50 to 200 from time t=50 to t=150\n",
            os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testShowOneShape1() {
    view1.showOneShape(animation, "r1");
    assertEquals("Create Color(r=0,g=128,b=128) rectangle r1 with corner "
            + "at (0, 0), width 100 and height 200\n"
            + "r1 appears at time t=1 and disappears at time t=100\n"
            + "r1 changes from Color(r=0,g=128,b=128) to Color(r=200,g=150,"
            + "b=100) from time t=1 to t=100\n"
            + "r1 moves from (0, 0) to (200, 200) from time t=1 to t=100\n"
            + "r1 changes width from 100 to 50 from time t=1 to t=100\n"
            + "r1 changes height from 200 to 100 from time t=1 to t=100\n"
            + "Create Color(r=128,g=100,b=100) rectangle r2 with corner at (100, 200), "
            + "width 200 and height 50\n"
            + "Create Color(r=0,g=0,b=255) ellipse e1 with center at (150, 300), "
            + "radius 50 and 100\n"
            + "Create Color(r=128,g=128,b=56) rectangle r3 with corner at (1, 300), "
            + "width 60 and height 80\n"
            + "Create Color(r=60,g=80,b=120) ellipse e2 with center at (450, 487), "
            + "radius 50 and 37\n"
            + "Create Color(r=60,g=80,b=120) rectangle r4 with corner at (300, 450), "
            + "width 100 and height 75\n", os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testShowOneShape2() {
    view1.showOneShape(animation, "r4");
    assertEquals("Create Color(r=0,g=128,b=128) rectangle r1 with corner "
            + "at (0, 0), width 100 and height 200\n"
            + "Create Color(r=128,g=100,b=100) rectangle r2 with corner at (100, 200), "
            + "width 200 and height 50\n"
            + "Create Color(r=0,g=0,b=255) ellipse e1 with center at (150, 300), radius "
            + "50 and 100\n"
            + "Create Color(r=128,g=128,b=56) rectangle r3 with corner at (1, 300), "
            + "width 60 and height 80\n"
            + "Create Color(r=60,g=80,b=120) ellipse e2 with center at (450, 487), "
            + "radius 50 and 37\n"
            + "Create Color(r=60,g=80,b=120) rectangle r4 with corner at (300, 450), "
            + "width 100 and height 75\n"
            + "r4 appears at time t=20 and disappears at time t=120\n"
            + "r4 changes from Color(r=60,g=80,b=120) to Color(r=100,g=200,"
            + "b=186) from time t=20 to t=120\n"
            + "r4 moves from (300, 450) to (700, 700) from time t=20 to t=120\n"
            + "r4 changes width from 100 to 60 from time t=20 to t=120\n"
            + "r4 changes height from 75 to 45 from time t=20 to t=120\n",
            os.toString(StandardCharsets.UTF_8));
  }

  @Test
  public void testShowOneShape3() {
    view1.showOneShape(animation, "e2");
    assertEquals("Create Color(r=0,g=128,b=128) rectangle r1 with "
            + "corner at (0, 0), width 100 and height 200\n"
            + "Create Color(r=128,g=100,b=100) rectangle r2 with corner at "
            + "(100, 200), width 200 and height 50\n"
            + "Create Color(r=0,g=0,b=255) ellipse e1 with center at "
            + "(150, 300), radius 50 and 100\n"
            + "Create Color(r=128,g=128,b=56) rectangle r3 with corner at "
            + "(1, 300), width 60 and height 80\n"
            + "Create Color(r=60,g=80,b=120) ellipse e2 with center at"
            + " (450, 487), radius 50 and 37\n"
            + "e2 appears at time t=20 and disappears at time t=120\n"
            + "e2 changes from Color(r=60,g=80,b=120) to Color"
            + "(r=100,g=200,b=186) from time t=20 to t=120\n"
            + "e2 moves from (400, 450) to (700, 700) from time t=20 to t=120\n"
            + "e2 changes xDiameter from 100 to 60 from time t=20 to t=120\n"
            + "e2 changes yDiameter from 75 to 45 from time t=20 to t=120\n"
            + "Create Color(r=60,g=80,b=120) rectangle r4 with corner at "
            + "(300, 450), width 100 and height 75\n", os.toString(StandardCharsets.UTF_8));
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeShowOneShape1() {
    view1.showOneShape(null, "r1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeShowOneShape2() {
    view1.showOneShape(animation, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeShowOneShape3() {
    view1.showOneShape(animation, "badName");
  }
}