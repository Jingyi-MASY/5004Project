package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;

public class SVGViewTest {
  private IView view1;
  ByteArrayOutputStream os = new ByteArrayOutputStream();
  private PrintStream out = new PrintStream(os);
  private IAnimation animation;

  @Before
  public void setUp() {
    animation = new AnimationImpl(100, 100, 1000, 1000);
    view1 = new SVGView(out, 100);
    animation = new AnimationImpl(0, 0, 800, 800);
    animation.declareShape("r1", "rectangle");
    animation.addMotion("r1", 1, 0, 0, 100, 200, 0, 128, 128,
            100, 200, 200, 50, 100, 200, 150, 100);
    animation.declareShape("r2", "rectangle");
    animation.addMotion("r2", 50, 100, 200, 200, 50, 128, 100, 100,
            150, 100, 400, 50, 200, 100, 150, 200);
    //animation.declareShape("r3", "rectangle");
    //animation.declareShape("e1", "ellipse");
    //animation.declareShape("e2", "ellipse");
    //animation.declareShape("e3", "ellipse");
  }

  @Test
  public void testShowAll() throws UnsupportedEncodingException {
    view1.getOutput();
    view1.showAll(animation);
    String output = os.toString(StandardCharsets.UTF_8);
    assertEquals("<svg width=\"800\" height=\"800\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"r1\" x=\"0\" y=\"0\" width=\"100\" height=\"200\" fill=\"rgb(0,128,128)" +
            "\" visibility=\"visible\" >\n" + "<animate attributeType=\"xml\" type=\"color\" " +
            "begin=\"10ms\" dur=\"990ms\" attributeName=\"fill\" from=\"rgb(0,128,128)\" " +
            "to=\"rgb(200,150,100)\" fill=\"freeze\" />\n" + "<animate attributeType=\"xml\" " +
            "type= \"move\" begin=\"10ms\" dur=\"990ms\" attributeName=\"x\" from=\"0\" to=\"200" +
            "\" fill=\"freeze\" />\n<animate attributeType=\"xml\" type= \"move\" begin=\"10ms" +
            "\" dur=\"990ms\" attributeName=\"y\" from=\"0\" to=\"200\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"10ms\" dur=\"990ms\" type=\"scale\" " +
            "attributeName=\"width\" from=\"100\" to=\"50\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"10ms\" dur=\"990ms\" type=\"scale\" " +
            "attributeName=\"height\" from=\"200\" to=\"100\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<rect id=\"r2\" x=\"100\" y=\"200\" width=\"200\" height=\"50\" fill=\"rgb(128,100," +
            "100)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" type=\"color\" begin=\"500ms\" dur=\"1000ms\" " +
            "attributeName=\"fill\" from=\"rgb(128,100,100)\" to=\"rgb(100,150,200)\" " +
            "fill=\"freeze\" />\n" + "<animate attributeType=\"xml\" type= \"move\" begin=\"500ms" +
            "\" dur=\"1000ms\" attributeName=\"x\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" type= \"move\" begin=\"500ms\" dur=\"1000ms\" " +
            "attributeName=\"y\" from=\"200\" to=\"400\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"1000ms\" type=\"scale\" " +
            "attributeName=\"width\" from=\"200\" to=\"50\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"1000ms\" type=\"scale\" " +
            "attributeName=\"height\" from=\"50\" to=\"200\" fill=\"freeze\" />\n" +
            "</rect>\n" + "\n" + "</svg>", os.toString(StandardCharsets.UTF_8));


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