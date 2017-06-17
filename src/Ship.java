import java.security.SecureRandom;

public class Ship {
    SecureRandom rand = new SecureRandom();
    
    void generateShip(int size, char board[][]){
        int row, column, temp;
        String randomChar = "HV";
        char pos = randomChar.charAt(rand.nextInt(2));
        
        if(pos=='V'){
            row = rand.nextInt(size);
            temp = row + size;
            column = rand.nextInt(7);
            
            for (int i=row;i<temp;i++)
                board[i][column] = 'X';
        } else {
            column = rand.nextInt(size);
            temp = column + size;
            row = rand.nextInt(7);
            
            for (int i=column;i<temp;i++)
                board[row][i] = 'X';
        }
    }
    
    void generateAllShips(char board[][]){
        boolean valid = false;

        while(!valid){
            clearBoard(board);
            
            this.generateShip(4, board);
            this.generateShip(3, board);
            this.generateShip(2, board);
            
            valid = validateShips(board);
        }
    }
    
    boolean validateShips(char board[][]){
        int count=0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                char el = board[i][j];
                if(el=='X') count++;
            }
        }
        return count==9;
    }
    
    void clearBoard(char board[][]){
        for(int i=0;i<7;i++)
            for(int j=0;j<7;j++)
                board[i][j]='~';
    }
}
