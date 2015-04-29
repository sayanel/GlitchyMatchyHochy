//======================================
// Company : GlitchyMatchyHochy
// Project : GlitchyMatchyHochy
// Author :  Mehdi-Antoine 
// Date :    27/04/2015.
//======================================

package com.fatman.graphics;
import java.util.ArrayList;

public interface Drawable {
    public void setDrawer(Drawer d);
    public Drawer getDrawer();
    public void notifyChanges();
}
