package br.com.trab1pc2.produto;

import java.util.List;
import java.util.ArrayList;

public class Pedido {
  private List<Item> itens = new ArrayList<Item>();
	private double precoTotal;
	
	// Construtores
	
	private Pedido() {
		precoTotal = 0;
	}
	
	public static Pedido getInstance() {
		return new Pedido();
	}
	
	// Métodos

	public boolean inserirItem(Item item) {	// faz a inserção de um Item ao vetor itens
		if (item != null) {
			
			int index = existeItem(item);
			
			if (index != -1) {
				itens.get(index).somarQtd(item.getQtd());
			} else {
				itens.add(item);
			}
			atualizarPrecoTotal(item.getPreco(), item.getQtd());
			return true; // item inserido com sucesso
		} else {
			return false; // throw Exception
		}
	}
	
	private double atualizarPrecoTotal(double preco, int qtd) { // atualizar o valor do precoTotal
		return (precoTotal += preco*qtd);
	}
	
//	private Item[] extendeVetor(Item[] itens) { // extende o vetor itens[] em +1
//		if (numItens >= itens.length) {
//			Item[] aux = new Item[itens.length+1];
//			for (int i = 0; i < itens.length; i++) {
//				aux[i] = itens[i];
//			}
//			
//			return aux;
//		} else {
//			return itens;
//		}
//	}

	// Métodos
	
	private int existeItem(Item item) {
		if (item != null) {
			for (int i = 0; i < itens.size(); i++) {
				if (itens.get(i).getProduto().getId() == item.getProduto().getId()) {
					return i;
				}
			}
			
			return -1;
		} else {
			return -1;
		}
	}
	
	// Getters and Setters
	
	/**
	 * @return the itens
	 */
	public List<Item> getItens() {
		return itens;
	}

	/**
	 * @return the precoTotal
	 */
	public double getPrecoTotal() {
		return precoTotal;
	}
	
	
}
