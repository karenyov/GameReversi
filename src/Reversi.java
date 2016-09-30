import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Reversi extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	int numDivisoes;
	Socket socket;

	Tabuleiro tabuleiro = new Tabuleiro(this);
	JMenuItem mnuConfig = new JMenuItem();
	JMenuItem mnuConfig2 = new JMenuItem();
	JMenuItem mnuConfig3 = new JMenuItem();
	JMenuItem mnuConfig4 = new JMenuItem();
	JMenuItem mnuIniciar = new JMenuItem();
	JMenuItem mnuRegras = new JMenuItem();
	JMenuItem mnuRanking = new JMenuItem();
	JMenuItem mnuDesafio = new JMenuItem();
	JLabel lblStatus = new JLabel();

	JTextArea areaTexto, areaConectados;
	JTextField mensagem;
	JButton btnEnviar, btnJogar, btnRegras, btnDesafio, btnRanking, btnSair,
			btnPlacar, btnChat,Desistir;
	PrintStream saida;
	BufferedReader entrada;

	JPanel painelTabuleiro;
	JPanel pnGeral;
	JMenuBar menu;

	private String nickname;
	@SuppressWarnings("unused")
	private String avatar;
	private String imagem;
	String nome;

	int cont = 0;

	JMenu mnuJogo;

	Reversi frame;

	public void iniciar() {
		// Reversi frame = new Reversi();
		frame.setVisible(true);
	}

	// Container tela = getContentPane();
	public String Imagem(String avatar) {
		// AVATAR
		if (avatar.equals("PADRÃO")) {
			imagem = "AvatarDefault2.png";
		}
		if (avatar.equals("WOODY")) {
			imagem = "AvatarWoody2.png";
		}
		if (avatar.equals("BUZZ LIGHTYEAR")) {
			imagem = "AvatarBuzz2.jpg";
		}
		if (avatar.equals("JESSIE")) {
			imagem = "AvatarJessie2.png";
		}
		if (avatar.equals("REX")) {
			imagem = "AvatarRex2.png";
		}
		if (avatar.equals("PORQUINHO")) {
			imagem = "AvatarPorquinho2.png";
		}
		if (avatar.equals("SR. CABEÇA DE BATATA")) {
			imagem = "AvatarBatata21.png";
		}
		if (avatar.equals("SRA. CABEÇA DE BATATA")) {
			imagem = "AvatarBatata22.png";
		}
		if (avatar.equals("SLINKY")) {
			imagem = "AvatarSlink2.png";
		}
		if (avatar.equals("BARBIE")) {
			imagem = "AvatarBarbie2.jpg";
		}
		if (avatar.equals("BALA NO ALVO")) {
			imagem = "AvatarBalaNoAlvo2.png";
		}
		if (avatar.equals("ALIENS")) {
			imagem = "AvatarAliens2.png";
		}
		if (avatar.equals("SARGENTO")) {
			imagem = "AvatarSargento2.png";
		}

		return imagem;
	}

	public Reversi(String avatar, String nickname) {
		this.nickname = nickname;
		this.avatar = avatar;
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		painelTabuleiro = new JPanel();
		menu = new JMenuBar();
		mnuJogo = new JMenu();

		this.setJMenuBar(menu);
		this.setSize(new Dimension(1100, 750));
		this.setTitle("REVERSI");

		menu.setBackground(new Color(0, 0, 255));
		mnuJogo.setText(nickname);
		mnuJogo.setIcon(new ImageIcon(Imagem(avatar)));
		mnuJogo.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));

		menu.add(mnuJogo);

		btnJogar = new JButton("Iniciar Jogo");
		btnJogar.setBounds(10, 83, 97, 33);
		btnJogar.setForeground(new Color(255, 204, 0));
		btnJogar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnJogar.setBorder(new LineBorder(Color.BLACK));
		btnJogar.setBackground(new Color(255, 0, 51));
		btnJogar.setBounds(10, 11, 97, 33);
		painelTabuleiro.add(btnJogar);
		btnJogar.addActionListener(this);

		btnRegras = new JButton("Regras");
		btnRegras.setBounds(10, 83, 97, 33);
		btnRegras.setForeground(new Color(255, 204, 0));
		btnRegras.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnRegras.setBorder(new LineBorder(Color.BLACK));
		btnRegras.setBackground(new Color(255, 0, 51));
		btnRegras.setBounds(125, 11, 97, 33);
		painelTabuleiro.add(btnRegras);
		btnRegras.addActionListener(this);

		btnDesafio = new JButton("Desafio");
		btnDesafio.setBounds(10, 83, 97, 33);
		btnDesafio.setForeground(new Color(255, 204, 0));
		btnDesafio.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnDesafio.setBorder(new LineBorder(Color.BLACK));
		btnDesafio.setBackground(new Color(255, 0, 51));
		btnDesafio.setBounds(240, 11, 97, 33);
		painelTabuleiro.add(btnDesafio);
		btnDesafio.addActionListener(this);

		btnRanking = new JButton("Ranking");
		btnRanking.setBounds(10, 83, 97, 33);
		btnRanking.setForeground(new Color(255, 204, 0));
		btnRanking.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnRanking.setBorder(new LineBorder(Color.BLACK));
		btnRanking.setBackground(new Color(255, 0, 51));
		btnRanking.setBounds(355, 11, 97, 33);
		painelTabuleiro.add(btnRanking);
		btnRanking.addActionListener(this);

		btnChat = new JButton("Chat");
		btnChat.setBounds(10, 83, 97, 33);
		btnChat.setForeground(new Color(255, 204, 0));
		btnChat.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnChat.setBorder(new LineBorder(Color.BLACK));
		btnChat.setBackground(new Color(255, 0, 51));
		btnChat.setBounds(471, 11, 97, 33);
		painelTabuleiro.add(btnChat);
		btnChat.addActionListener(this);

		btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon("sair.png"));
		btnSair.setBounds(10, 83, 97, 33);
		btnSair.setForeground(new Color(255, 204, 0));
		btnSair.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnSair.setBorder(new LineBorder(Color.BLACK));
		btnSair.setBackground(new Color(255, 0, 51));
		btnSair.setBounds(1002, 11, 29, 24);
		painelTabuleiro.add(btnSair);
		btnSair.addActionListener(this);

		lblStatus = new JLabel();
		lblStatus.setBounds(20, 580, 680, 50);
		lblStatus.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblStatus.setBorder(new LineBorder(Color.BLACK));
		painelTabuleiro.add(lblStatus);
		lblStatus.setVisible(false);
		
		Desistir = new JButton("Desistir");
		Desistir.setForeground(new Color(255, 204, 0));
		Desistir.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		Desistir.setBorder(new LineBorder(Color.BLACK));
		Desistir.setBackground(new Color(255, 0, 51));
		Desistir.setBounds(551, 502, 97, 33);
		painelTabuleiro.add(Desistir);
		Desistir.addActionListener(this);
		
		Desistir.setVisible(false);

		/*
		 * btnPlacar = new JButton("Placar"); btnPlacar.setBounds(10, 83, 97,
		 * 33); btnPlacar.setForeground(new Color(255, 204, 0));
		 * btnPlacar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		 * btnPlacar.setBorder(new LineBorder(Color.BLACK));
		 * btnPlacar.setBackground(new Color(255, 0, 51));
		 * btnPlacar.setBounds(465, 407, 97, 33);
		 * painelTabuleiro.add(btnPlacar);
		 */
		// btnPlacar.addActionListener(this);

		// btnPlacar.addActionListener(new IncluirListener());

		tabuleiro.setBounds(30, 80, 500, 500);
		painelTabuleiro.add(tabuleiro, new XYConstraints(0, 0, 410, 410));
		painelTabuleiro.setBackground(new Color(135, 206, 250));
		painelTabuleiro.setLayout(null);

		pnGeral = new JPanel(new BorderLayout());

		pnGeral.add(painelTabuleiro);

		this.getContentPane().add(pnGeral);
	}

	void mnuConfig_actionPerformed(ActionEvent e) {
		// INICIAR JOGO
		tabuleiro.setNumDivisoes(8);
		tabuleiro.initialize(nickname);
		mnuConfig.setEnabled(false);
		mnuIniciar.setEnabled(false);
		mnuConfig2.setEnabled(true);
		mnuRegras.setEnabled(true);
		mnuConfig3.setEnabled(true);
		mnuRanking.setEnabled(true);
		mnuConfig4.setEnabled(true);
		mnuDesafio.setEnabled(true);

	}

	void mnuConfig2_actionPerformed(ActionEvent e) {
		// REGRAS
		mnuIniciar.setEnabled(true);
		mnuConfig.setEnabled(true);
		mnuConfig2.setEnabled(false);
		mnuRegras.setEnabled(false);
		mnuConfig3.setEnabled(true);
		mnuRanking.setEnabled(true);
		mnuConfig4.setEnabled(true);
		mnuDesafio.setEnabled(true);

	}

	void mnuConfig3_actionPerformed(ActionEvent e) {
		// RANKING
		mnuIniciar.setEnabled(true);
		mnuConfig.setEnabled(true);
		mnuConfig2.setEnabled(true);
		mnuRegras.setEnabled(true);
		mnuConfig3.setEnabled(false);
		mnuRanking.setEnabled(false);
		mnuConfig4.setEnabled(true);
		mnuDesafio.setEnabled(true);

	}

	void mnuConfig4_actionPerformed(ActionEvent e) {
		// DESAFIO
		mnuIniciar.setEnabled(true);
		mnuConfig.setEnabled(true);
		mnuConfig2.setEnabled(true);
		mnuRegras.setEnabled(true);
		mnuConfig3.setEnabled(true);
		mnuRanking.setEnabled(true);
		mnuConfig4.setEnabled(false);
		mnuDesafio.setEnabled(false);

	}

	void mnuParar_actionPerformed(ActionEvent e) {
		mnuIniciar.setEnabled(true);
		mnuConfig.setEnabled(true);
		mnuConfig2.setEnabled(true);
		mnuRegras.setEnabled(true);
		mnuConfig3.setEnabled(true);
		mnuRanking.setEnabled(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object fonte = event.getSource();

		if ((fonte == btnEnviar)) // Envia informacao // pelo socket
		{
			// mostraStatus("String pStatus");
			String text = mensagem.getText();
			saida.println(text);
			mensagem.setText(new String("")); // recebe do servidor
		}
		if (fonte == btnJogar) {
			Desistir.setVisible(true);
			lblStatus.setVisible(true);
			tabuleiro.setNumDivisoes(8);
			tabuleiro.initialize(nickname);
		}

		if (fonte == btnRegras) {
			TelaRegras t = new TelaRegras();
			t.janela();
		}

		if (fonte == btnDesafio) {
			// Desafio d = new Desafio(nickname);
			Desafio x = new Desafio(nickname);
			//x.executa();
		}

		if (fonte == btnChat) {
			Chat cliente = new Chat(nickname, imagem);
			cliente.executa();
		}

		if (fonte == btnSair) {
			System.exit(0);
		}
		if (fonte == btnRanking) {
			TelaRanking t = new TelaRanking(nickname);
			t.executa();
			
		}
		if (fonte == Desistir) {
			int resposta;
			resposta = JOptionPane.showConfirmDialog(null,
					"Tem certeza que deseja desistir? Sua desistência ocorrerá na perda de 3 pontos.");

			if (resposta == JOptionPane.YES_OPTION) {
				RankingSocket r = new RankingSocket(nickname,0,0,0,1);
				r.executa();
				tabuleiro.setNumDivisoes(8);
				tabuleiro.initialize(nickname);
			}
			
		}
	}

	void mostraStatus(String pStatus) {
		tabuleiro.atualizaPontos();

		if (pStatus != null)
			pStatus = "  -  " + pStatus + "!";

		lblStatus.setText(" Jogadas: " + tabuleiro.jogadas
				+ "  -  Você x Computador: " + tabuleiro.pontosUsuario + " x "
				+ tabuleiro.pontosComputador + pStatus);
		this.paintAll(this.getGraphics());
	}

	void fimDeJogo() {
		ImageIcon icone;
		int vitoria,derrota,empate;
		mostraStatus("Fim de Jogo");

		String resultado = "Resultado:" + "\n   Jogador: "
				+ tabuleiro.pontosUsuario + " pontos" + "\n   Computador: "
				+ tabuleiro.pontosComputador + " pontos\n\n";

		if (tabuleiro.pontosComputador == tabuleiro.pontosUsuario) {
			resultado += "O jogo terminou empatado!";
			icone = new ImageIcon(("empate.jpg"));
			vitoria = 0;
			empate = 1;
			derrota = 0;
			RankingSocket r = new RankingSocket(nickname,vitoria,empate,derrota,0);
			r.executa();

		}

		else if (tabuleiro.pontosComputador > tabuleiro.pontosUsuario) {
			resultado += "O computador venceu!";
			icone = new ImageIcon(("gameover.jpg"));
			vitoria = 0;
			empate = 0;
			derrota = 1;
			RankingSocket r = new RankingSocket(nickname,vitoria,empate,derrota,0);
			r.executa();
		
		} else {
			resultado += "Você venceu!";
			icone = new ImageIcon(("vencedor.jpg"));
			vitoria = 1;
			empate = 0;
			derrota = 0;
			RankingSocket r = new RankingSocket(nickname,vitoria,empate,derrota,0);
			r.executa();
		}

		JOptionPane.showMessageDialog(this, resultado, "Fim do Jogo",
				JOptionPane.INFORMATION_MESSAGE, icone);

		mnuParar_actionPerformed(null);
	}

}

class Reversi_mnuConfig_actionAdapter implements java.awt.event.ActionListener {
	Reversi adaptee;

	Reversi_mnuConfig_actionAdapter(Reversi adaptee) {
		this.adaptee = adaptee;

	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuConfig_actionPerformed(e);
	}
}

class Reversi_mnuConfig2_actionAdapter implements java.awt.event.ActionListener {
	Reversi adaptee;

	Reversi_mnuConfig2_actionAdapter(Reversi adaptee) {
		this.adaptee = adaptee;

	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuConfig2_actionPerformed(e);
	}
}

class Reversi_mnuConfig3_actionAdapter implements java.awt.event.ActionListener {
	Reversi adaptee;

	Reversi_mnuConfig3_actionAdapter(Reversi adaptee) {
		this.adaptee = adaptee;

	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuConfig3_actionPerformed(e);
	}
}

class Reversi_mnuConfig4_actionAdapter implements java.awt.event.ActionListener {
	Reversi adaptee;

	Reversi_mnuConfig4_actionAdapter(Reversi adaptee) {
		this.adaptee = adaptee;

	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuConfig4_actionPerformed(e);
	}
}

@SuppressWarnings("serial")
class XYConstraints extends Component {

	public XYConstraints(int i, int j, int k, int l) {
		// TODO Auto-generated constructor stub
	}
}