import java.awt.Color;


public class Bolha {
	
	private double px;
	private double py;
	private double raio;
	private Velocidade v;
	private Color cor;
	private boolean principal;
	private Distancia d;
	
	public Bolha(double px, double py, Velocidade v , double raio, Color cor) {
		this.setPx(px);
		this.setPy(py);
		this.setV(v);
		this.setRaio(raio);
		this.setCor(cor);
		this.setPrincipal(false);
	}
	public Bolha(double px, double py, Velocidade v , double raio, Color cor, Distancia d){
		this(px,py,v,raio,cor);
		this.setD(d);
	}
	
	public void setPy(double py) {
		this.py = py;
	}
	public void setPx(double px) {
		this.px = px;
	}
	public void setV(Velocidade v) {
		this.v = v;
	}
	public void setRaio(double raio) {
		this.raio = raio;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	public void setD(Distancia d) {
		this.d = d;
	}
	
	
	
	public double getPy() {
		return py;
	}
	public double getPx() {
		return px;
	}
	public Velocidade getV() {
		return v;
	}
	public double getRaio() {
		return raio;
	}
	public Color getCor() {
		return cor;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public Distancia getD() {
		return d;
	}
	
	
	public void mover(double escala ) {
		
		setPx( getPx()+ getV().getVx() );
		setPy(  getPy()+ getV().getVy() );
		
		if (getPx() > escala || getPx() < 0 ){
			getV().setVx( -getV().getVx() );
		}
		if (getPy() > escala || getPy() < 0 ){
			getV().setVy( -getV().getVy() );
		}
		
	}
	
	// Para o caso de existir mapas com altura diferente da largura
	public void mover(double altura, double largura ) {
		
		setPx( getPx()+ getV().getVx() );
		setPy(  getPy()+ getV().getVy() );
		
		if (getPx() > largura || getPx() < 0 ){
			getV().setVx( -getV().getVx() );
		}
		if (getPy() > altura || getPy() < 0 ){
			getV().setVy( -getV().getVy() );
		}
		
	}
	
	// Para o caso de existir mapas com todas as dimensões diferentes
	public void mover(double chao, double teto, double esquerda, double direita ) {
		
		if (getPx() > direita || getPx() < esquerda  ){
			getV().setVx( -getV().getVx() );
		}
		if (getPy() > teto || getPy() < chao ){
			getV().setVy( -getV().getVy() );
		}
		
		setPx( getPx()+ getV().getVx() );
		setPy(  getPy()+ getV().getVy() );
		
	}
		
	public void print(){
		StdDraw.setPenColor(getCor());
		StdDraw.filledCircle(getPx(), getPy(), getRaio());
	}
		
}
