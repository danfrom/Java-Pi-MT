package git.danfrom.touch;

import java.awt.geom.Point2D;

public class TouchEvent {
  
  private int device;
  private int touchid;
  private Point2D localeLocation;
  private Point2D locationOnScreen;
  
  public TouchEvent (int device, int touchid, Point2D localeLocation, Point2D locationOnScreen) {
    this.device = device;
    this.touchid = touchid;
    this.localeLocation = localeLocation;
    this.locationOnScreen = locationOnScreen;
  }
  
  public Integer getDevice () {
    return this.device;
  }
  
  public Integer getTouchId () {
    return this.touchid;
  }
  
  public Point2D getLocaleLocation () {
    return this.localeLocation;
  }
  
  public Point2D getLocationOnScreen () {
    return this.locationOnScreen;
  }
  
}
