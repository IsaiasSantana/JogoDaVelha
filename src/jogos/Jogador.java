package jogos;

/**
 * Classe jogador.Define se � X ou O
 * @author isaiasSantana.
 *
 */
public class Jogador {

	private  String jogador; 
	
	//Construtor que recebe os jogadores(X,O).
	public Jogador(String jogador1,String jogador2){
		jogador = jogador1; // Primeiro jogador � X. 
	}
	
	/**
	 * Este m�todo faz a permuta entre os jogadores.
	 * @param jogador1 primeiro jogador.
	 * @param jogador2 segundo jogador.
	 */
	public  void trocarJogador(String jogador1,String jogador2){
		//Jogador X j� jogou, vez do O.
		if(jogador == jogador1) jogador = jogador2;
		else jogador = jogador1;// Novamente vez do X.
	}
	/**
	 * Apenas retorna o jogador atual.
	 * @return retorna o jogador atual.
	 */
	public String jogadorAtual(){
		return jogador;
	}
}