package cs5004.animator.controller;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import cs5004.animator.view.ViewOption;

/**
 * EasyAnimator class: it serves as the controller of the project.
 */
public class MockController {
  private static Readable in;
  private static PrintStream out;
  private static MockModel model;
  private static MockView iView;
  protected int speed;

  /**
   * This is the starting point of this project. Based on the content of the passed in args, the
   * project will show view in different format to display animation.
   * @param args  passed in parameter to give command, String type.
   */
  public static void main(String[] args) throws Exception {
    if (args == null) {
      throw new IllegalArgumentException("invalid null command");
    }
    // Set default value
    int speed = 1;
    out = null;
    in = null;
    ViewOption view = null;
    String[] fields = {"-view", "-in", "-out", "-speed"};
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equalsIgnoreCase(fields[3])) {
        speed = Integer.parseInt(args[i + 1]);
      } else if (args[i].equalsIgnoreCase(fields[0])) {
        for (ViewOption v : ViewOption.values()) {
          if (v.isView(args[i + 1])) {
            view = v;
          }
        }
      }
      //Read input source, and build FileReader, if not found JFrame ErrorMessage
      else if (args[i].equalsIgnoreCase(fields[1])) {
        String filePath = "";
        //TODO:Need to change, if refactor
        filePath = new File("").getAbsolutePath()
                + "/" + args[i + 1];
        in = new FileReader(filePath);
      } else if (args[i].equalsIgnoreCase(fields[2])) {
        //TODO:Need to change, if refactor
        String filePath = new File("").getAbsolutePath()
                + '/' + args[i + 1];
        out = new PrintStream(filePath);
      }
    }
    //If no in and view parameter passed in, JPanel ErrorMessage
    if (in == null || view == null) {
      //InputMessage.message("Must provide -in and -view.");
      throw new IllegalArgumentException("Must provide -in and -view.");
    }
    if (speed <= 0) {
      //InputMessage.message("Speed must be positive int.");
      throw new IllegalArgumentException("speed must be positive");
    }
    //AnimationBuilder implementation
    //model = AnimationReader.parseFile(in, new AnimationImpl.Builder());
    model = new MockModel(new StringBuilder(), 321000);
    //Create a view;
    /*
    if (view == ViewOption.SVG) {
      view = new SVGView(out, speed);
    } else if (view == ViewOption.VISUAL) {
      view = new VisualView(speed);
    } else if (view == ViewOption.TEXT) {
      view = new TextView(out);
    } else if (view == ViewOption.PLAYBACK) {
      view = new PlayBackView(out, speed);
    }
     */
    iView = new MockView(new StringBuilder(), 123789);
    iView.showAll(model);
    if (out != null) {
      out.close();
    }
  }
  public int getSpeed() {
    return this.speed;
  }
}