package git.danfrom.touch;

import git.danfrom.touch.TouchListener;
import java.util.ArrayList;

public interface Touchable {
  
  public void addTouchListener (TouchListener listener);
  public void removeTouchListener (TouchListener listener);
  public ArrayList<TouchListener> getTouchListeners ();
  public Boolean hasTouchListenersAttached();
  
}
