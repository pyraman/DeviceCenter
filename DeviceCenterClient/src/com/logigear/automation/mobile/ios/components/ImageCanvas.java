package com.logigear.automation.mobile.ios.components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.RenderedImage;

import javax.media.jai.PlanarImage;
import javax.swing.JPanel;

import com.logigear.automation.mobile.ios.IOSDevice;
import com.logigear.automation.mobile.ios.services.ScreenshotService;
import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;

@SuppressWarnings("serial")
public class ImageCanvas extends JPanel implements Runnable{
	
	private IOSDevice device;
	private ScreenshotService service;
	private Image image;
	private int imageWidth;
	private int imageHeight;
	
	private Thread thread;
	private boolean running;
	
	//METHODS   
	public synchronized void start (){
	    running = true;
	    try{
		    service = new ScreenshotService(device);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    thread = new Thread (this);
	    thread.start();
	}

	public synchronized void stop (){
	    running  = false;
	    try {
	    	thread.join();
	    	device.free();
	        System.out.println("The game stopped");
	    } 
	    catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	//INIT
	 public ImageCanvas(IOSDevice device, int nWidth, int nHeight){
		 this.device = device;
		 imageWidth = nWidth;
		 imageHeight = nHeight;
		 
		 start();
    }
	
	//MAIN RUN METHOD
	 
	@Override
	public void run() {
		while (running){
			long nStartTime = System.currentTimeMillis();
			
			load();
			repaint();
			
			long nStopTime = System.currentTimeMillis();
			long nDuration = nStopTime - nStartTime;
			
			int nFrameRate = (int)(1000/nDuration);
			
			System.out.println("Frame Rate: " + nFrameRate);
	      }
	}
	
		//PAINT WITH GRAPHICS METHOD
	  public void paint (Graphics g){
		  super.paint(g);
		  g.drawImage(image, 0, 0, this);

	  }
	
	  //LOAD IMAGES IN MEMORY
	  public void load (){
		  try{
			  byte[] bytes = service.takeScreenshot();
			  if(bytes == null){
				  return;
			  }
			  image = createImageFromTiff(bytes);
			  image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_AREA_AVERAGING);
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	  }
	  
	  private Image createImageFromTiff(byte[] data){
		  	Image image = null;
		  	try{
		  		SeekableStream stream = new ByteArraySeekableStream(data);
			    String[] names = ImageCodec.getDecoderNames(stream);
			    ImageDecoder dec = 
			      ImageCodec.createImageDecoder(names[0], stream, null);
			    RenderedImage im = dec.decodeAsRenderedImage();
			    image = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
			    return image;
		  	}
		  	catch(Exception e){
		  		e.printStackTrace();
		  		return image;
		  	}
		    
	  }
}
