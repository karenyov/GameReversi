
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class JogadorSocket {

	private Socket conexao;
	private Jogador jogador;
	private Socket socket;
	private PrintStream saida;
	private BufferedReader entrada;

	public JogadorSocket(Jogador jogador) {
		this.jogador = jogador;
	}

	void executa() {
		// CADASTRAR NOVO JOGADOR
		try {
			// CONEXAO COM O SERVIDOR
			socket = new Socket("", 12348);
			// ESCREVENDO OS DADOS PARA MANDAR PARA O SERVIDOR
			saida = new PrintStream(socket.getOutputStream());
			saida.println((jogador.getNome().toUpperCase() + ";"
					+ jogador.getCidade().toUpperCase() + ";"
					+ jogador.getEstado().toUpperCase() + ";"
					+ jogador.getEndereco().toUpperCase() + ";"
					+ jogador.getNumero() + ";"
					+ jogador.getEmail().toUpperCase() + ";" + jogador
					.getSenha().toUpperCase()) + ";" + jogador.getAvatar()+";"+jogador.getDerrota()+";"+jogador.getEmpate()+";"+jogador.getVitoria());

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
					Tela t = new Tela();
					t.Janela();
				}
				if (msg.equals("Este email ja existe! Conecte novamente com outro Email.")) {
					JOptionPane
							.showMessageDialog(
									null,
									"Este email ja existe! Conecte novamente com outro Email.",
									"Error", 3);
				}
				if (msg.equals("Cadastro Realizado com Sucesso.")) {
					JOptionPane.showMessageDialog(null,
							"Cadastro Realizado com Sucesso.");
					//this.conexao.close();
					Tela t = new Tela();
					t.Janela();
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
						Tela t = new Tela();
						t.Janela();
					}
					if (msg.equals("Este email ja existe! Conecte novamente com outro Email.")) {
						JOptionPane
								.showMessageDialog(
										null,
										"Este email ja existe! Conecte novamente com outro Email.",
										"Error", 3);
					}
					if (msg.equals("Cadastro Realizado com Sucesso.")) {
						JOptionPane.showMessageDialog(null,
								"Cadastro Realizado com Sucesso.");
						Tela t = new Tela();
						t.Janela();
					}
				}
			} catch (IOException e) {
				System.out.println("Ocorreu uma Falha... .. ."
						+ " IOException: " + e);
			}
		}
	}

}