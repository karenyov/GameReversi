import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

@SuppressWarnings("serial")
public class Tabuleiro extends Canvas implements MouseListener {
	private final int PECA_VAZIA = 0;
	public final int PECA_USUARIO = 1;
	private final int PECA_COMPUTADOR = 2;
	private final Color[] CORES = new Color[] { null, Color.red, Color.blue };

	private Reversi reversi;
	private int numDivisoes = 0;
	private int pecas[][];
	private int linhas[];
	private int offset = 0;
	private int radio = 0;
	public int jogadas = 0;
	private boolean active = false;

	public Tabuleiro() {
		// TODO Auto-generated constructor stub
	}

	// Este método é executado quando o usuário clica no tabuleiro}
	public void mouseClicked(MouseEvent e) {
		// Jogadas();
		int clickedX = e.getX();
		int clickedY = e.getY();
		try {

			// Atualiza a jogada do usuário
			Casa casaClicada = getCasaPontoClicado(clickedX, clickedY);
			if (casaClicada == null)
				return;
			// Testa se a posição clicada é válida
			if (!validaJogada(pecas, casaClicada.x, casaClicada.y, PECA_USUARIO)) {
				reversi.mostraStatus("Posição inválida");
				return;
			}

			// Altera a cor da peça clicada pelo usuário
			jogadas++;
			processaEscolha(pecas, casaClicada.x, casaClicada.y, PECA_USUARIO);
			update(this.getGraphics());

			// Testa se o jogo acabou ou se o computador cederá a vez
			if (!existePosicaoValida(pecas, PECA_COMPUTADOR)) {
				if (!existePosicaoValida(pecas, PECA_USUARIO)) {
					reversi.fimDeJogo();
					return;
				} else
					JOptionPane.showMessageDialog(this,
							"O computador está cedendo a vez,\n"
									+ "pois não tem onde jogar.", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
			} else {
				boolean temJogadaUsuario = false;
				while (!temJogadaUsuario) {
					reversi.mostraStatus("Vez do computador, aguarde");
					Thread.sleep(500);

					// Calcula a casa do computador
					copiadas = 0;
					Casa quadrante2 = calculaLocalOtimoComputador();
					System.out.println("copiadas = " + copiadas);

					if (quadrante2 != null) {
						jogadas++;
						processaEscolha(pecas, quadrante2.x, quadrante2.y,
								PECA_COMPUTADOR);
						update(this.getGraphics());
					}
					// Testa se o jogo acabou
					if (!existePosicaoValida(pecas, PECA_USUARIO)) {
						if (!existePosicaoValida(pecas, PECA_COMPUTADOR)) {
							reversi.fimDeJogo();
							return;
						} else
							JOptionPane
									.showMessageDialog(
											this,
											"Você cederá a vez,\npois não tem onde jogar.",
											"Aviso",
											JOptionPane.INFORMATION_MESSAGE);
					} else
						temJogadaUsuario = true;
				}
			}

			reversi.mostraStatus("Sua vez");
			mostraPossiveis(PECA_USUARIO, this.getGraphics());
		} catch (Exception ex) {
			System.out.println("Acho que o array estourou!!!");
			ex.printStackTrace();
		}
	}

	// Métodos que validam posições e jogadas no tabuleito
	private boolean existePosicaoValida(int[][] pPecas, int pPecaJogador) {
		for (int i = 0; i < numDivisoes; i++)
			for (int j = 0; j < numDivisoes; j++)
				if (validaJogada(pPecas, i, j, pPecaJogador))
					return true;
		return false;
	}

	private boolean validaJogada(int[][] pPecas, int pX, int pY,
			int pPecaJogador) {
		if (pPecas[pX][pY] != PECA_VAZIA)
			return false;
		for (int movimento = 0; movimento < 8; movimento++) {
			int deltaX = delta[0][movimento];
			int deltaY = delta[1][movimento];
			// Calcula o vizinho
			int i = pX + deltaX;
			int j = pY + deltaY;
			if (!(i >= 0 && j >= 0 && i < numDivisoes && j < numDivisoes))
				continue;
			int pecaVizinho = pPecas[i][j];
			// Testa se o vizinho é peça do oponente
			if (pecaVizinho != PECA_VAZIA && pecaVizinho != pPecaJogador) {
				for (int i2 = i + deltaX, j2 = j + deltaY; i2 >= 0 && j2 >= 0
						&& i2 < numDivisoes && j2 < numDivisoes; i2 += deltaX, j2 += deltaY) {
					int pPecaAtual = pPecas[i2][j2];
					if (pPecaAtual == PECA_VAZIA)
						break;
					else if (pPecaAtual == pPecaJogador)
						return true;
				}
			}
		}
		return false;
	}

	private Casa calculaLocalOtimoComputador() {
		int xMaximo = 0, yMaximo = 0;
		int alfa = Integer.MIN_VALUE;
		for (int i = 0; i < numDivisoes; i++)
			for (int j = 0; j < numDivisoes; j++)
				if (validaJogada(pecas, i, j, PECA_COMPUTADOR)) {
					int[][] pecasCopiadas = copiaPecas(pecas);
					processaEscolha(pecasCopiadas, i, j, PECA_COMPUTADOR);
					int valorAtual = 0;

					addPecasNaoUtilizadas(pecasCopiadas);
					if (alfa < valorAtual) {
						xMaximo = i;
						yMaximo = j;
						alfa = valorAtual;
					}
				}
		return new Casa(xMaximo, yMaximo);
	}

	private int miniMax(int[][] pPecas, int pPecaJogador, int pAltura,
			int pMaxAltura) {
		pAltura++;
		if ((pAltura > pMaxAltura)
				|| (!existePosicaoValida(pPecas, pPecaJogador)))
			return calculaPeso(pPecas, pPecaJogador);

		if (pPecaJogador == PECA_COMPUTADOR) {
			int valorMaximo = Integer.MIN_VALUE;
			for (int i = 0; i < numDivisoes; i++)
				for (int j = 0; j < numDivisoes; j++)
					if (validaJogada(pPecas, i, j, PECA_USUARIO)) {
						int[][] pecasCopiadas = copiaPecas(pPecas);
						processaEscolha(pecasCopiadas, i, j, PECA_USUARIO);
						int valorAtual = miniMax(pecasCopiadas, PECA_USUARIO,
								pAltura, pMaxAltura);
						addPecasNaoUtilizadas(pecasCopiadas);
						if (valorAtual > valorMaximo)
							valorMaximo = valorAtual;
					}
			return valorMaximo;
		} else {
			int valorMinimo = Integer.MAX_VALUE;
			for (int i = 0; i < numDivisoes; i++)
				for (int j = 0; j < numDivisoes; j++)
					if (validaJogada(pPecas, i, j, PECA_COMPUTADOR)) {
						int[][] pecasCopiadas = copiaPecas(pPecas);
						processaEscolha(pecasCopiadas, i, j, PECA_COMPUTADOR);
						int valorAtual = miniMax(pecasCopiadas,
								PECA_COMPUTADOR, pAltura, pMaxAltura);
						addPecasNaoUtilizadas(pecasCopiadas);
						if (valorAtual < valorMinimo)
							valorMinimo = valorAtual;
					}
			return valorMinimo;
		}
	}

	@SuppressWarnings("unused")
	private int alfaBeta(int[][] pPecas, int pPecaJogador, int pAltura,
			int pMaxAltura, int pAlfa) {
		pAltura++;
		if ((pAltura > pMaxAltura)
				|| (!existePosicaoValida(pPecas, pPecaJogador)))
			return calculaPeso(pPecas, pPecaJogador);

		if (pPecaJogador == PECA_COMPUTADOR) {
			for (int i = 0; i < numDivisoes; i++)
				for (int j = 0; j < numDivisoes; j++)
					if (validaJogada(pPecas, i, j, PECA_USUARIO)) {
						int[][] pecasCopiadas = copiaPecas(pPecas);
						processaEscolha(pecasCopiadas, i, j, PECA_USUARIO);
						int valorAtual = alfaBeta(pecasCopiadas, PECA_USUARIO,
								pAltura, pMaxAltura, pAlfa);
						addPecasNaoUtilizadas(pecasCopiadas);
						if (valorAtual > pAlfa)
							pAlfa = valorAtual;
						if (pAlfa >= alfaBeta(null, 0, 0, 0, 0))
							return alfaBeta(null, 0, 0, 0, 0);
					}
			return pAlfa;
		} else {
			for (int i = 0; i < numDivisoes; i++)
				for (int j = 0; j < numDivisoes; j++)
					if (validaJogada(pPecas, i, j, PECA_COMPUTADOR)) {
						int[][] pecasCopiadas = copiaPecas(pPecas);
						processaEscolha(pecasCopiadas, i, j, PECA_COMPUTADOR);
						int valorAtual = alfaBeta(pecasCopiadas,
								PECA_COMPUTADOR, pAltura, pMaxAltura, pAlfa);
						addPecasNaoUtilizadas(pecasCopiadas);

						return pAlfa;
					}

		}
		return pAlfa;
	}

	private int calculaPeso(int[][] pPecas, int pPecaJogador) {
		int peso = 0;
		for (int i = 0; i < numDivisoes; i++)
			for (int j = 0; j < numDivisoes; j++)
				if (pPecas[i][j] == pPecaJogador)

					return peso;
		return peso;
	}

	int copiadas;

	private int[][] copiaPecas(int[][] pPecas) {
		copiadas++;
		int[][] copia = getPecasNaoUtilizadas();
		for (int i = 0; i < numDivisoes; i++)
			for (int j = 0; j < numDivisoes; j++)
				copia[i][j] = pPecas[i][j];
		return copia;
	}

	// Métodos de cache de estrutura de dados para evitar
	// reinstanciação desnecessária.
	int maxCache = 100000;
	int qtdPecasNaoUtilizadas;
	int[][][] pecasNaoUtilizadas;

	private int[][] getPecasNaoUtilizadas() {
		if (qtdPecasNaoUtilizadas == 0)
			return new int[numDivisoes][numDivisoes];
		else {
			qtdPecasNaoUtilizadas--;
			return pecasNaoUtilizadas[qtdPecasNaoUtilizadas];
		}
	}

	private void addPecasNaoUtilizadas(int[][] pPecas) {
		if (qtdPecasNaoUtilizadas < maxCache) {
			pecasNaoUtilizadas[qtdPecasNaoUtilizadas] = pPecas;
			qtdPecasNaoUtilizadas++;
		}
	}

	// Instancia a estrutura do quadrante no ponto clicado
	private Casa getCasaPontoClicado(int clickedX, int clickedY) {
		if (linhas == null)
			return null;
		int casaX = 0;
		int casaY = 0;
		try {
			for (int i = 0; i < linhas.length; i++) {
				if (clickedX > linhas[i])
					casaX = i;
				if (clickedY > linhas[i])
					casaY = i;
			}
		} catch (Exception ex) {
			System.out.println("Problemas no calculo do quadrante!!!");
			ex.printStackTrace();
		}
		return new Casa(casaX, casaY);
	}

	// Métodos para processar as alterações de cor da
	// peça escolhida e seus vizinhos
	private int[][] delta = { { 0, 1, 1, 1, 0, -1, -1, -1 },
			{ -1, -1, 0, 1, 1, 1, 0, -1 } };
	private Vector vPecasAConfirmar = new Vector();

	private void processaEscolha(int[][] pPecas, int pX, int pY,
			int pPecaJogador) {
		pPecas[pX][pY] = pPecaJogador;
		alteraVizinhos(pPecas, pX, pY);
	}

	private void alteraVizinhos(int[][] pPecas, int pXInicial, int pYInicial) {
		int jogadorInicial = pPecas[pXInicial][pYInicial];

		for (int movimento = 0; movimento < 8; movimento++)
			movimenta(pPecas, pXInicial, pYInicial, delta[0][movimento],
					delta[1][movimento], jogadorInicial);
	}

	private void movimenta(int[][] pPecas, int pXInicial, int pYInicial,
			int pDeltaX, int pDeltaY, int pPecaInicial) {
		for (int i = pXInicial + pDeltaX, j = pYInicial + pDeltaY; i >= 0
				&& j >= 0 && i < numDivisoes && j < numDivisoes; i += pDeltaX, j += pDeltaY) {
			if (pPecas[i][j] == PECA_VAZIA)
				break;
			else {
				if (pPecas[i][j] != pPecaInicial)
					vPecasAConfirmar.add(new Casa(i, j));
				else {
					alteraCor(pPecas, vPecasAConfirmar, pPecaInicial);
					break;
				}
			}
		}
		vPecasAConfirmar.clear();
	}

	private void alteraCor(int[][] pPecas, Vector vPecasAConfirmar,
			int pPecaJogador) {
		int size = vPecasAConfirmar.size();
		for (int i = 0; i < size; i++) {
			Casa p = ((Casa) vPecasAConfirmar.get(i));
			pPecas[p.x][p.y] = pPecaJogador;
		}
	}

	// Feedback com o usuário
	int pontosComputador;
	int pontosUsuario;

	public void atualizaPontos() {
		pontosComputador = 0;
		pontosUsuario = 0;
		for (int i = 0; i < numDivisoes; i++) {
			for (int j = 0; j < numDivisoes; j++) {
				if (pecas[i][j] != PECA_VAZIA)
					if (pecas[i][j] == PECA_COMPUTADOR)
						pontosComputador++;
					else
						pontosUsuario++;
			}
		}
	}

	// Inicia as estruturas de dados
	public Tabuleiro(Reversi owner) {
		reversi = owner;
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		this.setBounds(0, 0, 400, 400);
	}

	private void preencheCasa(int pX, int pY, int pPecaJogador) {
		pecas[pX][pY] = pPecaJogador;
	}

	public void setNumDivisoes(int pNumDivisoes) {
		if (pNumDivisoes > 0) {
			this.numDivisoes = pNumDivisoes;
			offset = Math.round(400 / this.numDivisoes);
			radio = (offset * 5) / 6;
		}
	}

	protected void processMouseEvent(MouseEvent pE) {
		super.processMouseEvent(pE);
		if (pE.getID() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(pE);
		}
	}

	public void paint(Graphics pG) {
		if (active) {

			BufferedImage imagem = null;

			try {
				imagem = ImageIO.read(new File("fundo.png"));

				pG.drawImage(imagem, 0, 0, 400, 400, this);

				for (int i = 0; i <= this.numDivisoes; i++) {

					pG.drawLine(i * offset, 0, i * offset, numDivisoes * offset);
					pG.drawLine(0, i * offset, numDivisoes * offset, i * offset);
					if (i < this.numDivisoes) {
						linhas[i] = i * offset;
					}
				}

				for (int i = 0; i < pecas.length; i++) {
					for (int j = 0; j < pecas[i].length; j++) {
						if (pecas[i][j] != PECA_VAZIA) {
							pG.setColor(CORES[pecas[i][j]]);
							pG.fillOval(getCentro(i), getCentro(j), radio,
									radio);
							pG.setColor(Color.black);
							// pG.drawImage(imagem,getCentro(i), getCentro(j),
							// radio, radio, this);
							pG.drawOval(getCentro(i), getCentro(j), radio,
									radio);
						}

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void mostraPossiveis(int pPecaJogador, Graphics pG) {
		for (int i = 0; i < pecas.length; i++) {
			for (int j = 0; j < pecas[i].length; j++) {
				if (validaJogada(pecas, i, j, pPecaJogador)) {
					pG.setColor(Color.white);
					pG.drawOval(getCentro(i), getCentro(j), radio, radio);
				}
			}
		}
	}

	private int getCentro(int pCoordenada) {
		return (pCoordenada * offset) + Math.round(offset / 10);
	}

	public void initialize(String nickname2) {
		pecas = new int[numDivisoes][numDivisoes];
		pecasNaoUtilizadas = new int[maxCache][numDivisoes][numDivisoes];
		qtdPecasNaoUtilizadas = 0;
		linhas = new int[numDivisoes];
		active = true;
		jogadas = 0;
		this.update(this.getGraphics());
		preencheCasa(numDivisoes / 2, numDivisoes / 2, PECA_COMPUTADOR);
		preencheCasa(numDivisoes / 2 - 1, numDivisoes / 2 - 1, PECA_COMPUTADOR);
		preencheCasa(numDivisoes / 2 - 1, numDivisoes / 2, PECA_USUARIO);
		preencheCasa(numDivisoes / 2, numDivisoes / 2 - 1, PECA_USUARIO);
		this.update(this.getGraphics());
		reversi.mostraStatus("Pode começar");
	}

	public void finalize() {
		pecas = null;
		linhas = null;
		active = false;
		this.update(this.getGraphics());
	}

	public void mousePressed(MouseEvent pE) {
	}

	public void mouseReleased(MouseEvent pE) {
	}

	public void mouseEntered(MouseEvent pE) {
	}

	public void mouseExited(MouseEvent pE) {
	}
}
