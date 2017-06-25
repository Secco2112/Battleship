import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Battleship {
    
    static boolean DEBUG = false;

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        
        char field1[][] = new char[7][7];
        char field2[][] = new char[7][7];
        char generatedField1[][] = new char[7][7];
        char generatedField2[][] = new char[7][7];
        
        Game game = new Game(field1, field2, generatedField1, generatedField2);
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        
        game.startGame();
        
        while(true){
           for(int i=0;i<2;i++){
               System.out.printf("Digite o nome do jogador %d: ", i+1);
               player.name[i] = scanner.nextLine();
           }
           
           int time = 0;
           
           while(true){
               char[][] currentField = game.getCurrentField(time);
               char[][] currentGeneratedField = game.getCurrentGeneratedField(time);
               String name = player.getName(time);
               
               game.printBoard(player.gameTime(time));
               
               System.out.printf("É a vez de %s\n", name);
               
               System.out.printf("Jogada: ");
               String input = scanner.nextLine();
               input = player.validateInput(input);
               
               ArrayList<Integer> index = player.getIndexArray(input);
               
               if(DEBUG) System.out.println(index);
               
               boolean hit = player.hit(currentGeneratedField, index);
               
               game.updateField(hit, currentField, index, name);
               
               game.saveGame();
               
               game.separator();
               time++;
           }
        }
        
    }
    
}
