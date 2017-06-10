public class BatalhaNaval {

    public static void main(String[] args) {
        char campo[][] =  new char[7][7];
        Jogo jogo = new Jogo(campo);
        
        jogo.iniciarJogo();
        jogo.mostrarCampo();
    }
    
}
