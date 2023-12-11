import java.util.Scanner;

public class tictactoe{
	static char [][] board;
	
	public static void main (String [] args){
		int dimensions;
		board = new char[3][3];
		run();
	}
	
	public static boolean insert(int row, int col, char c){
		if (row >=1 && row <= board.length){
			if (col>=1 && row <=board[0].length){
				if (board[row-1][col-1]=='_'){
					board[row-1][col-1] = c;
					return true;
				} else return false;
			} else return false;
		} else return false;
	}

	public static boolean delete(int row, int col, char c){
		if (row >=1 && row <= board.length){
			if (col>=1 && row <=board[0].length){
				board[row-1][col-1] = c;
				return true;
			} else return false;
		} else return false;
	
	}
	public static char checkWin(){
		return 0;
	}
	/* some additional functions to spice up the game:
	swap two characters (in the event of a full board, players 
	may swap two characters whether it be their own or opposition*/

	public static boolean swap(int[] a, int[] b){
		char temp = board[a[0]][a[1]];
		board[a[0]][a[1]] = board[b[0]][b[1]];
		board[b[0]][b[1]] = temp;
		return true;
		/*TODO validation*/
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
		int xCredits = 0, oCredits = 0;
		char move = '/'; 
		init();
		/* Key
		/ none
		s swap (cost 3 credits)
		i insert (0 Credit)
		d delete and replace (allows users to remove another players move and
		place their own (costs 4 credits)
		credits increase by 1 with each turn
		*/
		while(running) {
			System.out.println("================================================");
			System.out.println("X Credits: "+xCredits+"|| O Credits: "+oCredits);
			System.out.print("Current Player: ");
			if (playerX) System.out.println("X");
			else System.out.println("O");
			System.out.println("================================================\n");
			printBoard();
			System.out.print("Move (s,i,d,p): ");
			move = In.next().charAt(0);
			if (move == 'g'){
				oCredits += 1000;
				continue;
			}
			if (move == 's') {
				int []a = new int[2];
				int []b = new int[2];
				System.out.print("Select Source Row: ");
				a[0] = In.nextInt()-1;
				System.out.print("Select Source Col: ");
				a[1] = In.nextInt()-1;
				System.out.print("Select Destination Row: ");
				b[0] = In.nextInt()-1;
				System.out.print("Select Destination Col: ");
				b[1] = In.nextInt()-1;

				
				if (playerX){
					if (xCredits >= 2) {
						/* execute the swap */
						swap(a,b);
						/*clear previous line and queue next prompt (first
						 * coordinates, clear and queue second prompt (next
						 * coordinates then finally clear and indicate status*/
					}
					else {
						/*invalid swap forfeit turn. clear previous line and
						 * show that the move was invalid*/
						 System.out.println("not enough credits for x to swap");
						//playerX = !playerX;
					}
				} else if (oCredits >= 2) {
					/*execute swap*/
					swap(a,b);

				} else {
					/*invalid swap forfeit turn*/
					//playerX = !playerX;
					System.out.println("not enough credits for o to swap");
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
					if (playerX && xCredits >= 4){
						delete(r,c, 'X'); 
						xCredits -= 4;
					} else if (playerX) System.out.println("Not enough credits for X");

					if (!playerX && oCredits >= 4){
						delete(r,c, 'O');
						oCredits -= 4;
					} else if (!playerX) System.out.println("Not enough credits for O");
					/*TODO remember to do credit reduction*/ 
					/*where character is the
					character to replace deleted char with*/
					//else if (xCredits <3 ) /*not enough credits*/
					//else if (!playerX && yCredits>=3) delete(r,c,'O');
					//else /*not enough credits*/
				}
			}
			if (playerX) xCredits++;
			else oCredits++;
			playerX = !playerX;
	
		}

	}

}
