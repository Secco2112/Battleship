import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

public class Game {
    char completeField1[][], completeField2[][];
    char field1[][], field2[][];
    File dataFile =  new File("");
    static boolean AUTOSAVE = false;
    
    Ship ship = new Ship();
    Player player = new Player();
    Scanner sc = new Scanner(System.in);
    
    public Game(char board1[][], char board2[][], char complete1[][], char complete2[][]){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                board1[i][j]='~';
                board2[i][j]='~';
            }
        }
        this.field1 = board1;
        this.field2 = board2;
        this.completeField1 = complete1;
        this.completeField2 = complete2;
        
        if(AUTOSAVE){
            try{
                String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
                dataFile = new File(currentPath + "/dist/save.txt");
                dataFile.createNewFile(); 
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public void print(String format, String message){
        if(format.equals("")) System.out.print(message);
        else if(format.equals("ln")) System.out.println(message);
        else if(format.equals("f")) System.out.printf("%s", message);
    }
    
    public void startGame(){
        ship.generateAllShips(completeField1);
        ship.generateAllShips(completeField2);
        
    }
    
    public void menu(){
        print("ln", "BATALHA NAVAL!\n");
        print("ln", "1. Novo jogo");
        if(dataFile.exists() && !dataFile.isDirectory()){
            print("ln", "2. Carregar jogo");
            print("ln", "3. Regras");
            print("ln", "4. Sair");
            
            print("", "\nConsole: ");
            int option = sc.nextInt();

            while(option<1 || option>4){
                print("ln", "Escolha uma opção entre 1 e 4.");
                print("", "\nConsole: ");
                option = sc.nextInt();
            }
            
            switch (option){
                case 2:
                    AUTOSAVE = true;
                    menu();
                case 3:
                    getRules();
                    menu();
                case 4:
                    System.exit(0);
                default:
                    break;
            }
        } else {
            print("ln", "2. Ativar autosalvamento");
            print("ln", "3. Regras");
            print("ln", "4. Sair");
            
            print("", "\nConsole: ");
            int option = sc.nextInt();

            while(option<1 || option>4){
                print("ln", "Escolha uma opção entre 1 e 4.");
                print("", "\nConsole: ");
                option = sc.nextInt();
            }
            switch (option){
                case 2:
                    print("ln", "Jogo carregado com sucesso.");
                    menu();
                case 3:
                    getRules();
                    menu();
                case 4:
                    System.exit(0);
                default:
                    break;
            }
        }
    }
    
    public void getRules(){
        this.print("ln", "\n-------------------------------- REGRAS ---------------------------------------\n");
        this.print("ln", "- Jogadores: 2;");
        this.print("ln", "- Tabuleiros - 2 tabuleiros 7x7;");
        this.print("ln", "- Navios - Cada jogador possui 3 navios:");
        this.print("ln", "    - 4, 3 e 2 posiçoes;");
        this.print("ln", "- Objetivo - Ser o último jogador a possuir algum navio não destruído.");
        this.print("", "\nBom jogo!");
        this.separator();
    }
    
    public char[][] getMatrix(int time){
        return time==0? this.field1: this.field2;
    }
    
    public void separator(){
        System.out.println("\n");
        for(int i=0;i<80;i++){
            System.out.print('-');
        }
        System.out.println("\n");
    }
    
    public char[][] getCurrentField(int time){
        return player.gameTime(time)==0? this.field1: this.field2;
    }
    
    public char[][] getCurrentGeneratedField(int time){
        return player.gameTime(time)==0? this.completeField1: this.completeField2;
    }
    
    public void updateField(boolean hit, char field[][], ArrayList<Integer> index, String name){
        if(hit){
            field[index.get(0)][index.get(1)] = 'X';
            System.out.printf("%s acertou um navio!", name);
        } else {
            field[index.get(0)][index.get(1)] = '*';
            System.out.printf("Que pena! %s acertou apenas água.", name);
        }
    }
    
    public void writeField(char field[][], BufferedWriter bw) throws IOException{
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                try{
                    bw.write(field[i][j] + " ");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        bw.newLine();
    }
    
    public void saveGame() throws FileNotFoundException, IOException{
        Scanner fileReader = new Scanner(dataFile);
        FileWriter writer = new FileWriter(dataFile.getAbsoluteFile());
        BufferedWriter bufWriter = new BufferedWriter(writer);

        this.writeField(field1, bufWriter);
        this.writeField(field2, bufWriter);
        this.writeField(completeField1, bufWriter);
        this.writeField(completeField2, bufWriter);
        bufWriter.close();
    }
    
    public void printBoard(int time){
        char c = 'A';
        for(int i=1;i<=7;i++){
            if(i==1) System.out.printf("   ");
            
            if(i==7) System.out.printf("%d\n", i);
            else System.out.printf("%d   ", i);
        }
        
        for(int i=0;i<7;i++){
            System.out.printf("%c  ", c++);
            for(int j=0;j<7;j++){
                if(j<6) System.out.printf("%c | ", time==0? field1[i][j]: field2[i][j]);
                else System.out.printf("%c", time==0? field1[i][j]: field2[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void printCompletedBoard(int time){
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
