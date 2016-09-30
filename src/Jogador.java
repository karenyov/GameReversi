public class Jogador {

	private String nome;
	private String cidade;
	private String estado;
	private String endereco;
	private int numero;
	private String email;
	private String senha;
	private String avatar;
	private int derrota;
	private int empate;
	private int vitoria;
	private int pontos;

	public Jogador(String nome, String cidade, String estado, String endereco,
			int numero, String email, String senha, String avatar, int vitoria,
			int empate, int derrota,int pontos) {
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.endereco = endereco;
		this.numero = numero;
		this.email = email;
		this.senha = senha;
		this.avatar = avatar;
		this.derrota = derrota;
		this.empate = empate;
		this.vitoria = vitoria;
		this.pontos = pontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getDerrota() {
		return derrota;
	}

	public void setDerrota(int derrota) {
		this.derrota = derrota;
	}

	public int getEmpate() {
		return empate;
	}

	public void setEmpate(int empate) {
		this.empate = empate;
	}

	public int getVitoria() {
		return vitoria;
	}

	public void setVitoria(int vitoria) {
		this.vitoria = vitoria;
	}

}
