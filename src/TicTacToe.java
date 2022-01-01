import java.lang.reflect.Array;
import java.util.*;

public class TicTacToe {
    public static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    public static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main (String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter you placement (1-9)");
            int place = scan.nextInt();
            while (playerPosition.contains(place) || cpuPosition.contains(place)) {
                System.out.println("Position taken! Enter a correct position (1-9)");
                place = scan.nextInt();
            }

            System.out.println(place);
            placePiece(gameBoard, place, "user");

            String res = checkWinner();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }

            Random rand = new Random();
            place = rand.nextInt(9) + 1;
            while (playerPosition.contains(place) || cpuPosition.contains(place)) {
                place = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, place, "cpu");

            printGameBoard(gameBoard);

            res = checkWinner();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Congratulation you won!";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU wins! Sorry...";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "CAT!";
            }
        }

        return "";
    }

    public static void placePiece(char[][] gameBoard, int place, String userName) {
        char symbol = ' ';
        if (userName.equals("user")) {
            symbol = 'X';
            playerPosition.add(place);
        } else {
            symbol = '0';
            cpuPosition.add(place);
        }

        switch (place) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char col : row) {
                System.out.print(col);
            }

            System.out.println();
        }
    }
}
