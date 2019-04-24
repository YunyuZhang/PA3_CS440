import java.util.*;
public class aiTicTacToe {

	public int player; //1 for player 1 and 2 for player 2

	public static List<List<positionTicTacToe>> wl = initializeWinningLines();
	public static int spaceLeft = 64;
	
	
	
	private static int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return board.get(index).state;
	}
	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
	{
		//TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
		
		do
			{
				int bestMove = Integer.MIN_VALUE;
				for(int i = 0;i< board.size();i++){
					if(board.get(i).state == 0){
						positionTicTacToe potential_move = board.get(i);
						potential_move.state = player;
						int move = minmax(board,2,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
						potential_move.state = 0;
						if(move > bestMove){
							myNextMove = potential_move;
							bestMove = move;
						}




					}
				}
//				Random rand = new Random();
//				int x = rand.nextInt(4);
//				int y = rand.nextInt(4);
//				int z = rand.nextInt(4);
//				myNextMove = new positionTicTacToe(x,y,z);
			}while(getStateOfPositionFromBoard(myNextMove,board)!=0);
		return myNextMove;
			
		
	}
	
	
	//TODO: Change possibleWinRows to make it return the winning rows that the position appears in
	/**
	 * Takes a Tic-Tac-Toe board and a player and returns the list of rows that the player is about to win in.
	 * @param board is a List of positionTicTacToe that represents the board state
	 * @param Integer player = 1 for player 1; player = 2 for player 2
	 * @return List of a List of positionTicTacToe values, each returning a line of positions that are about to win for the player
	 */
	public static List<List<positionTicTacToe>> possibleWinLines(List<positionTicTacToe> board, int player){
		List<List<positionTicTacToe>> possWinLines = new ArrayList<List<positionTicTacToe>>();

		for(List<positionTicTacToe> winning_combination:wl){
			//System.out.println(winning_combination);
			if (almostWinInLine(board, winning_combination, player)){

				possWinLines.add(winning_combination);
			}
		}
		return possWinLines;
	}
	
	public static boolean almostWinInLine(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player){

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
	
	
	public boolean hasWon() {
		
		// TODO: Still working on this rn.
		
		return false;
	}
	
	public static boolean hasWonInLine(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player) {
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
	 * 
	 * @param board
	 * @param current_position
	 * @param player
	 * @return
	 */
	public static int unblockedLines(List<positionTicTacToe> board, positionTicTacToe current_position, int player) {
		int count = 0;
		
		// TODO: Change into generating rows depending on current_position, rather than iterating over every winning row.
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
	 * LineCount counts the number of current positions that is currently occupied by the player. If another player also occupies the line, return 0.
	 * @param board
	 * @param position_list
	 * @param player
	 * @return returns 1-3 for the number of positions taken up by the player in the row. Returns 0 if all positions empty. Returns -1 if the enemy is in line.
	 */
	public static int lineCount(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player){
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
	

	
	/* TODO: Hash out the actual heuristic values given by the calcHeuristic() function.
	   Ideas:
	   - Prioritize Winning Move > Opponent's Winning Move (us losing) > Unblocked Winning Rows > Next best random move
	   - Prioritize moves that block an enemy's winning row(s)
	*/
	
	// Renamed calculate_heuristic() to calcHeuristic()
	
	/**
	 * 
	 * @param board
	 * @param player
	 * @return
	 */
	
	
	public static int calcHeuristic(List<positionTicTacToe> board, positionTicTacToe current_position, int player){
		int enemy;
		
		if(player == 1) enemy = 2;
		else enemy = 1;

		List<List<positionTicTacToe>> ourWinLines = possibleWinLines(board, player);
		if(!ourWinLines.isEmpty()) {
			//System.out.println("works");
			// Will only ever consider the first result of this list, because the game will end regardless of what line is looked at.
			if(contain(ourWinLines.get(0), current_position)) {  // TODO: Must ensure that current position is always an empty position. Else this bugs out.
				return 100;
			}
			else {
				return -100;
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
				return -50;
			}
		}
		
		int count = unblockedLines(board, current_position, player);

		//System.out.println("before: "+ count);
		List<List<positionTicTacToe>> winning_lines = initializeWinningLines();
		
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
	 * Given the list of four positions that make up a row, check to see if our given position is in that list.
	 * @param position_list
	 * @param current_position
	 * @return
	 */
	public static boolean contain(List<positionTicTacToe> position_list,positionTicTacToe current_position){
		for(positionTicTacToe position_spot:position_list){
			if (position_spot.x == current_position.x && position_spot.y == current_position.y && position_spot.z == current_position.z){
				return true;
			}
		}
		return false;
	}

	public static int minmax(List<positionTicTacToe> board,int depth, boolean maximingzingPlayer,int alpha,int beta){

		Integer positive_Inf = Integer.MAX_VALUE;
		Integer negative_Inf = Integer.MIN_VALUE;

		if(depth == 1 || spaceLeft > 1){
			ArrayList<Integer> score_list = new ArrayList<Integer>();
			for(int i = 0; i< board.size();i++){
				if(board.get(i).state == 0){
					positionTicTacToe potential_move = board.get(i);
					int value = calcHeuristic(board,potential_move,1);
					score_list.add(value);

				}

			}
			spaceLeft--;
			return Collections.max(score_list);

		}

		if (maximingzingPlayer){
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
			spaceLeft--;

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

			spaceLeft--;

			return value;

		}
	}



	

	
	
	private static List<List<positionTicTacToe>> initializeWinningLines()
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

	public static void main(String[] args) {

		//initializeWinningLines();
//		positionTicTacToe test_position1 = new positionTicTacToe(0,3,2,-1);
//		positionTicTacToe test_position2 = new positionTicTacToe(1,1,1,-1);

		//System.out.println(possibleWinLines(test_position1));




	}
}
