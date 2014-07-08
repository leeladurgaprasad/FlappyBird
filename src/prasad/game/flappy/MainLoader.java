package prasad.game.flappy;

import java.awt.AWTEvent;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainLoader extends JFrame {
	
	public static void main(String[] args) {
		new MainLoader();
	}
	 MainLoader()
	    {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	        add(new FrameCreater());
	        setResizable(false);
	        setSize(500, 500);
	        pack();
	        show();
	        
/*	 Rectangle r=new Rectangle(0,0,500,500);
	setMaximizedBounds(r);*/
	   
	  setTitle("Flappy Bird : By Prasad");
	 
	    }
	    public void processWindowEvent(WindowEvent event) {
	        if(event.getID() == WindowEvent.WINDOW_CLOSING)
	            System.exit(0);
	    }

}
