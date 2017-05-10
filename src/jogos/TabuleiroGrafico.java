package jogos;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TabuleiroGrafico 
{
	private JButton tabu[] = new JButton[9];
	JPanel painel = new JPanel();
	
	public TabuleiroGrafico()
	{
		painel.setLayout(new GridLayout(3,3));
		inicializarTabuleiro();
	}
	
	private void inicializarTabuleiro()
	{
		for(int i = 0; i < 9; i++)
		{
			tabu[i] = new JButton();
			painel.add(tabu[i]);
		}
	}
	
	protected JPanel painel()
	{
		 return painel;
	}
	
	protected JButton[] tabuleiro()
	{
		return tabu;
	}
}
