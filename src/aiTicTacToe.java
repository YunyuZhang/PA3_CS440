import java.util.*;
public class aiTicTacToe {

	public int player; //1 for player 1 and 2 for player 2
	private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
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
				Random rand = new Random();
				int x = rand.nextInt(4);
				int y = rand.nextInt(4);
				int z = rand.nextInt(4);
				myNextMove = new positionTicTacToe(x,y,z);
			}while(getStateOfPositionFromBoard(myNextMove,board)!=0);
		return myNextMove;
			
		
	}
	
	
	// Renamed heuristic() function to possibleWinRows() to more accurately describe its function
	//TODO: Change possibleWinRows to make it return the winning rows that the position appears in
	/**
	 * Takes a position on the Tic-Tac-Toe board and returns the number of winning rows it appears in.
	 * @param current_position the positionTicTacToe position of the piece being placed.
	 * @param player = 1 for player 1; player = 2 for player 2
	 * @return Integer that shows how many winning rows the position appears in.
	 */
	public List<List<positionTicTacToe>> possibleWinLines(List<positionTicTacToe> board, int player){
		List<List<positionTicTacToe>> possWinLines = new ArrayList<List<positionTicTacToe>>();
		List<List<positionTicTacToe>> winning_lines = initializeWinningLines();
		for(List<positionTicTacToe> winning_combination:winning_lines){
			System.out.println(winning_combination);
			if (almostWin(board, winning_combination, player)){
				possWinLines.add(winning_combination);
			}
		}
		return possWinLines;
	}
	
	
	public boolean almostWin(List<positionTicTacToe> board, List<positionTicTacToe> position_list, int player){
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
		else return false;
	}
	
	public int unblockedLines(List<positionTicTacToe> board, positionTicTacToe current_position, int player) {
		int count = 0;
		
		// TODO: Change into generating rows depending on current_position, rather than iterating over every winning row.
		
		List<List<positionTicTacToe>> possible_lines = initializeWinningLines();
		for(List<positionTicTacToe> line:possible_lines) {
			if(contain(line, current_position)) {
				if(lineCount(board, line, player) >= 1) {
					count++;
				}
			}
		}
		return count;
	}
	
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
	
	// TODO: Write a check for if enemy is about to win, AKA see if enemy has 3 positions and one empty position in a winning row
	
	
	
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
	
	
	public int calcHeuristic(List<positionTicTacToe> board, positionTicTacToe current_position, int player){
		int enemy;
		
		if(player == 1) enemy = 2;
		else enemy = 1;
		
		List<List<positionTicTacToe>> winning_line = initializeWinningLines();
		List<List<positionTicTacToe>> ourWinLines = possibleWinLines(board, player);
		if(!ourWinLines.isEmpty()) {
			if(contain(ourWinLines.get(0), current_position)) {  // TODO: Must ensure that current position is always an empty position. Else this bugs out.
				return 100;
			}
		}
		
		List<List<positionTicTacToe>> enemyWinLines = possibleWinLines(board, enemy);
		if(!ourWinLines.isEmpty()) {
			if(contain(enemyWinLines.get(0), current_position)) {  // TODO: Must ensure that current position is always an empty position. Else this bugs out.
				return 50;
			}
			else {
				return -50;
			}
		}
		
		
		
		
		//TODO: The rest of this fucking shit
		
		
		
		
		
		return 0;
		
		
		
		
		/*		List<List<positionTicTacToe>> winning_line = initializeWinningLines();
		int player1_counter = 0;
		int player2_counter = 0;
		for(positionTicTacToe move:board){
			if(move.state == 1) {
				//System.out.println("move is " + move.x + " " + move.y + " " + move.z);
				for (List<positionTicTacToe> winning_combination : winning_line) {
					if (contain(winning_combination, move)) {
						player1_counter++;
					}
				}
			}
		}

		for(positionTicTacToe move:board){
			if(move.state == 2) {
				//System.out.println("move is " + move.x + " " + move.y + " " + move.z);
				for (List<positionTicTacToe> winning_combination : winning_line) {
					if (contain(winning_combination, move)) {
						player2_counter++;
					}
				}
			}
		}

		return player1_counter - player2_counter;*/
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
		positionTicTacToe test_position1 = new positionTicTacToe(0,3,2,-1);
		positionTicTacToe test_position2 = new positionTicTacToe(1,1,1,-1);

		//System.out.println(possibleWinLines(test_position1));

	}
}
