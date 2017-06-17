import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

public class Battleship {
    
    static boolean DEBUG = false;

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        
        char campo[][] = new char[7][7];
        
        Game game = new Game(campo);
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        
        game.startGame();
        if(DEBUG) game.printBoard();
        
        while(true){
           for(int i=0;i<2;i++){
               System.out.printf("Digite o nome do jogador %d: ", i+1);
               player.name[i] = scanner.nextLine();
           }
           game.printBoard();
           int time = 0;
           while(true){
               System.out.printf("É a vez de %s\n", player.name[player.gameTime(time++)]);
               String input = player.readAndValidate();
               if(DEBUG) System.out.println(player.getElementFromInput(input, campo));
           }
        }
        
    }
    
}
