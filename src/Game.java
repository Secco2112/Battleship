import java.security.SecureRandom;

public class Game {
    char completeField1[][], completeField2[][];
    char field1[][], field2[][];
    
    Ship ship = new Ship();
    
    public Game(char board1[][], char board2[][]){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                board1[i][j]='~';
                board2[i][j]='~';
            }
        }
        this.completeField1 = board1;
        this.completeField2 = board2;
    }
    
    void print(String message, String... format){
        if(format.length==0) System.out.print(message);
        else if(format.equals("ln")) System.out.println(message);
        else if(format.equals("f")) System.out.printf("%s", message);
    }
    
    void startGame(){
        ship.generateAllShips(completeField1);
        ship.generateAllShips(completeField2);
        this.print("BATALHA NAVAL\n\n", "ln");
        
    }
    
    char[][] getMatrix(int time){
        return time==0? this.field1: this.field2;
    }
    
    void printBoard(int time){
        char c = 'A';
        for(int i=1;i<=7;i++){
            if(i==1) System.out.printf("   ");
            
            if(i==7) System.out.printf("%d\n", i);
            else System.out.printf("%d   ", i);
        }
        
        for(int i=0;i<7;i++){
            System.out.printf("%c  ", c++);
            for(int j=0;j<7;j++){
                if(j<6) System.out.printf("%c | ", time==0? completeField1[i][j]: completeField2[i][j]);
                else System.out.printf("%c", time==0? completeField1[i][j]: completeField2[i][j]);
            }
            System.out.println("");
        }
    }
}
