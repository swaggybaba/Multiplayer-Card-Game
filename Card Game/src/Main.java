import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter Number of PLayers (2,3,4)");
        int numberOfPlayer=scanner.nextInt();
        Game game=new Game(numberOfPlayer);
        game.startGame();
    }
}