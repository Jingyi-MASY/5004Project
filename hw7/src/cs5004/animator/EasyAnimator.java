package cs5004.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;


import cs5004.animator.model.AnimationImpl;
import cs5004.animator.util.AnimationReader;

import cs5004.animator.view.IView;
import cs5004.animator.view.InputMessage;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.PlayBackView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.ViewOption;
import cs5004.animator.model.IAnimation;
import cs5004.animator.view.VisualView;

/**
 * EasyAnimator class: it serves as the controller of the project.
 */
public final class EasyAnimator {
  private static int speed;
  private static Readable in;
  private static PrintStream out;

  /**
   * This is the starting point of this project. Based on the content of the passed in args, the
   * project will show view in different format to display animation.
   * @param args  passed in paramter to give command, String type.
   */
  public static void main(String[] args) {
    // Set default value
    speed = 1;
    IView iView = null;
    out = null;
    in = null;
    ViewOption view = null;

    String[] fields = {"-view", "-in", "-out", "-speed"};

    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equalsIgnoreCase(fields[3])) {
        try {
          speed = Integer.parseInt(args[i + 1]);
        } catch (Exception e) {
          InputMessage.message("Invalid speed format.");
        }
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
        try {
          //TODO:Need to change, if refactor
          filePath = new File("").getAbsolutePath()
                 + "/hw7/code/" + args[i + 1];
          in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
          InputMessage.message(filePath + "Input File not found.");
        }
      } else if (args[i].equalsIgnoreCase(fields[2])) {
        //TODO:Need to change, if refactor
        String filePath = new File("").getAbsolutePath()
                + '/' + args[i + 1];
        try {
          out = new PrintStream(filePath);
        } catch (FileNotFoundException e) {
          InputMessage.message("Output File not found.");
        }
      }
    }
    //If no in and view parameter passed in, JPanel ErrorMessage
    if (in == null || view == null) {
      InputMessage.message("Must provide -in and -view.");
    }

    if (speed <= 0) {
      InputMessage.message("Speed must be positive int.");
    }

    //AnimationBuilder implementation
    IAnimation model = AnimationReader.parseFile(in, new AnimationImpl.Builder());
    //Create a view;
    if (view == ViewOption.SVG) {
      iView = new SVGView(out, speed);
    } else if (view == ViewOption.VISUAL) {
      iView = new VisualView(out, speed);
    } else if (view == ViewOption.TEXT) {
      iView = new TextView(out);
    } else if (view == ViewOption.PLAYBACK) {
      iView = new PlayBackView(out, speed);
    }

    iView.showAll(model);

    if (out != null) {
      out.close();
    }
  }
}
