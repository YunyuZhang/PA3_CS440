import java.util.*;
public class gua {

    public int player; //1 for player 1 and 2 for player 2
    private static int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
    {
        //a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
        int index = position.x*16+position.y*4+position.z;
        return board.get(index).state;
    }
    public static boolean isMovesLeft(List<positionTicTacToe> board){
        for(int i=0;i<board.size();i++)
        {
            if(board.get(i).state==0)
            {
                return true;
            }
        }
        return false;
    }
    public static int evaluate1(List<positionTicTacToe> board, int player)
    {
        List<List<positionTicTacToe>> winningLines = initializeWinningLines();
        //List<List<positionTicTacToe>> winningLines = runTicTacToe.winningLines;
        int count = 0;
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

            if(state0 == state1 && state1 == state2 && state2 == state3 && state0 == player){
                count += 438076;
                //count = (int)Double.POSITIVE_INFINITY;
                //return count;
            }
            if(state0 == state1 && state1 == state2 && state2 == state3 && state0 == (3-player)){
                count -= 438076;
                //count -= 100000000;
            }

            if(state0 == player && state0 == state1 && state1 == state2 && state3 == 0 ||
                    state0 == player && state0 == state1 && state1 == state3 && state2 == 0 ||
                    state0 == player && state0 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == player && state1 == state2 && state2 == state3 && state0 == 0 ){
                count += 5776;
            }
            if(state0 == (3-player) && state0 == state1 && state1 == state2 && state3 == 0 ||
                    state0 == (3-player) && state0 == state1 && state1 == state3 && state2 == 0 ||
                    state0 == (3-player) && state0 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == (3-player) && state1 == state2 && state2 == state3 && state0 == 0 ){
                count -= 5776;
            }
            if(state0 == player && state0 == state1 && state2 == state3 && state2 == 0 ||
                    state0 == player && state0 == state2 && state1 == state3 && state1 == 0 ||
                    state0 == player && state0 == state3 && state1 == state2 && state1 == 0 ||
                    state1 == player && state1 == state2 && state0 == state3 && state0 == 0 ||
                    state1 == player && state1 == state3 && state0 == state2 && state0 == 0 ||
                    state2 == player && state2 == state3 && state0 == state1 && state0 == 0){
                count += 76;
            }
            if(state0 == (3-player) && state0 == state1 && state2 == state3 && state2 == 0 ||
                    state0 == (3-player) && state0 == state2 && state1 == state3 && state1 == 0 ||
                    state0 == (3-player) && state0 == state3 && state1 == state2 && state1 == 0 ||
                    state1 == (3-player) && state1 == state2 && state0 == state3 && state0 == 0 ||
                    state1 == (3-player) && state1 == state3 && state0 == state2 && state0 == 0 ||
                    state2 == (3-player) && state2 == state3 && state0 == state1 && state0 == 0){
                count -= 76;
            }
            if(state0 == player && state1 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == player && state0 == state2 && state2 == state3 && state0 == 0 ||
                    state2 == player && state0 == state1 && state1 == state3 && state0 == 0 ||
                    state3 == player && state0 == state1 && state1 == state2 && state0 == 0){
                count += 1;
            }
            if(state0 == (3-player) && state1 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == (3-player) && state0 == state2 && state2 == state3 && state0 == 0 ||
                    state2 == (3-player) && state0 == state1 && state1 == state3 && state0 == 0 ||
                    state3 == (3-player) && state0 == state1 && state1 == state2 && state0 == 0){
                count -= 1;
            }

        }
        //System.out.println(count);
        return count;
    }

    public int evaluate2(List<positionTicTacToe> board, int player)
    {
        List<List<positionTicTacToe>> winningLines = initializeWinningLines();
        //List<List<positionTicTacToe>> winningLines = runTicTacToe.winningLines;
        int count = 0;
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

            if(state0 == state1 && state1 == state2 && state2 == state3 && state0 == player){
                count += 438076;
                //count = (int)Double.POSITIVE_INFINITY;
                //return count;
            }
            if(state0 == state1 && state1 == state2 && state2 == state3 && state0 == (3-player)){
                count -= 438077;
                //count -= 100000000;
            }

            if(state0 == player && state0 == state1 && state1 == state2 && state3 == 0 ||
                    state0 == player && state0 == state1 && state1 == state3 && state2 == 0 ||
                    state0 == player && state0 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == player && state1 == state2 && state2 == state3 && state0 == 0 ){
                count += 5776;
            }
            if(state0 == (3-player) && state0 == state1 && state1 == state2 && state3 == 0 ||
                    state0 == (3-player) && state0 == state1 && state1 == state3 && state2 == 0 ||
                    state0 == (3-player) && state0 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == (3-player) && state1 == state2 && state2 == state3 && state0 == 0 ){
                count -= 5777;
            }
            if(state0 == player && state0 == state1 && state2 == state3 && state2 == 0 ||
                    state0 == player && state0 == state2 && state1 == state3 && state1 == 0 ||
                    state0 == player && state0 == state3 && state1 == state2 && state1 == 0 ||
                    state1 == player && state1 == state2 && state0 == state3 && state0 == 0 ||
                    state1 == player && state1 == state3 && state0 == state2 && state0 == 0 ||
                    state2 == player && state2 == state3 && state0 == state1 && state0 == 0){
                count += 76;
            }
            if(state0 == (3-player) && state0 == state1 && state2 == state3 && state2 == 0 ||
                    state0 == (3-player) && state0 == state2 && state1 == state3 && state1 == 0 ||
                    state0 == (3-player) && state0 == state3 && state1 == state2 && state1 == 0 ||
                    state1 == (3-player) && state1 == state2 && state0 == state3 && state0 == 0 ||
                    state1 == (3-player) && state1 == state3 && state0 == state2 && state0 == 0 ||
                    state2 == (3-player) && state2 == state3 && state0 == state1 && state0 == 0){
                count -= 77;
            }
            if(state0 == player && state1 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == player && state0 == state2 && state2 == state3 && state0 == 0 ||
                    state2 == player && state0 == state1 && state1 == state3 && state0 == 0 ||
                    state3 == player && state0 == state1 && state1 == state2 && state0 == 0){
                count += 1;
            }
            if(state0 == (3-player) && state1 == state2 && state2 == state3 && state1 == 0 ||
                    state1 == (3-player) && state0 == state2 && state2 == state3 && state0 == 0 ||
                    state2 == (3-player) && state0 == state1 && state1 == state3 && state0 == 0 ||
                    state3 == (3-player) && state0 == state1 && state1 == state2 && state0 == 0){
                count -= 2;
            }

        }
        //System.out.println(count);
        return count;
    }

    public static int minimax1(List<positionTicTacToe> board, int depth, int player, boolean isMax){
        int score = evaluate1(board, player);
        if(depth == 0 || isMovesLeft(board) == false){
            return score;
        }
        if(isMax){
            int best = -100000000;
            for(int i = 0; i < board.size(); i++){
                if(board.get(i).state == 0){
                    board.get(i).state = player;
                    best = Math.max(best,minimax1(board, depth-1, 3-player,false));
                    board.get(i).state = 0;
                }
            }
            return best;
        }
        else{
            int best = 100000000;
            for(int i = 0; i < board.size(); i++){
                if(board.get(i).state == 0){
                    board.get(i).state = player;
                    best = Math.min(best,minimax1(board, depth-1, 3-player, true));
                    board.get(i).state = 0;
                }
            }
            return best;
        }

    }

    public int minimax2(List<positionTicTacToe> board, int depth, int player, boolean isMax){
        int score = evaluate2(board, player);
        if(depth == 0 || isMovesLeft(board) == false){
            return score;
        }
        if(isMax){
            int best = -100000000;
            for(int i = 0; i < board.size(); i++){
                if(board.get(i).state == 0){
                    board.get(i).state = player;
                    best = Math.max(best,minimax2(board, depth-1, 3-player,false));
                    board.get(i).state = 0;
                }
            }
            return best;
        }
        else{
            int best = 100000000;
            for(int i = 0; i < board.size(); i++){
                if(board.get(i).state == 0){
                    board.get(i).state = player;
                    best = Math.min(best,minimax2(board, depth-1, 3-player, true));
                    board.get(i).state = 0;
                }
            }
            return best;
        }

    }
    public static positionTicTacToe myAIAlgorithm1(List<positionTicTacToe> board, int player)
    {
        //TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
        positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
        int bestVal = -100000000;
        for(int i = 0; i < board.size(); i++){
            if(board.get(i).state == 0){
                board.get(i).state = player;
                int moveVal = minimax1(board,1,3-player,false);
                board.get(i).state = 0;
                if(moveVal > bestVal){
                    myNextMove = board.get(i);
                    bestVal = moveVal;
                }
            }

        }
        //System.out.println(bestVal);
        return myNextMove;
    }
    public positionTicTacToe myAIAlgorithm2(List<positionTicTacToe> board, int player)
    {
        //TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
        positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
        int bestVal = -100000000;
        for(int i = 0; i < board.size(); i++){
            if(board.get(i).state == 0){
                board.get(i).state = player;
                int moveVal = minimax2(board,1,3-player,false);
                board.get(i).state = 0;
                if(moveVal > bestVal){
                    myNextMove = board.get(i);
                    bestVal = moveVal;
                }
            }

        }
        //System.out.println(bestVal);
        return myNextMove;
    }
    public positionTicTacToe RanAIAlgorithm(List<positionTicTacToe> board, int player)
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

        return winningLines;

    }
    public gua(int setPlayer)
    {
        player = setPlayer;
    }
}
