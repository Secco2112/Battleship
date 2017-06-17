import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    String name[] = new String[2];
    Scanner scanner = new Scanner(System.in);
    
    public String readAndValidate(){
        System.out.printf("Digite uma letra seguido por um dígito para identificar a linha e a coluna: ");
        
        String input = scanner.nextLine();
        String output = "";
        
        char row = input.charAt(0);
        char column = input.charAt(1);
        
        while(row<'A' || row>'G'){
            System.out.println("Caracter inválido para a linha. Realize a leitura novamente.");
            input = scanner.nextLine();
            row = input.charAt(0);
        }
        row = input.charAt(0);
        output+=row;
        
        while(column<'1' || column>'7'){
            System.out.println("Valor inválido para a coluna. Realize a leitura novamente.");
            input = scanner.nextLine();
            column = input.charAt(1);
        }
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
}
