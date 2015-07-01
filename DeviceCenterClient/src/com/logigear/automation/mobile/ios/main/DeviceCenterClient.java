package com.logigear.automation.mobile.ios.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.logigear.automation.mobile.ios.IOSDevice;
import com.logigear.automation.mobile.ios.components.ImageCanvas;

public class DeviceCenterClient {
	
	private static final int WIDTH = 480;
	private static final int HEIGHT = 800;
	
	public static void main(String[] args){
		final IOSDevice device = new IOSDevice("f32c2e1ae30ed0f9e82fc75c927c65fa1850e363");
		  
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  int nScreenWidth = (int)screenSize.getWidth();
		  final int nLeftCoor = (nScreenWidth - WIDTH)/2;
		  
		  SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	JFrame f = new JFrame("Window");
	        	    f.add(new ImageCanvas(device, WIDTH, HEIGHT));
	        	    
	        	    
	        	    f.setLocation(nLeftCoor, 0);
	        	    f.setSize(WIDTH, HEIGHT);
	        	    f.setVisible(true); 
	        	    f.requestFocus();
	            }
	        });
	}
}
