import java.security.SecureRandom;

public class Navio {
    SecureRandom rand = new SecureRandom();
    
    void geraNavio(int size, char campo[][]){
        int linha, coluna, aux;
        String randomChar = "HV";
        char pos = randomChar.charAt(rand.nextInt(2));
        
        if(pos=='V'){
            linha = rand.nextInt(size);
            aux = linha + size;
            coluna = rand.nextInt(7);
            
            for (int i=linha;i<aux;i++)
                campo[i][coluna] = 'X';
        } else {
            coluna = rand.nextInt(size);
            aux = coluna + size;
            linha = rand.nextInt(7);
            
            for (int i=coluna;i<aux;i++)
                campo[linha][i] = 'X';
        }
    }
    
    void gerarNavios(char campo[][]){
        boolean valida = false;

        while(!valida){
            limpaCampo(campo);
            
            this.geraNavio(4, campo);
            this.geraNavio(3, campo);
            this.geraNavio(2, campo);
            
            valida = validaNavios(campo);
        }
    }
    
    boolean validaNavios(char campo[][]){
        int cont=0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                char el = campo[i][j];
                if(el=='X') cont++;
            }
        }
        return cont==9;
    }
    
    void limpaCampo(char campo[][]){
        for(int i=0;i<7;i++)
            for(int j=0;j<7;j++)
                campo[i][j]='~';
    }
}
