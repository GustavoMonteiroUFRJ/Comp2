
public class Game {
	
	Bolha b;
	Bolha[] boas ;
	Bolha[] verdes ;
	Bolha[] malvadas ;
	Distancia[] d ;		
	
	int n = 30;
	double raio = 0.06;
	int ponto = 0;
	
	public void setGame(){
		
		b = new Bolha(0,0,new Velocidade(0,0),raio, StdDraw.RED) ;
		boas = new Bolha[n];
		verdes = new Bolha[(int) n/5];
		malvadas = new Bolha[3*n];
		d = new Distancia[n];
		
		//BOAS se acomulam
		double controle = 0.01;
		for (int i = 0; i < boas.length; i++) {
			boas[i] = new Bolha(2*Math.random(),2*Math.random(),new Velocidade(Math.random()*controle,Math.random()*controle),raio , StdDraw.PINK) ;	
		}
		
		// VERDES some com as acomuladas 
		controle = 0.01;
		for (int i = 0; i < verdes.length; i++) {
			verdes[i] = new Bolha(2*Math.random(),2*Math.random(),new Velocidade(Math.random()*controle,Math.random()*controle),raio , StdDraw.GREEN) ;	
		}
		
		// MALVADAS termina o jogo
		controle = 0.01;
		for (int i = 0; i < malvadas.length; i++) {
			verdes[i] = new Bolha(2*Math.random(),2*Math.random(),new Velocidade(Math.random()*controle,Math.random()*controle),raio , StdDraw.GREEN) ;	
		}
	
	}
	
	
	
	public void play1() {
		
		double auxX;
		double auxY;
		

//		StdDraw.setScale(0.5, 1.5);
		StdDraw.setScale(0, 2);
		
		while(true){

			auxX = StdDraw.mouseX();
			auxY = StdDraw.mouseY();
			
			for (int i = 0; i < boas.length; i++) {
				
				if (!boas[i].isPrincipal()){
					if( myMath.colidiu(b,boas[i]) ){
						boas[i].setV(b.getV());
						boas[i].setPrincipal(true);
						d[i] = myMath.vetorDistancia(b,boas[i]);
					}
					else{
						for (int j = 0; j < d.length; j++) {
							
							if( j!=i && boas[j].isPrincipal() && myMath.colidiu(boas[j],boas[i]) ){
								boas[i].setV(b.getV());
								boas[i].setPrincipal(true);
								d[i] = myMath.vetorDistancia(b,boas[i]);
								break;
							}				
						}
					}
				}
				
				if(boas[i].isPrincipal()){
					boas[i].setPx( d[i].getDx() + auxX );
					boas[i].setPy( d[i].getDy() + auxY );
				}
				else{
					boas[i].mover(2);
				}
				boas[i].print();
			}
			b.setPx(auxX);
			b.setPy(auxY);
			b.print();
			StdDraw.show(1);
			StdDraw.clear(StdDraw.WHITE);
		}
	}

}
