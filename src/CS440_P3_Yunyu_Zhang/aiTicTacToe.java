import java.util.*;
public class aiTicTacToe {

	public int player; //1 for player 1 and 2 for player 2

	public List<List<positionTicTacToe>> wl = initializeWinningLines();
	public ArrayList<Long> total_time_list = new ArrayList<Long>();

	
	
	private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return board.get(index).state;
	}

	/**
	 * Creates a deep copy of the board so that any board references in the calculations
	 * don't accidentally alter the official TTT board.
	 * @param board is the List<positionTicTacToe> TTT board that is being played on.
	 * @return returns a List<positionTicTacToe> TTT board that is a copy of the input, but doesn't reference it.
	 */
	private List<positionTicTacToe> deepCopyATicTacToeBoard(List<positionTicTacToe> board)
	{
		//deep copy of game boards
		List<positionTicTacToe> copiedBoard = new ArrayList<positionTicTacToe>();
		for(int i=0;i<board.size();i++)
		{
			copiedBoard.add(new positionTicTacToe(board.get(i).x,board.get(i).y,board.get(i).z,board.get(i).state));
		}
		return copiedBoard;
	}
	
	
	/**
	 * myAIAlgorithm is called by runTicTacToe for an AI player, to give it a position to move in TTT.
	 * @param board is the List<positionTicTacToe> TTT board that is being played on.
	 * @param player is 1 for X, or 2 for O.
	 * @return returns a positionTicTacToe position not occupied on the board input with the given player state.
	 */
	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
	{
		// this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.

		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
		final long startTime = System.currentTimeMillis();
				int maxScore = Integer.MIN_VALUE;
				for(int i = 0;i< board.size();i++){
					if(board.get(i).state == 0){
					List<positionTicTacToe> copyBoard = deepCopyATicTacToeBoard(board);
					copyBoard.get(i).state = player;
					int move = ultimateMinmax(copyBoard,0,player,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
					if(move > maxScore){
						//System.out.println(move);
						myNextMove.x = board.get(i).x;
						myNextMove.y = board.get(i).y;
						myNextMove.z = board.get(i).z;
						//myNextMove.printPosition();
						maxScore = move;
					}

				}
			}
		//printBoardScores(board, player);
		//myNextMove.printPosition();
		final long endTime = System.currentTimeMillis();

		long runtime = endTime - startTime;
		total_time_list.add(runtime);
		//System.out.println("move takes " + runtime/10000f + "seconds");

		return myNextMove;
	}
	
	
	public positionTicTacToe randomMove(List<positionTicTacToe> board, int player)
	{
		// this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);

		do
		{
			Random rand = new Random();
			int x = rand.nextInt(4);
			int y = rand.nextInt(4);
			int z = rand.nextInt(4);
			myNextMove = new positionTicTacToe(x,y,z);
		}while(getStateOfPositionFromBoard(myNextMove,board)!=0);
		return myNextMove;


	}


	
	/**
	 * Takes a Tic-Tac-Toe board and a player and returns the list of rows that the player is about to win in.
	 * @param board is a List of positionTicTacToe that represents the board state
	 * @param Integer player = 1 for player 1; player = 2 for player 2
	 * @return List of a List of positionTicTacToe values, each returning a line of positions that 
	 * are about to win for the player
	 */
	public List<List<positionTicTacToe>> possibleWinLines(List<positionTicTacToe> board, int player){
		List<List<positionTicTacToe>> possWinLines = new ArrayList<List<positionTicTacToe>>();

		for(List<positionTicTacToe> winning_combination:wl){
			//System.out.println(winning_combination);
			if (almostWinInLine(board, winning_combination, player)){

				possWinLines.add(winning_combination);
			}
		}
		return possWinLines;
	}
	
	/**
	 * Takes a Tic-Tac-Toe board and a player and returns the list of rows that the player is 
	 * able to block an about-to-win enemy in.
	 * @param board is a List of positionTicTacToe that represents the board state
	 * @param Integer player = 1 for player 1; player = 2 for player 2
	 * @return List of a List of positionTicTacToe values, each returning a line of positions
	 *  that the player is able to block an about-to-win enemy in.
	 */
	public int goodBlockLines(List<positionTicTacToe> board, int player){
		int count = 0;

		for(List<positionTicTacToe> winning_combination:wl){
			//System.out.println(winning_combination);
			if (almostWinInLine(board, winning_combination, player)){

				count++;
			}
		}
		return count;
	}
	
	/**
	 * Takes a Tic-Tac-Toe board and a player and returns the list of rows that the player
	 * is about to win in.
	 * @param board is a List of positionTicTacToe that represents the board state
	 * @param Integer player = 1 for player 1; player = 2 for player 2
	 * @return List of a List of positionTicTacToe values, each returning a line of positions
	 *  that the player is about to win in.
	 */
	public boolean almostWinInLine(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player){

		int count = 0;
		int enemy;
		
		if(player == 1) enemy = 2;
		else enemy = 1;
		
		for(positionTicTacToe position:position_list){
			if (getStateOfPositionFromBoard(position, board) == player){
				count++;
			}
			else if (getStateOfPositionFromBoard(position, board) == enemy) {
				return false;
			}
		}
		if(count == 3) return true;
		return false;
	}
	
	/**
	 * Checks if a player has won the TTT board.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return returns True if player has won, false else.
	 */
	public boolean hasWon(List<positionTicTacToe> board, int player) {
		
		for(List<positionTicTacToe> line:wl) {
			if(hasWonInLine(board, line, player)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Helper function for hasWon(). Checks if player has won in a given line.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param position_list is a List<positionTicTacToe> that contain four positions of a line in the TTT board.
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return returns True if the player has won in the line, false else.
	 */
	public boolean hasWonInLine(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player) {
		int count = 0;
		
		for(positionTicTacToe position:position_list) {
			if (getStateOfPositionFromBoard(position, board) == player){
				count++;
			}
		}
			
		if(count == 4) return true;
		return false;
	}
	
	
	/**
	 * UnblockedLines counts the number of unblocked lines at a position for a player in a TTT board.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param current_position is a positionTicTacToe of the position being checked.
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return
	 */
	public int unblockedLines(List<positionTicTacToe> board, positionTicTacToe current_position, int player) {
		int count = 0;
		
		// Change into generating rows depending on current_position, rather than iterating over every winning row.
		// Possible?
		for(List<positionTicTacToe> line:wl) {
			if(contain(line, current_position)) {
				if(lineCount(board, line, player) >= 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * totalUnblockedLines counts the total number of unblocked lines for a player in a TTT board.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return returns an integer that is the total number of unblocked lines.
	 */
	public int totalUnblockedLines(List<positionTicTacToe> board, int player) {
		int count = 0;
		
		for(List<positionTicTacToe> line:wl) {
			int lineCheck = lineCount(board, line, player);
			if( lineCheck >= 0) {
				count += lineCheck;
			}
		}
		return count;
	}
	
	
	/**
	 * LineCount counts the number of current positions that is currently occupied by the
	 *  player. If another player also occupies the line, return 0.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param position_list is a List<positionTicTacToe> that contain four positions of a line in the TTT board.
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return returns 1-3 for the number of positions taken up by the player 
	 * in the row. Returns 0 if all positions empty. Returns -1 if the enemy is in line.
	 */
	public int lineCount(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player){
		int count = 0;
		int enemy;
		
		if(player == 1) enemy = 2;
		else enemy = 1;
		
		for(positionTicTacToe position:position_list){
			if (getStateOfPositionFromBoard(position, board) == player){
				count++;
			}
			else if (getStateOfPositionFromBoard(position, board) == enemy) {
				return -1;
			}
		}
		return count;
	}
	
	/**
	 * goodBlockCount checks if the player has blocked the enemy who was about to win in a line.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param position_list is a List<positionTicTacToe> that contain four positions of a line in the TTT board.
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return
	 */
	public boolean goodBlockCount(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player) {
		int our_count = 0;
		int enemy_count = 0;
		int enemy = 3 - player;
		
		
		for(positionTicTacToe position:position_list) {
			if (getStateOfPositionFromBoard(position, board) == player){
				our_count++;
			}
			else if (getStateOfPositionFromBoard(position, board) == enemy) {
				enemy_count++;
			}
		}
		
		if(our_count == 1 && enemy_count == 3) {
			return true;
		}
		
		return false;
	}
	

	
	/* Hash out the actual heuristic values given by the calcHeuristic() function.
	   Ideas:
	   - Prioritize Winning Move > Opponent's Winning Move (us losing) > Unblocked Winning Rows > Next best random move
	   - Prioritize moves that block an enemy's winning row(s)
	*/

	

	

	//Should calcHeuristic return a value based on the player or the AI? (if player is enemy AKA not AI)
	// OBSOLETE but important
	public int calcHeuristic(List<positionTicTacToe> board, positionTicTacToe current_position, int player){
		int enemy;
		
		if(player == 1) enemy = 2;
		else enemy = 1;
		
		if(hasWon(board, player)) {
			return 100;
		}
		
		if(hasWon(board, enemy)) {
			return -100;
		}

		List<List<positionTicTacToe>> ourWinLines = possibleWinLines(board, player);
		if(!ourWinLines.isEmpty()) {
			//System.out.println("works");
			// Will only ever consider the first result of this list, because the game will end regardless of what line is looked at.
			if(contain(ourWinLines.get(0), current_position)) {  // TODO: Must ensure that current position is always an empty position. Else this bugs out.
				return 100;
			}
			else {
				return -100; // TODO: might want to get rid of this 
			}
		}
		
		List<List<positionTicTacToe>> enemyWinLines = possibleWinLines(board, enemy);
		if(!enemyWinLines.isEmpty()) {
      
			//System.out.println("works 2");
			// Will only ever consider the first result of this list, because the game will end regardless of what line is looked at.
			if(contain(enemyWinLines.get(0), current_position)) {  // TODO: Must ensure that current position is always an empty position. Else this bugs out.

				return 50;
			}
			else {
				return -50; // TODO: might want to get rid of this (Also might want to switch the values around idk)
			}
		}
		
		int count = unblockedLines(board, current_position, player);

		//System.out.println("before: "+ count);
		//List<List<positionTicTacToe>> winning_lines = initializeWinningLines();
		
		for(List<positionTicTacToe> line:wl) {

			if(contain(line, current_position)) {
				int linePoints = lineCount(board, line, player);
				if(linePoints > 0) {
					count += linePoints;
					//System.out.println("after: "+ count);
				}
			}
		}
		
		return count;
	}
	
	
	/**
	 * ultimateHeuristic returns a heuristic score of the current state of the board depending on the player.
	 * Higher score is more desirable.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return
	 */
	public int ultimateHeuristic(List<positionTicTacToe> board, int player) {
		int enemy;
		
		if(player == 1) enemy = 2;
		else enemy = 1;

		

		if(possibleWinLines(board, enemy).size() > 0 || hasWon(board, enemy)) {
			return -1000;
		}
		
		if(hasWon(board, player)) {
			return 1000;
		}
		
		
		int count = totalUnblockedLines(board, player);
		
		// WinPoints and lossPoints are lines where we about to win or lose
		int winPoints = possibleWinLines(board, player).size() * 2;
		int lossPoints = possibleWinLines(board, enemy).size() * -2; // Not necessary
		// Blockpoints are to encourage the AI creating a board state where an about-to-win enemy is blocked
		int blockPoints = goodBlockLines(board, enemy) * 2;
		

		
		int result = count + winPoints + lossPoints + blockPoints;
		

		//System.out.println("before: "+ count);
		//List<List<positionTicTacToe>> winning_lines = initializeWinningLines();
		
		return result;
	}
	
	
	/**
	 * Given the list of four positions that make up a row, check to see if our given position is in that list.
	 * @param position_list is a List<positionTicTacToe> that contain four positions of a line in the TTT board.
	 * @param current_position is a positionTicTacToe of the position on the TTT board.
	 * @return
	 */
	public boolean contain(List<positionTicTacToe> position_list,positionTicTacToe current_position){
		for(positionTicTacToe position_spot:position_list){
			if (position_spot.x == current_position.x && position_spot.y == current_position.y && position_spot.z == current_position.z){
				return true;
			}
		}
		return false;
	}
	public boolean hasSpaceLeft(List<positionTicTacToe> board){
		for(int i=0;i<board.size();i++)
		{
			if(board.get(i).state==0)
			{
				return true;
			}
		}
		return false;
	}

	// OBSOLETE but important
	public int minmax(List<positionTicTacToe> board,int depth, boolean maximizingPlayer,int alpha,int beta){

		Integer positive_Inf = Integer.MAX_VALUE;
		Integer negative_Inf = Integer.MIN_VALUE;
		//System.out.println("true or false " + maximizingPlayer);

		if(depth == 1 || !hasSpaceLeft(board)){
			ArrayList<Integer> score_list = new ArrayList<Integer>();
			for(int i = 0; i< board.size();i++){
				if(board.get(i).state == 0){
					positionTicTacToe potential_move = board.get(i);
					int value = calcHeuristic(board,potential_move,1);
					score_list.add(value);

				}

			}
			//System.out.println("herustic " + score_list + Collections.max(score_list));
			return Collections.max(score_list);

		}

		if (maximizingPlayer){
			//System.out.println("maxxminzing ");
			int value = negative_Inf;

			for(int i = 0;i< board.size();i++){
				if(board.get(i).state == 0){
					positionTicTacToe potential_move = board.get(i);
					value = calcHeuristic(board,potential_move,1);
					value = Math.max(value,minmax(board,depth-1,false,alpha,beta));
					alpha = Math.max(alpha,value);
					if (beta <= alpha){
						break;
					}

				}
			}
			//System.out.println("value in max " + value);

			return value;
		}
		else{
			int value = positive_Inf;
			for(int i = 0;i< board.size();i++){
				if(board.get(i).state == 0){
					positionTicTacToe potential_move = board.get(i);
					value = calcHeuristic(board,potential_move,2);
					value = Math.max(value,minmax(board,depth-1,true,alpha,beta));
					beta = Math.min(beta,value);
					if (beta <= alpha){
						break;
					}

				}
			}

			return value;

		}
	}


	/**
	 * This returns the best value from ultimateHeuristic() for the given player in a TTT board, using 
	 * alpha-beta pruning.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param depth is an integer that is the number of moves the AI will look ahead by. Even numbers and 0 only.
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @param maximizer is a boolean that should always be set to true when called outside this function. Ensures
	 * that the player called with the function is the maximizing player for the purpose of minmax.
	 * @param alpha integer used for alpha-beta pruning. Max integer for scores.
	 * @param beta integer used for alpha-beta pruning. Min integer for scores.
	 * @return returns the best score for player as determined by minmax.
	 */
	public int ultimateMinmax(List<positionTicTacToe> board,int depth,int player, boolean maximizer, int alpha,int beta){
		//https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
		int enemy = 3 - player;
		
		Integer positive_Inf = Integer.MAX_VALUE;
		Integer negative_Inf = Integer.MIN_VALUE;
		
		
		if(hasWon(board,enemy)) {
			return -1000;
		}
		
		if(hasWon(board,player)) {
			return 1000;
		}
		if(depth == 0 || !hasSpaceLeft(board)){
			//System.out.println(ultimateHeuristic(board));
			return ultimateHeuristic(board, player);

		}
		if (maximizer){
			int bestValue = negative_Inf;

			for(int i = 0;i< board.size();i++){
				if(board.get(i).state == 0){
					List<positionTicTacToe> copyBoard = deepCopyATicTacToeBoard(board);
					copyBoard.get(i).state = player;
					int value = ultimateMinmax(copyBoard,depth-1,player,false,alpha,beta);
					if(value > bestValue) {
						bestValue = value;
						//System.out.print("The maximizer at depth " + depth + " is: " + bestValue + " ");
						//board.get(i).printPosition();
					}
					
					//bestValue = Math.max(bestValue,value);
//					if(board.get(i).x == 0 && board.get(i).y == 1 && board.get(i).z == 0) {
//						System.out.print("The maximizer at depth " + depth + " is: " + bestValue + " ");
//						board.get(i).printPosition();
//					}
					alpha = Math.max(alpha,bestValue);
					//board.get(i).state = 0;

					if (beta <= alpha){
						break;
					}

				}
			}

			//System.out.print(value);
			return bestValue;
		}
		else{
			//System.out.println("minnnning  ");
			int bestValue = positive_Inf;
			for(int i = 0;i< board.size();i++){
				if(board.get(i).state == 0){
					List<positionTicTacToe> copyBoard = deepCopyATicTacToeBoard(board);
					copyBoard.get(i).state = enemy;
					int value = ultimateMinmax(copyBoard,depth-1,player,false,alpha,beta);
					if(value < bestValue) {
						bestValue = value;
						//System.out.print("The maximizer at depth " + depth + " is: " + bestValue + " ");
						//board.get(i).printPosition();
					}
					//bestValue = Math.min(bestValue,value);

					beta = Math.min(beta,bestValue);
					//board.get(i).state = 0;
					if (beta <= alpha){
						break;
					}

				}
			}

			//System.out.print(value);

			return bestValue;



		}
	}



	

	
	
	private List<List<positionTicTacToe>> initializeWinningLines()
	{
		//create a list of winning line so that the game will "brute-force" check if a player satisfied any 	winning condition(s).
		List<List<positionTicTacToe>> winningLines = new ArrayList<List<positionTicTacToe>>();
		
		//48 straight winning lines
		//z axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,j,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//y axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,j,-1));
				winningLines.add(oneWinCondtion);
			}
		//x axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,j,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//12 main diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,0,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,1,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,2,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//yz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//xy plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,0,i,-1));
				oneWinCondtion.add(new positionTicTacToe(1,1,i,-1));
				oneWinCondtion.add(new positionTicTacToe(2,2,i,-1));
				oneWinCondtion.add(new positionTicTacToe(3,3,i,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//12 anti diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,3,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,2,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,1,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,0,-1));
				winningLines.add(oneWinCondtion);
			}
		//yz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,3,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,0,-1));
				winningLines.add(oneWinCondtion);
			}
		//xy plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,3,i,-1));
				oneWinCondtion.add(new positionTicTacToe(1,2,i,-1));
				oneWinCondtion.add(new positionTicTacToe(2,1,i,-1));
				oneWinCondtion.add(new positionTicTacToe(3,0,i,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//4 additional diagonal winning lines
		List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,3,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,3,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,0,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(3,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(0,3,3,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,3,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,0,3,-1));
		winningLines.add(oneWinCondtion);

//		System.out.println("wl "+winningLines);
//		for(int i =0; i< winningLines.size();i++){
//			List<positionTicTacToe> one_line = winningLines.get(i);
//			for (int j = 0; j< one_line.size();j++){
//				String xyz_line = Integer.toString(one_line.get(j).x) + Integer.toString(one_line.get(j).y)+ Integer.toString(one_line.get(j).z)+ Integer.toString(one_line.get(j).state);
//
//				System.out.println(xyz_line);
//
//			}
//
//	}

		return winningLines;
		
	}
	public aiTicTacToe(int setPlayer)
	{
		player = setPlayer;
	}
	
	
	
	/**
	 * Helper code that prints the point value for a board if a piece were to be placed there by player.
	 * @param board is a List<positionTicTacToe> that represents the board state
	 * @param player = 1 for player 1; player = 2 for player 2
	 */
	public void printBoardScores(List<positionTicTacToe> board, int player) {
		
		for (int i=0;i<4;i++)
		{
			System.out.println("level(z) "+i);
			for(int j=0;j<4;j++)
			{
				System.out.print("["); // boundary
				for(int k=0;k<4;k++)
				{
					int index = j*16+k*4+i;
					//positionTicTacToe temp = new positionTicTacToe(i, j, k, 1);
					List<positionTicTacToe> tempBoard = deepCopyATicTacToeBoard(board);
					if (tempBoard.get(index).state == 0) {
						tempBoard.get(index).state = player;
					}
					
					System.out.print(" "); 
					System.out.print(ultimateHeuristic(tempBoard, player));
					System.out.print(" "); //print cross "X" for position marked by player 1
					//tempBoard.get(index).state = 0;
					if(k==3)
					{
						System.out.print("]"); // boundary
						System.out.println();
					}
					
					
				}

			}
			System.out.println();
		}
	}
	
}
