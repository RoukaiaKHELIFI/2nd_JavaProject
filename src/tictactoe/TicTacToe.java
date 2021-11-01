package tictactoe;

/**
 *
 * @author roukaia
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    //Attributs mtaa class TicTacToe  => position eli bech yakhtarha l joueur wel Computer (randomized) 
    //aalech f array list u may ask ?
    //well khater ay position bech idakhalha que ce soit cpu walla joueur
    //n7ot'ha f list pour verifier baad chkoun rba7 7asb l list mtaa reb7

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        char[][] gameBoard = {
            //hedhi bech n3amer feha
            {' ', '|', ' ', '|', ' '},
            //hedhi juste pour faire la separation
            {'-', '+', '-', '+', '-'},
            //and again
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

//So meloul 7achti bel affichage mtaa l matrice bien sur bech ikoun feragh
        printGameBoard(gameBoard);

        //boucle while juste bech najm n9olou i3awed i3abili each time 7atta lin nal9a winner bech naaml break lel boucle
        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.print("choose a number : ");
            int pos = sc.nextInt();
            System.out.println(pos);
            //tawa mademna khdhina l position eli bech i7ot feha l joueur el X mteeou 
            //bech nthabet ken l position eli 5tarha feha 7aja walla le which is important khater
            //manajamch n7ot X f blasset l O
            while (playerPosition.contains(pos) || cpuPosition.contains(pos)) {
                //lahné jit nthabet par exemple ena 7atit X f position 1 w baad 9otlou 3awed 7otlou f nafs l position
                //bien sur c pas possible khater l blasa adhika deja reservé
                //bien sur zeda manajmch n7ot X f blasset l O 
                System.out.println("Position Taken ! Enter a correct Position");
                pos = sc.nextInt();
            }

            //mel fonction hedhi 5dhina la position choisi m joueur
            placePiece(gameBoard, pos, "player");

            if (checkWinner().length() > 0) {
                //puisque l fonction mteei checkWinner ya traja3lek message congrats ya 7aja okhra bien sur l length mteeha bech ikoun akber m 0
                System.out.println(checkWinner());
                break;
            }
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            //lahné l cpu bech yekhou ay noumrou randomized m [0..9]
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {

                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);
            if (checkWinner().length() > 0) {
                System.out.println(checkWinner());
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        //for each row inside of the gameboard
        for (char[] row : gameBoard) {
            //for each symbole inside of the row
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbole = ' ';
        if (user.equals("player")) {
            //deja f parametre ena samutou user 9otlou kenou player (puisque f main ena m3ayta lel fonction mara player marra cpu)
            //A3tih X
            symbole = 'X';
            playerPosition.add(pos);
        } else if (user.equals("cpu")) {
            symbole = 'O';
            cpuPosition.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbole;
                break;
            case 2:
                gameBoard[0][2] = symbole;
                //i9oul l 9ayel aalech mich 01 
                //well khater tt simplement l '|' hedhi deja tetsama caractere donc tekhou blasa
                //wel blassa mteeha hiya 0 1  sooo blasset l case eli baad'ha base 0 2 
                break;
            case 3:
                gameBoard[0][4] = symbole;
                break;
            case 4:
                gameBoard[2][0] = symbole;
                break;
            case 5:
                gameBoard[2][2] = symbole;
                break;
            case 6:
                gameBoard[2][4] = symbole;
                break;
            case 7:
                gameBoard[4][0] = symbole;
                break;
            case 8:
                gameBoard[4][2] = symbole;
                break;
            case 9:
                gameBoard[4][4] = symbole;
                break;
            default:
                break;

        }

    }

    public static String checkWinner() {
        //Rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        //behy tawa ena chaamalt ?
        //7atit les cas mtaa l reb7 lkol f lists
        //mara f rows marra f colomns w mara criss cross

        //Cols
        List rightCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List leftCol = Arrays.asList(3, 6, 9);

        //Cris Cross
        List cris = Arrays.asList(1, 5, 9);
        List cros = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();

        //7atit les list mtaa l reb7 f List okhra pour facilité l boucle mteei
        //wiwali tt simplement ken playerPositions mteeou lkol techbah  l ay wahda m liste mtaa l reb7 yetssama howa l reba7
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(rightCol);
        winning.add(midCol);
        winning.add(leftCol);
        winning.add(cris);
        winning.add(cros);
        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Congratulations You Won ! ";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU Wins Sorry :( ";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {

                //ken taabew les cases lkol manghir 7atta reba7 f 7keya
                //tetsamma tie 
                return "Tie !!";
            }
        }

        return "";
    }

}
