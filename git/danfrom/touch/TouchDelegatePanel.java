package git.danfrom.touch;

import git.danfrom.touch.TouchDelagator;
import git.danfrom.touch.TouchEvent;
import git.danfrom.touch.TouchListener;
import git.danfrom.touch.TouchServer;
import git.danfrom.touch.Touchable;
import java.awt.Component;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComponent;

public class TouchDelegatePanel extends javax.swing.JPanel implements TouchDelagator {

  private TouchServer ts;
  
  private HashMap<Integer, JComponent> componentForTouchStart = new HashMap<> ();
  
  public TouchDelegatePanel() {
    initComponents();
    
    ts = new TouchServer(this);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  @Override
  public void touchAdded(int device, int touch_id, Point2D location) {
    TouchEvent te = new TouchEvent(device, touch_id, location);
    Point here = getLocationOnScreen();
    Point2D inHere = new Point2D.Double(location.getX() - here.x, location.getY() - here.y);
    Point p = new Point((int)inHere.getX(), (int)inHere.getY());
    try {
      Component c = getComponentAt(p);
      if (c instanceof Touchable) {
        Touchable t = (Touchable) c;
        componentForTouchStart.put(touch_id, (JComponent)t);
        if (t.hasTouchListenersAttached()) {
          ArrayList<TouchListener> listeners = t.getTouchListeners();
          Iterator<TouchListener> i = listeners.iterator();
          while (i.hasNext()) {
            TouchListener listener = i.next();
            listener.touchStart(te);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void touchUpdated(int device, int touch_id, Point2D location) {
    TouchEvent te = new TouchEvent(device, touch_id, location);
    Point here = getLocationOnScreen();
    Point2D inHere = new Point2D.Double(location.getX() - here.x, location.getY() - here.y);
    Point p = new Point((int)inHere.getX(), (int)inHere.getY());
    //System.out.println(p);
    try {
      Component c = getComponentAt(p);
      if (c instanceof Touchable) {
        Touchable t = (Touchable) c;
        if (t.hasTouchListenersAttached()) {
          ArrayList<TouchListener> listeners = t.getTouchListeners();
          Iterator<TouchListener> i = listeners.iterator();
          while (i.hasNext()) {
            TouchListener listener = i.next();
            listener.touchUpdate(te);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void touchRemoved(int device, int touch_id, Point2D location) {
    TouchEvent te = new TouchEvent(device, touch_id, location);
    Point here = getLocationOnScreen();
    Point2D inHere = new Point2D.Double(location.getX() - here.x, location.getY() - here.y);
    Point p = new Point((int)inHere.getX(), (int)inHere.getY());
    try {
      Component c = getComponentAt(p);
      if (c instanceof Touchable) {
        Touchable t = (Touchable) c;
        if (t.hasTouchListenersAttached()) {
          ArrayList<TouchListener> listeners = t.getTouchListeners();
          Iterator<TouchListener> i = listeners.iterator();
          while (i.hasNext()) {
            TouchListener listener = i.next();
            if (((JComponent)t).equals(componentForTouchStart.get(touch_id))) {
              listener.touchTap(te);
            }
            listener.touchEnd(te);
          }
          
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}
