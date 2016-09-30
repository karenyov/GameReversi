
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

@SuppressWarnings("serial")
public class Tela extends JFrame {

	// Componentes
	private JPanel contentPane;
	private JTextField nickname;
	private JPasswordField senha;

	/**
	 * Launch the application.
	 */
	static Tela frame = new Tela();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					BufferedImage imagem = ImageIO.read(new File("icone.png"));
					frame.setIconImage(imagem);
					frame.Janela();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TextField do nickname
		nickname = new JTextField();
		nickname.setForeground(Color.BLACK);
		nickname.setBounds(406, 219, 163, 25);
		nickname.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(nickname);
		nickname.setColumns(10);

		// TextField da senha
		senha = new JPasswordField();
		senha.setColumns(10);
		senha.setBounds(406, 255, 163, 25);
		senha.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(senha);

		// Label do login
		JLabel lblLogin = new JLabel("Login: ");
		lblLogin.setForeground(new Color(0, 51, 153));
		lblLogin.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		lblLogin.setBounds(322, 224, 74, 20);
		contentPane.add(lblLogin);

		// Label da senha
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(0, 51, 153));
		lblSenha.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		lblSenha.setBounds(322, 255, 74, 20);
		contentPane.add(lblSenha);

		// Label texto REVERSI
		JLabel lblReversi = new JLabel("");
		lblReversi.setIcon(new ImageIcon("logo.png"));
		lblReversi.setBounds(299, 72, 389, 102);
		contentPane.add(lblReversi);

		// Botão Novo
		JButton btnNovo = new JButton("Novo");
		btnNovo.setForeground(new Color(255, 204, 0));
		btnNovo.setBackground(new Color(255, 0, 51));
		btnNovo.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eNovo) {
				frame.setVisible(false);
				// TelaNovo t = new TelaNovo();
				// t.Janela();
				TelaAvatar t = new TelaAvatar();
				t.janela();

			}
		});
		btnNovo.setBounds(322, 291, 101, 25);
		btnNovo.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnNovo);

		// Botão entrar
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(255, 204, 0));
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent eEntrar) {
				if (nickname.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,
							"O campo Login deve ser preenchido!");
				} else {

					if (senha.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null,
								"O campo Senha deve ser preenchido!");
					} else {
						try {
							LoginSocket s = new LoginSocket(nickname.getText(),
									senha.getText());
							s.executa();

							// Reversi frame2 = new Reversi(nickname.getText(),
							// senha.getText(), 2);
							// frame2.iniciar(frame2, frame);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		});
		btnEntrar.setBackground(new Color(255, 0, 51));
		btnEntrar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnEntrar.setBounds(471, 291, 98, 25);
		btnEntrar.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnEntrar);

		JLabel Reversi = new JLabel("");
		Reversi.setIcon(new ImageIcon("Reversi.png"));
		Reversi.setBounds(306, 152, 382, 333);
		contentPane.add(Reversi);

		JLabel lblNuvem = new JLabel("");
		lblNuvem.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem.setBounds(66, 331, 198, 109);
		contentPane.add(lblNuvem);

		JLabel lblNuvem2 = new JLabel("");
		lblNuvem2.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem2.setBounds(265, 488, 198, 109);
		contentPane.add(lblNuvem2);

		JLabel lblNuvem3 = new JLabel("");
		lblNuvem3.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem3.setBounds(91, 88, 198, 109);
		contentPane.add(lblNuvem3);

		JLabel lblNuvem4 = new JLabel("");
		lblNuvem4.setIcon(new ImageIcon("nuvem.png"));
		lblNuvem4.setBounds(716, 30, 198, 109);
		contentPane.add(lblNuvem4);

		JLabel labelCowboy = new JLabel("");
		labelCowboy.setIcon(new ImageIcon("cowboy.png"));
		labelCowboy.setBounds(578, 137, 306, 511);
		contentPane.add(labelCowboy);
	}

	public void Janela() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					BufferedImage imagem = ImageIO.read(new File("icone.png"));
					frame.setIconImage(imagem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
