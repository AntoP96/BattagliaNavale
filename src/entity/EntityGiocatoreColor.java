package entity;
import java.awt.Color;
public class EntityGiocatoreColor extends EntityGiocatore{
	private Color color;
	
	public EntityGiocatoreColor(String nome,int selection) {
		super(nome);
		switch(selection) {
		case 1: 
			color=Color.red;
			break;
		case 2: 
			color=Color.blue;
			break;
		case 3:
			color=Color.black;
			break;
		case 4:
			color=Color.green;
			break;
		case 5:
			color=Color.pink;
			break;
		case 6:
			color=Color.orange;
			break;
		case 7:
			color=Color.darkGray;
			break;
		case 8:
			color=Color.yellow;
			break;
		case 9:
			color=Color.lightGray;
			break;
		case 10:
			color=Color.magenta;
			break;
			}
	}
	public Color getColor() {
		return this.color;
	}
}
