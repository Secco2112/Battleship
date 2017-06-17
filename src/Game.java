import java.security.SecureRandom;

public class Game {
    char check[][];
    char field[][];
    SecureRandom rand = new SecureRandom();
    
    Ship ship = new Ship();
    
    public Game(char board[][]){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                board[i][j]='~';
            }
        }
        this.check = board;
    }
    
    void startGame(){
        ship.generateAllShips(check);
    }
    
    void printBoard(){
        char c = 'A';
        for(int i=1;i<=7;i++){
            if(i==1) System.out.printf("   ");
            
            if(i==7) System.out.printf("%d\n", i);
            else System.out.printf("%d   ", i);
        }
        
        for(int i=0;i<7;i++){
            System.out.printf("%c  ", c++);
            for(int j=0;j<7;j++){
                if(j<6) System.out.printf("%c | ", check[i][j]);
                else System.out.printf("%c", check[i][j]);
            }
            System.out.println("");
        }
    }
}
