//this programs makes use of the TicTacToe class in TicTacToe.java to play a game of tictactoe
import java.util.Scanner;

public class Main {
	   public static void main(String[] args) {
		   	//prints instructions on how to play game
		    System.out.printf("\n    y3 EMPTY   EMPTY     O  \n    y2 EMPTY   EMPTY   EMPTY\n    y1   X     EMPTY   EMPTY\n        x1      x2      x3\n");
		   	System.out.println("\nplayers place their X/O's by entering corresponding x,y coordinates seperated by a space (ommit the comma).");
		   	System.out.println("for example, to place a marker where X is, you would enter '1 1'.");
		   	System.out.println("Or, to place a marker at O, you would enter '3 3'.\n");	   	
		   	
		   	new TicTacToe(); //create new instance
		   	Scanner input = new Scanner(System.in);
		   	
		   	//game mode selection
		   	int gameMode;
		   	while(true) { //loops until correct input (1 or 2)
		   		System.out.println("Gamemode 1: Player vs Player\nGamemode 2: Player vs NPC");
		   		System.out.println("Enter 1 or 2 to select your gamemode: ");
			   	gameMode = input.nextInt(); //takes in input from user for gameMode selection
			   	if(gameMode == 1 ||gameMode == 2) {
			   		break; //if user selection is one or 2 breaks the loop
			   	}
			   	else {
			   		System.out.println(gameMode + " is not a valid choice\n");//if not valid selection continue loop
			   	}
		   	}
		   	
		   	
			//Player vs Player gameMode
		   	if(gameMode == 1) {
			   	String player1Char = "";
			   	String player2Char = "";
			   	while(true) { //loops until correct selection of x or o
			   		System.out.println("Player 1: do you want to plays as X or O (enter X or O): ");
			   		String XorO = input.next();
				   	
			   		//sets playerChar to X or O depending on selection. Player 2 will automatically be set to the opposite of player 1's choice
			   		//loop breaks once valid choice is made
			   		if (XorO.equals("X") || XorO.equals("x")) {
				   		System.out.println("player 1 will play as X \nplayer 2 will play as O\n");
				   		player1Char = "X";
				   		player2Char = "O";
				   		break;
				   	}
				   	if (XorO.equals("O") || XorO.equals("o")) {
				   		System.out.println("player 1 will play as O \nplayer 2 will play as X\n");
				   		player1Char = "O";
				   		player2Char = "X";
				   		break;
				   	}
				   	else{
				   		System.out.println(XorO + " is not a valid choice.");//continues loop
				   	}
			   	}
			   	int counter = 0; //if counter reaches 9 gameboard is filled completely 
			   	int xCord,yCord; 
			   	boolean turn = true; //initilize turn, will allow loop for gameplay below to switch between p1 and p2
			   	TicTacToe.displayBoard(); //shows empty gameboard
		   		
			   	//loops for actual gameplay between player 1 and player 2
			   	while(true) {
			   		int playerTurn = turn?1:2; //switches between players 1 and 2 each loop
			   		System.out.printf("Player %d: enter (x y) coordinates of the position you wish to play in:", playerTurn);
			   		xCord = input.nextInt();
			   		yCord = input.nextInt();
			   		//rowcolumn[0] is where row is saved, rowcolumn[1] is where column is saved
			   		Integer[] rowcolumn = TicTacToe.convertXYcords(xCord, yCord); //saves the returned row/column converted from x/y cords

			   		//each set of if statements works the same, each just depends whether it is player 1/2's turn and whether they have chosen x or o
			   		if (playerTurn == 1) { //if it is player 1's turn
			   			if(player1Char.equals("X")){ //if they chose to play as X
			   				 try {
			   					TicTacToe.move(rowcolumn[0], rowcolumn[1], TicTacToe.Value.X); //uses method move to place the value
			   		   		 }
			   		   		 catch(Error e){ //if spot is taken error will return from move
			   		   			System.out.println(e); //print the error 
			   		   			continue; //since the space was taken the loop will continue without switching turns and reprompt the player
			   		   		 }
			   			}
			   			if(player1Char.equals("O")){
			   				 try {
			   					TicTacToe.move(rowcolumn[0], rowcolumn[1], TicTacToe.Value.O);
					   		 }
					   		 catch(Error e){
					   		   	System.out.println(e);
					   		   	continue;	
					   		 }
			   			}
			   		}
			   		if (playerTurn == 2) {
			   			if(player2Char.equals("X")) {
			   				try {
			   					TicTacToe.move(rowcolumn[0], rowcolumn[1], TicTacToe.Value.X);
			   				}
			   				catch(Error e) {
			   					System.out.println(e);
			   					continue;
			   				}
			   			}
			   			if(player2Char.equals("O")) {
			   				try {
			   					TicTacToe.move(rowcolumn[0], rowcolumn[1], TicTacToe.Value.O);
			   				}
			   				catch(Error e) {
			   					System.out.println(e);
			   					continue;
			   				}
			   			}
			   		}	
			   		TicTacToe.displayBoard(); //displays the sameboard with chosen move
			   		String winner = TicTacToe.checkWinner(); //uses class to check if there is a winner
			   		if (winner.equals("X") || winner.equals("O")) { //if x or o is returned from checkwinner then a winner exists (none is returned if there is not)
			   			System.out.printf("Congrats Player %d (%s's)! You Win!", playerTurn, winner); //prints the winner
			   			break; //loop breaks if there is winner
			   		}
			   		else {
			   			turn = !turn; //swaps turn for next loop player1<==>player2
			   		}
			   		if(counter == 9) { //if all 9 board spots are filled but there is no winner then it is a tie
			   			System.out.println("NO WINNER: TIE");
			   			break; //loop breaks if there is tie
			   		}
			   	}
		   	}
		   	
		   	//player vs NPC gamemode
		   	if(gameMode == 2) {
		   		String playerChar;
		   		String npcChar;
		   		while(true) { //loops until correct selection of x or o
			   		System.out.println("Player: do you want to plays as X or O (enter X or O): ");
			   		String XorO = input.next();
				   	
			   		//sets playerChar to X or O depending on selection. NPC will automatically be set to the opposite of player's choice
			   		//loop breaks once valid choice is made
			   		if (XorO.equals("X") || XorO.equals("x")) {
				   		System.out.println("player will play as X \nNPC will play as O\n");
				   		playerChar = "X";
				   		npcChar = "O";
				   		break;
				   	}
				   	if (XorO.equals("O") || XorO.equals("o")) {
				   		System.out.println("player will play as O \nNPC will play as X\n");
				   		playerChar = "O";
				   		npcChar = "X";
				   		break;
				   	}
				   	else{
				   		System.out.println(XorO + " is not a valid choice.");//continues loop
				   	}
			   	}
			   	int counter = 0; //if counter reaches 9 gameboard is filled completely 
			   	int xCord,yCord; 
			   	TicTacToe.displayBoard(); //shows empty gameboard
		   		
			   	//loops for actual gameplay between player and NPC
			   	while(true) {
			   		System.out.printf("Player's turn: enter (x y) coordinates of the position you wish to play in:");
			   		xCord = input.nextInt();
			   		yCord = input.nextInt();
			   		//rowcolumn[0] is where row is saved, rowcolumn[1] is where column is saved
			   		Integer[] rowcolumn = TicTacToe.convertXYcords(xCord, yCord); //saves the returned row/column converted from x/y cords

			   		//each set of if statements works the same, each just depends whether it is player 1/2's turn and whether they have chosen x or o
			   		if(playerChar.equals("X")){ //if they chose to play as X
			   			try {
			   				TicTacToe.move(rowcolumn[0], rowcolumn[1], TicTacToe.Value.X); //uses method move to place the value
			   			}
			   			catch(Error e){ //if spot is taken error will return from move
			   				System.out.println(e); //print the error 
			   				continue; //since the space was taken the loop will continue without switching turns and reprompt the player
			   			}
			   		}
			   		if(playerChar.equals("O")){
			   			try {
			   				TicTacToe.move(rowcolumn[0], rowcolumn[1], TicTacToe.Value.O);
			   			}
			   			catch(Error e){
			   				System.out.println(e);
			   				continue;	
			   			}
			   		}
			   		
			   		TicTacToe.displayBoard(); //displays the gameboard with chosen move
			   		
			   		//checks for winner / tie after player places x/o
			   		String winner = TicTacToe.checkWinner(); //uses class to check if there is a winner
			   		if (winner.equals("X") || winner.equals("O")) { //if x or o is returned from checkwinner then a winner exists (none is returned if there is not)
			   			System.out.printf("Congrats Player (%s's)! You Win!", winner); //prints the winner
			   			break; //loop breaks if there is winner
			   		}
			   		
			   		if(counter == 9) { //if all 9 board spots are filled but there is no winner then it is a tie
			   			System.out.println("NO WINNER: TIE");
			   			break; //loop breaks if there is tie;
			   		}
			   		
			   		//NPC's Turn
			   		System.out.println("NCP's turn");
			   		TicTacToe.npcPlayer(npcChar); //calls the method that makes the NPC's turn
			   		TicTacToe.displayBoard(); //displays the gameboard with NPC move

			   		//checks for winner after NPC places x/o
			   		winner = TicTacToe.checkWinner(); //uses class to check if there is a winner
			   		if (winner.equals("X") || winner.equals("O")) { //if x or o is returned from checkwinner then a winner exists (none is returned if there is not)
			   			System.out.printf("Player loses! NPC (%s's) Wins!", winner); //prints the winner
			   			break; //loop breaks if there is winner
			   		}
			   		
			   		if(counter == 9) { //if all 9 board spots are filled but there is no winner then it is a tie
			   			System.out.println("NO WINNER: TIE");
			   			break; //loop breaks if there is tie;
			   		}
			   	}

		   	}
	   }
}