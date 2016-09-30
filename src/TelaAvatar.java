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
public class TelaAvatar extends JFrame {

	private JPanel contentPane;
	static TelaAvatar frame = new TelaAvatar();
	private String Avatar;
	private JLabel lblAvatar;
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaAvatar();
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
					frame = new TelaAvatar();
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
	public TelaAvatar() {
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
				Tela t = new Tela();
				t.Janela();
				// Tela t = new Tela();
				// t.Janela();
			}
		});
		btnVoltar.setBackground(new Color(135, 206, 250));
		btnVoltar.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnVoltar.setBounds(10, 11, 46, 31);
		contentPane.add(btnVoltar);

		JLabel lblEscolhaSeuPersonagem = new JLabel("Escolha seu Personagem");
		lblEscolhaSeuPersonagem.setForeground(new Color(0, 51, 153));
		lblEscolhaSeuPersonagem.setFont(new Font("Gill Sans Ultra Bold",
				Font.PLAIN, 32));
		lblEscolhaSeuPersonagem.setBounds(263, 73, 485, 76);
		contentPane.add(lblEscolhaSeuPersonagem);

		lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageIcon("AvatarDefault.png"));
		lblAvatar.setBounds(359, 288, 274, 217);
		lblAvatar.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		// lblAvatar.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(lblAvatar);

		final JComboBox comboBoxAvatar = new JComboBox();
		comboBoxAvatar.setBounds(338, 203, 189, 20);
		contentPane.add(comboBoxAvatar);

		contentPane.add(comboBoxAvatar);
		comboBoxAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = comboBoxAvatar.getSelectedIndex();
				Avatar = (String) comboBoxAvatar.getSelectedItem();

				if (index == 0) {
					lblAvatar.setIcon(new ImageIcon("AvatarDefault.png"));
				}
				if (index == 1) {
					lblAvatar.setIcon(new ImageIcon("AvatarWoody.jpg"));
				}
				if (index == 2) {
					lblAvatar.setIcon(new ImageIcon("AvatarBuzz.jpg"));
				}
				if (index == 3) {
					lblAvatar.setIcon(new ImageIcon("AvatarJessie.jpg"));
				}
				if (index == 4) {
					lblAvatar.setIcon(new ImageIcon("AvatarRex.jpg"));
				}
				if (index == 5) {
					lblAvatar.setIcon(new ImageIcon("AvatarPorquinho.jpg"));
				}
				if (index == 6) {
					lblAvatar.setIcon(new ImageIcon("AvatarBatata.png"));
				}
				if (index == 7) {
					lblAvatar.setIcon(new ImageIcon("AvatarBatata2.png"));
				}
				if (index == 8) {
					lblAvatar.setIcon(new ImageIcon("AvatarSlink.jpg"));
				}
				if (index == 9) {
					lblAvatar.setIcon(new ImageIcon("AvatarBarbie.jpg"));
				}
				if (index == 10) {
					lblAvatar.setIcon(new ImageIcon("AvatarBalaNoAlvo.jpg"));
				}
				if (index == 11) {
					lblAvatar.setIcon(new ImageIcon("AvatarAliens.jpg"));
				}
				if (index == 12) {
					lblAvatar.setIcon(new ImageIcon("AvatarSargento.jpg"));
				}

			}
		});
		comboBoxAvatar.setForeground(Color.BLACK);
		comboBoxAvatar.setBackground(new Color(135, 206, 250));
		comboBoxAvatar.setModel(new DefaultComboBoxModel(new String[] {
				"PADRÃO", "WOODY", "BUZZ LIGHTYEAR", "JESSIE", "REX",
				"PORQUINHO", "SR. CABEÇA DE BATATA", "SRA. CABEÇA DE BATATA",
				"SLINKY", "BARBIE", "BALA NO ALVO", "ALIENS", "SARGENTO" }));
		contentPane.add(comboBoxAvatar);

		JLabel lblNuvem = new JLabel("");
		lblNuvem.setBounds(87, 414, 198, 109);
		lblNuvem.setIcon(new ImageIcon("nuvem.png"));
		contentPane.add(lblNuvem);

		JLabel lblNuvem2 = new JLabel("");
		lblNuvem2.setBounds(741, 87, 198, 109);
		lblNuvem2.setIcon(new ImageIcon("nuvem.png"));
		contentPane.add(lblNuvem2);

		JLabel lblNuvem3 = new JLabel("");
		lblNuvem3.setBounds(42, 127, 198, 109);
		lblNuvem3.setIcon(new ImageIcon("nuvem.png"));
		contentPane.add(lblNuvem3);

		JButton btnAvancar = new JButton("Avan\u00E7ar");
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				TelaNovo t = new TelaNovo(Avatar);
				t.Janela();

			}
		});
		btnAvancar.setForeground(new Color(255, 204, 0));
		btnAvancar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnAvancar.setBorder(new LineBorder(Color.BLACK));
		btnAvancar.setBackground(new Color(255, 0, 51));
		btnAvancar.setBounds(560, 197, 89, 31);
		contentPane.add(btnAvancar);

		JLabel lblNuvem4 = new JLabel("");
		lblNuvem4.setBounds(741, 414, 198, 109);
		lblNuvem4.setIcon(new ImageIcon("nuvem.png"));
		contentPane.add(lblNuvem4);

	}
}
