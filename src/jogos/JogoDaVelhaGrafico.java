package jogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Simula o jogo da velha. 
 * @author isaiasSantana 
 * @date 21/05/2013 20:58
 */
public class JogoDaVelhaGrafico extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Jogador jogadores = new Jogador("X","O");	// Contém os jogadores.
	private TabuleiroGrafico tabuleiro = new TabuleiroGrafico();	// contém um tabuleiro.
	private ImageIcon fotoO = new ImageIcon((getClass().getResource("fotosVelha/0.png")));  //Imagem que representa o jogador O.
	private ImageIcon fotoX = new ImageIcon((getClass().getResource("fotosVelha/x.png")));  //Imagem que representa o jogador X. 
	private static int jogadas = 0; //Conta o número de jogadas durante o jogo.
	private JButton novoJogo = new JButton("Novo Jogo"); //Botão que inicia um novo jogo.
	private JButton sair = new JButton("Sair");//Sai do jogo.
	private JPanel painel = new JPanel();// Painel que contém os botões novoJogo e sair.
	private JPanel painel2 = new JPanel();
	private JLabel titulo = new JLabel("Jogo Da Velha");
	private JLabel titulo2 = new JLabel("   Divirta-se! ou tente...");
	//private static boolean usuarioJogou = false;
	
	/*Construtor*/
	public JogoDaVelhaGrafico()
	{
		super("Jogo Da Velha");
		
		//A classe é responsavel pelo evento do tabuleiro		
		for(int i = 0; i < tabuleiro.tabuleiro().length; i++)
		{
			tabuleiro.tabuleiro()[i].addActionListener(this);
		}
		
		Container c = getContentPane(); //Container principal.
		
		c.setLayout(new BorderLayout());
		c.add(tabuleiro.painel,BorderLayout.CENTER); //Adiciona o tabuleiro do jogo no centro.
		
		//Classe anônima para tratar o evento do botão novoJogo.
		novoJogo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				resetarTabuleiro();
				jogadas = 0;
				jogadores = null;//tira a referência do jogadores que são coletados pelo coletor de lixo.
				jogadores = new Jogador("X","O");
			}
		});
		novoJogo.setFont(new Font("Comic Sans Ms",Font.ROMAN_BASELINE,18));
		novoJogo.setBackground(Color.CYAN);
		

		//Classe anônima para tratar o evento do botão sair.
		sair.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		sair.setFont(new Font("Comic Sans Ms",Font.ROMAN_BASELINE,18));
		sair.setBackground(Color.orange);
		
		//Adicionando no painel os botões sair e novoJogo.
		painel.setLayout(new GridLayout(1,2,3,5));
		painel.add(novoJogo);
		painel.add(sair);
		
		painel2.setLayout(new GridLayout(2,1,2,2));
		painel2.setBackground(Color.cyan);
		painel2.add(titulo);
		titulo2.setFont(new Font("Comic Sans Ms",Font.ROMAN_BASELINE,18));
		painel2.add(titulo2);
		
		/* *
		 * adiciona no conteiner o painel ao sul.
		 * 
		 * */
		
		c.add(painel,BorderLayout.SOUTH); //Adiciona o painel ao sul.
	
		titulo.setFont(new Font("Comic Sans Ms",Font.ROMAN_BASELINE,18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
				
		c.add(painel2,BorderLayout.NORTH); //Adiciona o painel2 ao norte.
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Operação padrão de fechamento de janela.
		setSize(400,400); //Largura do frame.
		setResizable(false);//A janela não pode ser maximizada.
		setVisible(true); //Janela visivel.
		setLocationRelativeTo(null);// Coloca a janela no centro da tela.
	}
	
	/**
	 * Verifica se o icone passado como parâmetro são iguais aos icones que estão no tabuleiro.
	 * @param icone do jogo da velha
	 * @return o icone vencendor do jogo caso contrário retorna null.
	 * @author isaiasSantanaDosSantos
	 */
	public Icon verificaVencedor(Icon icone){
		
		if(tabuleiro.tabuleiro()[0].getIcon() == icone && tabuleiro.tabuleiro()[1].getIcon() == icone && tabuleiro.tabuleiro()[2].getIcon() == icone||
		   tabuleiro.tabuleiro()[3].getIcon() == icone && tabuleiro.tabuleiro()[4].getIcon() == icone && tabuleiro.tabuleiro()[5].getIcon() == icone||
		   tabuleiro.tabuleiro()[6].getIcon() == icone && tabuleiro.tabuleiro()[7].getIcon() == icone && tabuleiro.tabuleiro()[8].getIcon() == icone||
		   tabuleiro.tabuleiro()[0].getIcon() == icone && tabuleiro.tabuleiro()[3].getIcon() == icone && tabuleiro.tabuleiro()[6].getIcon() == icone||
		   tabuleiro.tabuleiro()[1].getIcon() == icone && tabuleiro.tabuleiro()[4].getIcon() == icone && tabuleiro.tabuleiro()[7].getIcon() == icone||
		   tabuleiro.tabuleiro()[2].getIcon() == icone && tabuleiro.tabuleiro()[5].getIcon() == icone && tabuleiro.tabuleiro()[8].getIcon() == icone||
		   tabuleiro.tabuleiro()[0].getIcon() == icone && tabuleiro.tabuleiro()[4].getIcon() == icone && tabuleiro.tabuleiro()[8].getIcon() == icone||
		   tabuleiro.tabuleiro()[2].getIcon() == icone && tabuleiro.tabuleiro()[4].getIcon() == icone && tabuleiro.tabuleiro()[6].getIcon() == icone){
			return icone;//Alguém ganhou!
		}
		return null;	// ouve empate ou ainda não acabou o jogo
	}			
	
	
	/**
	 * Trata o evento do tabuleiro;
	 */
	public void actionPerformed(ActionEvent e)
	{
		um:
		for(int i = 0; i < tabuleiro.tabuleiro().length; i++)
		{
			if(e.getSource() == tabuleiro.tabuleiro()[i])
			{//procura envento.
				if(!ouveVencedor())
				{//ninguém ganhou.
					if(tabuleiro.tabuleiro()[i].getIcon() == null)
					{
						if( jogadores.jogadorAtual() == "X")
						{
							tabuleiro.tabuleiro()[i].setIcon(fotoX);
							jogadores.trocarJogador("X","O");
						}
						else
						{
							tabuleiro.tabuleiro()[i].setIcon(fotoO);
							jogadores.trocarJogador("X","O");						
						}
					jogadas++;
					}
					else mensagem(); // posição ocupada
					if(ouveVencedor())
					{
						break um;
					}
					if((jogadas == 9) && (!ouveVencedor()))
					{	
						JOptionPane.showMessageDialog(this,"\tHEHE,DEU VELHA!","Empate!",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}  
		}
	}
	
//	public void cPUJoga(){
//		 if(usuarioJogou && !ouveVencedor()){
//			 if(tabuleiro.tabuleiro()[0] != null){
//				 tabuleiro.tabuleiro()[8].setIcon(fotoO);
//			 }else if(tabuleiro.tabuleiro()[2] != null ){
//				 tabuleiro.tabuleiro()[6].setIcon(fotoO);
//			 } 
//		 }
//		 usuarioJogou = false;
//	}
	
	/**Método que retorna o vencedor*/
	private boolean ouveVencedor()
	{
		if(jogadas >= 5)
		{
		  if(verificaVencedor(fotoX) == fotoX)
		  {
				JOptionPane.showMessageDialog(this,"Jogador X ganhou!");
			return true;
		  }
		   else
			  if(verificaVencedor(fotoO) == fotoO)
			  {
				  JOptionPane.showMessageDialog(this,"Jogador O ganhou!");
				return true;
			  }
		}
		return false;
	}
	
//	private boolean acabou(){
//		return (ouveVencedor() || jogadas == 9);
//	}
	
	/**
	 * Exibe uma mensagem apenas só para mostrar que a posição está ocupada
	 */
	private void mensagem(){
		JOptionPane.showMessageDialog(this,"Posição ocupada!","Aviso",JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Retira todos os Icons do tabuleiro sem desreferência-lo
	 */
	private void resetarTabuleiro()
	{	
		for(int i = 0; i < tabuleiro.tabuleiro().length;i++)
		{
			tabuleiro.tabuleiro()[i].setIcon( null);
		}
	}
}