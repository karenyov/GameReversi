
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class TelaRegras extends JFrame {

	private JPanel contentPane;
	static TelaRegras frame = new TelaRegras();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaRegras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void janela() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaRegras();
					frame.setVisible(true);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaRegras() {
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
				frame.setVisible(false);
				//Tela t = new Tela();
				//t.Janela();
				// Tela t = new Tela();
				// t.Janela();
			}
		});
		btnVoltar.setBackground(new Color(135, 206, 250));
		btnVoltar.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnVoltar.setBounds(10, 11, 46, 31);
		contentPane.add(btnVoltar);

		JLabel lblEscolhaSeuPersonagem = new JLabel("Regras");
		lblEscolhaSeuPersonagem.setForeground(new Color(0, 51, 153));
		lblEscolhaSeuPersonagem.setFont(new Font("Gill Sans Ultra Bold",
				Font.PLAIN, 32));
		lblEscolhaSeuPersonagem.setBounds(414, 71, 295, 76);
		contentPane.add(lblEscolhaSeuPersonagem);
		
		JLabel lblRegra1 = new JLabel("\u25CF  Cada cor representa um jogador;");
		lblRegra1.setForeground(new Color(0, 0, 0));
		lblRegra1.setFont(new Font("Arial", Font.BOLD, 12));
		lblRegra1.setBounds(79, 158, 430, 38);
		contentPane.add(lblRegra1);
		
		JLabel lblOJogoComea = new JLabel("\u25CF  o jogo come\u00E7a com 4 pe\u00E7as formando um quadrado no centro do tabuleiro, 2 de cada cor;");
		lblOJogoComea.setForeground(Color.BLACK);
		lblOJogoComea.setFont(new Font("Arial", Font.BOLD, 12));
		lblOJogoComea.setBounds(79, 190, 530, 38);
		contentPane.add(lblOJogoComea);
		
		JLabel lblOJogadorPreto = new JLabel("\u25CF  o jogador deve colocar uma pe\u00E7a de sua cor em uma posi\u00E7\u00E3o em que exista ao menos uma linha reta (horizontal, vertical ou diagonal) ");
		lblOJogadorPreto.setForeground(Color.BLACK);
		lblOJogadorPreto.setFont(new Font("Arial", Font.BOLD, 12));
		lblOJogadorPreto.setBounds(79, 222, 970, 38);
		contentPane.add(lblOJogadorPreto);
		
		JLabel lblEntreEssaPosio = new JLabel("entre essa posi\u00E7\u00E3o e alguma outra pe\u00E7a sua;");
		lblEntreEssaPosio.setForeground(Color.BLACK);
		lblEntreEssaPosio.setFont(new Font("Arial", Font.BOLD, 12));
		lblEntreEssaPosio.setBounds(79, 253, 970, 31);
		contentPane.add(lblEntreEssaPosio);
		
		JLabel lblPodeHaverUma = new JLabel("\u25CF  Pode haver uma ou mais pe\u00E7as do advers\u00E1rio ness linha, desde que estejam em sequ\u00EAncia (n\u00E3o separadas);");
		lblPodeHaverUma.setForeground(Color.BLACK);
		lblPodeHaverUma.setFont(new Font("Arial", Font.BOLD, 12));
		lblPodeHaverUma.setBounds(79, 285, 751, 38);
		contentPane.add(lblPodeHaverUma);
		
		JLabel lblApsAJogada = new JLabel("\u25CF  Ap\u00F3s a jogada, todas as pe\u00E7as do advers\u00E1rio que estavam no caminho (da linha reta) s\u00E3o viradas (conquistadas);");
		lblApsAJogada.setForeground(Color.BLACK);
		lblApsAJogada.setFont(new Font("Arial", Font.BOLD, 12));
		lblApsAJogada.setBounds(79, 315, 751, 38);
		contentPane.add(lblApsAJogada);
		
		JLabel lblSeUmJogador = new JLabel("\u25CF  se um jogador n\u00E3o puder fazer uma jogada legal, passa a vez para o advers\u00E1rio");
		lblSeUmJogador.setForeground(Color.BLACK);
		lblSeUmJogador.setFont(new Font("Arial", Font.BOLD, 12));
		lblSeUmJogador.setBounds(79, 347, 751, 38);
		contentPane.add(lblSeUmJogador);
		
		JLabel lblSeNenhumJogador = new JLabel("\u25CF se nenhum jogador puder fazer uma jogada legal, ou n\u00E3o ouver mais espa\u00E7o no tabuleiro, o jogo acaba;");
		lblSeNenhumJogador.setForeground(Color.BLACK);
		lblSeNenhumJogador.setFont(new Font("Arial", Font.BOLD, 12));
		lblSeNenhumJogador.setBounds(79, 380, 751, 38);
		contentPane.add(lblSeNenhumJogador);
		
		JLabel lblOVencedor = new JLabel("\u25CF  o vencedor \u00E9 aquele que tiver mais pe\u00E7as da sua cor no tabuleiro ao final do jogo.");
		lblOVencedor.setForeground(Color.BLACK);
		lblOVencedor.setFont(new Font("Arial", Font.BOLD, 12));
		lblOVencedor.setBounds(79, 413, 751, 38);
		contentPane.add(lblOVencedor);
		
		JLabel lblNuvem = new JLabel("");
		lblNuvem.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem.setBounds(719, 54, 198, 109);
		contentPane.add(lblNuvem);
		
		JLabel lblNuvem2 = new JLabel("");
		lblNuvem2.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem2.setBounds(815, 471, 198, 109);
		contentPane.add(lblNuvem2);
		
		JLabel lblNuvem3 = new JLabel("");
		lblNuvem3.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem3.setBounds(108, 56, 198, 109);
		contentPane.add(lblNuvem3);
		
		JLabel lblSerAtribuido = new JLabel("\u25CF  Ser\u00E1 atribuido 3 pontos pela vit\u00F3ria ao jogador;");
		lblSerAtribuido.setForeground(Color.BLACK);
		lblSerAtribuido.setFont(new Font("Arial", Font.BOLD, 12));
		lblSerAtribuido.setBounds(79, 481, 430, 38);
		contentPane.add(lblSerAtribuido);
		
		JLabel lblSerAtribuido_1 = new JLabel("\u25CF  Ser\u00E1 atribuido 1 ponto a cada jogador se houver empate;");
		lblSerAtribuido_1.setForeground(Color.BLACK);
		lblSerAtribuido_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblSerAtribuido_1.setBounds(79, 513, 430, 38);
		contentPane.add(lblSerAtribuido_1);
		
		JLabel lblSerRetirado = new JLabel("\u25CF  Ser\u00E1 retirado 1 vitória (3 pontos) se um dos jogadores desistirem.");
		lblSerRetirado.setForeground(Color.BLACK);
		lblSerRetirado.setFont(new Font("Arial", Font.BOLD, 12));
		lblSerRetirado.setBounds(79, 542, 430, 38);
		contentPane.add(lblSerRetirado);

	}
}
