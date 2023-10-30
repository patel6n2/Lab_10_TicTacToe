import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String[][] board = new String[ROW][COL];
    public static void main(String[] args)
    {
        boolean playAgain = false;
        boolean playing = true;
        Scanner in = new Scanner(System.in);
        String player = "X";
        int moveCount = 0;
        int row = -1;
        int col = -1;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;
        do
        {
            clearBoard();
            player = "X";
            playing = true;
            moveCount = 0;
            do
            {
                do
                {
                    display();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(in,"Enter row",1,3);
                    col = SafeInput.getRangedInt(in,"Enter column",1,3);
                    row--;
                    col--;
                }while (!isValidMove(row,col));
                board [row][col] = player;
                moveCount++;
                if(moveCount >= MOVES_FOR_WIN)
                {
                    if(isWin(player))
                    {
                        display();
                        System.out.println("Player " + player + " wins!");
                        playing = false;
                    }
                }
                if(moveCount >= MOVES_FOR_TIE)
                {
                    if(isTie())
                    {
                        display();
                        System.out.println("It's a tie!");
                        playing = false;
                    }
                }
                if(player.equals("X"))
                    player = "O";
                else
                    player = "X";
            }while(playing);
            playAgain = SafeInput.getYNConfirm(in,"Play again?");
        }while(playAgain);
    }

    /**
     * sets all cells to a space
     */
    private static void clearBoard()
    {
        for(int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    /**
     * visual representation of board
     */
    private static void display()
    {
        System.out.println();
        for(int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                System.out.print(board[row][col]);
                if (col < COL - 1)
                    System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * checks move legality
     * @param row int proposed move row
     * @param col int proposed move column
     * @return boolean true if space at proposed move
     */
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    /**
     * checks for win state
     * @param player String current player (X or O)
     * @return boolean true if any win condition applies
     */
    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    /**
     * checks for column win
     * @param player String current player (X or O)
     * @return boolean true if win condition applies
     */
    private static boolean isColWin(String player)
    {
        for(int col = 0; col < COL; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * checks for row win
     * @param player String current player (X or O)
     * @return boolean true if win condition applies
     */
    private static boolean isRowWin(String player)
    {
        for(int row = 0; row < ROW; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * checks for diagonal win
     * @param player String current player (X or O)
     * @return boolean true if win condition applies
     */
    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board [2][2].equals(player)) || (board [0][2].equals(player) && board [1][1].equals(player) && board [2][0].equals(player));
    }

    /**
     * check for tie
     * @return boolean true if any tie condition applies
     */
    private static boolean isTie()
    {
        boolean vector1Eliminated = false;
        boolean vector2Eliminated = false;
        boolean vector3Eliminated = false;
        boolean vector4Eliminated = false;
        boolean vector5Eliminated = false;
        boolean vector6Eliminated = false;
        boolean vector7Eliminated = false;
        boolean vector8Eliminated = false;
        for(int row = 0; row < ROW; row++)
        {
            if((board[row][0].equals("X") && board[row][1].equals("O")) || (board[row][0].equals("O") && board[row][1].equals("X")))
            {
                vector1Eliminated = true;
            }
            if((board[row][0].equals("X") && board[row][2].equals("O")) || (board[row][0].equals("O") && board[row][2].equals("X")))
            {
                vector2Eliminated = true;
            }
            if((board[row][1].equals("X") && board[row][2].equals("O")) || (board[row][1].equals("O") && board[row][2].equals("X")))
            {
                vector3Eliminated = true;
            }
        }
        for (int col = 0; col < COL; col++)
        {
            if((board[0][col].equals("X") && board[1][col].equals("O")) || (board[0][col].equals("O") && board[1][col].equals("X")))
            {
                vector4Eliminated = true;
            }
            if((board[0][col].equals("X") && board[2][col].equals("O")) || (board[0][col].equals("O") && board[2][col].equals("X")))
            {
                vector5Eliminated = true;
            }
            if((board[1][col].equals("X") && board[2][col].equals("O")) || (board[1][col].equals("O") && board[2][col].equals("X")))
            {
                vector6Eliminated = true;
            }
        }
        if((board[0][0].equals("X") && board[1][1].equals("O")) || (board[0][0].equals("O") && board[1][1].equals("X")) || (board[1][1].equals("X") && board[2][2].equals("O")) || (board[1][1].equals("O") && board[2][2].equals("X")) || (board[0][0].equals("X") && board[2][2].equals("O")) || (board[0][0].equals("O") && board[2][2].equals("X")))
        {
            vector7Eliminated = true;
        }
        if((board[0][2].equals("X") && board[1][1].equals("O")) || (board[0][2].equals("O") && board[1][1].equals("X")) || (board[0][2].equals("X") && board[2][0].equals("O")) || (board[0][2].equals("O") && board[2][0].equals("X")) || (board[2][0].equals("X") && board[1][1].equals("O")) || (board[2][0].equals("O") && board[1][1].equals("X")))
        {
            vector8Eliminated = true;
        }
        return (vector1Eliminated && vector2Eliminated && vector3Eliminated && vector4Eliminated && vector5Eliminated && vector6Eliminated && vector7Eliminated && vector8Eliminated);
    }
}