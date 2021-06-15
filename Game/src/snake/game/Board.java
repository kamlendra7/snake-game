package snake.game;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Board extends JPanel implements ActionListener{
	private Image apple;
	private Image dot;
	private Image head;
	private Timer timer;
	private int dots; 
	private int apple_x;
	private int apple_y;
	private final int Random_position=20;
	private final int Dot_Size=10;
	private final int All_Dot=900;
	private final int x[]=new int[All_Dot];
	private final int y[]=new int[All_Dot];
	private boolean leftdirect=false;
	private boolean rightdirect=true;
	private boolean updirect=false;
	private boolean downdirect=false;
	 private boolean inGame=true;
	Board(){
		addKeyListener(new TApdater());	
		setBackground(Color.black);
		setPreferredSize(new Dimension(300,300));
		setFocusable(true);
		loadimages();
		initGame();
	}

	public void loadimages() {
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("snake/game/icon/apple.png"));
		apple=i1.getImage();
		ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("snake/game/icon/dot.png"));
		dot=i2.getImage();
		ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("snake/game/icon/head.png"));
		head=i3.getImage();
	}
	
	public void initGame() {
		dots=3;
		
		for(int i=0;i<dots;i++) {
			x[i]=50-i*Dot_Size;
			y[i]=50;
		}
			locateapple();
			
			timer=new Timer(140,this);
			timer.start();
	}
	public void locateapple() {
		int r=(int)(Math.random() * Random_position);
		apple_x=(r*Dot_Size);
		
		 r=(int)(Math.random() * Random_position);
		apple_y=(r*Dot_Size);
	}
	public void checkcollision() {
		for(int i=dots;i>0;i--) {
			if((i>4) &&(x[0]==x[i])&&(y[0]==y[i])){
				inGame=false;
			}
		}
		if(y[0]>= 300) {
			inGame=false;
		}
		if(x[0]>= 300) {
			inGame=false;
		}
		if(x[0]<0)
		{
			inGame=false;
		}
		if(y[0]<0)
		{
			inGame=false;
		}
		if(!inGame) {
			timer.stop();
		}
	}
	public void move() {
		for(int i=dots;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		if(leftdirect) {
			x[0]-=Dot_Size;
		}
		if(rightdirect) {
			x[0]+=Dot_Size;
		}
		if(updirect) {
			y[0]-=Dot_Size;
		}
		if(downdirect) {
			y[0]+=Dot_Size;
		}
	}
	public void checkApple() {
		if((x[0]==apple_x) &&(y[0]==apple_y)) {
			dots++;
			locateapple();
			
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		if(inGame) {
			g.drawImage(apple,apple_x,apple_y,this);
			for(int z=0;z<dots;z++) {
				if(z==0) {
					g.drawImage(head,x[z],y[z],this);	
				}else {
					g.drawImage(dot,x[z],y[z],this);
				}
			}
			Toolkit.getDefaultToolkit().sync();
		}else {
			gameover(g);
		}
		
	}
	
	public void gameover(Graphics g) {
		String msg="Game Over";
		Font font=new Font("SAN_SERIF",Font.BOLD,14);
		FontMetrics metrics=getFontMetrics(font); 	
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(msg, (300-metrics.stringWidth(msg))/2, 300/2);
	}
	public void actionPerformed(ActionEvent ae) {
		if(inGame) {
				checkApple();
				checkcollision();
				move();
		}
		repaint();
	}
	
	private class TApdater extends KeyAdapter{
		 public void keyPressed(KeyEvent e) {
		       int key=e.getKeyCode();
		       
		       if(key==KeyEvent.VK_LEFT &&(!rightdirect)) {
		       leftdirect=true;
		       updirect=false;
		       downdirect=false;
		       }
		       
		       
		       if(key==KeyEvent.VK_RIGHT &&(!leftdirect)) {
			       rightdirect=true;
			       updirect=false;
			       downdirect=false;
			       }
		       
		       if(key==KeyEvent.VK_UP &&(!downdirect)) {
			       leftdirect=false;
			       updirect=true;
			       rightdirect=false;
			    }
		       
		       if(key==KeyEvent.VK_DOWN&&(!updirect)) {
			       leftdirect=false;
			       rightdirect=false;
			       downdirect=true;
			   }
		
		 
		 }

	}
}
