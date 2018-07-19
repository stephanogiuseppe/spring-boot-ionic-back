package com.cursomodelagemconceitual.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa física"),
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {

		if (codigo == null) {
			return null;
		}

		for (TipoCliente cliente : TipoCliente.values()) {
			if (cliente.codigo == codigo) {
				return cliente;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
