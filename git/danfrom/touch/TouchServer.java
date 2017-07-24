package git.danfrom.touch;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TouchServer extends Thread {
  
  private TouchDelagator td = null;
  
  public TouchServer (TouchDelagator td) {
    this.td = td;
    start();
  }
  
  public void run() {
    try {
      String[] args2 = new String[] {"/bin/bash", "-c", "xinput test-xi2 --root 8"};
      Process proc = new ProcessBuilder(args2).start();
      BufferedReader stdInput = new BufferedReader(new 
           InputStreamReader(proc.getInputStream()));

      BufferedReader stdError = new BufferedReader(new 
           InputStreamReader(proc.getErrorStream()));

      // read the output from the command
      String s = null;
      //while ((s = stdInput.readLine()) != null) {

      String info = "";

      while (true) {
        s = stdInput.readLine();
        if (s.equals("EVENT type 22 (RawTouchBegin)")) {
          info += s + ".....\n";
        }
        if (s.equals("EVENT type 23 (RawTouchUpdate)")) {
          info += s + "\n";
        }
        if (s.equals("EVENT type 24 (RawTouchEnd)")) {
          info += s + "\n";
        }
        if (s != null && !info.equals("") && !s.startsWith("EVENT type")) {
          info += s + "\n";
        }
        if (s == null || s.equals("")) {
          parseRaw(info);
          info = "";
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void parseRaw (String raw) {
    String[] lines = raw.split("\n");
    
    int i;
    int l = lines.length;
    
    int device = -1; // Device id (id)
    int detail = -1; // touch id
    double _x = -0;
    double _y = -0;
    Point2D location; // The touch point
    
    for (i = 0; i < l; i++) {
      String line = lines[i].replace("    ", "");
      if (line.startsWith("device: ")) {
        device = new Integer(line.replace("device: ", "").split(" ")[0]);
      }
      if (line.startsWith("detail: ")) {
        detail = new Integer(line.replace("detail: ", "").split(" ")[0]);
      }
      if (line.startsWith("  0: ")) {
        String x = line.replace("  0: ", "").split(" ")[0];
        _x = new Double(x);
      }
      if (line.startsWith("  1: ")) {
        String y = line.replace("  1: ", "").split(" ")[0];
        _y = new Double(y);
      }
    }
    
    location = new Point2D.Double(_x, _y);
    switch (lines[0]) {
      case "EVENT type 22 (RawTouchBegin).....":
      case "EVENT type 22 (RawTouchBegin)":
        td.touchAdded(device, detail, location);
        break;
      case "EVENT type 23 (RawTouchUpdate)":
        td.touchUpdated(device, detail, location);
        break;
      case "EVENT type 24 (RawTouchEnd)":
        td.touchRemoved(device, detail, location);
        break;
    }
  }
  
}
