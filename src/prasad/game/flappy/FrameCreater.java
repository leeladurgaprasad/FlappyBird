package prasad.game.flappy;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.*;

import javax.swing.JFrame;

public class FrameCreater extends Canvas implements Runnable, KeyListener,
		MouseListener {

	private Thread looper;
	private Image background, tree, flikker, birdAnimate,coin;
	private Image bird[];
	private int width, height;
	private int distanceBetweentrees, treestpnt, treestpnt2;
	private int position, position2;
	private int birdNow = 0, birddelay = 0, birdPosition = 200, birdPickup = 0,
			birdAnimateDelay = 0, birdFallRate = 0,birdFlyRate = 0,birdFlytime = 0,birdDisplacement = 0,relativeDisplacement=0,previos=0;
	private boolean birdRun = false;
	private int pipeAnimateDelay = 0;
	private int birdXPosition;
	

	public FrameCreater() {

		width = 500;
		height = 500;

		bird = new Image[4];

		String mdir = this.getClass().getResource("/desert.png").toString();

		/* some mod
		 * String imageName =
		 * "C:/Documents and Settings/Administrator/My Documents/ser___/New Folder/Flappy/Downloads/desert.png"
		 * ; imageName =
		 * getClass().getResource("/images/desert.png").toString(); background =
		 * imageFromDisk(imageName); imageName =
		 * "C:/Documents and Settings/Administrator/My Documents/ser___/New Folder/Flappy/Downloads/tree.png"
		 * ; tree = imageFromDisk(imageName); imageName =
		 * "C:/Documents and Settings/Administrator/My Documents/ser___/New Folder/Flappy/Downloads/bird/0-m.gif"
		 * ; bird[0] = imageFromDisk(imageName); imageName =
		 * "C:/Documents and Settings/Administrator/My Documents/ser___/New Folder/Flappy/Downloads/bird/1-m.gif"
		 * ; bird[1] = imageFromDisk(imageName); imageName =
		 * "C:/Documents and Settings/Administrator/My Documents/ser___/New Folder/Flappy/Downloads/bird/2-m.gif"
		 * ; bird[2] = imageFromDisk(imageName); imageName =
		 * "C:/Documents and Settings/Administrator/My Documents/ser___/New Folder/Flappy/Downloads/bird/3-m.gif"
		 * ; bird[3] = imageFromDisk(imageName);
		 */

		String imageName = this.getClass().getResource("/desert.PNG").getFile()
				.toString();

		background = imageFromDisk(imageName);
		imageName = this.getClass().getResource("/tree.png").getFile()
				.toString();
		tree = imageFromDisk(imageName);
		imageName = this.getClass().getResource("/0-m.gif").getFile()
				.toString();

		bird[0] = imageFromDisk(imageName);
		imageName = this.getClass().getResource("/1-m.gif").getFile()
				.toString();
		bird[1] = imageFromDisk(imageName);
		imageName = this.getClass().getResource("/2-m.gif").getFile()
				.toString();
		bird[2] = imageFromDisk(imageName);
		imageName = this.getClass().getResource("/3-m.gif").getFile()
				.toString();
		bird[3] = imageFromDisk(imageName);
		
		/*imageName = this.getClass().getResource("/coin4.gif").getFile()
				.toString();
		coin = imageFromDisk(imageName);*/
		
		

		birdAnimate = bird[0];
		treestpnt = generateRandom(150, 420);
		treestpnt2 = generateRandom(150, 420);
		distanceBetweentrees = 170;
		position = width;
		position2 = position + (int)(width/2)+63;
		
		birdXPosition =150;

		looper = new Thread(this);
		looper.start();
		addKeyListener(this);
		addMouseListener(this);

		// Window Operations

		setSize(width, height);
		
		// pack();
		show();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		looper.resume();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		birdRun = true;
		birdFlytime = 0;
		System.out.println("clicked "+birdPosition);
		
		birdPosition = birdPosition-5;
		birddelay =0;
		previos =0;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		mypaint(getGraphics());
		looper.suspend();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void run() {
		while (true) {
			delay(1);
			repaint();
			birdAnimateDelay++;
			pipeAnimateDelay++;

			if (birdAnimateDelay > 7) {
				birdAnimateDelay = 0;
				birddelay++;
				birdNow++;
				birdFlytime++;

				
				if(birdPosition < 500) {
					
					//70-0.01*(x-84)^2
				birdDisplacement = -(int) ( 100 - 0.25 * Math.pow(birdFlytime-20,2));
				relativeDisplacement = birdDisplacement - previos; 
				
				//System.out.println(birdDisplacement+"   :"+relativeDisplacement+"   :"+birdPosition+"   :"+previos);
				
				birdPosition = birdPosition+ relativeDisplacement;
				previos = birdDisplacement;
				}else {
					
					//System.out.println(birdDisplacement+"   :"+relativeDisplacement+"   :"+birdPosition+"   :"+previos+" --else");
					
				}
				
				if (birddelay > 6 ) {
					birddelay = 0;
					birdAnimate = bird[birdNow];

				}
				if (birdNow >= 3) {
					birdNow = 0;
				}
				
				if(position <= (birdXPosition+93 )&& position >=(birdXPosition-63)){
					
					if(!(((birdPosition+50) < treestpnt) && ((birdPosition+20) > (treestpnt-distanceBetweentrees))))
					{
						
						System.out.println("hit "+birdPosition + " "+ treestpnt+" "+(treestpnt-distanceBetweentrees) );
						//mypaint(getGraphics());
						//looper.suspend();
					}
				}
				
				

			}

			if (pipeAnimateDelay > 10) {
				pipeAnimateDelay = 0;
				position = position - 2;
				position2 = position2 - 2;	
				if (position < -63) {
					position = width+63;
					treestpnt = generateRandom(150, 420);
					System.out.println("first tree st pos : "+treestpnt);
					System.out.println("1 : "+position+" 2 : "+position2);

				}
				if (position2 < -63) {
					position2 = width+63;
					treestpnt2 = generateRandom(150, 420);
					System.out.println("secon tree st pos : "+treestpnt2);
					System.out.println("1 : "+position+" 2 : "+position2);
				}
				repaint();
			}
			


		}

	}
	
	

	private void delay(int n) {
		try {
			looper.sleep(n);
		} catch (Exception e) {
			System.out.println(e);
		}
	}



	private Image imageFromDisk(String name) {

		Image image = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		image = tk.getImage(name);
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return (image);

	}

	public void paint(Graphics g) {

		g.drawImage(background, 0, 0, this);

		// first tree bottom
		g.drawImage(tree, position, treestpnt, this);
		// first tree upper
		g.drawImage(tree, position, -(355 - treestpnt + distanceBetweentrees),
				this);

		// second tree bottom
		g.drawImage(tree, position2, treestpnt2, this);
		// second tree upper
		g.drawImage(tree, position2,-(355 - treestpnt2 + distanceBetweentrees), this);

		// Bird
		g.drawImage(birdAnimate, birdXPosition, birdPosition, this);
		//g.drawImage(coin, position+100, 250, this);

	}
	
	public void mypaint(Graphics g){
		Font font = new Font("Arial",1,35);
		Color color = new Color(0.6f,0.1f,0.9f);
		g.setColor(color);
		g.setFont(font);
		g.drawString("HIT", width/2, height/2);
		delay(500);
	}
	
	
	

	public void update(Graphics g) {
		if (flikker == null)
			flikker = createImage(width, height);
		Graphics ig = flikker.getGraphics();
		paint(ig);
		g.drawImage(flikker, 0, 0, this);

	}

	public int generateRandom(int min, int max) {
		int random = -1;
		
		while (random < min) {
			random = (int) (Math.random() * max);
			
		}
		
		return random;
	}

}
