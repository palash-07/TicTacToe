public class Tictactoe {
    private String[][] board;
    private static int rows = 3,columns = 3;

    // Constructor
    public Tictactoe() {
        board = new String[rows][columns];
        this.initialize();
    }

    // Initializing the empty board
    public void initialize() {
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < columns ; j++)
                board[i][j] = "   ";
        }
    }

    // Printing the board
    public String print() {
        String res = "";
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < columns ; j++){
                if (j != columns-1)
                res += board[i][j] + '|';
                else res += board[i][j];
            }
            if (i != rows-1)
            res += "\n___|___|___\n";
            else
                res += "\n   |   |   \n";
        }
        return res;
    }

    // Checks if valid or not
    public boolean isValid(int x,int y){
        if (x < 0 || y < 0 || x > 2 || y > 2) return false;
        return true;
    }

    // Checks if the position is empty or not
    public boolean isEmpty(int x,int y){
        if (board[x][y].equals("   ")) return true;
        return false;
    }

    // fills the position according to the given coordinates
    public boolean fill(int x,int y,String player){
        if (isValid(x,y) && isEmpty(x,y)) board[x][y] = " "+player+" ";
        else {
            System.out.println("This is not a valid coordinate to choose. TRY AGAIN");
            return false;
        }
        return true;
    }

    // Checks if we already found a winner
    public int isGameOver(String player){
        String p = " "+player+" ";
        // checking horizontal
        for (int i = 0 ; i < rows ; i++){
            if (!isEmpty(i,0) && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return (board[i][0].equals(p)) ? 1 : -1;
        }
        // checking vertical
        for (int i = 0 ; i < columns ; i++){
            if (!isEmpty(0,i) && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return (board[0][i].equals(p)) ? 1 : -1;
        }
        // checking diagonals
        if (!isEmpty(0,0) && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))return (board[1][1].equals(p) ? 1 : -1);
        if (!isEmpty(1,1) && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) return (board[1][1].equals(p) ? 1 : -1);

        return 0; // if none of them is winner
    }

    public boolean isBoardLeft(){
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < columns ; j++){
                if (board[i][j] == "   ") return true;
            }
        }
        return false;
    }

    private int minimax(String player,String opponent,boolean turn){
        int score = isGameOver(player);
        if (score == 1) return score;
        if (score == -1) return -1;

        if (!isBoardLeft()) return 0;
        int best;
        if (turn){ // this is our turn so we need the best result (+ highest)
            best = -10;
            for (int i = 0 ; i < rows ; i++) {
                for (int j = 0; j < columns; j++) {
                    if (board[i][j] == "   ") {
                        board[i][j] = " " + player + " ";
                        best = Math.max(best, minimax(player, opponent, !turn));
                        board[i][j] = "   ";
                    }
                }
            }
        }
        else { // opponent is also smart so he will choose his best score which will be greater in negative
            best = 10;
            for (int i = 0 ; i < rows ; i++) {
                for (int j = 0; j < columns; j++) {
                    if (board[i][j] == "   ") {
                        board[i][j] = " " + opponent + " ";
                        best = Math.min(best, minimax(player, opponent, !turn));
                        board[i][j] = "   ";
                    }
                }
            }
        }
        return best;
    }

    // Minimax Algorithm
    public int [] getComputerMove(String player){
        int bestScore = -10;
        int bestRow = -1,bestCol = -1;
        String opponent = (player.equals("X")) ? "O" : "X";
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0; j < columns ; j++){
                if (board[i][j] == "   "){
                    board[i][j] = " "+player+" ";
                    int moveVal = minimax(player,opponent,false);
                    board[i][j] = "   ";
                    if (moveVal > bestScore) {
                        bestRow = i; bestCol = j; bestScore = moveVal;
                    }
                }
            }
        }
        int [] res = new int[2];
        res[0] = bestRow; res[1] = bestCol;
        return res;
    }
}
