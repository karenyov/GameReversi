
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class LoginSocket {

	private Socket conexao;
	private Socket socket;
	private PrintStream saida;
	private BufferedReader entrada;
	private String senha, nickname;

	public LoginSocket(String nickname,String senha) {
		this.nickname = nickname;
		this.senha = senha;
	}

	public void executa() {
		// LOGIN
		try {
			// CONEXAO COM O SERVIDOR
			socket = new Socket("", 12347);
			// ESCREVENDO OS DADOS PARA MANDAR PARA O SERVIDOR
			saida = new PrintStream(socket.getOutputStream());
			saida.println(nickname.toUpperCase()+";"+senha.toUpperCase());

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

				// EXIBINDO AS MENSAGENS
				if (msg == null) {
					System.out.println("Conexão encerrada!");
					System.exit(0);
				}
				if (msg.equals("Login não encontrado.")) {
					JOptionPane
							.showMessageDialog(
									null,
									"Login não encontrado.",
									"Error", 3);
				}
				if (msg.equals("Senha Incorreta.")) {
					JOptionPane.showMessageDialog(null,
							"Senha Incorreta.","Error", 3);
					
				}
				if (msg.contains("§")) {
					String [] linha = msg.split("§");
					String av = linha[0],nic = linha [1];
					Reversi frame = new Reversi(av,nic);
					frame.setVisible(true);
				}
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

					// EXIBINDO AS MENSAGENS
					if (msg == null) {
						System.out.println("Conexão encerrada!");
						System.exit(0);
					}
					if (msg.equals("Login não encontrado.")) {
						JOptionPane
								.showMessageDialog(
										null,
										"Login não encontrado.",
										"Error", 3);
					}
					if (msg.equals("Senha Incorreta.")) {
						JOptionPane.showMessageDialog(null,
								"Senha Incorreta.","Error", 3);
						
					}
					if (msg.contains("§")) {
						String [] linha = msg.split("§");
						String av = linha[0],nic = linha [1];
						Reversi frame = new Reversi(av,nic);
						frame.setVisible(true);
					}

				}
			} catch (IOException e) {
				System.out.println("Ocorreu uma Falha... .. ."
						+ " IOException: " + e);
			}
		}
	}

}