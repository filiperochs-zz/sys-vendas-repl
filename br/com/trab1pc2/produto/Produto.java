package br.com.trab1pc2.produto;

public class Produto {
	private long id; // (Auto gerado)
	private String nome;
	private String descricao;
	private double preco;
	private int qtdEstoque;
	private boolean excluido;
	
	private static long geraId=1;

	// Construtor Base
	
	private Produto(String nome, String descricao, double preco, int qtdEstoque) {
		
		this.id = geraId;
		geraId++;
		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.excluido = false;
	}
	
	// getInstance
	
	public static Produto getInstance(String nome, String descricao, double preco, int qtdEstoque) {
		if (((nome != null && nome.length() >= 2) && !nome.equalsIgnoreCase(" ")) && descricao != null && preco > 0 && qtdEstoque >= 0) {
			return new Produto(nome, descricao, preco, qtdEstoque);
		} else {
			return null;
		}
	}
	
	// Métodos
	
	public boolean alterarEstoque(int qtd) {
		if (qtd > 0 && qtdEstoque <= qtd) {
			this.qtdEstoque -= qtd;
			return true;
			
		} else {
			return false;
		}
	}
	
	public boolean excluir() {
		this.excluido = true;
		return true;
	}
	
	// GGAS

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @return the qtdEstoque
	 */
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	
	/**
	 * @return the excluido
	 */
	public boolean getExcluido() {
		return excluido;
	}
	
}
