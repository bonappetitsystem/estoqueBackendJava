package com.estoque.repository.filter;

public class VendasFilter {

    private String cliente;
    private String vendedor;
    private String codigoVenda;

    public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getCodigoVenda() {
		return codigoVenda;
	}
	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}


}
