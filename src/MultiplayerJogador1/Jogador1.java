package MultiplayerJogador1;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jogador1 {
    private JPanel panel;
    private Tabuleiro tabuleiro;
    private Chat chat;
    private int portaDoServidor;
    private String nomeDoJogador;
    private JLabel lblNickname;

    
	static Jogador1 j;
    
    public static void main(String[] args) {

    	Jogador1 d = new Jogador1("");
    	j = d;
    	d.show("",j);
    }
    
    public void executar(final String jogador){
    	javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        //show(jogador);
                    }
                }
            );
    }

    public void show(String jogador,Jogador1 j)
    {
        JFrame window = new JFrame("Reversi");
        //window.setIconImage(new ImageIcon("rsc/logo.png").getImage());
        window.setSize(1200, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        Jogador1 game = j;
        game.panel.setOpaque(true);

        window.setContentPane(game.panel);

        window.setVisible(true);

    }

    public Jogador1(String jogador) {
    	
    	this.nomeDoJogador = jogador;
    	
        panel = new JPanel();

        //portaDoServidor =  Integer.parseInt(JOptionPane.showInputDialog("Informe a porta do servidor"));
        //nomeDoJogador = JOptionPane.showInputDialog("Informe o nome do jogador");

        tabuleiro = new Tabuleiro(12360, nomeDoJogador);
        Thread tb = new Thread(tabuleiro);
        tb.start();

        chat = new Chat(12361, nomeDoJogador);
        Thread bp = new Thread(chat);
        bp.start();
        
        
        lblNickname = new JLabel(nomeDoJogador);
        lblNickname.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
        lblNickname.setBounds(23, 11, 365, 22);

        panel.setLayout(null);
        panel.setBackground(new Color(135, 206, 250));
        panel.add(tabuleiro);
        panel.add(chat);
        panel.add(lblNickname);
        
        tabuleiro.setBounds(0,60,550,550);
        tabuleiro.setBackground(new Color(135, 206, 250));

        chat.setBackground(new Color(135, 206, 250));
        chat.setBounds(560, 80, 520, 500);

    }
}

