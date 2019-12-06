package br.com.trab1pc2.sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.trab1pc2.produto.Item;
import br.com.trab1pc2.produto.Pedido;
import br.com.trab1pc2.produto.Produto;

import java.util.List;
import java.util.ArrayList;

// Sistema será um singleton
public class Sistema {
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Venda> vendas = new ArrayList<Venda>();
	private static Sistema s = new Sistema();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	// Contrutor

	private Sistema() {
	}

	public static Sistema getInstance() {
		return s;
	}

	// Métodos

	public boolean inserirProduto(Produto produto) {
		if (produto != null && !existeNome(produto)) {

			produtos.add(produto);

			return true;
		} else {
			return false;
		}
	}

	public boolean alterarProduto(long id, Produto produto) {
		if (produto != null) {
			boolean retorna = substituirProduto(id, produto);

			return retorna;
		} else {
			return false;
		}
	}

	public boolean alterarVenda(Venda venda) {
		if (venda != null) {
			boolean retorna = substituirVenda(venda.getId(), venda);

			return retorna;
		} else {
			return false;
		}
	}

	public boolean existeNome(Produto produto) {
		if (produto != null) {
			boolean existe = false;
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getNome().equalsIgnoreCase(produto.getNome())) {
					return true;
				}
			}

      return false;
		} else {
			return false;
		}
	}

	public boolean existeIDProduto(long id) {
		if (id > 0) {
			boolean existe = false;
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getId() == id) {
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	public boolean inserirVenda(Venda venda) {
		if (venda != null) {

			vendas.add(venda);

			return true;
		} else {
			return false;
		}
	}

//	private Produto[] extendeVetor(Produto[] produtos) { // extende o vetor produtos[] em +1
//		if (numProdutos >= produtos.length) {
//			Produto[] aux = new Produto[produtos.length + 1];
//			for (int i = 0; i < produtos.length; i++) {
//				aux[i] = produtos[i];
//			}
//
//			return aux;
//		} else {
//			return produtos;
//		}
//	}

//	private Venda[] extendeVetor(Venda[] vendas) { // extende o vetor vendas[] em +1
//		if (numVendas >= vendas.length) {
//			Venda[] aux = new Venda[vendas.length + 1];
//			for (int i = 0; i < vendas.length; i++) {
//				aux[i] = vendas[i];
//			}
//
//			return aux;
//		} else {
//			return vendas;
//		}
//	}

	public List<Produto> listarProdutos() { // Listar todos os produtos (retorna um vetor produtos[] de Produto)
		List<Produto> produtos = new ArrayList<Produto>();

		for (int i = 0; i < this.produtos.size(); i++) {
			if (!this.produtos.get(i).getExcluido()) {
				produtos.add(this.produtos.get(i));
			}

		}

		return produtos;
	}

	public Venda ultimaVenda() { // Listar todas as vendas (retorna um vetor vendas[] de Venda)
		Venda venda = null;
    int ult = vendas.size();
		venda = vendas.get(ult);

		return venda;
	}
	
	public List<Venda> listarVendas() { // Listar todas as vendas (retorna um vetor vendas[] de Venda)
		List<Venda> vendas = new ArrayList<Venda>();

		for (int i = 0; i < vendas.size(); i++) {
			vendas.add(this.vendas.get(i));
		}

		return vendas;
	}

	public List<Item> listarProdutosVenda(Venda venda) {
		if (venda != null) {
			List<Item> itens = new ArrayList<Item>();

			for (int i = 0; i < venda.getPedido().getItens().size(); i++) {
				itens.add(i, venda.getPedido().getItens().get(i));
			}

			return itens;
		} else {
			return null;
		}
	}

	public List<Venda> listarVendasDia(String data) { // Listar todas as vendas no dia (retorna um vetor vendas[] de Venda)

		Date dataFormatada = null;
		try {
			dataFormatada = formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dataFormatada != null) {
			List<Venda> vendas = new ArrayList<Venda>();
			
			for (int i = 0; i < this.vendas.size(); i++) {
				if (this.vendas.get(i).getData().equals(dataFormatada)) {
					vendas.add(this.vendas.get(i));
				}
			}
		}

		return vendas;
	}

	public List<Venda> listarVendasCliente(String cliente) { // Listar todas as vendas de um cliente (retorna um vetor vendas[] de Venda)
		if (cliente != null && cliente.length() >= 2) {
			List<Venda> vendas = new ArrayList<Venda>();
			
			for (int i = 0; i < this.vendas.size(); i++) {
				if (this.vendas.get(i).getCliente().equalsIgnoreCase(cliente)) {
					vendas.add(this.vendas.get(i));
				}
			}
	
			return vendas;
			
		} else {
			return null;
		}
	}

	public Venda buscarVendaID(long id) {
		if (id > 0 && vendas.size() > 0) {
			int j = 0;
			for (int i = 0; i < vendas.size(); i++) {
				if (vendas.get(i).getId() == id) {
					return vendas.get(i);
				}
			}

			return null;
		} else {
			return null;
		}
	}

	public boolean buscarProdutoVenda(Produto produto) {
		if (produto != null && existeIDProduto(produto.getId())) {
			for (int i = 0; i < vendas.size(); i++) {
				List<Item> itens = listarProdutosVenda(vendas.get(i));

				for (int j = 0; j < itens.size(); j++) {
					if (itens.get(j).getProduto().getId() == produto.getId()) {
						return true;
					}
				}
			}

			return false;

		} else {
			return false;
		}
	}

	public Item criarItem(long id, int qtd) {
		Produto produto = buscarProduto(id);

		if (produto != null) {
			if (qtd <= produto.getQtdEstoque()) {
				return Item.getInstance(produto, produto.getPreco(), qtd);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Pedido criarPedido() {
		return Pedido.getInstance();
	}

	public boolean realizarVenda(Venda venda) {
		if (venda != null) {
			if (inserirVenda(venda)) {
				Produto produto;
				for (int i = 0; i < venda.getPedido().getItens().size(); i++) {
					produto = buscarProduto(venda.getPedido().getItens().get(i).getProduto().getId());
					if (produto.alterarEstoque(venda.getPedido().getItens().get(i).getQtd())) {
						excluirVenda(venda.getId());
						return false;
					}
				}

				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public List<Item> listarItens(Pedido pedido) {
		if (pedido != null) {
			return pedido.getItens();
		} else {
			return null;
		}
	}

	public Produto buscarProduto(long id) {
		if (id > 0) {
			int j = 0;
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getId() == id) {
					return produtos.get(i);
				}
			}

			return null;
		} else {
			return null;
		}
	}

	public Produto buscarProdutoNome(String nome) {
		if (nome != null && nome.length() >= 2) {

			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
					return produtos.get(i);
				}
			}

			return null;
		} else {
			return null;
		}
	}

	private boolean substituirProduto(long id, Produto produto) {
		if (id > 0 && produto != null) {

			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getId() == id) {
          produtos.remove(i);
					produtos.add(i, produto);
          return true;
				}
			}
    }

    return false;
	}

	public boolean excluirProduto(int id) {
		if (id > 0) {

      for (int i = 0; i < produtos.size(); i++) {
        if (produtos.get(i).getId() == id) {
          produtos.remove(i);
          return true;
        }
      }

    }

    return false;
	}

	private boolean substituirVenda(long id, Venda venda) {
		if (id > 0 && venda != null) {

			for (int i = 0; i < vendas.size(); i++) {
				if (vendas.get(i).getId() == id) {
          vendas.remove(i);
          vendas.add(i, venda);
          return true;
        }
			}

		}

    return false;
	}

	public boolean excluirVenda(long l) {
		if (l > 0) {
			
			for (int i = 0; i < vendas.size(); i++) {
				if (vendas.get(i).getId() == l) {
					vendas.remove(i);
          return true;
				}
      }
    }

    return false;
	}
}