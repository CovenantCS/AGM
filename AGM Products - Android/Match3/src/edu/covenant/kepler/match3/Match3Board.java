package edu.covenant.kepler.match3;

import java.util.Random;
import java.util.Scanner;

import android.content.Context;
import android.graphics.Color;
import coreAssets.CollisionException;
import coreAssets.ContinuousActionBoard;
import coreAssets.GameOverException;
import coreAssets.Point;
import coreAssets.Rectangle;
import coreAssets.SimpleScore;
import coreAssets.Size;
import coreAssets.TextSprite;

public class Match3Board extends ContinuousActionBoard {
	
	private TilePile tilePile;
	private boolean ptrReleased=true;
	private int tileColor;
	private int color1;
	private int color2;
	
	private int[] tile1={0,0};
	private int[] tile2={0,0};
	private static int[] colors={255 << 24 | 0 << 16 | 0 << 8 | 255,
		50 << 24 | 255 << 16 | 50 << 8 | 255,
		50 << 24 | 50 << 16 | 255 << 8 | 255,//light clear blue
		255 << 24 | 255 << 16 | 50 << 8 | 255,
		255 << 24 | 50 << 16 | 255 << 8 | 255,
		50 << 24 | 255 << 16 | 255 << 8 | 255};
	
	public Match3Board(int width, int height) {
		super(width, height);
		this.name = "Match3";
	    this.score = new SimpleScore();
		userInterupt = false;
	}
	
	public Match3Board() {
		super();
		this.name = "Match3";
	    this.score = new SimpleScore();
		userInterupt = false;
	}
	
	public Match3Board(int tileColor){
		super();
		this.name = "Match3";
	    this.score = new SimpleScore();
		this.tileColor=tileColor;
		userInterupt = false;
	}
	
	public void buildGameBoard() {
		tilePile = new TilePile(new Rectangle(new Point(0, 0), new Size(getWidth(), getHeight() - (getHeight() / 10))));
		addStationaryPiece(tilePile);	
		
		addText(new TextSprite( "Score: "+score, Color.BLACK, 10, (float)(getWidth() * 0.05), (float)(getHeight() * 0.9) ));
	}

	public String getSaveData() {
    	try {
    		/*return pucksupply.getSaveData() + ":" + puck.getSaveData() + ":"
					+ paddle.getSaveData() + ":" + brickpile.getSaveData() + ":"
					+ score;*/
			String x="";
			for (int i = 0; i < tilePile.pile.length; i++) {
				
				for (int j = 0; j < tilePile.pile.length; j++) {
					
					x+=tilePile.pile[i][j].getColor()+",";
					
					
				}
			}
			x+=score;
			return x;
		//	return (tilePile.getSaveData()+":"+score);
    	}
    	catch (NullPointerException e) {
    		return "";
    	}
	}

	public void setSaveData(String data) {
		/*pucksupply.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		puck.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
		paddle.setSaveData(data);
		data = data.substring(data.indexOf(":") + 1);
//		brickpile.setSaveData(data.substring(0, data.indexOf(":")));
		gameOver = false;
		data = data.substring(data.indexOf(":") + 1);
		score = new SimpleScore(Integer.parseInt(data));*/
		
		gameOver = false;
		//tilePile.setSaveData(data);
		//Scanner split=new Scanner(data);
		//split.useDelimiter(":");
		
		Scanner check=new Scanner(data);
		check.useDelimiter(",");
		
		for (int i = 0; i < tilePile.pile.length; i++) {
			
			for (int j = 0; j < tilePile.pile.length; j++) {
				String x=check.next();
				System.out.println(x);
				tilePile.pile[i][j].setColor(Integer.parseInt(x));
				
				
			}
		}
		data = data.substring(data.indexOf(":") + 1);
		score = new SimpleScore(Integer.parseInt(check.next()));
		textComponents.elementAt(0).setValue("Score: "+score);
	}

	protected void handleGameOverException(boolean won) {
		if (won) {
			score.incScore(1);
		}
		
	}

	protected void handleSpriteDeletedException() throws GameOverException {
		/*try {
			movableComponents.removeElement(puck);
			puck = pucksupply.getPuck(new Point((getWidth() / 2),
					(getHeight() / 2)));
			movableComponents.addElement(puck);
		} catch (GameOverException oope) {
			stopMovement();
			gameOver = true;
			throw new GameOverException(false, "You lost, score: " + score);
		}*/
	}

	protected void handleCollisionException(CollisionException ce) {
		/*if (ce.getSprite1().name.equals("Puck")
				&& ce.getSprite2().name.equals("Brick"))
			score.incScore(1);*/
	}

	public void ptrPressed(int x, int y) {
		
		 if (ptrReleased==true){
			 tile1[1]=(int) (x)/(getWidth()/tilePile.pile.length);
		        tile1[0]= (int) (y)/(getWidth()/tilePile.pile.length);
		        if (tile1[0]<tilePile.pile.length&&tile1[0]>=0&&tile1[1]<tilePile.pile.length&&tile1[1]>=0){
		        	color1=tilePile.pile[tile1[0]][tile1[1]].getColor();
		        	ptrReleased=false;
		        }
         	
         	}
         }
	
	
	public void ptrReleased(int x, int y){
		ptrReleased=true;
        tile2[1]=(x/(getWidth()/tilePile.pile.length));
        tile2[0]= (y/(getWidth()/tilePile.pile.length));
        if (tile2[0]<tilePile.pile.length&&tile2[0]>=0&&tile2[1]<tilePile.pile.length&&tile2[1]>=0){
        	color2=tilePile.pile[tile2[0]][tile2[1]].getColor();

        }
        //switch color and ints
       if (((tile1[0]==tile2[0]-1||tile1[0]==tile2[0]+1)&&tile1[1]==tile2[1])
    		   					||((tile1[1]==tile2[1]-1||tile1[1]==tile2[1]+1))&&tile1[0]==tile2[0]){
    	   tilePile.pile[tile2[0]][tile2[1]].setColor(color1);
    	   tilePile.pile[tile1[0]][tile1[1]].setColor(color2);
    	   
    	   //ints
    	   int tmp = tilePile.pile[tile1[0]][tile1[1]].num;
    	   tilePile.pile[tile1[0]][tile1[1]].num = tilePile.pile[tile2[0]][tile2[1]].num;
    	   tilePile.pile[tile2[0]][tile2[1]].num = tmp;
    	   
        	// Start check for matches
    	   boolean firstcheck=true;
    	   for (int c=0; c<2; c++){
    		   while (!allClear()){
    				//if(match==true){
    					//Fill Board
    					for(int i=tilePile.pile.length-1; i > 0; i--){
    						for(int j=tilePile.pile[0].length-1; j >= 0; j--){
    							if(tilePile.pile[i][j].getColor() == 0){
    								//Change Color
    								tilePile.pile[i][j].setColor(tilePile.pile[i-1][j].getColor());
    								//tilePile.pile[i][j].num = tilePile.pile[i-1][j].num;
    								tilePile.pile[i-1][j].setColor(0);
    							}
    						}
    					}
    					
    					//fill Top Row
    					for(int j = 0; j < tilePile.pile.length; j++){
    						if(tilePile.pile[0][j].getColor() ==0){
    							//New Color and Int for Tile
    							Random rand = new Random();
    							int tmpNum = rand.nextInt(8);
    							//tilePile.pile[0][j].num = tmpNum;
    							tilePile.pile[0][j].setColor(tilePile.colors[tmpNum]);
    						}
    					}
    					
    				//}
    					//match=checkMatches();
    				}
    	   boolean match = checkMatches();
    	   if (match==true){
    		   c--;
    	   }
			
			//if off by one error, look here****
			
		   
		 
		   
    	   if(match==false&&firstcheck){
               tilePile.pile[tile2[0]][tile2[1]].setColor(color2);
                  tilePile.pile[tile1[0]][tile1[1]].setColor(color1);
                 
           }
          firstcheck=false;
			
			
       }
    	   
    	  
    	   
    	   if(gameOver())
			{
				this.gameOver = true;
			}
    	   
    	   
       }
    }
	public boolean gameOver(){
		for (int q=0; q<tilePile.pile.length; q++){
 		   for (int r=0; r<tilePile.pile.length-1; r++){
 			   int tmp2=0;
 			   tmp2=tilePile.pile[q][r].getColor();
 			   tilePile.pile[q][r].setColor(tilePile.pile[q][r+1].getColor());
 			   tilePile.pile[q][r+1].setColor(tmp2);
 			   for(int i = 0; i < tilePile.pile.length; i++){
 				   for(int j = 0; j < tilePile.pile[0].length-2; j++){
 						if((tilePile.pile[i][j].getColor() != 0) && (tilePile.pile[i][j].getColor() == tilePile.pile[i][j+1].getColor()) ){
 							if(tilePile.pile[i][j+1].getColor() == tilePile.pile[i][j+2].getColor()){
 								tmp2=tilePile.pile[q][r].getColor();
 				 				tilePile.pile[q][r].setColor(tilePile.pile[q][r+1].getColor());
 				 				tilePile.pile[q][r+1].setColor(tmp2);
 								return false;	
 							}	
 						}
 						else{
 							//System.out.println("check k: "+k);
 						}
 					}
 				}
 				
 				//Vertical = a[0][0] == a[1][0]
 			   
 				for(int j = 0; j < tilePile.pile.length; j++){
 					for(int i = 0; i < tilePile.pile[0].length-2; i++){
 						if((tilePile.pile[i][j].getColor() != 0) && (tilePile.pile[i][j].getColor() == tilePile.pile[i+1][j].getColor())){
 							if(tilePile.pile[i][j].getColor()== tilePile.pile[i+2][j].getColor()){
 								tmp2=tilePile.pile[q][r].getColor();
 				 				tilePile.pile[q][r].setColor(tilePile.pile[q][r+1].getColor());
 				 				tilePile.pile[q][r+1].setColor(tmp2);
 								return false;
 								
 							}	
 						}
 						else{
 							//System.out.println("check k: "+k);
 						}
 					}
 				}
 				tmp2=tilePile.pile[q][r].getColor();
 				tilePile.pile[q][r].setColor(tilePile.pile[q][r+1].getColor());
 				tilePile.pile[q][r+1].setColor(tmp2);
 		   }
 	   }
 	   for (int r=0; r<tilePile.pile.length; r++){
 		   for (int q=0; q<tilePile.pile.length-1; q++){
 			   int tmp2=0;
 			   tmp2=tilePile.pile[q][r].getColor();
 			   tilePile.pile[q][r].setColor(tilePile.pile[q+1][r].getColor());
 			   tilePile.pile[q+1][r].setColor(tmp2);
 			   for(int i = 0; i < tilePile.pile.length; i++){
 				   for(int j = 0; j < tilePile.pile[0].length-2; j++){
 						if((tilePile.pile[i][j].getColor() != 0) && (tilePile.pile[i][j].getColor() == tilePile.pile[i][j+1].getColor()) ){
 							if(tilePile.pile[i][j+1].getColor() == tilePile.pile[i][j+2].getColor()){
 								tmp2=tilePile.pile[q][r].getColor();
 				 				tilePile.pile[q][r].setColor(tilePile.pile[q+1][r].getColor());
 				 				tilePile.pile[q+1][r].setColor(tmp2);
 								return false;	
 							}	
 						}
 						else{
 							//System.out.println("check k: "+k);
 						}
 					}
 				}
 				
 				//Vertical = a[0][0] == a[1][0]
 			   
 				for(int j = 0; j < tilePile.pile.length; j++){
 					for(int i = 0; i < tilePile.pile[0].length-2; i++){
 						if((tilePile.pile[i][j].getColor() != 0) && (tilePile.pile[i][j].getColor() == tilePile.pile[i+1][j].getColor())){
 							if(tilePile.pile[i][j].getColor()== tilePile.pile[i+2][j].getColor()){
 								tmp2=tilePile.pile[q][r].getColor();
 				 				tilePile.pile[q][r].setColor(tilePile.pile[q+1][r].getColor());
 				 				tilePile.pile[q+1][r].setColor(tmp2);
 								return false;
 							}	
 						}
 						else{
 							//System.out.println("check k: "+k);
 						}
 					}
 				}
 				tmp2=tilePile.pile[q][r].getColor();
 				tilePile.pile[q][r].setColor(tilePile.pile[q+1][r].getColor());
 				tilePile.pile[q+1][r].setColor(tmp2);
 		   }
 	   }
 	   return true;
	}
	
	public boolean allClear(){
		for(int i = 0; i < tilePile.pile.length; i++){
			for(int j = 0; j < tilePile.pile.length; j++){
				if(tilePile.pile[i][j].getColor() == 0){
					return false;
					
				}
			}
		}
		return true;
	}
	
	public void ptrDragged(int x, int y) {
	}


	public void keyUp(boolean down) {
		userInterupt = true;
	}
	public boolean checkMatches(){
		boolean match=false;
		for(int i = 0; i < tilePile.pile.length; i++){
			   for(int j = 0; j < tilePile.pile.length-2; j++){
					if((tilePile.pile[i][j].getColor() != 0) && (tilePile.pile[i][j].getColor() == tilePile.pile[i][j+1].getColor()) ){
						if(tilePile.pile[i][j+1].getColor() == tilePile.pile[i][j+2].getColor()){
							match = true;
							int length = 3;
							boolean plus = true;
							int o = 3;
							while(plus==true){
		//							System.out.println(o);
								if(((j+o) < tilePile.pile[0].length-1) && (tilePile.pile[i][j].getColor()== tilePile.pile[i][j+o].getColor())){
		//								System.out.println("fall in");
									length++;
									o++;
		//								System.out.println("Length: "+length+" o: "+o);
								}
								else{
									plus = false;
		//								System.out.println("2 - Length: "+length+" o: "+o);
								}
							}
							
							for(int k = 0; k < length; k++){
								tilePile.pile[i][j+k].setColor(0);
							}
							score.incScore(length);
							textComponents.elementAt(0).setValue("Score: "+score);
		//						fillBoard();
						}	
					}
					else{
						//System.out.println("check k: "+k);
					}
				}
			}
			
			//Vertical = a[0][0] == a[1][0]
		   
			for(int j = 0; j < tilePile.pile.length; j++){
				for(int i = 0; i < tilePile.pile[0].length-2; i++){
					if((tilePile.pile[i][j].getColor() != 0) && (tilePile.pile[i][j].getColor() == tilePile.pile[i+1][j].getColor())){
						if(tilePile.pile[i][j].getColor()== tilePile.pile[i+2][j].getColor()){
							match = true;
							int length = 3;
							boolean plus = true;
							int o = 3;
							while(plus==true){
								if(((i+o) < tilePile.pile.length-1) && (tilePile.pile[i][j].getColor() == tilePile.pile[i+o][j].getColor())){
									length = length + 1;
									o++;
								}
								else{
									plus = false;
								}
							}
		//						System.out.println("len "+length);
							for(int k = length-1; k >= 0; k--){
								tilePile.pile[i+k][j].setColor(0);
							}
							score.incScore(length);
							textComponents.elementAt(0).setValue("Score: "+score);
				//			fillBoard();
						}	
					}
					else{
						//System.out.println("check k: "+k);
					}
				}
			}
		
		return match;
		
	}
	/*@Override
	public void loadGame(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame(Context context) {
		// TODO Auto-generated method stub
		
	}*/

}