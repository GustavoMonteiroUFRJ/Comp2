
public class myMath {
	static int[] vetorPontos = {0,1,5,11,17,25,33,41,51};
	
	
	// retorna o pontos que o jogador ganhou quando ele pegar uma bolha verde estando com N bolas agrupadas
	public static int ponto(int n){
		if(n < 3){
			return vetorPontos[0];
		}
		if ( n >= 11){
			return vetorPontos[8] + (n-10)*10;
		}
		return vetorPontos[n-2];
	}
	
	// retorna um vetor distancia entre a bolhas principal uma nova bolha que ira se agrupar
	public static Distancia vetorDistancia(Bolha a, Bolha b){
		return new Distancia(b.getPx()-a.getPx() , b.getPy()-a.getPy());
	}
	
	// retorna a distancia entre 2 bolhas
	public static double distancia (Bolha a, Bolha b){
		return Math.sqrt( Math.pow(a.getPx() - b.getPx() , 2)  +   Math.pow(a.getPy() - b.getPy() , 2) ) ;
	}
	
	// checa se duas bolas se colidiram
	public static boolean colidiu (Bolha a, Bolha b){
		boolean controle = false;
		if ( distancia(a,b) <= (a.getRaio()+b.getRaio()) ){
			controle = true;
		}
		return controle;
	}
	

}
