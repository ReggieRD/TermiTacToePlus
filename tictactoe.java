import java.util.Scanner;

public class tictactoe{
	static char [][] board;
	
	public static void main (String [] args){
		int dimensions;
		board = new char[3][3];
		run();
	}
	
	public static boolean insert(int row, int col, char c){
		return false;
	}

	public static boolean delete(int row, int col, char c){
		return false;
	}
	public static char checkWin(){
		return 0;
	}
	/* some additional functions to spice up the game:
	swap two characters (in the event of a full board, players 
	may swap two characters whether it be their own or opposition*/

	public static boolean swap(int[] a, int[] b){
		return false;
	}
	
	/*===============================================================
	visualisation methods
	===============================================================*/

	public static void printBoard(){
		System.out.print("   ");
		for (int col = 0; col < board[0].length; col ++){
			System.out.print(col+1+" ");
		}
		System.out.println();
		for (int row = 0; row < board.length; row++){
			System.out.print(row+1+"  ");
			for (int col =0; col < board[0].length; col++){
				System.out.print(board[row][col] + "|");
			}
			System.out.println();
		}
	}

	public static void init() {
		for (int row = 0; row < board.length; row++){
			for (int col =0; col < board[0].length; col++){
				board[row][col]='_';
			}
		}
	 
	}

	/*==============================================================
	main game loop funtion run()
	==============================================================*/
	public static void run(){
		Scanner In = new Scanner(System.in);	
		boolean running = true;
		boolean playerX = true; /* if false then playerO */
		int xCredits = 0, yCredits = 0;
		char move = '/'; 
		init();
		/* Key
		/ none
		s swap (cost 2 credits)
		i insert (0 Credit)
		d delete and replace (allows users to remove another players move and
		place their own (costs 3 credits)
		credits increase by 1 with each turn
		*/
		while(running) {
			printBoard();
			System.out.print("Move (s,i,d): ");
			move = In.next().charAt(0);
			if (move == 's') {
				if (playerX){
					if (xCredits >= 2) {
						/* execute the swap */
						/*clear previous line and queue next prompt (first
						 * coordinates, clear and queue second prompt (next
						 * coordinates then finally clear and indicate status*/
					}
					else {
						/*invalid swap forfeit turn. clear previous line and
						 * show that the move was invalid*/
						playerX = !playerX;
					}
				} else if (yCredits >= 2) {
					/*execute swap*/

				} else {
					/*invalid swap forfeit turn*/
					playerX = !playerX;
				}
			} else if (move == 'i' || move == 'd') {
				/*prompts to get coordinates*/
				System.out.print("Select Row: ");
				int r = In.nextInt();
				System.out.print("Select Col: ");
				int c = In.nextInt();
				if (move == 'i'){
					if (playerX) insert(r,c, 'X');
					else insert(r,c,'O');
				} else {
					if (playerX && xCredits >= 3) delete(r,c, 'X'); /*where character is the
					character to replace deleted char with*/
					//else if (xCredits <3 ) /*not enough credits*/
					//else if (!playerX && yCredits>=3) delete(r,c,'O');
					//else /*not enough credits*/
				}
			}

		}

	}

}
