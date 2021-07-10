//Class is used to play a tictactoe game in console
//Class contains a private 3x3 2D array, uses enum types to represent values in each cell of the array
//Class initilizes board elements as empty

import java.util.Random; //used to generate movements in npcPlayer method

public class TicTacToe {
   public enum Value{ EMPTY, X, O}; //declares the enum values
   private static Value [][] gameBoard = new Value[3][3]; //declares private 2-dimensional 3x3 array
   
   //initilize the 3x3 gamegameBoard as empty
   public TicTacToe() {
       for(int i = 0 ; i <3 ;i++ ) {
           for(int j = 0; j<3 ;j++) {
               gameBoard[i][j] = Value.EMPTY; //Traverses array and sets all values as EMPTY
           }
       }
   }
   
  //places the X/O in chosen spot, throws error if spot is already taken
   public static void move(int row, int column,  Value x ) {
       if(Value.EMPTY == gameBoard[row ][column]) { //checks if the chosen placement is empty
           gameBoard[row][column] = x; //places X/O in spot if it is
       }    
      
       else { //if the spot is taken
           throw new Error("That space has already been taken, choose another!"); //throws error if the spot is taken
       }  
   }

   //converts the x,y coordinates entered by the user into the correct rows/columns for the 2D array
   public static Integer[] convertXYcords(int x, int y) {
	   int row = 0; //init row       
	   int column = 0;   //init column
		
	   //column 0
	   if(x == 1) {
		  //each if statement in each column finds the corresponding [row][column] 	
		  if(y ==1) {
			row = 2;
			column = 0;
		  }
		  if(y ==2) {
			row = 1;
			column = 0;
		  }
		  if(y ==3) {
			row = 0;
			column =  0;
		  }
	   }
	   //column 1
	   if(x == 2) {
		   if(y ==1) {
			 row = 2;
			 column = 1;	  
		   }
		   if(y ==2) {
			  row = 1;
			  column = 1;  
		   }
		   if(y ==3) {
			  row = 0;
			  column = 1;	  
		   }
	   } 
	   //column 2
	   if( x == 3) {
		   if(y ==1) {
			  row = 2;
			  column = 2;	  
		   }
		   if(y ==2) {
			  row = 1;
			  column = 2;  
		   }
		   if(y ==3) {
			  row = 0;
			  column= 2; 
		   }
	   }
	   Integer[] rowcolumn = {row, column}; //creates an array of the row/comumn coordinates so they can be returned
	   return rowcolumn; //returns converted coordinates
   }
   
   //checks each combination of rows/columns/diagonals to see if there is a winner
   //each set of if statements for the row/column/diagonal checks for both x and o
   public static String checkWinner() {
	   //row 0
	   if (gameBoard[0][0] == Value.X && gameBoard[0][1] == Value.X && gameBoard[0][2] == Value.X){	//if all the values are equal (excluding EMPTY) in a row/column/diagonal then there must be a win	
			return ("X");
	   }
	   if (gameBoard[0][0] == Value.O && gameBoard[0][1] == Value.O && gameBoard[0][2] == Value.O){		
			return ("O");
	   }
		//row 1
	   if (gameBoard[1][0] == Value.X && gameBoard[1][1] == Value.X && gameBoard[1][2] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[1][0] == Value.O && gameBoard[1][1] == Value.O && gameBoard[1][2] == Value.O){		
			return ("O");
	   }
		//row 2
	   if (gameBoard[2][0] == Value.X && gameBoard[2][1] == Value.X && gameBoard[2][2] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[2][0] == Value.O && gameBoard[2][1] == Value.O && gameBoard[2][2] == Value.O){		
			return ("O");
	   }
		//column 0
	   if (gameBoard[0][0] == Value.X && gameBoard[1][0] == Value.X && gameBoard[2][0] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[0][0] == Value.O && gameBoard[1][0] == Value.O && gameBoard[2][0] == Value.O){		
			return ("O");
	   }
		//column 1
	   if (gameBoard[0][1] == Value.X && gameBoard[1][1] == Value.X && gameBoard[2][1] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[0][1] == Value.O && gameBoard[1][1] == Value.O && gameBoard[2][1] == Value.O){		
			return ("O");
	   }
		//column 2
	   if (gameBoard[0][2] == Value.X && gameBoard[1][2] == Value.X && gameBoard[2][2] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[0][2] == Value.O && gameBoard[1][2] == Value.O && gameBoard[2][2] == Value.O){		
			return ("O");
	   }
		//diagonal 1
	   if (gameBoard[0][0] == Value.X && gameBoard[1][1] == Value.X && gameBoard[2][2] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[0][0] == Value.O && gameBoard[1][1] == Value.O && gameBoard[2][2] == Value.O){		
			return ("O");
	   }
		//diagonal 2
	   if (gameBoard[2][0] == Value.X && gameBoard[1][1] == Value.X && gameBoard[0][2] == Value.X){		
			return ("X");
	   }
	   if (gameBoard[2][0] == Value.O && gameBoard[1][1] == Value.O && gameBoard[0][2] == Value.O){		
			return ("O");
	   }
	   else {
		   	//no winner found
		   	return "none";
		}
   }
   
   //prints the gameBoard in console
   public static void displayBoard() {
       for(Value[] row : gameBoard) { //nested for for iteration through gameBoard
           for(Value element : row) {
               System.out.print("\t"+element); //tab separates each value on the gameBoard
           }
           System.out.println();
       }
   } 
   
   //makes move for NPC by random number generation (not exactly the smartest NPC, but hey, it works!)
   public static void npcPlayer(String npcChar) {
	   Random random = new Random();
	   while(true) { //loops until NPC has found a valid spot to place and X/O
		   int xCord = random.nextInt((3 - 1) + 1) + 1; //generates random xcord 1-3
		   int yCord = random.nextInt((3 - 1) + 1) + 1; //generates random ycord 1-3
//		   System.out.println(xCord); //was used for troubleshooting
//		   System.out.println(yCord);
		   Integer[] rowcolumn = convertXYcords(xCord, yCord); //convert the cords

		   try {
			   if(npcChar == "X") { //if the NPC has x as its char
				   move(rowcolumn[0], rowcolumn[1], Value.X); //place the value in the array
				   break;
			   }
			   else {// if the npc has an o as its char
				   move(rowcolumn[0], rowcolumn[1], Value.O); //place the value in the array
				   break;
		   		}
		   	}
		   	catch(Error e) { //if the npc places and x/o in a spot that is taken
		   		continue; //it will continue to loop
		   }
	   }
   }
}