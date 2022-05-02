
public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board();
        ConnectFour game = new ConnectFour(board);
         board.reset();
         AIPlayer bob = new AIPlayer('B', board, "Bob");
         game.setPlayer1(new HumanPlayer('R', board, "Alice")); 
         game.setPlayer2(new AIPlayer('B', board, "Maestro"));
         game.playGame();



        
    }
}