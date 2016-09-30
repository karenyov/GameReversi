import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollBar;

import MultiplayerJogador1.Jogador1;
import MultiplayerJogador2.Jogador2;

public class Desafio extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nome;
	JButton btnDesafiar,btnOffline ;

	private Socket conexao;
	private String nickname;

	Socket socket;

	PrintStream saida;
	
	JTextArea textAreaConectado;
	
	Tabuleiro tabuleiro = new Tabuleiro();
	JPanel painelTabuleiro;
	JPanel pnGeral;
	JMenuBar menu;

	BufferedReader entrada;
	private JButton btnOnline;
	private JScrollBar scrollBar;
	String jogador;
	static Jogador1 j ;
	static Jogador2 j2 ;
	
	public static void main(String[] args) {
		Jogador1 j = new Jogador1("nickname");
		j.executar("nickname");

	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Desafio(String nickname) {
		setVisible(true);
		this.nickname = nickname;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1100, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Label Cadastro de Jogadores
		JLabel lblDesafio = new JLabel("Desafio");
		lblDesafio.setForeground(new Color(0, 51, 153));
		lblDesafio.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 32));
		lblDesafio.setBounds(269, 64, 513, 76);
		contentPane.add(lblDesafio);

		btnDesafiar = new JButton("Desafiar");
		btnDesafiar.addActionListener(this);
		btnDesafiar.setForeground(new Color(255, 204, 0));
		btnDesafiar.setBackground(new Color(255, 0, 51));
		btnDesafiar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnDesafiar.setBounds(594, 249, 89, 31);
		btnDesafiar.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnDesafiar);

		nome = new JTextField();
		nome.setBounds(377, 252, 195, 26);
		nome.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(nome);
		nome.setColumns(10);
		btnDesafiar.addActionListener(this);
		
		btnOffline = new JButton("Offline");
		btnOffline.addActionListener(this);
		btnOffline.setForeground(new Color(255, 204, 0));
		btnOffline.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnOffline.setBorder(new LineBorder(Color.BLACK));
		btnOffline.setBackground(new Color(255, 0, 51));
		btnOffline.setBounds(145, 174, 89, 31);
		contentPane.add(btnOffline);
		
		btnOnline = new JButton("Online");
		btnOnline.addActionListener(this);
		btnOnline.setForeground(new Color(255, 204, 0));
		btnOnline.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnOnline.setBorder(new LineBorder(Color.BLACK));
		btnOnline.setBackground(new Color(255, 0, 51));
		btnOnline.setBounds(36, 174, 89, 31);
		contentPane.add(btnOnline);
		
		textAreaConectado = new JTextArea(15, 30);
		textAreaConectado.setBorder(new LineBorder(Color.BLACK));
		textAreaConectado.setBounds(36, 254, 202, 304);
		contentPane.add(textAreaConectado);
	

	}

	void executa() {

		try {
			socket = new Socket("", 12346);
			saida = new PrintStream(socket.getOutputStream());
			saida.println(nickname + ";" + nome.getText());
			Recebedor r = new Recebedor(socket.getInputStream());
			new Thread(r).start();
		} catch (IOException e) {
			System.out.println("Falha na Conexao... .. ." + " IOException: "
					+ e);
		}
	}

	// execução da thread
	public void run() {
		try {
			// recebe mensagens de outro cliente através do servidor
			BufferedReader entrada = new BufferedReader(new InputStreamReader(
					this.conexao.getInputStream()));
			// cria variavel de mensagem
			String msg;
			while (true) {
				// pega o que o servidor enviou
				msg = entrada.readLine();
				// se a mensagem contiver dados, passa pelo if, caso contrario
				// cai no break e encerra a conexao
				if (msg == null) {
					System.out.println("Conexão encerrada!");
					System.exit(0);
				}
				//JOptionPane.showMessageDialog(null, msg);
				if (msg.contains("->") == true) {
					String[] linha = msg.split(",");

					for (int i = 0; i < linha.length; i++) {
						textAreaConectado.append(linha[i] + "\n");
					}

				}
				if (msg.contains(" - ") == true) {
					int resposta;
					resposta = JOptionPane.showConfirmDialog(null,
							msg);

					if (resposta == JOptionPane.YES_OPTION) {
						//aceitou
						saida.println("Desafio aceito."+":"+jogador);
						
						Jogador1 d = new Jogador1(nickname);
				    	j = d;
				    	d.show("",j);
						break;
						
					}
					else{
						//nao aceitou
						saida.println("Desafio rejeitado."+":"+jogador);
					}
				}
				
				if (msg.contains("Desafio aceito")) {
					JOptionPane.showMessageDialog(null,msg+nickname+jogador);
					
					Jogador2 d2 = new Jogador2(nickname);
			    	j2 = d2;
			    	d2.show("",j2);
			    	break;
				}
				
			}
		} catch (IOException e) {
			// caso ocorra alguma exceção de E/S, mostra qual foi.
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
				// recebe mensagens de outro cliente através do servidor
				entrada = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				// cria variavel de mensagem
				String msg;
				while (true) {
					// pega o que o servidor enviou
					msg = entrada.readLine();
					// se a mensagem contiver dados, passa pelo if, caso
					// contrario cai no break e encerra a conexao
					if (msg == null) {
						System.out.println("Conexão encerrada!");
						System.exit(0);
					}
					//JOptionPane.showMessageDialog(null, msg);
					if (msg.contains("->") == true) {
						String[] linha = msg.split(",");

						for (int i = 0; i < linha.length; i++) {
							textAreaConectado.append(linha[i] + "\n");
						}

					}
					if (msg.contains(" - ") == true) {
						
						int resposta;
						resposta = JOptionPane.showConfirmDialog(null,
								msg);

						if (resposta == JOptionPane.YES_OPTION) {
							//aceitou
							saida.println("Desafio aceito."+":"+jogador);
							
							Jogador1 d = new Jogador1(nickname);
					    	j = d;
					    	d.show("",j);
							break;
							
						}
						else{
							//nao aceitou
							saida.println("Desafio rejeitado."+":"+jogador);
						}
					}
					
					if (msg.contains("Desafio aceito")) {
						JOptionPane.showMessageDialog(null,msg+nickname+jogador);
						
						Jogador2 d2 = new Jogador2(nickname);
				    	j2 = d2;
				    	d2.show("",j2);
				    	break;
						//CHAMAR SOCKET COM JOGO
						
						//String[] linha = msg.split(",");
					}

				}
			} catch (IOException e) {
				// caso ocorra alguma exceção de E/S, mostra qual foi.
				System.out.println("Ocorreu uma Falha... .. ."
						+ " IOException: " + e);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object fonte = event.getSource();

		if (fonte == btnDesafiar) // Envia informacao pelo socket
		{
			if (nome.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Esse campo deve ser preenchido");
			}
			else{
				
				saida.println(" - Aceitar desafio de "+nickname+"?:"+nome.getText().toUpperCase());
				jogador = nome.getText().toUpperCase();
			}
			//saida.println("");
		}
		
		if (fonte == btnOffline) {
			saida.println("");
			setVisible(false);
		}
		if (fonte == btnOnline) {
			executa();
		}
	}
}
