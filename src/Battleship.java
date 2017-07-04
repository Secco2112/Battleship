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
        
        if(DEBUG){
            game.printCompletedBoard(0);
            game.printCompletedBoard(1);
        }
        
        while(true){
           game.menu();
            
           for(int i=0;i<2;i++){
               if(i==0) game.print("ln", "");
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
               
               if(hit){
                   if(player.gameTime(time)==0 && player.checksCorrects1[index.get(0)][index.get(1)]==0){
                       player.checksCorrects1[index.get(0)][index.get(1)]=1;
                       player.hits[player.gameTime(time)]++;
                   } else if(player.gameTime(time)==1 && player.checksCorrects2[index.get(0)][index.get(1)]==0) {
                       player.checksCorrects2[index.get(0)][index.get(1)]=1;
                       player.hits[player.gameTime(time)]++;
                   }
                   
                   if(player.won(time)){
                       System.out.printf("\nParabéns, %s! Você venceu o jogo!\n", name);
                       break;
                   }
               }
               
               game.separator();
               time++;
           }
        }
        
    }
    
}
