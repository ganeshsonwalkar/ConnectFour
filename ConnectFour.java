import java.util.Scanner;

public class ConnectFour {

    // Declaring and defining the references and variables.
    Scanner sc = null;
    static int c = 0;
    static int r = 0;
    static int p=0;
    ConnectFour() {
    sc = new Scanner(System.in);
        
    }

    // this method will be used to create the design of game.
    // we will be using multi-dimentional array for rows and columns.
    public String[][] designLayout(int r, int c) { 
    // using multi-dimentional string array for the layout.
    //multiplying and adding the values of column and rows to make perfect layout.
        String[][] block = new String[r][c*2];

        //Using nested loops for the layout.
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                
                if (j % 2 == 0) {
                    block[i][j] = "|";
                } else {
                    block[i][j] = " ";
                }
                if(i==(r-1)) block[i][j]= "-";
            }
        }
        return block;
    }

    // printLayout method will be used to print the layout on the console.
    public void printLayout(String[][] block) {
        // Using nested loops it will print the multidimensional array.
        // Using enhanced for loop to print the values.
        for (String[] block1 : block) {
            for (String item : block1) {
                System.out.print(item);
            }
            System.out.println();
        }
    }

    public void redPattern(String[][] block) {
        System.out.println("Red Player's Turn.");
        System.out.println("Choose any column from 0-"+(c)+" :");

        // we will have to use the following formula in order to
        // put the red in that specific column.
        int dec=sc.nextInt();
        if(dec==0) System.exit(1);
        int col = 2 * dec + 1;
        //Using for loop we can put the red in that specific column.
        for (int i = c - 2; i >= 0; i--) {         
            if (block[i][col] == " ") {
                block[i][col] = "r";
                break;
            }
        }

    }

    public void yellowPattern(String[][] block) {
        //same logic will be used here that we have used for red.
        System.out.println("Yellow Player's Turn.");
        System.out.println("Choose any column from 0-"+(c)+" :");
        int dec=sc.nextInt();
        if(dec==0) System.exit(1);
        int col = 2 * dec + 1;
        for (int i = c - 2; i >= 0; i--) {
            if (block[i][col] == " ") {
                block[i][col] = "y";
                break;
            }
        }
    }

    public String resultChecking(String[][] block) {
        // here we will check for the winning conditions like,horizontal line,
        //vertical line, diagonal line. 
        // this for loop is used to find the horizontal line.
        for (int i = 0; i < c-1; i++) {
            for (int j = 0; j < c; j += 2) {
                if ((block[i][j + 1] != " ")
                        && (block[i][j + 3] != " ")
                        && (block[i][j + 5] != " ")
                        && (block[i][j + 7] != " ")
                        && ((block[i][j + 1] == block[i][j + 3])
                        && (block[i][j + 3] == block[i][j + 5])
                        && (block[i][j + 5] == block[i][j + 7]))) 
                {   
                    // if theres an pattern we found then we will return the color red or yellow.
                    // and print the winner.
                    return block[i][j + 1];
                }
            }
        }

        for (int i = 1; i < 15; i += 2) {
            for (int j = 0; j <= p; j++) {
                if ((block[j][i] != " ")
                        && (block[j + 1][i] != " ")
                        && (block[j + 2][i] != " ")
                        && (block[j + 3][i] != " ")
                        && ((block[j][i] == block[j + 1][i])
                        && (block[j + 1][i] == block[j + 2][i])
                        && (block[j + 2][i] == block[j + 3][i]))) {
                    return block[j][i];
                }
            }
        }
        for (int i = 0; i <= p; i++) {
            for (int j = 1; j < 9; j += 2) {
                if ((block[i][j] != " ")
                        && (block[i + 1][j + 2] != " ")
                        && (block[i + 2][j + 4] != " ")
                        && (block[i + 3][j + 6] != " ")
                        && ((block[i][j] == block[i + 1][j + 2])
                        && (block[i + 1][j + 2] == block[i + 2][j + 4])
                        && (block[i + 2][j + 4] == block[i + 3][j + 6]))) {
                    return block[i][j];
                }
            }
        }

        for (int i = 0; i <= p; i++) {
            for (int j = 7; j < 15; j += 2) {
                if ((block[i][j] != " ")
                        && (block[i + 1][j - 2] != " ")
                        && (block[i + 2][j - 4] != " ")
                        && (block[i + 3][j - 6] != " ")
                        && ((block[i][j] == block[i + 1][j - 2])
                        && (block[i + 1][j - 2] == block[i + 2][j - 4])
                        && (block[i + 2][j - 4] == block[i + 3][j - 6]))) {
                    return block[i][j];
                }
            }
        }

        return null;
    }

    public static void main(String[] arg) {
    ConnectFour cf = new ConnectFour();
    if(Integer.parseInt(arg[0])<=0 && Integer.parseInt(arg[1])<=0 && Integer.parseInt(arg[2])<=0){
    System.out.println("Please enter positive numbers as an argument.");
    System.exit(1);
    }else 
    {
    r=Integer.parseInt(arg[0])*2;
    c=Integer.parseInt(arg[1])/2;
    p=Integer.parseInt(arg[2]);
    } 


    String[][] block = cf.designLayout(Integer.parseInt(arg[0]),Integer.parseInt(arg[1]));
    boolean turn = true;
    //use boolean variable to keep track.
    int count = 0;
    cf.printLayout(block);
    while(turn)
    {
       //Let's say that Red gets the first turn and thus
       //every other turn 
       if (count % 2 == 0){ 
        cf.redPattern(block);
       }
       else {
        cf.yellowPattern(block);
       }
       count++;//We need to keep track of the turns
       cf.printLayout(block);
       //Let's say we want to check for a winner during every
       //turn made and say who it is
       if (cf.resultChecking(block) != null)
       {
          if (cf.resultChecking(block) == "r")
             System.out.println("The Red player has won.");
          else if (cf.resultChecking(block)== "y")
            System.out.println("The Yellow player has won.");
         //Well, if someone one, then the game has to end
         turn = false;
    }
  }
}
}