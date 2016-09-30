package MultiplayerJogador1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.BufferOverflowException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;


public class Tabuleiro extends JPanel implements Runnable {

	Casa[][] cells = new Casa[8][8];
	private int curPlayer;
	private int player1pieces;
	private int player2pieces;
	private static final int NUMCELLS = 8;
	private String playerName;
	private String opponentName;
	private JLabel score;
	private JLabel placar;
	private PrintStream output;
	private BufferedReader input;
	private Socket connection;
	private ServerSocket server;
	private int serverPort;
	private JLabel connectionStatus;
	private JButton playAgainButton;
	
	
	//adicionado
	private int offset = 0;
	private int radio = 0;
	private int numDivisoes = 0;
	private int linhas[];


	public Tabuleiro(int serverPort, String playerName) {
		this.serverPort = 12360;
		this.playerName = playerName;

		curPlayer = 1;

		placar = new JLabel("Placar");
		placar.setBounds(23, 11, 365, 22);
		add(placar);

		score = new JLabel("");
		//score.setBounds(0, 200, 100, 100);
		//score.setForeground(Color.WHITE);
		//score.setBorder(new LineBorder(null, 2, true));
		add(score);

		connectionStatus = new JLabel("Servidor" + serverPort);
		connectionStatus.setBounds(560, 10, 180, 60);
		add(connectionStatus);

		ActListener csActionListener = new ActListener();
		playAgainButton = new JButton("");
		playAgainButton.setText("Jogar Novamente!");
		playAgainButton.setVerticalTextPosition(AbstractButton.CENTER);
		playAgainButton.setHorizontalTextPosition(AbstractButton.CENTER);
		playAgainButton.addActionListener(csActionListener);
		playAgainButton.setMnemonic(KeyEvent.VK_P);
		playAgainButton.setActionCommand("playAgain");
		playAgainButton.setBounds(0, 0, 100, 100);
		playAgainButton.setForeground(Color.red);

		playAgainButton.setVisible(false);
		playAgainButton.setEnabled(false);
		add(playAgainButton);

		drawBoard();

		MouseEvt mouseEvent = new MouseEvt();
		addMouseListener(mouseEvent);

	}

	private void drawBoard() {
		initCells();
		placeInitCells();
		createPolygons();
	}

	private void initCells() {
		for (int i = 0; i < NUMCELLS; i++) {
			for (int j = 0; j < NUMCELLS; j++) {
				this.cells[i][j] = new Casa();
				this.cells[i][j].setX(i);
				this.cells[i][j].setY(j);
				this.cells[i][j].setDraw(Boolean.TRUE);
			}
		}
	}

	private void placeInitCells() {
		cells[3][3].setPlayer(1);
		cells[3][4].setPlayer(2);
		cells[4][3].setPlayer(2);
		cells[4][4].setPlayer(1);
	}
	
	private void createPolygons() {
		int npoints = 4; // Number of points that the polygon has
		int width = 60; // Width of the cell
		int height = 60; // Height of the cell
		int initX = 30; // DistÃ¢ncia da Margem em X
		int initY = 50; // DistÃ¢ncia da Margem em Y
		Polygon p; // Ponteiro de um poligono

		for (int i = 0; i < NUMCELLS; i++) {
			for (int j = 0; j < NUMCELLS; j++) {
				// int xpoints[] = {i * width + initX,
				// ((i * width) + (width / 2)) + initX,
				// (i + 1) * width + initX,
				// ((i * width) + (width / 2)) + initX};

				int x1, y1;
				int x2, y2;
				int x3, y3;
				int x4, y4;

				x1 = i * width + initX;
				x2 = ((i * width) + (width / 2)) + initX;
				x3 = (i + 1) * width + initX;
				x4 = ((i * width) + (width / 2)) + initX;

				y1 = ((j * height) + (height / 2)) + initY;
				y2 = (j * height + initY);
				y3 = ((j * height) + (height / 2)) + initY;
				y4 = ((j + 1) * height + initY);

				int xpoints[] = { x1, x2, x2, x1 };

				int ypoints[] = { y1, y1, y2, y2 };

				// int ypoints[] = {((j * height) + (height / 2)) + initY,
				// (j * height + initY),
				// ((j * height) + (height / 2)) + initY,
				// ((j + 1) * height + initY)};
				p = new Polygon(xpoints, ypoints, npoints);
				cells[i][j].setPolygon(p);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage imagem = null;

	
			try {
				
				imagem = ImageIO.read(new File("r.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.drawImage(imagem, -8, 15 , 540, 540, this);
		

		//g.setColor(Color.red);
		//g.drawLine(0, 35, 1200, 35);
		

		for (int i = 0; i < NUMCELLS; i++) {
			for (int j = 0; j < NUMCELLS; j++) {
				if (cells[i][j].getDraw() == true) {
					{
						g.setColor(cells[i][j].getColor());
						
						if ((cells[i][j].getColor() == Color.BLACK) || (cells[i][j].getColor() == Color.WHITE)){
							g.fillPolygon(cells[i][j].getPolygon());
						}
						g.drawPolygon(cells[i][j].getPolygon());
					}
				}
			}
		}
	}

	public class MouseEvt extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (curPlayer == 1) {
				try {
					cellMovement(e.getX(), e.getY());
					sendData(playerName + ":" + e.getX() + ":" + e.getY()); // NetWork
																			// Problem
				} catch (IOException ex) {
					Logger.getLogger(Tabuleiro.class.getName()).log(Level.SEVERE,
							null, ex);
				}
			} else {
				showMessage("Vez do outro Jogador!");
			}

		}
	}

	public class ActListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("playAgain")) {
				try {
					playAgain();
				} catch (IOException ex) {
					Logger.getLogger(Tabuleiro.class.getName()).log(Level.SEVERE,
							null, ex);
				}
			}
		}
	}

	private void showMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()// atualiza a displayArea
			{
				connectionStatus.setText(messageToDisplay);
			}
		});
	}

	/**
	 * @param x
	 *            position X from the mouse event
	 * @param y
	 *            position Y from the mouse event
	 */
	private void cellMovement(int x, int y) {
		for (int i = 0; i < NUMCELLS; i++) {
			for (int j = 0; j < NUMCELLS; j++) {
				if (cells[i][j].Contains(x, y) == true) {
					if (cells[i][j].getPlayer() == 0) {
						showMessage("                              ");
						if (curPlayer == 1) {
							if (verifyHasMovements()) {
								if (verifyMovement(i, j)) {
									curPlayer++;
									showMessage("It's " + opponentName
											+ " turn!");
								}
							} else {
								curPlayer++;
								showMessage("Você não tem movimentos válidos "+ " vez:" + opponentName );
							}

						} else if (curPlayer == 2) {
							if (verifyHasMovements()) {
								if (verifyMovement(i, j)) {
									curPlayer--;
									showMessage("<html>Sua vez!<html/>");
								}
							} else {
								curPlayer--;
								showMessage("<html>Sua vez!<html/>");
							}

						}
					} else {
						showMessage(" ----- Movimento inválido. ------ ");
					}
				}
			}
		}
		countPieces();
		showScore();
		repaint();
	}
	

	private void countPieces() {
		this.player1pieces = this.player2pieces = 0;
		for (int i = 0; i < NUMCELLS; i++) {
			for (int j = 0; j < NUMCELLS; j++) {
				if (cells[i][j].getPlayer() == 1) {
					this.player1pieces++;
				} else if (cells[i][j].getPlayer() == 2) {
					this.player2pieces++;
				}
			}
		}
		if (player1pieces + player2pieces == 64) {
			showWinner();
		}
	}

	private void showWinner() {
		String winner;

		if (player1pieces > player2pieces) {
			winner = this.playerName;
		} else {
			winner = this.opponentName;
		}
		playAgainButton.setVisible(true);
		playAgainButton.setEnabled(true);

		@SuppressWarnings("RedundantStringConstructorCall")
		String msg = new String("Vencedor:  " + winner);
		showMessage(msg);

		repaint();

	}

	private void playAgain() throws IOException {
		sendData("playAgain" + ":" + this.playerName);
		for (int i = 0; i < NUMCELLS; i++) {
			for (int j = 0; j < NUMCELLS; j++) {
				this.cells[i][j].setPlayer(0);
			}
		}
		placeInitCells();
		playAgainButton.setEnabled(false);
		playAgainButton.setVisible(false);
		repaint();
	}

	private void showScore() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String gameScore;
				
				
				gameScore = "Você: " + player1pieces + " peças "+   "X" + " ";
				if (player2pieces == 1){
					gameScore += opponentName + ": " + player2pieces+ " peça.";
					
				}
				
				else{
				gameScore += opponentName + ": " + player2pieces+ " peças.";
				}
				
				//score.setText(gameScore);
				placar.setText(gameScore);
			}
		});
	}

	private boolean verifyHasMovements() {
		boolean valueReturn = false;
		int num = 2;

		for (int posX = 0; posX < NUMCELLS; posX++) {
			for (int posY = 0; posY < NUMCELLS; posY++) {
				if (cells[posX][posY].getPlayer() == 0) {
					valueReturn = paintCapturedCellsPlus0Plus1(posX, posY, num)
							|| valueReturn;
					valueReturn = paintCapturedCellsMinus1Plus1(posX, posY, num)
							|| valueReturn;
					valueReturn = paintCapturedCellsMinus1Plus0(posX, posY, num)
							|| valueReturn;
					valueReturn = paintCapturedCellsMinus1Minus1(posX, posY,
							num) || valueReturn;
					valueReturn = paintCapturedCellsPlus0Minus1(posX, posY, num)
							|| valueReturn;
					valueReturn = paintCapturedCellsPlus1Minus1(posX, posY, num)
							|| valueReturn;
					valueReturn = paintCapturedCellsPlus1Plus0(posX, posY, num)
							|| valueReturn;
					valueReturn = paintCapturedCellsPlus1Plus1(posX, posY, num)
							|| valueReturn;
				}
			}
		}
		return valueReturn;
	}

	private boolean verifyMovement(int posX, int posY) {
		boolean valueReturn = false;
		int num = 1; // Fala que a funÃ§Ã£o Ã© verifyMovement

		valueReturn = paintCapturedCellsPlus0Plus1(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsMinus1Plus1(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsMinus1Plus0(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsMinus1Minus1(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsPlus0Minus1(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsPlus1Minus1(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsPlus1Plus0(posX, posY, num)
				|| valueReturn;
		valueReturn = paintCapturedCellsPlus1Plus1(posX, posY, num)
				|| valueReturn;
		if (curPlayer == 2 && !valueReturn) {
			System.out.println("MOVIMENTO INVÁLIDO!");
		} // Debug
		if (valueReturn == false) {
			showMessage("MOVIMENTO INVÁLIDO!");
		}
		return valueReturn;
	}

	private boolean paintCapturedCellsPlus0Plus1(int positionX, int positionY,
			int num) {
		int dY = 1;
		int posY = positionY + dY;
		boolean valueReturn = false;
		if (posY < NUMCELLS && curPlayer != cells[positionX][posY].getPlayer()) {
			while (valueReturn == false && posY < (NUMCELLS - 1)
					&& cells[positionX][posY].getPlayer() != 0) {
				posY += dY;
				if (curPlayer == cells[positionX][posY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int j = positionY; j < posY; j = j + dY) {
							cells[positionX][j].setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsMinus1Plus1(int positionX, int positionY,
			int num) {
		int dX = -1;
		int dY = 1;
		int posX = positionX + dX;
		int posY = positionY + dY;
		boolean valueReturn = false;
		if (posX >= 0 && posY < NUMCELLS
				&& curPlayer != cells[posX][posY].getPlayer()) {
			while (valueReturn == false && posX > 0 && posY < (NUMCELLS - 1)
					&& cells[posX][posY].getPlayer() != 0) {
				posX += dX;
				posY += dY;
				if (curPlayer == cells[posX][posY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int i = 0; i <= mod(positionX, posX); i++) {
							cells[positionX + dX * i][positionY + dY * i]
									.setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsMinus1Plus0(int positionX, int positionY,
			int num) {
		int dX = -1;
		int posX = positionX + dX;
		boolean valueReturn = false;
		if (posX >= 0 && curPlayer != cells[posX][positionY].getPlayer()) {
			while (valueReturn == false && posX > 0
					&& cells[posX][positionY].getPlayer() != 0) {
				posX += dX;
				if (curPlayer == cells[posX][positionY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int i = positionX; i > posX; i = i + dX) {
							cells[i][positionY].setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsMinus1Minus1(int positionX,
			int positionY, int num) {
		int dX = -1;
		int dY = -1;
		int posX = positionX + dX;
		int posY = positionY + dY;
		boolean valueReturn = false;
		if (posX >= 0 && posY >= 0
				&& curPlayer != cells[posX][posY].getPlayer()) {
			while (valueReturn == false && posX > 0 && posY > 0
					&& cells[posX][posY].getPlayer() != 0) {
				posX += dX;
				posY += dY;
				if (curPlayer == cells[posX][posY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int i = 0; i <= mod(positionX, posX); i++) {
							cells[positionX + dX * i][positionY + dY * i]
									.setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsPlus0Minus1(int positionX, int positionY,
			int num) {
		int dY = -1;
		int posY = positionY + dY;
		boolean valueReturn = false;
		if (posY >= 0 && curPlayer != cells[positionX][posY].getPlayer()) {
			while (valueReturn == false && posY > 0
					&& cells[positionX][posY].getPlayer() != 0) {
				posY += dY;
				if (curPlayer == cells[positionX][posY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int j = positionY; j > posY; j = j + dY) {
							cells[positionX][j].setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsPlus1Minus1(int positionX, int positionY,
			int num) {
		int dX = 1;
		int dY = -1;
		int posX = positionX + dX;
		int posY = positionY + dY;
		boolean valueReturn = false;
		if (posX < NUMCELLS && posY >= 0
				&& curPlayer != cells[posX][posY].getPlayer()) {
			while (valueReturn == false && posX < (NUMCELLS - 1) && posY > 0
					&& cells[posX][posY].getPlayer() != 0) {
				posX += dX;
				posY += dY;
				if (curPlayer == cells[posX][posY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int i = 0; i <= mod(positionX, posX); i++) {
							cells[positionX + dX * i][positionY + dY * i]
									.setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsPlus1Plus0(int positionX, int positionY,
			int num) {
		int dX = 1;
		int posX = positionX + dX;
		boolean valueReturn = false;
		if (posX < NUMCELLS && curPlayer != cells[posX][positionY].getPlayer()) {
			while (valueReturn == false && posX < (NUMCELLS - 1)
					&& cells[posX][positionY].getPlayer() != 0) {
				posX += dX;
				if (curPlayer == cells[posX][positionY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int i = positionX; i < posX; i = i + dX) {
							cells[i][positionY].setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private boolean paintCapturedCellsPlus1Plus1(int positionX, int positionY,
			int num) {
		int dX = 1;
		int dY = 1;
		int posX = positionX + dX;
		int posY = positionY + dY;
		boolean valueReturn = false;
		if (posX < NUMCELLS && posY < NUMCELLS
				&& curPlayer != cells[posX][posY].getPlayer()) {
			while (valueReturn == false && posX < (NUMCELLS - 1)
					&& posY < (NUMCELLS - 1)
					&& cells[posX][posY].getPlayer() != 0) {
				posX += dX;
				posY += dY;
				if (curPlayer == cells[posX][posY].getPlayer()) {
					valueReturn = true;
					if (num == 1) {
						for (int i = 0; i <= mod(positionX, posX); i++) {
							cells[positionX + dX * i][positionY + dY * i]
									.setPlayer(curPlayer);
						}
					}
				}// enf if
			}
		}// enf if
		return valueReturn;
	}

	private int mod(int num1, int num2) {
		if (num1 > num2) {
			return (num1 - num2);
		} else {
			return (num2 - num1);
		}
	}

	private void waitConnection() throws IOException {
		connection = server.accept();
		connectionStatus("Conectado");
	}

	private void sendData(String message) throws IOException {
		try {
			output = new PrintStream(connection.getOutputStream());
			output.println(message);
			output.flush();
		} catch (BufferOverflowException boe) {
			JOptionPane.showMessageDialog(null, "Error while sending data!");
		}
	}

	private void getIOData() throws IOException {
		output = new PrintStream(connection.getOutputStream());
		input = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		// System.out.println("(ins getIO)input.readLine= "+input.readLine());//DEBUG
	}

	private void refreshScreenStatus() throws IOException {
		boolean goingWell = true;
		do {
			try {
				String receivedData;
				receivedData = (String) input.readLine();
				String[] data = receivedData.split(":");

				this.opponentName = data[0];
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				showMessage(receivedData);

				if (curPlayer == 2) {
					System.out.println("Testing Transmission");// Debug
					cellMovement(x, y);
					repaint();
				}
			} catch (IOException ioE) {
				goingWell = false;
				JOptionPane.showMessageDialog(null, "Unknown data type!");
			}
		} while (goingWell);
	}

	private void connectionStatus(final String status) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				connectionStatus.setText(status);
			}
		});
	}

	@SuppressWarnings("CallToThreadDumpStack")
	private void closeConnection() {
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	@SuppressWarnings("CallToThreadDumpStack")
	private void runServer() {
		try {
			server = new ServerSocket(this.serverPort, 1);
			if (server.getLocalPort() != 12360) {
				System.out.println("Not conn!");
			} // debug
			try {
				waitConnection();
				getIOData();
				System.out.println("Before refresh");
				refreshScreenStatus();
			} catch (EOFException eofException) {
				connectionStatus("Disconnected");
				JOptionPane.showMessageDialog(null, "Connection Failure");
			} finally {
				closeConnection();
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	@Override
	public void run() {
		runServer();
	}
	public JLabel getScore() {
		return score;
	}
}

