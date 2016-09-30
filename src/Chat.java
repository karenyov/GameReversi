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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Chat extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Socket conexao;
	private Socket socket;
	private JTextArea areaTexto;
	private JButton btnEnviar,btnOffiline;
	private PrintStream saida;
	private BufferedReader entrada;
	private String nickname, avatar;
	private JTextField mensagem;
	private JLabel lblAvatar;
	private JTextArea textAreaConectados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// Chat frame = new Chat();
		// frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public Chat(String nickname, String avatar) {
		setVisible(true);
		this.nickname = nickname;
		this.avatar = avatar;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 200, 475, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		areaTexto = new JTextArea(15, 30);
		areaTexto.setBounds(196, 11, 253, 301);
		areaTexto.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(areaTexto);

		mensagem = new JTextField();
		mensagem.setBounds(196, 335, 177, 31);
		contentPane.add(mensagem);
		mensagem.setColumns(10);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setForeground(new Color(255, 204, 0));
		btnEnviar.setBackground(new Color(255, 0, 51));
		btnEnviar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnEnviar.addActionListener(this);
		btnEnviar.setBounds(383, 334, 66, 31);
		btnEnviar.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnEnviar);

		lblAvatar = new JLabel("");
		lblAvatar.setBounds(10, 11, 76, 56);
		contentPane.add(lblAvatar);
		
		textAreaConectados = new JTextArea(15, 30);
		textAreaConectados.setBounds(10, 88, 167, 224);
		textAreaConectados.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(textAreaConectados);
		
		btnOffiline = new JButton("Offline");
		btnOffiline.setForeground(new Color(255, 204, 0));
		btnOffiline.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnOffiline.addActionListener(this);
		btnOffiline.setBorder(new LineBorder(Color.BLACK));
		btnOffiline.setBackground(new Color(255, 0, 51));
		btnOffiline.setBounds(10, 335, 66, 31);
		contentPane.add(btnOffiline);

	}

	void executa() {

		try {
			socket = new Socket("", 12345);
			saida = new PrintStream(socket.getOutputStream());
			lblAvatar.setIcon(new ImageIcon(avatar));
			saida.println(nickname);
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
				areaTexto.append(msg + "\n");

				if (msg.contains("->") == true) {
					String[] linha = msg.split(",");

					for (int i = 0; i < linha.length; i++) {
						textAreaConectados.append(linha[i] + "\n");
					}

				}
			}
		} catch (IOException e) {
			// caso ocorra alguma exceção de E/S, mostra qual foi.
			System.out.println("Ocorreu uma Falha... .. ." + " IOException: "
					+ e);
		}
	}

	class Recebedor implements Runnable {


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
					areaTexto.append(msg + "\n");

					if (msg.contains("->") == true) {
						String[] linha = msg.split(",");

						for (int i = 0; i < linha.length; i++) {
							textAreaConectados.append(linha[i] + "\n");
						}

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

		if (fonte == btnEnviar) // Envia informacao pelo socket
		{
			String text = mensagem.getText();
			areaTexto.append("Eu: " + text + "\n");
			saida.println(text);
			mensagem.setText(new String("")); // recebe do servidor

		}
		
		if (fonte == btnOffiline) {
		
			saida.println("");
			setVisible(false);
		}
	}
}
