import java.util.Random;

public class AIPlayer extends Player {

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

     public void makeMove(Board board) {
        System.out.println(name + " Is entering their move: ");
        Random rand = new Random();
        if (board.winningDiagonalRight(Character.toString(symbol)) != 0) {
            board.addChip(board.winningDiagonalRight(Character.toString(symbol)), symbol);
        } else if (board.winningDiagonalLeft(Character.toString(symbol)) != 0) {
            board.addChip(board.winningDiagonalLeft(Character.toString(symbol)), symbol);

        } else if (board.winningColumn(Character.toString(symbol)) != 0) {
            board.addChip(board.winningColumn(Character.toString(symbol)), symbol);

        } else if (board.winningVertical(Character.toString(symbol)) != 0) {
            board.addChip(board.winningVertical(Character.toString(symbol)), symbol);
        
        } else if (board.winningGap(Character.toString(symbol)) != 0) {
            board.addChip(board.winningGap(Character.toString(symbol)), symbol);
        } else if (board.diagonalGapLeft(Character.toString(symbol)) != 0) {
            board.addChip(board.diagonalGapLeft(Character.toString(symbol)), symbol);
        } else if (board.diagonalGapRight(Character.toString(symbol)) != 0) {
            board.addChip(board.diagonalGapRight(Character.toString(symbol)), symbol);
        }
           else if (board.winningDiagonalRight() != 0) {
                board.addChip(board.winningDiagonalRight(), symbol);
            } else if (board.winningDiagonalLeft() != 0) {
                board.addChip(board.winningDiagonalLeft(), symbol);
    
            } else if (board.winningColumn() != 0) {
                board.addChip(board.winningColumn(), symbol);
    
            } else if (board.winningVertical() != 0) {
                board.addChip(board.winningVertical(), symbol);
            
            } else if (board.winningGap() != 0) {
                board.addChip(board.winningGap(), symbol);
            } else if (board.diagonalGapLeft() != 0) {
                board.addChip(board.diagonalGapLeft(), symbol);
            } else if (board.diagonalGapRight() != 0) {
                board.addChip(board.diagonalGapRight(), symbol);
        } else {
            int col = (2 * rand.nextInt(7)) + 1;
            while (board.isFull(col)) {
                col = (2 * rand.nextInt(7)) + 1;
            }
            board.addChip(col, symbol);
        }
    }
}
