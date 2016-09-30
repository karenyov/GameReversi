package MultiplayerJogador2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import MultiplayerJogador1.Jogador1;


public class Jogador2 {

    private static JPanel jpanel;
    private static Tabuleiro tabuleiro;
    private static Chat chat;

    private static String nomeJogador;
    private static String serverIp;
    private static int serverPort;
    
    private JLabel lblNickname;
    
    static Jogador1 j ;

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                       //show("OI");
                    }
                });
    }
    
    public void executar(final String jogador){
    	javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                       // show(jogador);
                    }
                }
            );
    }

    public void show(String jogador,Jogador2 j) {
        JFrame window = new JFrame("Reversi");
        window.setSize(new Dimension(1200, 650));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        Jogador2 game = j;
        game.jpanel.setOpaque(true);

        window.setContentPane(game.jpanel);

        window.setVisible(true);

    }

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public Jogador2(String jogador2){
    	
    	this.nomeJogador = jogador2;
        jpanel = new JPanel();

        //serverIp = JOptionPane.showInputDialog(null, "Please type the server ip:");
        serverPort = 12360;
       // nomeJogador = JOptionPane.showInputDialog(null, "Please type the player name:");

        tabuleiro = new Tabuleiro( serverIp, serverPort, nomeJogador);

        Thread bd = new Thread(tabuleiro);
        bd.start();

        chat = new  Chat(serverIp, ++serverPort, nomeJogador);
        Thread gc = new Thread(chat);
        gc.start();
        
        lblNickname = new JLabel(nomeJogador);
        lblNickname.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		lblNickname.setBounds(23, 11, 365, 22);

        jpanel.setLayout(null);

        jpanel.add(tabuleiro);
        jpanel.add(chat);
        jpanel.add(lblNickname);
        jpanel.setBackground(new Color(135, 206, 250));

        tabuleiro.setBounds(0,60,550,550);
        tabuleiro.setBackground(new Color(135, 206, 250));

        chat.setBackground(new Color(135, 206, 250));
        chat.setBounds(560, 60, 520, 500);

    }
}
