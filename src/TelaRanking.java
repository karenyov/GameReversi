import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TelaRanking extends JFrame {

	private JPanel contentPane;

	private Socket conexao;
	private Jogador jogador;
	private Socket socket;
	private PrintStream saida;
	private BufferedReader entrada;

	private static String nickname;
	
	private static TelaRanking frame = new TelaRanking(nickname);

	JLabel lblNickname, lblPrimeiro, lblNickname1,
			lblSegundo, lblNickname2, lblTerceiro, lblNickname3, lblQuarto,
			lblNickname4, lblQuinto, lblNickname5, lblSexto, lblNickname6,
			lblSetimo, lblNickname7, Primeiro, Segundo, Terceiro, Pontos,
			lblPontos1, lblPontos2, lblPontos3, lblPontos4, lblPontos5,
			lblPontos6, lblPontos7, lblJ1, lblJ2, lblJ3, lblJ4, lbJ5, lblJ6,
			lblJ7, lblV1, lblV2, lblV3, lblV4, lblV5, lblV6, lblV7, lblE1,
			lblE2, lblE3, lblE4, lblE5, lblE6, lblE7, lblD1, lblD2, lblD3,
			lblD4, lblD5, lblD6, lblD7,label1,lblPontosJogador,lblJogosJogador,lblVitoriasJogador,lblEmpatesJogador,lblDerrotasJogador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		TelaRanking t = new TelaRanking("KINHO");
		//t.Janela();
		t.executa();
		//JOptionPane.showMessageDialog(null, "");
	}
	
	public void Janela() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//frame.setVisible(true);
					BufferedImage imagem = ImageIO.read(new File("icone.png"));
					frame.setIconImage(imagem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public TelaRanking(String nickname) {
		//setVisible(true);
		this.nickname = nickname;
		
		/*java.awt.EventQueue.invokeLater(new Runnable() { 
			public void run() { 
			frame.setUndecorated(true); //Retirar barra de título 
			frame.setResizable(false); //Não permitir redimensionamento 
			frame.setVisible(true); 
			} 
			});
*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Botão para voltar
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("voltar.png"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				// TelaAvatar t = new TelaAvatar();
				// t.janela();
				// Tela t = new Tela();
				// t.Janela();
			}
		});
		btnVoltar.setBackground(new Color(135, 206, 250));
		btnVoltar.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnVoltar.setBounds(10, 11, 46, 31);
		contentPane.add(btnVoltar);

		JLabel Controle = new JLabel("");
		Controle.setIcon(new ImageIcon("ranking.png"));
		Controle.setBounds(285, 71, 63, 41);
		contentPane.add(Controle);

		// Label Ranking
		JLabel lblRanking = new JLabel("Ranking");
		lblRanking.setForeground(new Color(0, 51, 153));
		lblRanking.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 32));
		lblRanking.setBounds(358, 54, 513, 76);
		contentPane.add(lblRanking);

		lblNickname = new JLabel("Nickname");
		lblNickname.setForeground(new Color(255, 0, 51));
		lblNickname.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		lblNickname.setBounds(64, 176, 439, 50);
		contentPane.add(lblNickname);

		lblPrimeiro = new JLabel("1\u00BA");
		lblPrimeiro.setForeground(new Color(0, 51, 153));
		lblPrimeiro.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPrimeiro.setBounds(64, 245, 73, 28);
		contentPane.add(lblPrimeiro);

		lblNickname1 = new JLabel("Nickname");
		lblNickname1.setForeground(new Color(0, 51, 153));
		lblNickname1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname1.setBounds(157, 242, 320, 34);
		contentPane.add(lblNickname1);

		lblSegundo = new JLabel("2\u00BA");
		lblSegundo.setForeground(new Color(0, 51, 153));
		lblSegundo.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblSegundo.setBounds(64, 284, 73, 28);
		contentPane.add(lblSegundo);

		lblNickname2 = new JLabel("Nickname");
		lblNickname2.setForeground(new Color(0, 51, 153));
		lblNickname2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname2.setBounds(157, 281, 320, 34);
		contentPane.add(lblNickname2);

		lblTerceiro = new JLabel("3\u00BA");
		lblTerceiro.setForeground(new Color(0, 51, 153));
		lblTerceiro.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblTerceiro.setBounds(64, 323, 73, 28);
		contentPane.add(lblTerceiro);

		lblNickname3 = new JLabel("Nickname");
		lblNickname3.setForeground(new Color(0, 51, 153));
		lblNickname3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname3.setBounds(157, 317, 320, 34);
		contentPane.add(lblNickname3);

		lblQuarto = new JLabel("4\u00BA");
		lblQuarto.setForeground(new Color(0, 51, 153));
		lblQuarto.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblQuarto.setBounds(64, 363, 73, 28);
		contentPane.add(lblQuarto);

		lblNickname4 = new JLabel("Nickname");
		lblNickname4.setForeground(new Color(0, 51, 153));
		lblNickname4.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname4.setBounds(157, 357, 320, 34);
		contentPane.add(lblNickname4);

		lblQuinto = new JLabel("5\u00BA");
		lblQuinto.setForeground(new Color(0, 51, 153));
		lblQuinto.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblQuinto.setBounds(64, 402, 73, 28);
		contentPane.add(lblQuinto);

		lblNickname5 = new JLabel("Nickname");
		lblNickname5.setForeground(new Color(0, 51, 153));
		lblNickname5.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname5.setBounds(157, 396, 320, 34);
		contentPane.add(lblNickname5);

		lblSexto = new JLabel("6\u00BA");
		lblSexto.setForeground(new Color(0, 51, 153));
		lblSexto.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblSexto.setBounds(64, 441, 73, 28);
		contentPane.add(lblSexto);

		lblNickname6 = new JLabel("Nickname");
		lblNickname6.setForeground(new Color(0, 51, 153));
		lblNickname6.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname6.setBounds(157, 438, 320, 34);
		contentPane.add(lblNickname6);

		lblSetimo = new JLabel("7\u00BA");
		lblSetimo.setForeground(new Color(0, 51, 153));
		lblSetimo.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblSetimo.setBounds(64, 480, 73, 28);
		contentPane.add(lblSetimo);

		lblNickname7 = new JLabel("Nickname");
		lblNickname7.setForeground(new Color(0, 51, 153));
		lblNickname7.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblNickname7.setBounds(157, 474, 320, 34);
		contentPane.add(lblNickname7);

		Primeiro = new JLabel("");
		Primeiro.setIcon(new ImageIcon("primeiro.gif"));
		Primeiro.setBounds(23, 245, 27, 28);
		contentPane.add(Primeiro);

		Segundo = new JLabel("");
		Segundo.setIcon(new ImageIcon("segundo.gif"));
		Segundo.setBounds(23, 281, 27, 28);
		contentPane.add(Segundo);

		Terceiro = new JLabel("");
		Terceiro.setIcon(new ImageIcon("terceiro.gif"));
		Terceiro.setBounds(23, 317, 27, 28);
		contentPane.add(Terceiro);

		Pontos = new JLabel("Pontos");
		Pontos.setForeground(new Color(0, 51, 153));
		Pontos.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		Pontos.setBounds(513, 140, 63, 34);
		contentPane.add(Pontos);

		JLabel J = new JLabel("J");
		J.setForeground(new Color(0, 51, 153));
		J.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		J.setBounds(612, 140, 40, 34);
		contentPane.add(J);

		JLabel V = new JLabel("V");
		V.setForeground(new Color(0, 51, 153));
		V.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		V.setBounds(656, 140, 40, 34);
		contentPane.add(V);

		JLabel E = new JLabel("E");
		E.setForeground(new Color(0, 51, 153));
		E.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		E.setBounds(706, 140, 40, 34);
		contentPane.add(E);

		JLabel D = new JLabel("D");
		D.setForeground(new Color(0, 51, 153));
		D.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		D.setBounds(756, 140, 40, 34);
		contentPane.add(D);

		lblPontos1 = new JLabel("0");
		lblPontos1.setForeground(new Color(0, 51, 153));
		lblPontos1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos1.setBounds(513, 239, 63, 34);
		contentPane.add(lblPontos1);

		lblPontos2 = new JLabel("0");
		lblPontos2.setForeground(new Color(0, 51, 153));
		lblPontos2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos2.setBounds(513, 278, 63, 34);
		contentPane.add(lblPontos2);

		lblPontos3 = new JLabel("0");
		lblPontos3.setForeground(new Color(0, 51, 153));
		lblPontos3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos3.setBounds(513, 317, 63, 34);
		contentPane.add(lblPontos3);

		lblPontos4 = new JLabel("0");
		lblPontos4.setForeground(new Color(0, 51, 153));
		lblPontos4.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos4.setBounds(513, 357, 63, 34);
		contentPane.add(lblPontos4);

		lblPontos5 = new JLabel("0");
		lblPontos5.setForeground(new Color(0, 51, 153));
		lblPontos5.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos5.setBounds(513, 396, 63, 34);
		contentPane.add(lblPontos5);

		lblPontos6 = new JLabel("0");
		lblPontos6.setForeground(new Color(0, 51, 153));
		lblPontos6.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos6.setBounds(513, 435, 63, 34);
		contentPane.add(lblPontos6);

		lblPontos7 = new JLabel("0");
		lblPontos7.setForeground(new Color(0, 51, 153));
		lblPontos7.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontos7.setBounds(513, 474, 63, 34);
		contentPane.add(lblPontos7);

		lblJ1 = new JLabel("0");
		lblJ1.setForeground(new Color(0, 51, 153));
		lblJ1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJ1.setBounds(612, 239, 40, 34);
		contentPane.add(lblJ1);

		lblJ2 = new JLabel("0");
		lblJ2.setForeground(new Color(0, 51, 153));
		lblJ2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJ2.setBounds(612, 275, 40, 34);
		contentPane.add(lblJ2);

		lblJ3 = new JLabel("0");
		lblJ3.setForeground(new Color(0, 51, 153));
		lblJ3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJ3.setBounds(612, 317, 40, 34);
		contentPane.add(lblJ3);

		lblJ4 = new JLabel("0");
		lblJ4.setForeground(new Color(0, 51, 153));
		lblJ4.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJ4.setBounds(612, 357, 40, 34);
		contentPane.add(lblJ4);

		lbJ5 = new JLabel("0");
		lbJ5.setForeground(new Color(0, 51, 153));
		lbJ5.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lbJ5.setBounds(612, 396, 40, 34);
		contentPane.add(lbJ5);

		lblJ6 = new JLabel("0");
		lblJ6.setForeground(new Color(0, 51, 153));
		lblJ6.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJ6.setBounds(612, 435, 40, 34);
		contentPane.add(lblJ6);

		lblJ7 = new JLabel("0");
		lblJ7.setForeground(new Color(0, 51, 153));
		lblJ7.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJ7.setBounds(612, 474, 40, 34);
		contentPane.add(lblJ7);

		lblV1 = new JLabel("0");
		lblV1.setForeground(new Color(0, 51, 153));
		lblV1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV1.setBounds(662, 239, 40, 34);
		contentPane.add(lblV1);

		lblV2 = new JLabel("0");
		lblV2.setForeground(new Color(0, 51, 153));
		lblV2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV2.setBounds(662, 275, 40, 34);
		contentPane.add(lblV2);

		lblV3 = new JLabel("0");
		lblV3.setForeground(new Color(0, 51, 153));
		lblV3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV3.setBounds(662, 315, 40, 34);
		contentPane.add(lblV3);

		lblV4 = new JLabel("0");
		lblV4.setForeground(new Color(0, 51, 153));
		lblV4.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV4.setBounds(662, 357, 40, 34);
		contentPane.add(lblV4);

		lblV5 = new JLabel("0");
		lblV5.setForeground(new Color(0, 51, 153));
		lblV5.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV5.setBounds(662, 396, 40, 34);
		contentPane.add(lblV5);

		lblV6 = new JLabel("0");
		lblV6.setForeground(new Color(0, 51, 153));
		lblV6.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV6.setBounds(662, 435, 40, 34);
		contentPane.add(lblV6);

		lblV7 = new JLabel("0");
		lblV7.setForeground(new Color(0, 51, 153));
		lblV7.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblV7.setBounds(662, 474, 40, 34);
		contentPane.add(lblV7);

		lblE1 = new JLabel("0");
		lblE1.setForeground(new Color(0, 51, 153));
		lblE1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE1.setBounds(712, 239, 40, 34);
		contentPane.add(lblE1);

		lblE2 = new JLabel("0");
		lblE2.setForeground(new Color(0, 51, 153));
		lblE2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE2.setBounds(712, 275, 40, 34);
		contentPane.add(lblE2);

		lblE3 = new JLabel("0");
		lblE3.setForeground(new Color(0, 51, 153));
		lblE3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE3.setBounds(712, 315, 40, 34);
		contentPane.add(lblE3);

		lblE4 = new JLabel("0");
		lblE4.setForeground(new Color(0, 51, 153));
		lblE4.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE4.setBounds(712, 357, 40, 34);
		contentPane.add(lblE4);

		lblE5 = new JLabel("0");
		lblE5.setForeground(new Color(0, 51, 153));
		lblE5.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE5.setBounds(712, 396, 40, 34);
		contentPane.add(lblE5);

		lblE6 = new JLabel("0");
		lblE6.setForeground(new Color(0, 51, 153));
		lblE6.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE6.setBounds(712, 438, 40, 34);
		contentPane.add(lblE6);

		lblE7 = new JLabel("0");
		lblE7.setForeground(new Color(0, 51, 153));
		lblE7.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblE7.setBounds(712, 474, 40, 34);
		contentPane.add(lblE7);

		lblD1 = new JLabel("0");
		lblD1.setForeground(new Color(0, 51, 153));
		lblD1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD1.setBounds(756, 239, 40, 34);
		contentPane.add(lblD1);

		lblD2 = new JLabel("0");
		lblD2.setForeground(new Color(0, 51, 153));
		lblD2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD2.setBounds(756, 275, 40, 34);
		contentPane.add(lblD2);

		lblD3 = new JLabel("0");
		lblD3.setForeground(new Color(0, 51, 153));
		lblD3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD3.setBounds(756, 315, 40, 34);
		contentPane.add(lblD3);

		lblD4 = new JLabel("0");
		lblD4.setForeground(new Color(0, 51, 153));
		lblD4.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD4.setBounds(756, 357, 40, 34);
		contentPane.add(lblD4);

		lblD5 = new JLabel("0");
		lblD5.setForeground(new Color(0, 51, 153));
		lblD5.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD5.setBounds(756, 396, 40, 34);
		contentPane.add(lblD5);

		lblD6 = new JLabel("0");
		lblD6.setForeground(new Color(0, 51, 153));
		lblD6.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD6.setBounds(756, 435, 40, 34);
		contentPane.add(lblD6);

		lblD7 = new JLabel("0");
		lblD7.setForeground(new Color(0, 51, 153));
		lblD7.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblD7.setBounds(756, 474, 40, 34);
		contentPane.add(lblD7);
		
	    lblPontosJogador = new JLabel("0");
		lblPontosJogador.setForeground(new Color(255, 0, 51));
		lblPontosJogador.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblPontosJogador.setBounds(513, 185, 63, 34);
		contentPane.add(lblPontosJogador);
		
		lblJogosJogador = new JLabel("0");
		lblJogosJogador.setForeground(new Color(255, 0, 51));
		lblJogosJogador.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblJogosJogador.setBounds(612, 185, 40, 34);
		contentPane.add(lblJogosJogador);
		
		lblVitoriasJogador = new JLabel("0");
		lblVitoriasJogador.setForeground(new Color(255, 0, 51));
		lblVitoriasJogador.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblVitoriasJogador.setBounds(662, 185, 40, 34);
		contentPane.add(lblVitoriasJogador);
		
		lblEmpatesJogador = new JLabel("0");
		lblEmpatesJogador.setForeground(new Color(255, 0, 51));
		lblEmpatesJogador.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblEmpatesJogador.setBounds(706, 185, 40, 34);
		contentPane.add(lblEmpatesJogador);
		
		lblDerrotasJogador = new JLabel("0");
		lblDerrotasJogador.setForeground(new Color(255, 0, 51));
		lblDerrotasJogador.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 12));
		lblDerrotasJogador.setBounds(756, 177, 40, 50);
		contentPane.add(lblDerrotasJogador);

	}
	void executa() {
		setVisible(true);
		// CADASTRAR NOVO JOGADOR
		try {
			// CONEXAO COM O SERVIDOR
			socket = new Socket("", 12350);
			// ESCREVENDO OS DADOS PARA MANDAR PARA O SERVIDOR
			saida = new PrintStream(socket.getOutputStream());
			saida.println(nickname);

			Recebedor r = new Recebedor(socket.getInputStream());
			// INICIANDO A THREAD
			new Thread(r).start();
		} catch (IOException e) {
			System.out.println("Falha na Conexao... .. ." + " IOException: "
					+ e);
		}

	}

	// execução da thread
	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(
					this.conexao.getInputStream()));
			String msg;
			while (true) {
				msg = entrada.readLine();
				
				String[] mensagem = msg.split(";");
				
				if (mensagem[0].equals("0")) {
					lblNickname.setText(mensagem[6]);
					lblVitoriasJogador.setText(mensagem[9]);
					lblEmpatesJogador.setText(mensagem[10]);
					lblDerrotasJogador.setText(mensagem[11]);
					lblPontosJogador.setText(mensagem[12]);
					
					lblJogosJogador.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
					
				}
				
				if (mensagem[0].equals("1")) {
					lblNickname1.setText(mensagem[6]);
					lblV1.setText(mensagem[9]);
					lblE1.setText(mensagem[10]);
					lblD1.setText(mensagem[11]);
					lblPontos1.setText(mensagem[12]);
					
					lblJ1.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
				}
				if (mensagem[0].equals("2")) {
					lblNickname2.setText(mensagem[6]);
					lblV2.setText(mensagem[9]);
					lblE2.setText(mensagem[10]);
					lblD2.setText(mensagem[11]);
					lblPontos2.setText(mensagem[12]);
					
					lblJ2.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
				}
				if (mensagem[0].equals("3")) {
					lblNickname3.setText(mensagem[6]);
					lblV3.setText(mensagem[9]);
					lblE3.setText(mensagem[10]);
					lblD3.setText(mensagem[11]);
					lblPontos3.setText(mensagem[12]);
					
					lblJ3.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
				}
				if (mensagem[0].equals("4")) {
					lblNickname4.setText(mensagem[6]);
					lblV4.setText(mensagem[9]);
					lblE4.setText(mensagem[10]);
					lblD4.setText(mensagem[11]);
					lblPontos4.setText(mensagem[12]);
					
					lblJ4.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
				}
				if (mensagem[0].equals("5")) {
					lblNickname5.setText(mensagem[6]);
					lblV5.setText(mensagem[9]);
					lblE5.setText(mensagem[10]);
					lblD5.setText(mensagem[11]);
					lblPontos5.setText(mensagem[12]);
					
					lbJ5.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
				}
				if (mensagem[0].equals("6")) {
					lblNickname6.setText(mensagem[6]);
					lblV6.setText(mensagem[9]);
					lblE6.setText(mensagem[10]);
					lblD6.setText(mensagem[11]);
					lblPontos6.setText(mensagem[12]);
					lblJ6.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					
				}
				if (mensagem[0].equals("7")) {
					lblNickname7.setText(mensagem[6]);
					lblV7.setText(mensagem[9]);
					lblE7.setText(mensagem[10]);
					lblD7.setText(mensagem[11]);
					lblPontos7.setText(mensagem[12]);	
					
					lblJ7.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
				}
				repaint();
				revalidate();

			}
		} catch (IOException e) {
			System.out.println("Ocorreu uma Falha... .. ." + " IOException: "
					+ e);
		}
	}

	class Recebedor implements Runnable {

		@SuppressWarnings("unused")
		private InputStream servidor;

		public Recebedor(InputStream servidor) {
			this.servidor = servidor;
		}

		@Override
		public void run() {
			try {
				entrada = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				String msg;
				while (true) {

					msg = entrada.readLine();
					
					String[] mensagem = msg.split(";");
					
					if (mensagem[0].equals("0")) {
						lblNickname.setText(mensagem[6]);
						lblVitoriasJogador.setText(mensagem[9]);
						lblEmpatesJogador.setText(mensagem[10]);
						lblDerrotasJogador.setText(mensagem[11]);
						lblPontosJogador.setText(mensagem[12]);
						
						lblJogosJogador.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));

						
					}
					
					if (mensagem[0].equals("1")) {
						lblNickname1.setText(mensagem[6]);
						lblV1.setText(mensagem[9]);
						lblE1.setText(mensagem[10]);
						lblD1.setText(mensagem[11]);
						lblPontos1.setText(mensagem[12]);
						
						lblJ1.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
						
					}
					if (mensagem[0].equals("2")) {
						lblNickname2.setText(mensagem[6]);
						lblV2.setText(mensagem[9]);
						lblE2.setText(mensagem[10]);
						lblD2.setText(mensagem[11]);
						lblPontos2.setText(mensagem[12]);
						
						lblJ2.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
						
					}
					if (mensagem[0].equals("3")) {
						lblNickname3.setText(mensagem[6]);
						lblV3.setText(mensagem[9]);
						lblE3.setText(mensagem[10]);
						lblD3.setText(mensagem[11]);
						lblPontos3.setText(mensagem[12]);
						
						lblJ3.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
						
					}
					if (mensagem[0].equals("4")) {
						lblNickname4.setText(mensagem[6]);
						lblV4.setText(mensagem[9]);
						lblE4.setText(mensagem[10]);
						lblD4.setText(mensagem[11]);
						lblPontos4.setText(mensagem[12]);
						
						lblJ4.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
						
					}
					if (mensagem[0].equals("5")) {
						lblNickname5.setText(mensagem[6]);
						lblV5.setText(mensagem[9]);
						lblE5.setText(mensagem[10]);
						lblD5.setText(mensagem[11]);
						lblPontos5.setText(mensagem[12]);
						
						lbJ5.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
						
					}
					if (mensagem[0].equals("6")) {
						lblNickname6.setText(mensagem[6]);
						lblV6.setText(mensagem[9]);
						lblE6.setText(mensagem[10]);
						lblD6.setText(mensagem[11]);
						lblPontos6.setText(mensagem[12]);
						lblJ6.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
						
					}
					if (mensagem[0].equals("7")) {
						lblNickname7.setText(mensagem[6]);
						lblV7.setText(mensagem[9]);
						lblE7.setText(mensagem[10]);
						lblD7.setText(mensagem[11]);
						lblPontos7.setText(mensagem[12]);	
						
						lblJ7.setText(Integer.toString(Integer.parseInt(mensagem[9]) + Integer.parseInt(mensagem[10]) + Integer.parseInt(mensagem[11])));
					}
					
					repaint();
					revalidate();
					
				}
			} catch (IOException e) {
				System.out.println("Ocorreu uma Falha... .. ."
						+ " IOException: " + e);
			}
		}
	}
}
