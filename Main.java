import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Tictactoe game = new Tictactoe();
        String player = "X";
        int cnt = 0;
        while (true){
            if (cnt%2 == 0) {
                System.out.println(game.print());
                System.out.print("Enter the coordinates : ");
                int row = sc.nextInt(); int col = sc.nextInt();
                boolean t = game.fill(row,col,player);
                if (!t){
                    System.out.println("This move is not valid. Try Another");
                    continue;
                }
            }
            else{
                System.out.println(game.print());
                System.out.println("Computer's Turn");
                int[] coord = game.getComputerMove(player);
                game.fill(coord[0],coord[1],player);
            }

            cnt++;
            if (game.isGameOver(player) != 0){
                System.out.println(game.print());
                if (player == "X") System.out.println("Result : You win"); // This is never gonna happen LOL
                else System.out.println("Result : Computer Wins");
                break;
            }
            if (player == "X") player = "O";
            else player = "X";
            if (cnt == 9) {
                System.out.println(game.print());
                System.out.println("Result : Tie");
                break;
            }
        }
    }
}
