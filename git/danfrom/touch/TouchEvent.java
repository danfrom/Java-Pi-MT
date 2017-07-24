package git.danfrom.touch;

import java.awt.Point;
import java.awt.geom.Point2D;

public class TouchEvent {
  
  private int device;
  private int touchid;
  private Point2D location;
  
  public TouchEvent (int device, int touchid, Point2D location) {
    this.device = device;
    this.touchid = touchid;
    this.location = location;
  }
  
  public Integer getDevice () {
    return this.device;
  }
  
  public Integer getTouchId () {
    return this.touchid;
  }
  
  public Point2D getLocationOnScreen () {
    return this.location;
  }
  
}
