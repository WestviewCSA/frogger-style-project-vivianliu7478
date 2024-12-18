import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	static boolean debugging = true;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//elf object
	Elf elf = new Elf();
	//Elf elf2 = new Elf(100, 200);

	//make the object for background later
	
	//a row of _____-Scrolling objects
	//sleigh1
	Sleigh[] row1 = new Sleigh[10];
	ArrayList<Sleigh> row1List = new ArrayList<Sleigh>();
	//sleigh2
	Sleigh2[] row2 = new Sleigh2[10];
	ArrayList<Sleigh2> row2List = new ArrayList<Sleigh2>();
	//present
	Present[] row3 = new Present[10];
	ArrayList<Present> row3List = new ArrayList<Present>();
	
	//frame width/height
	static int width = 600;
	static int height = 800;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		//paint the other objects on the screen
		elf.paint(g);
		//elf2.paint(g);
		
		
		
		//have the row1 objects paint on the screen
		//for each obj in row1
		//sleigh1
		for(Sleigh obj : row1) { //for every Sleigh object in row1 array
			obj.paint(g);
		}
		
		//sleigh2
		for(Sleigh2 obj : row2) {//for every Sleigh2 object in row2 array
			obj.paint(g);
		}
		
		//present
		for(Present obj : row3) {//for every Present object in row3 array
			obj.paint(g);
		}
		for(Present obj : row3List) {//for every Present object in row3 ArrayList
			obj.paint(g);
		}

	//collision detection
	//for each Sleigh object in row1 array
	for(Sleigh obj : row1) {
		//invoked the collided method for your
		//class - pass the main character
		//as your argument
		if(obj.collided(elf)) {
			System.out.println("ow!");
		}
		
	}
	
	for(Sleigh2 obj : row2) {
		//invoked the collided method for your
		//class - pass the main character
		//as your argument
		if(obj.collided(elf)) {
			System.out.println("ow!");
		}
		
	}
	
	//boolean riding = false;
	//for(Sleigh obj : row1){
		//if(obj.collided(mainDuck)){
			//mainDuck.setVx(obj.getVx());
			//riding = true;
			//break;
	//}
//}
	/*
	 * main character stops moving if not a rideable object
	 * but also let's limit it in the Y
	 */
	//if(!riding && mainDuck.getY() > 300){
		//mainDuck.setVx(0);
		//mainDuck.x = 150;
		//mainDuck.y = 100;
	/*
	 * if ! riding any the elf is in the "Water" area 
	 * reset back to starting
	 */

	
			
			
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();
		
		/*
		 * Set up any 1D array here - create the objects that go in them
		 * 
		 */
		//traverse the array
		//sleigh1
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new Sleigh(i*220 ,300);
		}
		
		//practice row for ArrayList
		for(int i = 0; i < 3; i++) {//run the body 10x
			this.row1List.add(new Sleigh(i*180, 100));
		}
		//sleigh2
		for(int i = 0; i < row2.length; i++) {
			row2[i] = new Sleigh2(i*220, 500);
		}
		//present
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new Present(i*210, 50);
		}
	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0);
		/*
		 * add this if you do rideable object for mouse function
		 */
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()== 87) {
			//move main character up
			elf.move(0);
		}else if(arg0.getKeyCode()== 83) {
			//move character down
			elf.move(1);
		}
		if(arg0.getKeyCode()== 65) {
			//move main character up
			elf.move(2);
		}else if(arg0.getKeyCode()== 68) {
			//move character down
			elf.move(3);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


