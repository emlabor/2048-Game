import java.util.Scanner;
public class Play2048{
    public static int score=0;
    public static void printWelcomeAndDirections(){
        System.out.println("Press U for up, D for down, L for left, and R for right.");
    }

    public static void printBoard(int [][] board) {
        int digits = 0;
        System.out.println("---------------------");
        for (int r = 0; r<board.length; r++)
        {
            for (int c =0; c<board[0].length; c++)
            {
                if (board[r][c] > -1 && board[r][c] < 10)
                    digits = 1;
                if (board[r][c] >10 && board[r][c] <100)
                    digits = 2;
                if (board[r][c] >100 && board[r][c]<1000)
                    digits =3;
                if (board[r][c] >1000)
                    digits = 4;
                digits = 4 - digits;
                String spaces = "";
                for (int i =0;i<digits; i++){
                    spaces = spaces + " ";
                }
                if (board[r][c] == 0)
                    System.out.print("|" + spaces + " ");
                else   
                    System.out.print("|" + spaces + board[r][c]);
            }
            System.out.println("|");
            System.out.println("---------------------");
        }
    }

    public static void placeBlock(int [][] board){
        // places a single random 2 somewhere in a cell that contains 0.
        // optional:  place a random 4, 10% of the time
        boolean open = false;
        for (int r = 0; r<board.length;r++)
        {
            for (int c =0; c<board.length;c++)
            {
                if (board[r][c] == 0)
                    open = true;
            }
        }

        int r = (int)(Math.random()*4);
        int c = (int)(Math.random()*4);
        while (open && board[r][c] != 0)
        {
            r = (int)(Math.random()*4);
            c = (int)(Math.random()*4);
        }
        board[r][c] = 2;
    }

    public static char getUserChoice(){
        Scanner bob = new Scanner (System.in);
        System.out.println("Make your move. ");
        String choice = bob.next().toLowerCase();
        boolean valid = false;
        if (choice.equals("l") || choice.equals("r") || choice.equals("d") || choice.equals("u"))
            valid = true;
        while (!valid)
        {
            System.out.print("Invalid character. Try again. ");
            choice = bob.next().toLowerCase();
            if (choice.equals("l") || choice.equals("r") || choice.equals("d") || choice.equals("u"))
                valid = true;
        }
        return (char)(choice.charAt(0));
    }

    public static void processUserChoice(int [][] board) {
        char entry = getUserChoice();
        if (entry == 'u')
        {
            slideUp(board); mergeUp(board); slideUp(board);
        }
        if (entry == 'd')
        {
            slideDown(board); mergeDown(board); slideDown(board);
        }
        if (entry == 'l')
        {
            slideLeft(board); mergeLeft(board); slideLeft(board);
        }
        if (entry == 'r')
        {
            slideRight(board); mergeRight(board); slideRight(board);
        }
    }

    public static String updateGameState(int [][] board){
        for (int r=0; r<board.length; r++)
        {
            for (int c=0; c<board[0].length; c++)
            {
                if (board[r][c] == 2048)
                    return "win";
            }
        }
        for (int r=0; r<board.length; r++)
        {
            for (int c=0; c<board[0].length; c++)
            {
                if (board[r][c] == 0)
                    return "continue";
            }
        }
        return "lose";
    }

    public static void slideUp(int [][] board){
        for (int i =0; i<4; i++){
            for (int r =1; r<board.length; r++)
            {
                for (int c =0; c<board[0].length; c++)
                {
                    if (board[r-1][c] == 0)
                    {
                        board[r-1][c] = board[r][c];
                        board[r][c] = 0;
                    }
                }
            }
        }
    }

    public static void mergeUp(int [][] board){
        for (int r =1; r<board.length; r++)
        {
            for (int c =0; c<board[0].length; c++)
            {
                if (board[r-1][c] == board[r][c])
                {
                    board[r-1][c] = board[r][c] + board[r-1][c];
                    score += 2*(board[r][c]);
                    board[r][c] = 0;
                }
            }
        }
    }

    public static void slideDown(int[][]board){
        for (int i=0; i<4; i++){
            for (int r =0; r<board.length-1; r++)
            {
                for (int c =0; c<board[0].length; c++)
                {
                    if (board[r+1][c] == 0)
                    {
                        board[r+1][c] = board[r][c];
                        board[r][c] = 0;
                    }
                }
            }
        }
    }

    public static void mergeDown(int[][]board){
        for (int r =0; r<board.length-1; r++)
        {
            for (int c =0; c<board[0].length; c++)
            {
                if (board[r+1][c] == board[r][c])
                {
                    board[r+1][c] = board[r][c] + board[r+1][c];
                    score += 2*(board[r][c]);
                    board[r][c] = 0;
                }
            }
        }
    }

    public static void slideLeft(int[][]board){
        for (int i =0; i<4; i++){
            for (int r =0; r<board.length; r++)
            {
                for (int c =1; c<board[0].length; c++)
                {
                    if (board[r][c-1] == 0)
                    {
                        board[r][c-1] = board[r][c];
                        board[r][c] = 0;
                    }
                }
            }
        }
    }

    public static void mergeLeft(int[][]board){
        for (int r =0; r<board.length; r++)
        {
            for (int c =1; c<board[0].length; c++)
            {
                if (board[r][c-1] == board[r][c])
                {
                    board[r][c-1] = board[r][c] + board[r][c-1];
                    score += 2*(board[r][c]);
                    board[r][c] = 0;
                }
            }
        }
    }

    public static void slideRight(int[][]board){
        for (int i=0; i<4; i++){
            for (int r =0; r<board.length; r++)
            {
                for (int c =0; c<board[0].length-1; c++)
                {
                    if (board[r][c+1] ==0)
                    {
                        board[r][c+1] = board[r][c];
                        board[r][c] = 0;
                    }
                }
            }
        }
    }

    public static void mergeRight(int[][]board){
        for (int r =0; r<board.length; r++)
        {
            for (int c =0; c<board[0].length-1; c++)
            {
                if (board[r][c+1] == board[r][c])
                {
                    board[r][c+1] = board[r][c] + board[r][c+1];
                    score += 2*(board[r][c]);
                    board[r][c] = 0;
                }
            }
        }
    }

    public static boolean canMerge(int [][]board){
        for (int r =0; r<board.length; r++)
        {
            for (int c =0; c<board[0].length-1; c++)
            {
                if (board[r][c+1] == board[r][c])
                {
                    return true;
                }
            }
        }
        for (int r =0; r<board.length; r++)
        {
            for (int c =1; c<board[0].length; c++)
            {
                if (board[r][c-1] == board[r][c])
                {
                    return true;
                }
            }
        }
        for (int r =0; r<board.length-1; r++)
        {
            for (int c =0; c<board[0].length; c++)
            {
                if (board[r+1][c] == board[r][c])
                {
                    return true;
                }
            }
        }
        for (int r =1; r<board.length; r++)
        {
            for (int c =0; c<board[0].length; c++)
            {
                if (board[r-1][c] == board[r][c])
                {
                    return true;
                }
            }
        }
        return false;
    }

        public static void main(String [] args){
        int [][] board = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        //int score = 0;
        printWelcomeAndDirections(); 
        placeBlock(board); placeBlock(board); 
        printBoard(board);
        System.out.println("Score: " + score);

        boolean merge = canMerge(board);
        String gameState = updateGameState(board);
        while (gameState.equals("continue") || merge)
        {
            boolean change = false;
            int [][] copy = new int[4][4];
            for (int r = 0; r<board.length; r++)
            {
                for (int c =0; c<board[0].length; c++){
                    copy[r][c] = board[r][c];
                }
            }
            processUserChoice(board);
            for (int r = 0; r<board.length;r++){
                for (int c =0; c<board[0].length;c++){
                    if (copy[r][c] != board[r][c])
                        change = true;
                }
            }
            if (change)
            {
                placeBlock(board);
            }
            gameState = updateGameState(board);
            merge = canMerge(board);
            printBoard(board);
            System.out.println("Score: " + score);
        }
        if (gameState.equals("lose") && !merge)
            System.out.println("Game Over. You lose!");
        if (gameState.equals("win") && !merge)
            System.out.println("Game Over. You won!");
    }
}