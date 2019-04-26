import java.util.*;

//TicTacToe-4x4x4
public class runTicTacToe {
	
	private List<List<positionTicTacToe>>  winningLines = new ArrayList<>(); 
	private List<positionTicTacToe> board = new ArrayList<>();
	private aiTicTacToe ai1;
	private aiTicTacToe ai2;

	private gua guaAi;
	
	public int result;
	public runTicTacToe()
	{
		//initialize winning lines
		winningLines = initializeWinningLines();
		//initialize board
		board = createTicTacToeBoard();
		
		//initialize AI players
		ai1 = new aiTicTacToe(1);
		ai2 = new aiTicTacToe(2);
	}
	public static List<positionTicTacToe> createTicTacToeBoard()
	{
		//create a 3-d 4x4x4 TicTacToe board and store it in a list
		List<positionTicTacToe> boardTicTacToe = new ArrayList<positionTicTacToe>();
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				for(int k=0;k<4;k++)
					{
						boardTicTacToe.add(new positionTicTacToe(i,j,k,0)); //0 is state "not marked"
					}
		return boardTicTacToe;
	}
	
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
		
		return winningLines;
		
	}

	
	private static int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> targetBoard)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return targetBoard.get(index).state;
	}
	private int isEnded()
	{
		//test whether the current game is ended
		
		//brute-force
		for(int i=0;i<winningLines.size();i++)
		{
			
			positionTicTacToe p0 = winningLines.get(i).get(0);
			positionTicTacToe p1 = winningLines.get(i).get(1);
			positionTicTacToe p2 = winningLines.get(i).get(2);
			positionTicTacToe p3 = winningLines.get(i).get(3);
			
			int state0 = getStateOfPositionFromBoard(p0,board);
			int state1 = getStateOfPositionFromBoard(p1,board);
			int state2 = getStateOfPositionFromBoard(p2,board);
			int state3 = getStateOfPositionFromBoard(p3,board);
			
			//if they have the same state (marked by same player) and they are not all marked.
			if(state0 == state1 && state1 == state2 && state2 == state3 && state0!=0)
			{
				//someone wins
				p0.state = state0;
				p1.state = state1;
				p2.state = state2;
				p3.state = state3;
				
				//print the satisified winning line (one of them if there are several)
				p0.printPosition();
				p1.printPosition();
				p2.printPosition();
				p3.printPosition();
				return state0;
			}
		}
		for(int i=0;i<board.size();i++)
		{
			if(board.get(i).state==0)
			{
				//game is not ended, continue
				return 0;
			}
		}
		return -1; //call it a draw
	}
	
	public void printAllWinningLines()
	{
		//print all winning lines to help understand in what cases will any player win
		//System.out.println(winningLines.size());
		for (int i=0;i<winningLines.size();i++)
		{
			System.out.println("Winning Line "+i+":");
			for (int j=0;j<winningLines.get(i).size();j++)
			{
				winningLines.get(i).get(j).printPosition();
			}
		}
	}
	public static void printBoardTicTacToe(List<positionTicTacToe> targetBoard)
	{
		//print each position on the board, uncomment this for debugging if necessary
		/*
		System.out.println("board:");
		System.out.println("board slots: "+board.size());
		for (int i=0;i<board.size();i++)
		{
			board.get(i).printPosition();
		}
		*/
		
		//print in "graphical" display
		for (int i=0;i<4;i++)
		{
			System.out.println("level(z) "+i);
			for(int j=0;j<4;j++)
			{
				System.out.print("["); // boundary
				for(int k=0;k<4;k++)
				{
					if (getStateOfPositionFromBoard(new positionTicTacToe(j,k,i),targetBoard)==1)
					{
						System.out.print("X"); //print cross "X" for position marked by player 1
					}
					else if(getStateOfPositionFromBoard(new positionTicTacToe(j,k,i),targetBoard)==2)
					{
						System.out.print("O"); //print cross "O" for position marked by player 2
					}
					else if(getStateOfPositionFromBoard(new positionTicTacToe(j,k,i),targetBoard)==0)
					{
						System.out.print("_"); //print "_" if the position is not marked
					}
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
	public static boolean makeMove(positionTicTacToe position, int player, List<positionTicTacToe> targetBoard)
	{
		//make move on Tic-Tac-Toe board, given position and player 
		//player 1 = 1, player 2 = 2
		
		//brute force (obviously not a wise way though)
		for(int i=0;i<targetBoard.size();i++)
		{
			if(targetBoard.get(i).x==position.x && targetBoard.get(i).y==position.y && targetBoard.get(i).z==position.z) //if this is the position
			{
				if(targetBoard.get(i).state==0)
				{
					targetBoard.get(i).state = player;
					//printBoardTicTacToe(targetBoard);
                    //System.out.println("================");
					return true;
				}
				else
				{
					System.out.println("Error: this is not a valid move.");
				}
			}
			
		}
		return false;
	}
	//change it to void later
	public int  run()
	{

		Random rand = new Random();
		int turn = rand.nextInt(2)+1; //1 = player1's turn, 2 = player2's turn, who go first is randomized 
		
		while((result = isEnded())==0) //game loop
		{
			if(turn==1)
			{
				positionTicTacToe player1NextMove = ai1.myAIAlgorithm(board,1,2); //1 stands for player 1
				if(makeMove(player1NextMove,1,board))
					turn = 2;
			}
			else if(turn==2)
			{//debug
<<<<<<< HEAD
				positionTicTacToe player2NextMove = ai2.myAIAlgorithm(board,1,0); //2 stands for player 2
=======
				positionTicTacToe player2NextMove = guaAi.myAIAlgorithm1(board,2); //2 stands for player 2
>>>>>>> 247c8ba78165bc7d0daaf166cdf44238a3498552
				if(makeMove(player2NextMove,2,board))
					turn = 1;
			}
			else 
			{
				//exception occurs, stop
				System.out.println("Error!");
			}
		}
		
			//game is ended
		if(result==1)
		{
			//game ends, player 1 wins 
			System.out.println("Player1 Wins");
			printBoardTicTacToe(board);
			System.out.println("Player average move time " + average(ai1.total_time_list) + " seconds ");
			return 1;

		}
		else if(result==2)
		{
			//game ends, player 2 wins
			System.out.println("Player2 Wins");
			printBoardTicTacToe(board);
			System.out.println("Player average move time " + average(ai1.total_time_list)+ " seconds ");
			return 2;
		}
		else if(result==-1)
		{
			//game ends, it's a draw 
			System.out.println("This is a draw.");
			printBoardTicTacToe(board);
			System.out.println("Player average move time  " + average(ai1.total_time_list)+ " seconds ");
			return -1;
		}
		else
		{
			//exception occurs, stop
			System.out.println("Error!");
			System.out.println("Player average move time  " + average(ai1.total_time_list)+ " seconds ");
			return -10;
		}
		
	}
	public static float average(ArrayList<Long> time_list){
		long sumTime = 0;
		for(long each_time: time_list){
			sumTime += each_time;
		}

		//System.out.println("sum " + sumTime+ " size " + time_list.size()+ " result " + sumTime/time_list.size()/1000.f);

		return sumTime/time_list.size()/1000.f;

	}
	

	
	//run the game once

	public static void main(String[] args) {		

		//run game loop
        int player1Win = 0;
        int player2Win =0;
<<<<<<< HEAD
        int numberofGame = 100;
        for(int i =0;i< numberofGame;i++){
=======
        for(int i =0;i< 10;i ++){
>>>>>>> 247c8ba78165bc7d0daaf166cdf44238a3498552
            runTicTacToe rttt = new runTicTacToe();
            int result = rttt.run();
            if (result == 1)
                player1Win++;
            if(result ==2)
                player2Win++;

        }
        System.out.println("Player 1 wins: " + player1Win);
		System.out.println("Player 2 wins: " + player2Win);
		System.out.println("Player 1 winning percentage:" + ((double) player1Win)/numberofGame);
		System.out.println("Player 2 winning percentage:" + ((double) player2Win)/numberofGame);




//        runTicTacToe rttt = new runTicTacToe();
//        rttt.run();




//        //test
//		positionTicTacToe x1 = new positionTicTacToe(1,0,0,1);
//		positionTicTacToe x2 = new positionTicTacToe(2,0,0,1);
//        positionTicTacToe x3 = new positionTicTacToe(0,2,0,1);
//        positionTicTacToe x4 = new positionTicTacToe(0,0,2,1);
//
//		positionTicTacToe o1 = new positionTicTacToe(0,3,0,2);
//        positionTicTacToe o2 = new positionTicTacToe(1,3,0,2);
//        positionTicTacToe o3 = new positionTicTacToe(2,3,0,2);
//        positionTicTacToe o4 = new positionTicTacToe(3,3,0,2);
//
//        List<positionTicTacToe> test_board = new ArrayList<>();
//		test_board = createTicTacToeBoard();
//		makeMove(x1,1,test_board);
//        makeMove(x2,1,test_board);
//        makeMove(x3,1,test_board);
//        makeMove(x4,1,test_board);
//
//        makeMove(o1,2,test_board);
//        makeMove(o2,2,test_board);
//        makeMove(o3,2,test_board);
//        makeMove(o4,2,test_board);
//
//        printBoardTicTacToe(test_board);
//
//        positionTicTacToe test_x = new positionTicTacToe(0,0,0,0);
//
//        System.out.println("heuristic:" + aiTicTacToe.calcHeuristic(test_board,test_x,1));
//        //System.out.println(aiTicTacToe.calcHeuristic(test_board,1));
//        System.out.println("Minmax: "+ aiTicTacToe.minmax(test_board,2,true,Integer.MAX_VALUE,Integer.MIN_VALUE));





	}
}


