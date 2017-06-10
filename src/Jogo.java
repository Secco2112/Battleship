import java.security.SecureRandom;

public class Jogo {
    char m[][];
    SecureRandom rand = new SecureRandom();
    
    Navio navio = new Navio();
    
    public Jogo(char campo[][]){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                campo[i][j]='~';
            }
        }
        this.m = campo;
    }
    
    void iniciarJogo(){
        navio.gerarNavios(m);
    }
    
    void mostrarCampo(){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(j<6) System.out.printf("%c | ", m[i][j]);
                else System.out.printf("%c", m[i][j]);
            }
            System.out.println("");
        }
    }
}
