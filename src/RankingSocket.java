
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class RankingSocket {

	private Socket conexao;
	private Socket socket;
	private PrintStream saida;
	private BufferedReader entrada;
	private String nickname;
	private int vitoria, derrota, empate;
	private int desistencia;

	public RankingSocket(String nickname, int vitoria, int empate,int derrota,int desistencia) {
		this.nickname = nickname;
		this.vitoria = vitoria;
		this.empate = empate;
		this.derrota = derrota;
		this.desistencia = desistencia;

	}
	public static void main(String[] args) {
		RankingSocket r = new RankingSocket("KINHO", 1, 0, 0,0) ;
		r.executa();
	}

	public void executa() {
		// CADASTRAR NOVO JOGADOR
		try {
			// CONEXAO COM O SERVIDOR
			socket = new Socket("", 12349);
			// ESCREVENDO OS DADOS PARA MANDAR PARA O SERVIDOR
			saida = new PrintStream(socket.getOutputStream());
			saida.println(nickname + ";" + vitoria + ";" + empate + ";"
					+ derrota+";"+desistencia);

		} catch (IOException e) {
			System.out.println("Falha na Conexao... .. ." + " IOException: "
					+ e);
		}
	}

}