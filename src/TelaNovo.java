
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class TelaNovo extends JFrame {

	private JPanel contentPane;
	private String Estado;

	/**
	 * Launch the application.
	 */

	private JTextField nome;
	private JTextField cidade;
	private JTextField endereco;
	private JTextField numero;
	private JTextField email;
	private JPasswordField senha;
	private JPasswordField senhaNovamente;
	private JLabel label1, label2, label3, label4, label5, label6;
	int index;
	static String avatar;

	static TelaNovo frame = new TelaNovo(avatar);

	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public TelaNovo(String avatar) {
		this.avatar = avatar;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Label Cadastro de Jogadores
		JLabel lblCadastroDeJogadores = new JLabel("Cadastro de Jogadores");
		lblCadastroDeJogadores.setForeground(new Color(0, 51, 153));
		lblCadastroDeJogadores.setFont(new Font("Gill Sans Ultra Bold",
				Font.PLAIN, 32));
		lblCadastroDeJogadores.setBounds(269, 64, 513, 76);
		contentPane.add(lblCadastroDeJogadores);

		// Botão para voltar
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("voltar.png"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				TelaAvatar t = new TelaAvatar();
				t.janela();
				// Tela t = new Tela();
				// t.Janela();
			}
		});
		btnVoltar.setBackground(new Color(135, 206, 250));
		btnVoltar.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnVoltar.setBounds(10, 11, 46, 31);
		contentPane.add(btnVoltar);

		// Label Nome
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setForeground(new Color(0, 51, 153));
		lblNome.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblNome.setBounds(222, 194, 71, 22);
		contentPane.add(lblNome);

		// TextField Nome
		nome = new JTextField();
		nome.setBounds(319, 192, 399, 24);
		nome.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(nome);
		nome.setColumns(10);

		// Label Cidade
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(new Color(0, 51, 153));
		lblCidade.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblCidade.setBounds(222, 230, 71, 22);
		contentPane.add(lblCidade);

		// Label Estado
		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setForeground(new Color(0, 51, 153));
		lblEstado.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 14));
		lblEstado.setBounds(553, 230, 92, 22);
		contentPane.add(lblEstado);

		// TextField Cidade
		cidade = new JTextField();
		cidade.setColumns(10);
		cidade.setBounds(319, 228, 218, 24);
		cidade.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(cidade);

		// ComboBox dos Estados
		final JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estado = (String) comboBoxEstado.getSelectedItem();
				index = comboBoxEstado.getSelectedIndex();

			}
		});
		comboBoxEstado.setBackground(new Color(135, 206, 250));
		comboBoxEstado.setForeground(Color.BLACK);
		comboBoxEstado.setBounds(655, 227, 63, 20);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { " ",
				"AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT",
				"MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS",
				"RO", "RP", "SC", "SP", "SE", "TO" }));
		contentPane.add(comboBoxEstado);

		label1 = new JLabel("*");
		label1.setForeground(Color.RED);
		label1.setFont(new Font("Verdana", Font.BOLD, 13));
		label1.setBounds(724, 194, 58, 14);
		contentPane.add(label1);

		label2 = new JLabel("*");
		label2.setForeground(Color.RED);
		label2.setFont(new Font("Verdana", Font.BOLD, 13));
		label2.setBounds(539, 227, 21, 14);
		contentPane.add(label2);

		label3 = new JLabel("*");
		label3.setForeground(Color.RED);
		label3.setFont(new Font("Verdana", Font.BOLD, 13));
		label3.setBounds(724, 227, 58, 14);
		contentPane.add(label3);

		JLabel lblCamposDe = new JLabel(
				"(*) Campos de preenchimento obrigat\u00F3rio");
		lblCamposDe.setForeground(Color.RED);
		lblCamposDe.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblCamposDe.setBounds(235, 163, 325, 31);
		contentPane.add(lblCamposDe);

		endereco = new JTextField();
		endereco.setColumns(10);
		endereco.setBorder(new LineBorder(Color.BLACK));
		endereco.setBounds(319, 267, 218, 24);
		contentPane.add(endereco);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setForeground(new Color(0, 51, 153));
		lblEndereco.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblEndereco.setBounds(222, 267, 87, 22);
		contentPane.add(lblEndereco);

		JLabel lblN = new JLabel("N\u00BA: ");
		lblN.setForeground(new Color(0, 51, 153));
		lblN.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblN.setBounds(574, 263, 74, 22);
		contentPane.add(lblN);

		numero = new JTextField();
		numero.setColumns(10);
		numero.setBorder(new LineBorder(Color.BLACK));
		numero.setBounds(647, 263, 71, 24);
		contentPane.add(numero);

		JLabel lblDadosParaLogin = new JLabel("Dados para Login");
		lblDadosParaLogin.setForeground(Color.RED);
		lblDadosParaLogin.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblDadosParaLogin.setBounds(292, 355, 325, 31);
		contentPane.add(lblDadosParaLogin);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(0, 51, 153));
		lblEmail.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblEmail.setBounds(269, 385, 81, 22);
		contentPane.add(lblEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(0, 51, 153));
		lblSenha.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblSenha.setBounds(269, 418, 81, 22);
		contentPane.add(lblSenha);

		email = new JTextField();
		email.setColumns(10);
		email.setBorder(new LineBorder(Color.BLACK));
		email.setBounds(414, 386, 218, 24);
		contentPane.add(email);

		senha = new JPasswordField();
		senha.setColumns(10);
		senha.setBorder(new LineBorder(Color.BLACK));
		senha.setBounds(414, 419, 218, 24);
		contentPane.add(senha);

		label4 = new JLabel("*");
		label4.setForeground(Color.RED);
		label4.setFont(new Font("Verdana", Font.BOLD, 13));
		label4.setBounds(642, 388, 21, 14);
		contentPane.add(label4);

		label5 = new JLabel("*");
		label5.setForeground(Color.RED);
		label5.setFont(new Font("Verdana", Font.BOLD, 13));
		label5.setBounds(642, 421, 21, 14);
		contentPane.add(label5);

		senhaNovamente = new JPasswordField();
		senhaNovamente.setColumns(10);
		senhaNovamente.setBorder(new LineBorder(Color.BLACK));
		senhaNovamente.setBounds(414, 451, 218, 24);
		contentPane.add(senhaNovamente);

		label6 = new JLabel("*");
		label6.setForeground(Color.RED);
		label6.setFont(new Font("Verdana", Font.BOLD, 13));
		label6.setBounds(642, 454, 21, 14);
		contentPane.add(label6);

		JLabel lblRepitaASenha = new JLabel("Repita a senha:");
		lblRepitaASenha.setForeground(new Color(0, 51, 153));
		lblRepitaASenha
				.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 14));
		lblRepitaASenha.setBounds(269, 450, 135, 22);
		contentPane.add(lblRepitaASenha);

		// Botão para limpar os campos do formulário
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText("");
				label1.setText("*");
				label1.setIcon(new ImageIcon(""));
				cidade.setText("");
				label2.setText("*");
				label2.setIcon(new ImageIcon(""));
				label3.setText("*");
				label3.setIcon(new ImageIcon(""));
				endereco.setText("");
				numero.setText("");
				email.setText("");
				label4.setText("*");
				label4.setIcon(new ImageIcon(""));
				senha.setText("");
				label5.setText("*");
				label5.setIcon(new ImageIcon(""));
				senhaNovamente.setText("");
				label6.setText("*");
				label6.setIcon(new ImageIcon(""));
			}
		});
		btnLimpar.setForeground(new Color(255, 204, 0));
		btnLimpar.setBackground(new Color(255, 0, 51));
		btnLimpar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnLimpar.setBounds(378, 525, 89, 31);
		btnLimpar.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnLimpar);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaCampos();

			}
		});
		btnEnviar.setForeground(new Color(255, 204, 0));
		btnEnviar.setBackground(new Color(255, 0, 51));
		btnEnviar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		btnEnviar.setBounds(477, 525, 89, 31);
		btnEnviar.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(btnEnviar);

		JLabel Controle = new JLabel("");
		Controle.setIcon(new ImageIcon("controle.png"));
		Controle.setBounds(178, 73, 63, 41);
		contentPane.add(Controle);

		// NUVENS NA TELA
		JLabel labelNuvem = new JLabel("");
		labelNuvem.setIcon(new ImageIcon("nuvem.png"));
		labelNuvem.setBounds(33, 328, 198, 109);
		contentPane.add(labelNuvem);

		JLabel labelNuvem2 = new JLabel("");
		labelNuvem2.setIcon(new ImageIcon("nuvem.png"));
		labelNuvem2.setBounds(724, 435, 198, 109);
		contentPane.add(labelNuvem2);

		JLabel labelNuvem3 = new JLabel("");
		labelNuvem3.setIcon(new ImageIcon("nuvem.png"));
		labelNuvem3.setBounds(757, 103, 198, 109);
		contentPane.add(labelNuvem3);

	}

	/**
	 * método para verificar se os campos estão vazios
	 */
	@SuppressWarnings("deprecation")
	public void verificaCampos() {
		if (nome.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo Nome deve ser preenchido");
		} else {
			label1.setText("");
			label1.setIcon(new ImageIcon("iconeValidacao.gif"));
		}
		if (cidade.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo Cidade deve ser preenchido");
		} else {
			label2.setText("");
			label2.setIcon(new ImageIcon("iconeValidacao.gif"));
		}
		if (index == 0) {
			JOptionPane.showMessageDialog(null,
					"O campo Estado deve ser selecionado");
		} else {
			label3.setText("");
			label3.setIcon(new ImageIcon("iconeValidacao.gif"));
		}
		if (email.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo Email deve ser preenchido");
		} else {
			label4.setText("");
			label4.setIcon(new ImageIcon("iconeValidacao.gif"));
		}
		if (senha.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo Senha deve ser preenchido");
		}
		if (senhaNovamente.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null,
					"O campo Repita a Senha deve ser preenchido");
		}

		// Todos os campos forem preenchidos!!!
		if ((!nome.getText().trim().equals(""))
				&& (!cidade.getText().trim().equals("")) && (index != 0)
				&& (!email.getText().trim().equals(""))
				&& (!senha.getText().trim().equals(""))
				&& (!senhaNovamente.getText().trim().equals(""))) {

			// Verificar se os dados estão corretos
			if (!senha.getText().equals(senhaNovamente.getText())) {
				JOptionPane.showMessageDialog(null, "Senha não corresponde");
				label5.setText("");
				label5.setIcon(new ImageIcon("iconeErro.gif"));
				label6.setText("");
				label6.setIcon(new ImageIcon("iconeErro.gif"));
			} else {
				label5.setText("");
				label5.setIcon(new ImageIcon("iconeValidacao.gif"));
				label6.setText("");
				label6.setIcon(new ImageIcon("iconeValidacao.gif"));

				int resposta;
				resposta = JOptionPane.showConfirmDialog(null,
						"Deseja confirmar os dados?");

				if (resposta == JOptionPane.YES_OPTION) {
					// frame.setVisible(false);
					// Adicionar novo jogador
					Jogador novo = new Jogador(nome.getText(),
							cidade.getText(), Estado, endereco.getText(),
							Integer.parseInt(numero.getText()),
							email.getText(), senha.getText(), avatar,0,0,0,0);

					try {
						JogadorSocket jogadorSocket = new JogadorSocket(novo);
						jogadorSocket.executa();
						frame.setVisible(false);
						Tela t = new Tela();
						t.Janela();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

				}
			}

		}

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
