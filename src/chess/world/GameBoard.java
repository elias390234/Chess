package chess.world;

/**
 * Functionality

 * 
 * Set up original board of 2 castles, 2 bishops, 2 knights, 1 queen, 1 king and 8 pawns 
 *    for both colors
 * Keep track of whose turn it is, and whether or not they have clicked on a piece to move
 * 
 * When a cell is click, determine what is in that cell
 * If it is a piece,determine what type of piece it is and what color
 * if it is that teams turn, get the possible move locations
 * Get the location that the person clicks, and determine if it was valid
 * If it was valid move the piece there
 * If a piece moves to a location with a piece from the other color, remove that piece
 * 
 * Determine if either king is in check, and/or checkmate
 * Once someone is in checkmate, declare the other team the winner
 * 
 * Possibly check if there is a tie
 * 
 * UML
 * private int whoseTurnCounter
 * private boolean hasPickedPiece
 * private ArrayList<Piece> whitePieces;
 * private ArrayList<Piece> blackPieces;
 * private ArrayList<Piece> whiteTakenPieces;
 * private ArrayList<Piece> blackTakenPieces;
 * 
 * public Gameboard()
 * public void setUpWhitePieces()
 * public void setUpBlackPieces()
 * 
 * public void locationClicked(Location loc)
 * public boolean isCorrectColor()
 * public boolean isValidMoveLocation()
 * 
 * public boolean checkMate()
 * public boolean check()
 * 
 * 
 */

import java.awt.Color;
import java.util.ArrayList;

import Grid.BoundedGrid.*;
import Piece.*;
public class GameBoard extends World<Piece> {  
	    private static final String DEFAULT_MESSAGE = "Click on a piece to make a move.";
	    private int whoseTurnCounter;
		private boolean hasPickedPiece;
		private Piece selectedPiece;
		
		private ArrayList<Piece> whitePieces;
		private ArrayList<Piece> blackPieces;
		private ArrayList<Piece> whiteTakenPieces;
		private ArrayList<Piece> blackTakenPieces;
		
		
		/**
		 * Constructor to initialize variables 
		 */
		public GameBoard(){
			super(8,8);
			whoseTurnCounter =0;
			hasPickedPiece = false;
			whitePieces = new ArrayList<Piece>();
			blackPieces = new ArrayList<Piece>();
			whiteTakenPieces = new ArrayList<Piece>();
			blackTakenPieces = new ArrayList<Piece>();
			selectedPiece = null;
		}
	    
		/**
		 * sets up the original array of black pieces and white pieces
		 */
		public void setUpPieces(){
			Piece currentPiece;
			Piece currentBlackPiece;
			Piece currentWhitePiece;
			String WHITE = "White";
			String BLACK = "Black";
			
			for(int i=0;i<8;i++){
				currentPiece = new Pawn();
				currentPiece.placeSelfInGrid(new Location(6,i));
				whitePieces.add(currentPiece);
				
				currentPiece = new Pawn(BLACK);
				curentPiece.placeSelfInGrid(new Location(1,i));
				blackPieces.add(currentPiece);
				
				if(i==0 || i == 7){
					currentPiece = new Castle(WHITE);
					currentPiece = new Castle(BLACK);
				} else if (i==1 || i == 6){
					currentWhitePiece = new Knight(WHITE);
					currentBlackPiece = new Knight(BLACK);
				} else if(i == 2 || i == 5){
					currentWhitePiece = new Bishop(WHITE);
					currentBlackPiece = new Bishop(BLACK);
				} else if (i == 3){
					currentWhitePiece = new King(WHITE);
					currentBlackPiece = new Queen(BLACK);
				}else if(i == 4){
					currentWhitePiece = new Queen(WHITE);
					currentBlackPiece = new King(BLACK);
				}
				
				currentWhitePiece.placeSelfInGrid(new Location(7,i));
				whitePieces.add(currentPiece);
				
				currentBlackPiece.placeSelfInGrid(new Location(0,i));
				blackPieces.add(currentPiece);
			}
			
			/**
			 * sets the hasSelectedPiece variable
			 */
			public void setHasSelectedPiece()
			{
				if(hasSelectedPiece){
					hasSelectedPiece = false;
				} else {
					hasSelectedPiece = true;
				}
			}
			
			/**
			 * determines whose turn it is
			 */
			public String whoseTurn()
			{
				if(whoseTurnCounter % 2 == 0){
					return "White";
				} 
				return "Black";
			}
			
			/**
			 * determines if a piece that was selected to move was of the correct team
			 */
			public boolean isCorrectColor(Piece selectedPiece){
				if (selectedPiece.getColor().equals(whoseTurn())){
					return true;
				}
				return false;
			}
			
			/**
			 * determines if the white king is in check
			 */
			public boolean whiteCheck(){
				boolean check=false;
				 return check;
			}
			
			/**
			 * determines if the black king is in check
			 */
			public boolean blackCheck(){
				boolean check=false;
				 return check;
			}
			
			/**
			 * determines if the black king is in check mate
			 */
			public boolean blackCheckMate(){
				boolean checkMate=false;
				 return checkMate;
			}
			
			/**
			 * determines if the white king is in check mate
			 */
			public boolean whiteCheckMate(){
				boolean checkMate=false;
				 return checkMate;
			}
			
			/**
		     * This method is called when the user clicks on a location in the
		     * WorldFrame.
		     * 
		     * @param loc the grid location that the user selected
		     * @return true if the world consumes the click, or false if the GUI should
		     * invoke the Location->Edit menu action
		     */
			/**
			 * @TODO need to unhighlight pieces after each move (toggleHighlight(Location loc))
			 */
		    public boolean locationClicked(Location loc)
		    {
		       if(!hasPickPiece)
		       {
		    	   if(getGrid().get(loc) == null)
		    	   {
		    		   setMessage("There is not a piece in that location");
		    		   return null;
		    	   }
		    	   
		    	   selectedPiece = getGrid().get(loc);
		    	   if(isCorrectColor(selectedPiece))
		    	   {
		    		   setHasSelectedPiece();
		    		   for (Location currentLocation: selectedPiece.getPossibleMoveLocations())
		    		   {
		    			   getFrame().highlightLocation(currentLocation);
		    		   }
		    	   } else {
		    		   setMessage("It is " + whoseTurn() + "'s turn.");
		    	   }
		       } else {
		    	   if(selectedPiece.getLocation().equals(loc))
		    	   {
		    		   selectedPiece = null;
		    		   setHasSelectedPiece();
		    		   
		    	   }
		    	   else if(!isPossibleMoveLocation(loc, selectedPiece.getPossibleMoveLocations()))
		    	   {
		    		   setMessage("That is not a valid move location for that piece");
		    	   } else {
		    		   if(getGrid().get(loc) != null){
		    			   if (tempPiece.getColor().equals("Black"))
		    				   takeBlackPiece(loc);
		    			   else 
		    				   takeWhitePiece(loc);
		    		   }
		    		   selectedPiece.moveTo(loc);
		    	   }
		       }
		       
		    }
		    
		    
		    public void takeWhitePiece(Location loc)
		    {
		    	for(int i=0;i<whitePieces.size();i++)
		    	{
		    		if (whitePieces.get(i).getLocation().equals(loc)
		    		{
		    			whitePieces.get(i).removeSelfFromGrid();
		    			whiteTakenPieces.add(whitePieces.get(i));
		    			whitePieces.remove(i);
		    			
		    		}
		    	}
		    }
		   
		    public void takeBlackPiece(Location loc)
		    {
		    	for(int i=0;i<blackPieces.size();i++)
		    	{
		    		if (blackPieces.get(i).getLocation().equals(loc)
		    		{
		    			blackPieces.get(i).removeSelfFromGrid();
		    			blackTakenPieces.add(blackPieces.get(i));
		    			blackPieces.remove(i);
		    			
		    		}
		    	}
		    }
		    public boolean isPossibleMoveLocation(Location loc, ArrayList<Location> locs)
		    {
		    	for(Location currentLocation :locs)
		    	{
		    		if(currentLocation.equals(loc))
		    			return true;
		    	}
		    	return false;
		    }
		    
		    public void show()
		    {
		    	if (getMessage() == null)
		    		setMessage(DEFAULT_MESSAGE);
		    	super.show();
		    }

		    public void step()
		    {
		        Grid<Actor> gr = getGrid();
		        ArrayList<Actor> actors = new ArrayList<Actor>();
		        for (Location loc : gr.getOccupiedLocations())
		            actors.add(gr.get(loc));
	
		        for (Actor a : actors)
		        {
		            // only act if another actor hasn't removed a
		            if (a.getGrid() == gr)
		                a.act();
		        }
		    }

		    /**
		     * Adds an actor to this world at a given location.
		     * @param loc the location at which to add the actor
		     * @param occupant the actor to add
		     */
		    public void add(Location loc, Actor occupant)
		    {
		        occupant.putSelfInGrid(getGrid(), loc);
		    }

		    /**
		     * Removes an actor from this world.
		     * @param loc the location from which to remove an actor
		     * @return the removed actor, or null if there was no actor at the given
		     * location.
		     */
		    public Actor remove(Location loc)
		    {
		        Actor occupant = getGrid().get(loc);
		        if (occupant == null)
		            return null;
		        occupant.removeSelfFromGrid();
		        return occupant;
		    }		
	}
		/**
		 * Stuff Mark need to do
		 * 		-highlighting method, highlightLocation(Location loc)
		 * 		-method to output a message, outputMessage(Sting message)
