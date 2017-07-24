package git.danfrom.touch;

import java.awt.geom.Point2D;

public interface TouchDelagator {
  
  public void touchAdded (int device, int touch_id, Point2D location);
  public void touchUpdated (int device, int touch_id, Point2D location);
  public void touchRemoved (int device, int touch_id, Point2D location);
  
}
