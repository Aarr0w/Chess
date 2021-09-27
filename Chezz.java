
import javax.swing.JFrame;
//import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.net.URL;
import javax.imageio.ImageIO;


// image URL:  https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent

public class Chezz
{
    public Chezz()
    {
        JFrame frame = new MyFrame();

		frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chess");   
        frame.pack();
		frame.setVisible(true);
    }

    public static void main(String[] args)
    {
		new Chezz();
    }

    
	
	

}
class MyFrame extends JFrame implements MouseListener{

	public int xClick; int yClick;
	public boolean[][] Occupy= new boolean[8][8];
	public int[][] Checkers = new int[8][8];
	public int[][] C = new int[8][8];
	public Unit[][] Pieces = new Unit[8][8];
	public Rectangle[][] Rects = new Rectangle[8][8];
	Player P1; Player P2;
	int turn =1;
	public MyFrame()
	{	
		addMouseListener(this);
	
		P1 = new Player(-1,Color.blue);
		P1.name = "Player 1";
		P2 = new Player(1,Color.green);
		P2.name = "Player 2";
		SetRays(); SetPieces(); 
		xClick =0; yClick=0; 
	
	}


	public Dimension getPreferredSize(){
		return new Dimension(700,700);
	}

	public void paint(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0,0,800,800);

		drawBoard(g);

		
        if(P1.check && turn==1){g.setFont(new Font("TimesRoman",Font.ITALIC,40)); g.setColor(Color.red); g.drawString("CHECK",100,100);}
        
        if(P2.check && turn==-1){g.setFont(new Font("TimesRoman",Font.ITALIC,40)); g.setColor(Color.red); g.drawString("CHECK",500,100);}
       
        
      	if(P1.kong.cm && turn!=-1)
      	{g.setFont(new Font("TimesRoman",Font.BOLD,90)); g.setColor(P2.color); g.drawString(P2.name,300-P2.name.length()*15,350);
      	 g.drawString("WINS",260,410);}
		
		if(P2.kong.cm && turn!=1)
		{g.setFont(new Font("TimesRoman",Font.BOLD,90)); g.setColor(P1.color); g.drawString(P1.name,300-P1.name.length()*15,350);
		 g.drawString("WINS",260,410);}
	
        
        //g.setColor(Color.red); g.drawString("turn = "+turn,350,50);
		g.setFont(new Font("TimesRoman",Font.BOLD,30)); 
		g.setColor(P1.color); g.drawString(P1.name,20,70);
		if(turn==1){g.drawLine(20,80,120,80);}
		g.setColor(P2.color); g.drawString(P2.name,580,70);
		if(turn==-1){g.drawLine(570,80,690,80);}   
		        
         	     
       
	}
	
	public void drawBoard(Graphics g) // calls methods that display full board
	{
		makeRects(g);
		addPieces(g);		
	}
	
	public void makeRects(Graphics g) // shows board including available move/selected pieces
	{
		Color myBlue = new Color(180,200,240);
		for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		 {
		  
		  if(Checkers[y][x]==0){g.setColor(Color.white);}
		  if(Checkers[y][x]==2){g.setColor(Color.orange);}
		  if(Checkers[y][x]==3){g.setColor(Color.blue);}
		  if(Checkers[y][x]==7){g.setColor(myBlue);}
		  g.fillRect(100+(60*x),100+(60*y),60,60);
		  g.setColor(Color.black);g.drawRect(100+(60*x),100+(60*y),60,60);}
		}
		
		
    }
    
    public void addPieces(Graphics g) // puts pieces onto the board
    {
    	for(int y=0;y<8;y++)
    	{for(int x=0;x<8;x++)
    	 {if(Occupy[y][x]==true){g.setColor(Pieces[y][x].returnColor());
		  //g.drawString(String.valueOf(Pieces[y][x].returnChar()),120+(60*x),120+(60*y));
		  g.drawImage(Pieces[y][x].image,99+(60*x),99+(60*y),null);
		  }}
		}
		
	} 
	
	public void SetRays() // just draws empty white/orange chess board pattern
	{ 
		int n=0;
		for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		 {if(n%2==0){Checkers[y][x]=0; C[y][x]=0;}   // white
		  else{Checkers[y][x]=2; C[y][x]=2;}        // orange
		  if(x!=7){n++;}
		  Rects[y][x]= new Rectangle(100+(60*x),100+(60*y),60,60);
		 }
		}
  
		
    }
    
    public void SetPieces() // initializes all the pieces on the proper player's side, binds king unit to correlating pieces/players
    {
    	for(int y=0;y<8;y++)
		{Arrays.fill(Occupy[y],false);}
		

		try {
		    BufferedImage BP = ImageIO.read(getClass().getResource("/images/BlkPwn.png"));
		    BufferedImage WP = ImageIO.read(getClass().getResource("/images/WhtPwn.png"));
			BufferedImage BR = ImageIO.read(getClass().getResource("/images/BlkRk.png"));
			BufferedImage WR = ImageIO.read(getClass().getResource("/images/WhtRk.png"));
			BufferedImage Bn = ImageIO.read(getClass().getResource("/images/BlkKnght.png"));
			BufferedImage Wn = ImageIO.read(getClass().getResource("/images/WhtKnght.png"));
			BufferedImage BB = ImageIO.read(getClass().getResource("/images/BlkBshp.png"));
			BufferedImage WB = ImageIO.read(getClass().getResource("/images/WhtBshp.png"));
			BufferedImage BK = ImageIO.read(getClass().getResource("/images/BlkKng.png"));
			BufferedImage WK = ImageIO.read(getClass().getResource("/images/WhtKng.png"));
			BufferedImage BQ = ImageIO.read(getClass().getResource("/images/BlkQn.png"));
			BufferedImage WQ = ImageIO.read(getClass().getResource("/images/WhtQn.png"));


			

			for(int x=0;x<8;x++)
    	{  Pieces[1][x] = new Pawn(1,x,-1,BP); Occupy[1][x]=true; //Occupy[0][x]= true;
    	   Pieces[6][x] = new Pawn(6,x,1,WP); Occupy[6][x]=true;// Occupy[7][x]=true;  
    	}
    	Pieces[0][0] = new Rook(0,0,-1,BR);Pieces[0][7] = new Rook(0,7,-1,BR);     Occupy[0][0]=true;Occupy[0][7]=true;
    	Pieces[7][0] = new Rook(7,0,1,WR);Pieces[7][7] = new Rook(7,7,1,WR);	Occupy[7][0]=true;Occupy[7][7]=true;
    	
    	Pieces[0][1] = new Knight(0,1,-1,Bn);Pieces[0][6] = new Knight(0,6,-1,Bn);	Occupy[0][1]=true;Occupy[0][6]=true;
    	Pieces[7][1] = new Knight(7,1,1,Wn);Pieces[7][6] = new Knight(7,6,1,Wn);	Occupy[7][1]=true;Occupy[7][6]=true;
    	
    	Pieces[0][2] = new Bishop(0,2,-1,BB);Pieces[0][5] = new Bishop(0,5,-1,BB);	Occupy[0][2]=true;Occupy[0][5]=true;
    	Pieces[7][2] = new Bishop(7,2,1,WB);Pieces[7][5] = new Bishop(7,5,1,WB);	Occupy[7][2]=true;Occupy[7][5]=true;
    	
    	Pieces[0][3] = new King(0,3,-1,BK);		Occupy[0][3]=true;  P2.KingMe((King)Pieces[0][3]);
    	Pieces[7][3] = new King(7,3,1,WK);	Occupy[7][3]=true;  P1.KingMe((King)Pieces[7][3]); 
    	
    	Pieces[0][4] = new Queen(0,4,-1,BQ);	Occupy[0][4]=true;
    	Pieces[7][4] = new Queen(7,4,1,WQ);	Occupy[7][4]=true;
    	
    	for(int y=0;y<2;y++)
    	{for(int x=0;x<8;x++)
    	 {Pieces[y][x].setKing((King)Pieces[0][3]);}
    	}
    	
    	for(int y=6;y<8;y++)
    	{for(int x=0;x<8;x++)
    	 {Pieces[y][x].setKing((King)Pieces[7][3]);}
    	}


			} 
			catch (IOException e) {
			e.printStackTrace();
			} 	
    	
    }
		      
		    
    
	public void showBoard(int[][] a) // uses [][]a (selected unit's available moves) to make appropriate spots visible
	{ for(int y=0;y<8;y++)
	  {for(int x=0;x<8;x++)
		{if(a[y][x] == 3){Checkers[y][x]=3;}
	     //if(a[y][x] == 5){Checkers[y][x]=5;}
	    }
	  }	  	  
	}
	
	public void Refresh(int yC,int xC,int y,int x)   //moves occupancy of piece off of old position to new
	{Occupy[yC][xC]=false; Occupy[y][x] =true;}

	
	
	 public void mouseClicked (MouseEvent mouseEvent)  // different parts for if: new piece selected, selected piece is to move
	 {
	 	int a = mouseEvent.getX();
	 	int b = mouseEvent.getY();
	 	
	 	  for(int y=0;y<8;y++)
	     {for(int x=0;x<8;x++)
	      {
	      	
	       	 	
	      	if(Rects[y][x].contains(a,b) && Checkers[y][x]==3) // moves to available spot
	       { 
	       	 Pieces[yClick][xClick].move(y,x); 
	       	 if(Pieces[yClick][xClick].symbol =='P'){ ((Pawn)Pieces[yClick][xClick]).first = false;}
	       	 Refresh(yClick,xClick,y,x);
	         Pieces[y][x]=Pieces[yClick][xClick]; Pieces[yClick][xClick]=null;      
			 
			 //Checkers[yClick][xClick] = 0;
	       	 SetRays(); yClick=y; xClick=x;
	       	 turn=-turn; 
	       }
	       	 
	      	if(Rects[y][x].contains(a,b) && Occupy[y][x] ==true && Pieces[y][x].side == turn ) // displays move options to selected piece
		   { SetRays(); 
			 Checkers[y][x]=7; 
	         	if(turn==1)
	         	{if(!P1.check){showBoard(Pieces[y][x].smartMove(Occupy,Pieces));}
	         	 else{showBoard(Pieces[y][x].checkMove(Pieces[y][x].showMove(Occupy,Pieces),Occupy,Pieces));} 
	         	}
	         if(turn==-1)
	         	{if(!P2.check){showBoard(Pieces[y][x].smartMove(Occupy,Pieces));}
	         	 else{showBoard(Pieces[y][x].checkMove(Pieces[y][x].showMove(Occupy,Pieces),Occupy,Pieces));} 
	         	}
	         	 
	          yClick=y; xClick=x;
	       }
	          
	          	 
	        
	      }
		 }
		 
	    checkMe();
		 
		 //this.revalidate();
		 this.repaint();
	 } 
     public void mouseEntered (MouseEvent mouseEvent) {} 
     public void mousePressed (MouseEvent mouseEvent) {} 
     public void mouseReleased (MouseEvent mouseEvent) {}  
     public void mouseExited (MouseEvent mouseEvent) {}
	

	public void clear(Graphics g) //empties the frame before redrawing elements
	{g.setColor(Color.white);
	 g.fillRect(0,0,800,800);
	}

	public void checkMe()     //sets Player to inCheck if conditions are met 
	{
		boolean c = false;
	 	
	 	for(int y=0;y<8;y++)
	    {for(int x=0;x<8;x++)
			{if(Occupy[y][x]) 
	      		{if(Pieces[y][x].side!=turn)
	      	 	{  if(Pieces[y][x].isCheck(Pieces[y][x].showMove(Occupy,Pieces),Occupy,Pieces))
					{ c=true; }
			
	       	    if(!c)
					{	c = Pieces[y][x].kool.inCheck(Occupy,Pieces);}
			  }
				   }
			}
		}

		if(c)
		 {if(turn==1)
			{P1.nowCheck();
			 P1.kong.checkForMate(Occupy,Pieces);
		 }
		  if(turn==-1)
			{P2.nowCheck();
			 P2.kong.checkForMate(Occupy,Pieces);}
		 }
		 else{
			   P1.notCheck();
			   P2.notCheck();
			}

	}

}

class Player
{
	 int side;
	 String name="";
	 boolean nchosen=false;
	 boolean check=false;
	 Color color;
	 Unit[] LostUnits;
	 King kong;
	 
	 
	 
	 
	 public Player(int S,Color c)
	 {side = S; color = c;}
	 
	 public boolean NameChosen()
	 { return nchosen;}
	 
	 public void Chose()
	 {  nchosen = true;}
	 	 
	 public String returnName()
	 { return "|"+name+"|";}
	 
	 
	 public void nowCheck()
	 {check=true;}
	 public void notCheck()
	 {check=false;}
	 public void KingMe(King king)
	 { kong=king;}
	 	 		
	
}

abstract class Unit
{
	char symbol;
	BufferedImage image = null;
	int side,y,x;
	Color col;
	King kool; 
	
	public Unit(int Y,int X,int s, char c)
	{   if(s==1){col=Color.blue;}
	    else{col=Color.green;}
	    side=s;
		symbol=c;
		y=Y;x=X;
		
	}
	public void move(int newY, int newX)
	{ y=newY; x=newX;}
	public int side()
	{return side;}
	public char returnChar()
	{return symbol;}
	public Color returnColor()
	{ return col;}
	
	abstract int[][] showMove( boolean[][] Occ , Unit[][] P);
	
	public boolean isCheck(int[][] a,boolean[][] Occ, Unit[][] P)
	{   boolean b=false;
	    for(int y=0;y<8;y++)
	    {for(int x=0;x<8;x++)
			{if(Occ[y][x])
				{if(P[y][x].side!=this.side)
					{if(a[y][x]==3 && P[y][x].symbol=='K')
						{b=true;}
					}
				}
			}
		}
	    return b;
	}

	public int[][] smartMove(boolean[][] Occ, Unit [][] P)
	{
		Unit was; boolean w;
		int[][] temp = this.showMove(Occ,P);

		Occ[this.y][this.x] = false;  // temporarily 'moves' King to view and remove options behind him in threat's range
		for(int y=0;y<8;y++){for(int x=0;x<8;x++)
		{for(int q=0;q<8;q++){for(int z=0;z<8;z++){
			was = P[y][x]; w=Occ[y][x];  
			if(temp[y][x]==3)
			{
				Occ[y][x]=true;P[y][x]=this;
				if(Occ[q][z])
				{  if(P[q][z].side !=this.side && P[q][z].symbol !='K')
					{	if(P[q][z].isCheck(P[q][z].showMove(Occ,P),Occ,P))
						{temp[y][x]=0;}
					}
					
				}	
  	     		Occ[y][x]=w;P[y][x]=was;
  	
		}}}}} Occ[this.y][this.x] = true;

		return temp;
	}

	public int[][] checkMove(int[][] a,boolean[][] Occ,Unit[][] P)
	{ int[][] b = new int[8][8]; boolean t = false;
	  int[][] c = new int[8][8];
	  int n =0; 
	  ArrayList<Unit> Threats = new ArrayList();
	  Unit was;boolean w; 
	  for(int x=0;x<8;x++){Arrays.fill(c[x],0);}
	  	for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		{if(Occ[y][x]==true)
		 {	if(P[y][x].side!=this.side)
		    {if(P[y][x].showMove(Occ,P)[this.kool.y][this.kool.x]==3 )
		     {b= crossMove(this,P[y][x],Occ,P); Threats.add(P[y][x]);t=true;  // t = true --> a threat exists
		     if(n==0){Eq(c,b);}else{c=crossMat(b,c);}n++;}}	    
		 }	    
		}
		}
		
		for(Unit threat: Threats)
		{ c[threat.y][threat.x] = this.showMove(Occ,P)[threat.y][threat.x];}
		
		if(t)   // analyzes if moving to available spots is no longer check
		{ Occ[this.y][this.x] = false;
		for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		{	for(Unit threat: Threats){
	     	was = P[y][x]; w=Occ[y][x];  
         	if(c[y][x]==3){Occ[y][x]=true;P[y][x]=this;
         	if(threat.showMove(Occ,P)[this.kool.y][this.kool.x]==3 && !(y==threat.y && x ==threat.x)){c[y][x]=0;}	
  	     	Occ[y][x]=w;P[y][x]=was;}
  	
		}}} 
		Occ[this.y][this.x] = true;
		}
		
		
		
	  return c; 
    }
    
    public void Eq(int[][] a, int[][] b) // a becomes copy of b
    {for(int y=0;y<8;y++)
     {for(int x=0;x<8;x++)
      {a[y][x]=b[y][x];}}
    }
    
    public int[][] crossMove(Unit a, Unit b,boolean[][] Occ,Unit[][] P ) // returns spots available to both units
    {int[][] A = new int[8][8];
     for(int y=0;y<8;y++)
     {for(int x=0;x<8;x++)
      {if((a.showMove(Occ,P)[y][x]==3)&&(b.showMove(Occ,P)[y][x]==3))
       {A[y][x]=3;}}}
     return A;
    }
    
    public int[][] crossMat(int[][] a,int[][] b) // returns spots marked available in both arrays
    {int[][] A = new int[8][8];
     for(int y=0;y<8;y++)
     {for(int x=0;x<8;x++)
      {if((a[y][x]==3)&&(b[y][x]==3))
       {A[y][x]=3;}}}
     return A;
    }
    
	public void setKing(King K)
	{   kool = K;	}	
	
	public void setImg(BufferedImage i)
	{	image = i;	}
}

class Pawn extends Unit
{ 
	boolean first=true;
	public Pawn(int Y,int X,int s, BufferedImage i)
	{super(Y,X,s,'P');
	 setImg(i);
	}
	
	public int[][] showMove(boolean[][] Occ, Unit[][] P)
	{   int[][] a = new int[8][8];
		//a[y][x]=3;
		side =-side;
	     if(first){if(Occ[y+side][x]==false && Occ[y+(2*side)][x] ==false){ a[y+(2*side)][x] = 3;}}
		 if(y+side >0 && y+side<8){if(Occ[y+side][x] == false){a[y+side][x] = 3;}}
		side =-side;
		 if(x+1<8) {if(Occ[y-side][x+1] == true && P[y-side][x+1].side!=this.side){a[y-side][x+1] = 3;}}
		 if(x-1>=0) {if(Occ[y-side][x-1] == true && P[y-side][x-1].side!=this.side){a[y-side][x-1] = 3;}}
		
		 
		return a;}
	
   //	public int[][] checkMove(int[][] a,boolean[][] Occ,Unit[][] P){return a;}
}


class Rook extends Unit
{
	public Rook(int y,int x,int s,BufferedImage i)
	{super(y,x,s,'R');
	 setImg(i);
	}
	
	public int[][] showMove(boolean[][] Occ, Unit[][] P)
	{ int[][] a = new int[8][8];
	  //a[y][x]=3;	
	  int m=y; int n=x;
	  
	  while(m<7)
	  {   
	  	 if(Occ[m+1][n]!=true)        
	     {a[m+1][n]=3; m++;}
	     else{if(Occ[m+1][n]==true && P[m+1][n].side!=this.side)
	     {a[m+1][n]=3;} m=7;}
	  }
	  m=y;
	  
	  while(m>0)
	  {	if(Occ[m-1][n]!=true)
	    {a[m-1][n]=3; m--;}
	    else{if(Occ[m-1][n]==true && P[m-1][n].side!=this.side)
	    {a[m-1][n]=3;}m=0;}
	  }
	  m=y;
	  while(n+1<8)
	  {  if(Occ[m][n+1]!=true)        
	     { a[m][n+1]=3; n++;}
	     else{if(Occ[m][n+1]==true && P[m][n+1].side!=this.side)
	     {a[m][n+1]=3; }n=8;}
	  }
	  n=x;
	  while(n>0)
	  {  if(Occ[m][n-1]!=true)        
	     { a[m][n-1]=3; n--;}
	     else{if(Occ[m][n-1]==true && P[m][n-1].side!=this.side)
	     {a[m][n-1]=3;}n=0;}
	  }	 
	  // add castle conditions 
	  return a;
	}
	
}


class Knight extends Unit
{
	public Knight(int y,int x,int s, BufferedImage i)
	{super(y,x,s,'k');
	 setImg(i);
	}
	
	public int[][] showMove(boolean[][] Occ, Unit[][] P)
	{int[][] a = new int[8][8];
	 //a[y][x]=3;
	 //int m=y; int n=x;
	 	
	 	
	 if(x<6){if(y<7){if(Occ[y+1][x+2]==true){if(P[y+1][x+2].side!=this.side){a[y+1][x+2]=3;}}else{a[y+1][x+2]=3;}}
	         if(y>0){if(Occ[y-1][x+2]==true){if(P[y-1][x+2].side!=this.side){a[y-1][x+2]=3;}}else{a[y-1][x+2]=3;}}
	 	    }
	 if(x>1){if(y<7){if(Occ[y+1][x-2]==true){if(P[y+1][x-2].side!=this.side){a[y+1][x-2]=3;}}else{a[y+1][x-2]=3;}}
	         if(y>0){if(Occ[y-1][x-2]==true){if(P[y-1][x-2].side!=this.side){a[y-1][x-2]=3;}}else{a[y-1][x-2]=3;}}
	 	    }
	 if(y<6){if(x<7){if(Occ[y+2][x+1]==true){if(P[y+2][x+1].side!=this.side){a[y+2][x+1]=3;}}else{a[y+2][x+1]=3;}}
	         if(x>0){if(Occ[y+2][x-1]==true){if(P[y+2][x-1].side!=this.side){a[y+2][x-1]=3;}}else{a[y+2][x-1]=3;}}
	 	    }
	 if(y>1){if(x<7){if(Occ[y-2][x+1]==true){if(P[y-2][x+1].side!=this.side){a[y-2][x+1]=3;}}else{a[y-2][x+1]=3;}}
	         if(x>0){if(Occ[y-2][x-1]==true){if(P[y-2][x-1].side!=this.side){a[y-2][x-1]=3;}}else{a[y-2][x-1]=3;}}
	 	    }	 	
	 	
	return a;}

}
class Bishop extends Unit
{
	public Bishop(int y,int x,int s, BufferedImage i)
	{super(y,x,s,'B');
	 setImg(i);
	}
	public int[][] showMove(boolean[][] Occ, Unit[][] P)
	{int[][] a = new int[8][8];
	 int m=y; int n=x;
	
	 while(n+1<8  &&  m+1<8)
	  {  if(Occ[m+1][n+1]!=true)        
	     { a[m+1][n+1]=3;m++;n++;}
	     else{if(Occ[m+1][n+1]==true && P[m+1][n+1].side!=this.side)
	     {a[m+1][n+1]=3;}m=8;}
	  }
	  m=y;n=x;
	  while(n>0  && m+1<8)
	  { if(Occ[m+1][n-1]!=true)        
	     { a[m+1][n-1]=3;m++;n--;}
	     else{if(Occ[m+1][n-1]==true && P[m+1][n-1].side!=this.side)
	     {a[m+1][n-1]=3; }m=8;}
	  }
	  m=y;n=x;
	  while(n>0  && m>0)
	  { if(Occ[m-1][n-1]!=true)
	    {a[m-1][n-1]=3;m--;n--;}
	    else{if(Occ[m-1][n-1]==true && P[m-1][n-1].side!=this.side)
	    {a[m-1][n-1]=3; }m=-1;} 
	  }
	  m=y;n=x;
	  while(n+1<8  && m>0)
	  { if(Occ[m-1][n+1]!=true)
	    {a[m-1][n+1]=3;m--;n++;}
	    else{if(Occ[m-1][n+1]==true && P[m-1][n+1].side!=this.side)
	    {a[m-1][n+1]=3; }n=8;} 
	  }
	  
	return a;}
	
}
class King extends Unit
{
	boolean cm=false;
	public King(int y,int x,int s, BufferedImage i)
	{super(y,x,s,'K');
	 setImg(i);
	}
		
	private int[][] sMove(boolean[][] Occ, Unit[][] P)
	{int[][] a = new int[8][8];
	
	 if(x<7){ if(Occ[y][x+1]==false){a[y][x+1]=3;} else{if(P[y][x+1].side!=this.side){a[y][x+1]=3;}}
	         if(y>0){if(Occ[y-1][x]==false){a[y-1][x]=3;} else{if(P[y-1][x].side!=this.side){a[y-1][x]=3;}} 
	         	     if(Occ[y-1][x+1]==false){a[y-1][x+1]=3;} else{if(P[y-1][x+1].side!=this.side){a[y-1][x+1]=3;}}}
	         	     
	         if(y<7){if(Occ[y+1][x]==false){a[y+1][x]=3;} else{if(P[y+1][x].side!=this.side){a[y+1][x]=3;}}
	                 if(Occ[y+1][x+1]==false){a[y+1][x+1]=3;} else{if(P[y+1][x+1].side!=this.side){a[y+1][x+1]=3;}}}}
	                 
	 if(x>0){if(Occ[y][x-1]==false){a[y][x-1]=3;} else{if(P[y][x-1].side!=this.side){a[y][x-1]=3;}}
	 		 if(y>0){if(Occ[y-1][x-1]==false){a[y-1][x-1]=3;} else{if(P[y-1][x-1].side!=this.side){a[y-1][x-1]=3;}}}
	 		 if(y<7){if(Occ[y+1][x-1]==false){a[y+1][x-1]=3;} else{if(P[y+1][x-1].side!=this.side){a[y+1][x-1]=3;}}}} 	 
	 
	 return a;
	 	
	}
	
	public int[][] showMove(boolean[][] Occ, Unit[][] P)
	{int[][] a = new int[8][8];

	 if(x<7){ if(Occ[y][x+1]==false){a[y][x+1]=3;} else{if(P[y][x+1].side!=this.side){a[y][x+1]=3;}}
	         if(y>0){if(Occ[y-1][x]==false){a[y-1][x]=3;} else{if(P[y-1][x].side!=this.side){a[y-1][x]=3;}} 
	         	     if(Occ[y-1][x+1]==false){a[y-1][x+1]=3;} else{if(P[y-1][x+1].side!=this.side){a[y-1][x+1]=3;}}}
	         	     
	         if(y<7){if(Occ[y+1][x]==false){a[y+1][x]=3;} else{if(P[y+1][x].side!=this.side){a[y+1][x]=3;}}
	                 if(Occ[y+1][x+1]==false){a[y+1][x+1]=3;} else{if(P[y+1][x+1].side!=this.side){a[y+1][x+1]=3;}}}}
	                 
	 if(x>0){if(Occ[y][x-1]==false){a[y][x-1]=3;} else{if(P[y][x-1].side!=this.side){a[y][x-1]=3;}}
	 		 if(y>0){if(Occ[y-1][x-1]==false){a[y-1][x-1]=3;} else{if(P[y-1][x-1].side!=this.side){a[y-1][x-1]=3;}}}
	 		 if(y<7){if(Occ[y+1][x-1]==false){a[y+1][x-1]=3;} else{if(P[y+1][x-1].side!=this.side){a[y+1][x-1]=3;}}}} 	 
	 
	 return KMove(a,Occ,P);
	 	
	}
	public int[][] KMove(int[][] a,boolean[][] Occ,Unit[][] P)
	{	int[][] c = new int[8][8];    //c is eventually returned 
		Unit m=this; 
		for(int x=0;x<8;x++){Arrays.fill(c[x],0);}
		ArrayList<Unit> Threats = new ArrayList();
	    Unit was;boolean w; 
		
		for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		{ c[y][x]=a[y][x];
		  if(Occ[y][x]==true)
		 {	
		 	
		 	if(P[y][x].side!=this.side && P[y][x].symbol!='K') // (symbol to prevent StckOvrFlw with self-reference)
		    { m=P[y][x]; 
		   	if(m.showMove(Occ,P)[this.y][this.x] == 3)
		   	{Threats.add(m);}
		    }
		 }
		 	for(int q=0;q<8;q++)     // prevent movement to spaces in danger when outside of check
		   	{for(int z=0;z<8;z++)
		   	 { if(Occ[q][z])
		   	 {if(P[q][z].side!=this.side && P[q][z].symbol!='K')
		   	  {if(P[q][z].showMove(Occ,P)[y][x]==3 && c[y][x]==3)
		   	  	{c[y][x]=5;}}
			 }		   	   	 		     	
		   }
		   	    
		 }
		 	 		    
		}
	    }
			for(int q=0;q<8;q++)
			{for(int z=0;z<8;z++)
			{ 
			  for(Unit threat: Threats)
		        {if(c[q][z]==3){c[q][z] = (threat.showMove(Occ,P)[q][z] ==3)? 5:3;}}
		    }
			}
		
		for(Unit threat: Threats)
		{ c[threat.y][threat.x] = sMove(Occ,P)[threat.y][threat.x];}
		


		Occ[this.y][this.x] = false;  // temporarily 'moves' King to view and remove options behind him in threat's range
		for(int y=0;y<8;y++){for(int x=0;x<8;x++)
		{for(int q=0;q<8;q++){for(int z=0;z<8;z++){
			was = P[y][x]; w=Occ[y][x];  
			if(c[y][x]==3)
			{
				Occ[y][x]=true;P[y][x]=this;
				if(Occ[q][z])
				{  if(P[q][z].side !=this.side && P[q][z].symbol !='K')
					{	if(P[q][z].showMove(Occ,P)[y][x] == 3)
						{c[y][x]=0;}
					}
					
				}	
  	     		Occ[y][x]=w;P[y][x]=was;
  	
		}}}}} Occ[this.y][this.x] = true;
        			     						 	   			
		return c;
	}

	public void checkForMate(boolean[][] Occ, Unit[][] P)
	{
		int[][] c = this.showMove(Occ,P);
		int[][] a = c;
        cm=false;	
        for(int y =0;y<8;y++)
        {for(int x =0;x<8;x++)
         {if(c[y][x]==5){cm=true;}
          if(c[y][x]==3){cm=false;y=8;x=8;}
         }
		}
		
		//function to account for allied movements in check
		if(cm)
		{
		for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		 {if(Occ[y][x])
		 {if(P[y][x].side==this.side && P[y][x].symbol!='K')
		  {
			  for(int z = 0;z<8;z++)
			  {
				for (int element : P[y][x].checkMove(a,Occ,P)[z])
				{if(element==3)
					{cm = false;}
				}
			  }

		  }
	     }
		 }
		}
		} 

	}



	public boolean inCheck(boolean[][] Occ, Unit[][] P) // returns if this king unit is in immediate danger
	{
		boolean u = false;
		for(int y=0;y<8;y++)
		{for(int x=0;x<8;x++)
		 {if(Occ[y][x])
		 {if(P[y][x].side!=this.side)
		  {if(P[y][x].isCheck(P[y][x].showMove(Occ,P),Occ,P))
		   {u = true;}
		  }
	     }
		 }
		}
	 return u;
	}
	
	public int[][] checkMove(int[][] a,boolean[][] Occ,Unit[][] P){return showMove(Occ,P);}
	
	
	public boolean checkMate()
	{return cm;}

		
}
class Queen extends Unit
{
	public Queen(int y,int x,int s,BufferedImage i)
	{super(y,x,s,'Q');
	 setImg(i);
	}
	public int[][] showMove(boolean[][] Occ, Unit[][] P)
	{int[][] a = new int[8][8];	
	 int m=y; int n=x;
	  // Rook Half
	  while(m<7)
	  {   
	  	 if(Occ[m+1][n]!=true)        
	     {a[m+1][n]=3; m++;}
	     else{if(Occ[m+1][n]==true && P[m+1][n].side!=this.side)
	     {a[m+1][n]=3;} m=7;}
	  }
	  m=y;
	  
	  while(m>0)
	  {	if(Occ[m-1][n]!=true)
	    {a[m-1][n]=3; m--;}
	    else{if(Occ[m-1][n]==true && P[m-1][n].side!=this.side)
	    {a[m-1][n]=3;}m=0;}
	  }
	  m=y;
	  while(n+1<8)
	  {  if(Occ[m][n+1]!=true)        
	     { a[m][n+1]=3; n++;}
	     else{if(Occ[m][n+1]==true && P[m][n+1].side!=this.side)
	     {a[m][n+1]=3; }n=8;}
	     
	  }
	  n=x;
	  while(n>0)
	  {  if(Occ[m][n-1]!=true)        
	     { a[m][n-1]=3; n--;}
	     else{if(Occ[m][n-1]==true && P[m][n-1].side!=this.side)
	     {a[m][n-1]=3;}n=0;}
	  }	 
	  m=y;n=x;////	BishopHalf
	  while(n+1<8  &&  m+1<8)
	  {  if(Occ[m+1][n+1]!=true)        
	     { a[m+1][n+1]=3;m++;n++;}
	     else{if(Occ[m+1][n+1]==true && P[m+1][n+1].side!=this.side)
	     {a[m+1][n+1]=3;}m=8;}
	  }
	  m=y;n=x;
	  while(n>0  && m+1<8)
	  { if(Occ[m+1][n-1]!=true)        
	     { a[m+1][n-1]=3;m++;n--;}
	     else{if(Occ[m+1][n-1]==true && P[m+1][n-1].side!=this.side)
	     {a[m+1][n-1]=3; }m=8;}
	  }
	  m=y;n=x;
	  while(n>0  && m>0)
	  { if(Occ[m-1][n-1]!=true)
	    {a[m-1][n-1]=3;m--;n--;}
	    else{if(Occ[m-1][n-1]==true && P[m-1][n-1].side!=this.side)
	    {a[m-1][n-1]=3; }m=-1;} 
	  }
	  m=y;n=x;
	  while(n+1<8  && m>0)
	  { if(Occ[m-1][n+1]!=true)
	    {a[m-1][n+1]=3;m--;n++;}
	    else{if(Occ[m-1][n+1]==true && P[m-1][n+1].side!=this.side)
	    {a[m-1][n+1]=3; }n=8;} 
	  }
	
	
	return a;}

}
