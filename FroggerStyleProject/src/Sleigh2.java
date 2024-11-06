
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Sleigh2{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	//Attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;              // collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image

	public Sleigh2() {
		forward 	= getImage("/imgs/"+"sleigh2.png"); //load the image for sleigh (the obstacle)
		//don't forget to remove these as comments eventually when adding more of the pngs
		backward 	= getImage("/imgs/"+"sleigh2.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"sleigh2.png"); //load the image for Tree
		right 	    = getImage("/imgs/"+"sleigh2.png"); //load the image for Tree

		//width and height for hit box so adjust it to the png size
		width = 100;
		height = 100;
		
		//used for placement on the JFrame
		x = -width;
		y = 100;
		
		//if your movement will not be "hopping" base
		vx = 7;
		vy = 0; //velocity i the y direction does not change
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	//2nd constructor - allow setting x and y during construction
	public Sleigh2(int x, int y){
		//call the default constructor for all normal stuff
		this(); //invokes default constructor
		
		//do the specific task for THIS constructor
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		//updates the x and y
		x+=vx;
		y+=vy;	
		//for infinite scrolling - teleport to the other side
		//once it leaves the other side
		if(x > 650) {
			x = -150;
		}
		
		init(x,y);
		g2.drawImage(forward, tx, null);
		
		//draw hit box based o x, y, width, height
		//for collision detection
		if(Frame.debugging) {
			//draw hit box only if debugging
			g.setColor(Color.green);
			g.drawRect(x,  y,  width,  height);
			
		}

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}


