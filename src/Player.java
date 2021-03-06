import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    String name[] = new String[2];
    int hits[] = new int[2];
    int checksCorrects1[][] = new int[7][7];
    int checksCorrects2[][] = new int[7][7];
    static int bomb1 = 1, bomb2 = 1;
    
    Scanner scanner = new Scanner(System.in);
    
    public String validateInput(String input){
        String output = "";

        char row = input.charAt(0);
        char column = input.charAt(1);

        while((row<'A' || row>'G') || (column<'1' || column>'7')){
            System.out.println("Posição inválida. Realize a leitura novamente.");
            System.out.print("Jogada: ");
            input = scanner.nextLine();
            row = input.charAt(0);
            column = input.charAt(1);
        }
        row = input.charAt(0);
        output+=row;

        column = input.charAt(1);
        output+=column;

        return output;
    }
    
    public int gameTime(int time){
        return time%2;
    }
    
    public char getElementFromInput(String input, char board[][]){
        char row = input.charAt(0);
        int column = Integer.valueOf(input.charAt(1)) - 48;
        
        Map<Character, Integer> rowChar2Int = new HashMap<Character, Integer>();
        
        char j = 'A';
        for(int i=0; i<7; i++){
            rowChar2Int.put(j++, i);
        }
        
        return board[rowChar2Int.get(row)][column-1];
    }
    
    public ArrayList getIndexArray(String input){
        ArrayList<Integer> index = new ArrayList<Integer>();
        
        char row = input.charAt(0);
        int column = Integer.valueOf(input.charAt(1)) - 48;
        
        Map<Character, Integer> rowChar2Int = new HashMap<Character, Integer>();
        
        char j = 'A';
        for(int i=0; i<7; i++){
            rowChar2Int.put(j++, i);
        }
        
        index.add(rowChar2Int.get(row));
        index.add(column-1);
        
        return index;
    }
    
    public boolean hit(char board[][], ArrayList<Integer> index){
        return board[index.get(0)][index.get(1)] == 'X';
    }
    
    public String getName(int time){
        return this.name[this.gameTime(time)];
    }
    
    public boolean won(int time){
        return this.hits[this.gameTime(time)]==9;
    }
    
    public int getBomb(int time){
        return this.gameTime(time)==0? bomb1: bomb2;
    }
    
    public void clearBomb(int time){
        if(this.gameTime(time)==0){
            bomb1--;
        } else {
            bomb2--;
        }
    }
    
    public boolean played(int time, ArrayList<Integer> index){
        if(this.gameTime(time)==0){
            return this.checksCorrects1[index.get(0)][index.get(1)]==1;
        } else {
            return this.checksCorrects2[index.get(0)][index.get(1)]==1;
        }
    }
    
    public void printCheck(int time){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                System.out.printf("%d ", this.gameTime(time)==0? this.checksCorrects1[i][j]: this.checksCorrects2[i][j]);
            }
        }
        System.out.println("");
    }
}