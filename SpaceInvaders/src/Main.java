
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.Input;

import java.awt.Font;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Main extends StateBasedGame {
	
	public Main(String gamename)
	{
		super(gamename);
	}
	
	static public class Globals{
		static public int gridWidth=10;
		static public int gridHeight=8;
		static public int scrHeight=800;
		static public int scrWidth=1000;
	}
    public static final int MAINMENU = 0;
    public static final int GAME = 1;
    public static final int scale=10;
    public static double version=1.0;
    public static int score=0;
    boolean started=true;
    public Random rand=new Random();
	public static List<Integer> listScores = new ArrayList<Integer>();
	File file = new File("scores.txt");
    private AppGameContainer app;
    public class MainMenu extends BasicGameState{
    		public static final int ID = 0;
    		public Mouse mouse;
    		private Image Menu1;
    		private Image Menu2;
    		private Image Menu3;
    		private Image Menu4;
    		private Image Menu5;
    		private Image Menu6;
    		private Image Menu7;
    		private Image Menu8;
    		private Rectangle rect;
    		boolean var=false;
    		Font font1;
    		int x=350;
    		int x1=10;
    		int y3=10;
    		int y=200;
    		int y1=300;
    		int y2=400;
    		int y4=200;
    		boolean show=true;
    		boolean show1=true;
    		boolean show2=true;
    		boolean show3=false;
    		Writer wr1;
    		@Override
    		public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    				app = (AppGameContainer) gc;
    				font1= new Font("impact/impact.ttf", Font.BOLD, 20);
    				TrueTypeFont trueTypeFont = new TrueTypeFont(font1, true);
    				Menu1= new Image("Images/MENU1.png");
    				Menu2= new Image("Images/!MENU1.png");
    				Menu3= new Image("Images/MENU2.png");
    				Menu4= new Image("Images/!MENU2.png");
    				Menu5= new Image("Images/MENU3.png");
    				Menu6= new Image("Images/!MENU3.png");
    				Menu7= new Image("Images/MENU4.png");
    				Menu8= new Image("Images/!MENU4.png");
    				rect= new Rectangle(0, 0, Globals.scrWidth, Globals.scrHeight);
            		BufferedReader reader = null;
            		if(started) {
            			try {
            				reader = new BufferedReader(new FileReader(file));
            				String text = null;

            				while ((text = reader.readLine()) != null) {
            					if(text.length()>0)
            						listScores.add(Integer.parseInt(text));
            		        
            				}
            			} catch (FileNotFoundException z1) {
            				z1.printStackTrace();
            			} catch (IOException z) {
            				z.printStackTrace();
            			} finally {
            				try {
            					if (reader != null) {
            						reader.close();
            					}
            				} catch (IOException z) {
            				}
            			}
            			started=false;
            		}
        		}

    		
    		@Override
    		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    			if(show && var==false)
    				g.drawImage(Menu1, x, y);
    			else if (show==false && var==false)
    				g.drawImage(Menu2, x, y);
    			if(show1 && var==false)
    				g.drawImage(Menu3, x, y1);
    			else if (show1==false && var==false)
    				g.drawImage(Menu4, x, y1);
    			if (show2 && var==false)
    				g.drawImage(Menu5, x, y2);
    			else if (show2==false && var==false)
    				g.drawImage(Menu6, x, y2);
    			if(var) {
    				g.drawRect(0, 0, Globals.scrWidth, Globals.scrHeight);
    				g.drawString("Najlepsze rezultaty", 350, 160);
    				y4=200;
    				int index=1;
    				for(int p=listScores.size()-1;p>=0;p--) {
    					g.drawString(index+". " + listScores.get(p).toString(), 350, y4);
    					y4=y4+40;
    					index++;
    					if(index==11)
    						break;
    				}	
    				if (show3)
    					g.drawImage(Menu8, x1, y3);
    				else 
    					g.drawImage(Menu7, x1, y3);
    			}	
    		}
    		@Override
    		public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
    			try {
					Writer wr = new FileWriter("scores.txt");
					Collections.sort(listScores);
					int index =1;
					for(int p=listScores.size()-1; p>=0;p--) {
						wr.write(listScores.get(p).toString()+System.getProperty("line.separator"));
						index++;
						if(index==11)
							break;
					}	
					wr.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
    			
    			if(mouse.getX()>x && mouse.getX()<x+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y))
    				show=false;
    			else
    				show =true;
    			if(mouse.getX()>x && mouse.getX()<x+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y1)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y1))
    				show1=false;
    			else
    				show1 =true;
    			if(mouse.getX()>x && mouse.getX()<x+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y2)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y2))
    				show2=false;
    			else
    				show2 =true;
    			if(mouse.getX()>x1 && mouse.getX()<x1+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y3)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y3)&& var)
					show3=true;	
    			else 
					show3=false;
    			if(mouse.getX()>x1 && mouse.getX()<x1+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y3)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y3) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
    				var=false;
    			if(mouse.getX()>x && mouse.getX()<x+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
    				
    				sbg.enterState(1);
    			}	
    			if(mouse.getX()>x && mouse.getX()<x+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y1)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y1) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
    					var=true;		
    			}
    			
    			if(mouse.getX()>x && mouse.getX()<x+Menu1.getWidth() && mouse.getY()>((Globals.scrHeight-y2)-Menu1.getHeight())&& mouse.getY()<(Globals.scrHeight-y2) && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    				
    					
    						
    				System.exit(0);
    			}
    		}
    		@Override
    		public int getID() {
    			return MainMenu.ID;
    		}
    }
    public class Game extends BasicGameState{
    	
    		public static final int ID = 1;
    		private boolean Right;
    		private boolean Left;
    		private boolean Space;
    		private boolean Esc;
    		private boolean direction=true;
    		private boolean Pause=false;
    		private boolean isPaused=false;
    		private Image spaceShip;
    		private Image bulletImg;
    		private Image enemyShip;
    		private Image background;
    		private Image star;
    		private Image enBulletImg;
    		private Image explosion;
    		private Image pauseImg;
    		private Image health;
    		private Image shieldIcon;
    		private Image shield;
    		Writer wr1;
    		int move1=1;
    		int move5=5;
    		int index=0;
    		int enemyY=10;
    		int iter=0;
    		int timer=0;
    		int healthTimer=0;
    		int shieldTimer=0;
    		int lvl=0;
    		int expX;
    		int expY;
    		int howMany;
    		int healthY=Globals.scrHeight/2;
    		int lvlY=Globals.scrHeight/2;
    		ship newShip=new ship((Globals.scrHeight-100)/10, (Globals.scrWidth/20),100);
    		List<bullet> bulArray = new ArrayList<bullet>();
    		List <ship> enemyList = new ArrayList<ship>();
    		List <star> starList = new ArrayList<star>();
    		boolean tmp=true; 
    		boolean destr=true;
    		boolean expl;
    		boolean plyrExpl=false;
    		boolean showLvl;
    		boolean showHealth=false;
    		boolean healthHit=false;
    		boolean healthStr=false;
    		boolean shieldOn=false;
    		boolean showShield=false;
    		boolean shieldStr=false;
    		boolean shieldHit=false;
	    	boolean showFinalScore=false;
    		int numberOfShips=0;
    		int randomNum=rand.nextInt(10000)+8000;
    		int randomX=rand.nextInt(Globals.scrWidth-100);
    		int randomShieldNum=rand.nextInt(1000);
    		
    		Iterator<ship> enIt= enemyList.iterator();
			Iterator<bullet> it= bulArray.iterator();
    		@Override
    		public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    			spaceShip= new Image("Images/space.png");
    			bulletImg = new Image("Images/blue.png");
    			enemyShip= new Image("Images/enemy.png");
    			background = new Image("Images/bc.png");
    			star=new Image("Images/stars.png");
    			enBulletImg=new Image("Images/red.png");
    			explosion=new Image("Images/expl.png");
    			pauseImg=new Image("Images/pause.png");
    			health=new Image("Images/health.png");
    			shieldIcon=new Image("Images/shield.png");
    			shield = new Image("Images/shieldG.png");
    		}
    		@Override
    		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    			
    			g.drawImage(background,0,0);
    			g.drawString("Score: "+score, 250, 0);
    			g.drawString("Health: "+newShip.getHealth(), 100, 0);
    			g.drawString("[P] Pauza", 600, 0);
    			g.drawString("level: "+lvl, 800, 0);
    			
    			if(shieldOn==false)
    				g.drawImage(spaceShip,	newShip.getX()*scale, newShip.getY()*scale);
    			else
    				g.drawImage(shield,	newShip.getX()*scale-10, newShip.getY()*scale);
    			if(showShield) {
    				
    				g.drawImage(shieldIcon,randomX , 400);
    				g.drawString(""+(300-shieldTimer), randomX, 380);
    				
    			}
    			if(shieldOn) {
    				g.drawString(""+(900-shieldTimer), newShip.getX()*scale, newShip.getY()*scale-20);
    			}
    			if(shieldStr) {
    				g.drawString("Shield activated!", Globals.scrWidth/2 ,   healthY);
    				healthY=healthY-3;
    				if(healthY<=0) {
    					healthY=Globals.scrHeight/2;
    					shieldHit=false;
    					randomX=rand.nextInt(Globals.scrWidth-10);
    					shieldStr=false;
    				}	
    			}
    			if(showHealth) {
    				
    				g.drawImage(health,randomX , 400);
    				g.drawString(""+(200-healthTimer), randomX, 380);
    				
    			}
    			if(healthStr) {
    				g.drawString("Health restored!", Globals.scrWidth/2 ,   healthY);
    				healthY=healthY-3;
    				if(healthY<=0) {
    					healthY=Globals.scrHeight/2;
    					healthHit=false;
    					randomX=rand.nextInt(Globals.scrWidth-10);
    					healthStr=false;
    				}	
    			}
			if(showFinalScore) {
    				g.drawString("You lost!!! Your final score: "+score + "\n*Press Enter to continue* ",Globals.scrWidth/2 ,Globals.scrHeight/2);
    			}
    			if(expl) {
    				g.drawImage(explosion, expX*scale, expY*scale);
    				expl=false;
    			}
    			if(plyrExpl) {
    				g.drawImage(explosion, newShip.getX()*scale, newShip.getY()*scale-10);
    				plyrExpl=false;
    			}
    				
    			if(showLvl&& lvl!=0) {
    				
    				g.drawString("level "+ lvl, Globals.scrWidth/2 ,   lvlY);
    				lvlY=lvlY-3;
    				if(lvlY<=0) {
    					showLvl=false;
    					lvlY=Globals.scrHeight/2;
    				}	
    			}
    			for(int f=0; f<bulArray.size(); f++) {
					bullet obj= bulArray.get(f);
					if(obj.isEnemy==false)
						g.drawImage(bulletImg, obj.getX()*scale, obj.getY()*scale);
					else {
						g.drawImage(enBulletImg, obj.getX()*scale, obj.getY()*scale);
					}
						
    		
    			}
    			for(int f=0; f<starList.size(); f++) {
					star obj= starList.get(f);
					g.drawImage(star,	obj.getX()*scale, obj.getY()*scale);
    			}	
    			for(int f=0; f<enemyList.size(); f++) {
					ship obj=enemyList.get(f);
					g.drawImage(enemyShip,	obj.getX()*scale, obj.getY()*scale);
					
    			}
    			if(isPaused && showFinalScore==false)
    				g.drawImage(pauseImg, Globals.scrWidth/4, Globals.scrHeight/2);
    		}	
    		@Override
    		public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
    				if(healthTimer==randomNum) {
    					showHealth=true;
    					healthTimer=0;
    				}
    				if(healthTimer==200 || healthHit) {
    					showHealth=false;
    					randomNum=rand.nextInt(10000);
    					healthHit=false;
    				
    				}
    				if(shieldTimer==randomShieldNum) {
    					showShield=true;
    					shieldTimer=0;
    				}
    				if(shieldTimer==300 || shieldHit) {
    					randomShieldNum=rand.nextInt(10000);
    					showShield=false;
    					shieldHit=false;
    				}
    				if(shieldTimer==900 && shieldOn) {
    					shieldOn=false;
    				}
    					
    				if(enemyList.isEmpty()) {
    					destr=true;
    					showLvl=true;
    				}	
    				if(destr&&timer ==30) {
    					direction=true;
    					lvl++;
    				}	
    				if(destr&& timer ==30 && isPaused==false) {
    					int l=0;
    					howMany=lvl;
    					if(lvl>5)
    						howMany=4;
    					for(int j=0; j<howMany; j++) {
    						for( l=0; l<(Globals.scrWidth/10)-10;l=l+5){
    						
    							ship enemy = new ship(enemyY,l,5);
    							enemyList.add(enemy);
    							numberOfShips++;
    						}
    						l=0;
    						enemyY=enemyY+5;
    						
    					}
    					enemyY=10;
    					destr=false;
    				}
    				if((timer==10 || timer == 20)&&isPaused==false) {
    					for(int p=0;p<5;p++) {
    						star obj =new star (0, index=rand.nextInt(Globals.scrWidth/10));
    						starList.add(obj);
    					}
    				}
    				
    				if(timer==30&&isPaused==false&& enemyList.isEmpty()==false) {
    					for(int p=0;p<lvl/2;p++) {
    						index= rand.nextInt(enemyList.size());
    						bullet bul1 =new bullet(enemyList.get(index).getY()+1,enemyList.get(index).getX(), 5 ,true);
    						bulArray.add(bul1);
    					}
    				}
    				Iterator<bullet> it= bulArray.iterator();
    				while(it.hasNext()) {
    					bullet obj= it.next();
    					if(obj.isEnemy)
    						obj.setY(+move1);
    					else  
    						obj.setY(-move1);
    					
    					if((obj.getY()>=0 && obj.isEnemy==false) || (obj.getY() <=73 && obj.isEnemy)) {
    						int k=0;
    						while(k<numberOfShips && k<enemyList.size()) {
    							ship enemy1=enemyList.get(k);
    							if( obj.getMinY() <= enemy1.getMaxY() && obj.getMaxX()>=enemy1.getMinX()&&obj.getMinX()<=enemy1.getMaxX() && obj.isEnemy==false) {
    									enemy1.setHealth(enemy1.getHealth()-obj.getPower());
    									score=score+5;
    									if(enemy1.getHealth()==0) {
    										expX=enemy1.getX();
    										expY=enemy1.getY();
    										expl=true;
    										enemyList.remove(k);
    										numberOfShips=numberOfShips-1;
    										k=k+1;
    										score=score+(lvl*5);
    										it.remove();
    									}	
    									break;	
    							}
    							else {
    								k=k+1;
    								continue;
    							}	
    						}
    						if(obj.getMaxY()>=newShip.getMaxY() && obj.getMaxX() >= newShip.getMinX() && obj.getMinX() <= newShip.getMaxX() && obj.isEnemy&&shieldOn==false) {
    							newShip.setHealth(newShip.getHealth()- obj.getPower());
    							it.remove();
    							plyrExpl=true; 
    							if(newShip.getHealth()<= 0 ) { 
    								showFinalScore=true;
    								isPaused=true;
    								move1=0;
    	    							move5=0;
    								Enter=gc.getInput().isKeyPressed(Input.KEY_ENTER);
    								if(Enter) {
    									showFinalScore=false;
    									lvl=0;
    									listScores.add(score);
    									Collections.sort(listScores);
    									score=0;
    									gc.reinit();
    									sbg.enterState(0);
    								}
    								
    							}	
    						}
    						if(obj.getMinY() <= 400+health.getHeight() && obj.getMaxX()>=randomX && obj.getMinX()<=randomX+health.getWidth() && obj.isEnemy==false && showHealth) {
    							it.remove();
    							healthStr=true;
    							newShip.setHealth(100);
    							showHealth=false;
    							healthHit=true;
    						}
    						if(obj.getMinY() <= 400+shieldIcon.getHeight() && obj.getMaxX()>=randomX && obj.getMinX()<=randomX+shieldIcon.getWidth() && obj.isEnemy==false && showShield) {
    							it.remove();
    							shieldStr=true;
    							shieldOn=true;
    							showShield=false;
    							shieldHit=true;
    						}
    					}
    					
    					else {
    						it.remove();
    						//
    						continue;
    					}
    						
    					
    				}	
    				for(int p=0; p<starList.size();p++) {
    					star obj=starList.get(p);
    					if(obj.getY() <=70)
    						obj.setY(+move1);
    					else{
    						starList.remove(p);
    						continue;
    					}
    				}
    				if(timer==30 && isPaused==false) {
    					for(int l=0; l<enemyList.size();l++){
    						ship obj=enemyList.get(l);
    						if(direction) {
    							obj.setX(+move5);
    							obj.setX(+move5);
    						}	
    						else {
    							obj.setX(-move5);
    							obj.setX(-move5);
    						}
    					}
    					if(direction)
    						direction=false;
    					else 
    						direction=true;
    				}	
    				Pause=gc.getInput().isKeyPressed(Input.KEY_P);
    				Esc=gc.getInput().isKeyPressed(Input.KEY_ESCAPE);
    				Right=gc.getInput().isKeyPressed(Input.KEY_RIGHT);
    				Left=gc.getInput().isKeyPressed(Input.KEY_LEFT);
    				Space=gc.getInput().isKeyPressed(Input.KEY_SPACE);
    				if(Right){
    					if(newShip.getX()>=Globals.scrWidth/10-5)
    						newShip.setX(0);
    					else
    						newShip.setX(move5);
    				}
    				if(Left) {
    					if( newShip.getX()==0)
    						newShip.setX(0);
    					else
    						newShip.setX(-move5);
    				}
    				if(Esc) {
    					lvl=0;
						listScores.add(score);
						Collections.sort(listScores);
						
						score=0;
						gc.reinit();
						sbg.enterState(0);
						
    				}
    				
    				if(Space) {
    					bullet bul =new bullet(newShip.getY()-1, newShip.getX(), 5,false);
    					bulArray.add(bul);
    				}
    				if(Pause) {
    					if(isPaused&& showFinalScore==false) {
    						move1=1;
    						move5=5;
    						isPaused=false;
    					}
    					else {
    						move1=0;
    						move5=0;
    						isPaused=true;
    					}
    					
    				}
    				if(isPaused==false) {
    					shieldTimer++;
    					timer++;
    					healthTimer++;
    				}	
    				if(timer==31)
    					timer=0;
    			}
    		@Override
    		public int getID() {
    			return Game.ID;
    		}
    }
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MainMenu());
        this.addState(new Game());
    }
	
	
	static public class ship{
		private int x;
		private int y;
		private int health;
		private int maxX;
		private int minX;
		private int maxY;
		private int minY;
		public ship(int y, int x, int health) {
			this.x=x;
			this.y=y;
			this.health=health;
			this.maxX=this.x*scale+15;
			this.minX=this.x*scale-15;
			this.maxY=this.y*scale+15;
			this.minY=this.y*scale-15;
		}
		int getX(){
			return this.x;
		}
		int getY() {
			return this.y;
		}
		void setX(int x) {
			this.x=this.x+x;
			this.maxX=this.x*scale+15;
			this.minX=this.x*scale-15;
		}
		void setY(int y) {
			this.y=this.y+y;
			this.maxY=this.y*scale+15;
			this.minY=this.y*scale-15;
		}
		int getMaxX(){
			return this.maxX;
		}
		int getMinX(){
			return this.minX;
		}
		int getMaxY(){
			return this.maxY;
		}
		int getMinY(){
			return this.minY;
		}
		void setHealth(int t) {
			this.health=t;
		}
		int getHealth() {
			return this.health;
		}
		
	}
	static public class bullet{
		private int x;
		private int y;
		private int maxX;
		private int minX;
		private int maxY;
		private int minY;
		private int power;
		private boolean isEnemy;
		public bullet(int y, int x, int power, boolean isEnemy ) {
			this.x=x;
			this.y=y;
			this.power=power;
			this.isEnemy=isEnemy;
			this.maxX=x*scale+6;
			this.minX=x*scale-6;
			this.maxY=this.y*scale+6;
			this.minY=this.y*scale-6;
		}
		void setY(int y) {
			this.y=this.y+y;
			this.maxY=this.y*scale+6;
			this.minY=this.y*scale-6;
		}
		int getX(){
			return this.x;
		}
		int getMaxX(){
			return this.maxX;
		}
		int getMinX(){
			return this.minX;
		}
		int getMaxY(){
			return this.maxY;
		}
		int getMinY(){
			return this.minY;
		}
		int getY() {
			return this.y;
		}
		int getPower() {
			return this.power;
		}
	}
	static public class star{
		private int x;
		private int y;
		star(int y, int x ){
			this.x=x;
			this.y=y;
		}
		int getX() {
			return this.x;
		}
		int getY() {
			return this.y;
		}
		void setX(int x) {
			this.x=this.x+x;
		}
		void setY(int y) {
			this.y=this.y+y;
		}
	}


	public static void main(String[] args) {
			
			try
	        {
	            AppGameContainer app = new AppGameContainer(new Main("Space Invaders Version: "+version));
	            app.setDisplayMode(Globals.scrWidth, Globals.scrHeight, false);
	            app.setTargetFrameRate(60);
	            app.start();
	        }
	        catch (SlickException e)
	        {
	            e.printStackTrace();
	        }
	}

}		
