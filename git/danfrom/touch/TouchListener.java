package git.danfrom.touch;

public interface TouchListener {
  
  public void touchStart (TouchEvent te);
  public void touchUpdate (TouchEvent te);
  public void touchEnd (TouchEvent te);
  public void touchTap (TouchEvent te);
  
}
