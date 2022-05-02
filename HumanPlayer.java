import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);

    }

    public void makeMove(Board board) {
        boolean isValid = false;
        Scanner obj = new Scanner(System.in);
        System.out.println(name + ", Please input your move: ");
        int col = obj.nextInt();

        if (col < 1 || col > 7) {
            while (isValid == false) {
                System.out.println(name + ", Input invalid please try again: ");
                col = obj.nextInt();
                if (col >= 1 && col <= 7) {
                    isValid = true;
                }
            }
        }

        while (board.isFull(2 * (col - 1) + 1)) {
            System.out.println(name + ", That column is full...please pick another: ");
            col = obj.nextInt();
        }


        board.addChip(2 * (col - 1) + 1, symbol);

    }

}