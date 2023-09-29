package com.fstates.library;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean wPressed, sPressed, aPressed, dPressed;

    @Override
    public void keyPressed(KeyEvent arg0)
    {
        
        int code = arg0.getKeyCode();

        if(code == KeyEvent.VK_W)
        {   
            wPressed = true;
        }
        if(code == KeyEvent.VK_S)
        {   
            sPressed = true;
        }
        if(code == KeyEvent.VK_A)
        {   
            aPressed = true;
        }
        if(code == KeyEvent.VK_D)
        {   
            dPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0)
    {
        int code = arg0.getKeyCode();

        if(code == KeyEvent.VK_W)
        {   
            wPressed = false;
        }
        if(code == KeyEvent.VK_S)
        {   
            sPressed = false;
        }
        if(code == KeyEvent.VK_A)
        {   
            aPressed = false;
        }
        if(code == KeyEvent.VK_D)
        {   
            dPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0)
    {

    }
}
