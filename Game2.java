import java.util.ArrayList;



public class Game2 {
	
	Bolha principal;
	ArrayList<Bolha> bem;
	ArrayList<Bolha> mal;
	ArrayList<Bolha> verdes;
	
	
	double raio = 0.02;	
	int escalaReal = 1;
	int escalaInvisivel = 2;
	int pontos = 0;
	
	double controleBem = 0.001;
	double controleMal = 0.001;
	double controleVerdes = 0.001;
	
	int[][] nivelDificuldade = {{40,30,15},{30,30,10}, {30,40,10} };
	
	// Construtor; 
	public Game2(){
		this.bem = new ArrayList<Bolha>();
		this.mal = new ArrayList<Bolha>();
		this.verdes = new ArrayList<Bolha>();
		this.principal = new Bolha(0,0,new Velocidade(0,0),raio,StdDraw.BLUE);
	}
	
	// inicializa as variáveis;
	public void setGame(int nivel){
		
		int QuantidadeInicialBem 	= nivelDificuldade[nivel][0] ;
		int QuantidadeInicialMal 	= nivelDificuldade[nivel][1] ;
		int QuantidadeInicialVerdes = nivelDificuldade[nivel][2] ;
		
		for (int i = 0; i < QuantidadeInicialBem; i++) {
			bem.add(new Bolha(escalaInvisivel*Math.random(),escalaInvisivel*Math.random(),new Velocidade(Math.random()*controleBem,Math.random()*controleBem),raio , StdDraw.CYAN));
		}
	
		for (int i = 0; i < QuantidadeInicialMal; i++) {		
			mal.add(new Bolha(escalaInvisivel*Math.random(),escalaInvisivel*Math.random(),new Velocidade(Math.random()*controleMal,Math.random()*controleMal),raio , StdDraw.RED));
		}
		
		for (int i = 0; i < QuantidadeInicialVerdes; i++) {		
			verdes.add(new Bolha(escalaInvisivel*Math.random(),escalaInvisivel*Math.random(),new Velocidade(Math.random()*controleVerdes,Math.random()*controleVerdes),raio , StdDraw.GREEN));
		}
	}
	
	//  Agrupa uma Bolha do bem ao grupo principal;
	public void agrupa(Bolha a){
		a.setD( myMath.vetorDistancia(principal, a) );
		a.setPrincipal(true);
	}
	
	// Some com as bolas acumuladas quando é encontrado uma bola verde, e já computa os pontos!; 
	public void estouraAsBolas(){
		int contador = 0;
		for (int i = 0; i < bem.size(); i++) {
			if(bem.get(i).isPrincipal()){
				bem.remove(i);
				contador++;
				i--;
			}
		}
		pontos += myMath.ponto(contador); 
	}
	
	//... ;
	public void atualizaPosicao(){
		
		double auxX = StdDraw.mouseX();
		double auxY = StdDraw.mouseY();
		
		principal.setPx(auxX);
		principal.setPy(auxY);
		
		for (int i = 0; i < bem.size(); i++) {
			
			if(bem.get(i).isPrincipal()){
				bem.get(i).setPx( bem.get(i).getD().getDx() + auxX );
				bem.get(i).setPy( bem.get(i).getD().getDy() + auxY );
			}
			else {
				bem.get(i).mover(escalaInvisivel);
			}
		}
		for (int i = 0; i < mal.size(); i++) {
			mal.get(i).mover(escalaInvisivel);
		}
		for (int i = 0; i < verdes.size(); i++) {
			verdes.get(i).mover(escalaInvisivel);
		}
		StdDraw.show(1);	
	}
	
	// ... ;
	public void atualizaTela(){
		
		StdDraw.clear(StdDraw.WHITE);
		principal.print();
		for (int i = 0; i < bem.size(); i++) {
			bem.get(i).print();
		}
		for (int i = 0; i < mal.size(); i++) {
			mal.get(i).print();
		}
		for (int i = 0; i < verdes.size(); i++) {
			verdes.get(i).print();
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(escalaInvisivel/2 + 0.35, escalaInvisivel/2 + 0.47, "PONTOS = "+ pontos);
				
		StdDraw.show(1);	
	}
	
	// Verifica se o jogo acabou;
	public boolean isOver(){
		for (int i = 0; i < mal.size(); i++) {
			if ( myMath.colidiu(principal, mal.get(i))){
				return true;
			}
		}
		
		for (int i = 0; i < bem.size(); i++) {
			if ( bem.get(i).isPrincipal()){
				for (int j = 0; j < mal.size(); j++) {
					if ( myMath.colidiu(bem.get(i), mal.get(j))){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// de tempos em tempos ele adiciona mais bolhas do bem, do mal, e verdes; 
	public void relimentando(long tempo){
		if( tempo % 500 == 0 ){	
			bem.add(new Bolha(escalaInvisivel*Math.random(),escalaInvisivel*Math.random(),new Velocidade(Math.random()*controleBem,Math.random()*controleBem),raio , StdDraw.CYAN));
		}
		if( tempo % 100000 == 0 ){
			mal.add(new Bolha(escalaInvisivel*Math.random(),escalaInvisivel*Math.random(),new Velocidade(Math.random()*controleMal,Math.random()*controleMal),raio , StdDraw.RED));	
		}
		if (tempo % 10000 == 0 ) {
			verdes.add(new Bolha(escalaInvisivel*Math.random(),escalaInvisivel*Math.random(),new Velocidade(Math.random()*controleVerdes,Math.random()*controleVerdes),raio , StdDraw.GREEN));			
		}
	
	}
	
	
	// Roda o jogo
	public void play(){
		
		StdDraw.setScale(0.5 , escalaReal + 0.5);
		
		long tempo = 0;
		while(!isOver()){
			
			atualizaPosicao();
			atualizaTela();
			
			
			// agrupa todas as bolas novas!
			for (int i = 0; i < bem.size(); i++) {
				if(!bem.get(i).isPrincipal()){
					if (myMath.colidiu(bem.get(i),principal)){
						agrupa(bem.get(i));
					}
					else{
						for (int j = 0; j < bem.size(); j++) {
							if (bem.get(j).isPrincipal() && myMath.colidiu(bem.get(j), bem.get(i))){
								agrupa(bem.get(i));
								break;
							}
						}
					}
				}
			}
			
			// checa se a principal colidiu com alguma verde
			for (int j = 0; j < verdes.size(); j++) {
				if (myMath.colidiu(verdes.get(j), principal)){
					estouraAsBolas();
					verdes.remove(j);
				}
			}
			
			
			// checa as bolas do bem colidiram com as verdes
			for (int i = 0; i < bem.size(); i++) {
				
				if(bem.get(i).isPrincipal()){
					for (int j = 0; j < verdes.size(); j++) {
						if (myMath.colidiu(verdes.get(j), bem.get(i))){
							estouraAsBolas();
							verdes.remove(j);
							i  = bem.size();
							break;
						}
					}
				}	
			}
			
			
			tempo++;
			relimentando(tempo);
			
		}
		
		StdDraw.text(escalaInvisivel/2, escalaInvisivel/2, "GAME OVER");
		StdDraw.show(30);
			
	}
	
		
}
