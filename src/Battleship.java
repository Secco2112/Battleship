import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

public class Battleship {
    
    static boolean DEBUG = false;

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        
        char field1[][] = new char[7][7];
        char field2[][] = new char[7][7];
        
        Game game = new Game(field1, field2);
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        
        game.startGame();
        
        if(DEBUG){
            game.printBoard(0);
            game.printBoard(1);
        }
        
        while(true){
           for(int i=0;i<2;i++){
               System.out.printf("Digite o nome do jogador %d: ", i+1);
               player.name[i] = scanner.nextLine();
           }
           int time = 0;
           game.printBoard(player.gameTime(time));
           while(true){
               System.out.printf("É a vez de %s\n", player.name[player.gameTime(time++)]);
               String input = player.readAndValidate();
               if(DEBUG) System.out.println(player.getElementFromInput(input, player.gameTime(time)==0? field1: field2));
           }
        }
        
    }
    
}
