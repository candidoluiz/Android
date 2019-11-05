package com.example.dto;

public class MercadoProdutoDto {
    private int mercadoProdutoId;
    private MercadoDto mercadoDto;
    private ProdutoDto produtoDto;
    private double preco;

    public MercadoProdutoDto(int mercadoProdutoId, MercadoDto mercadoDto, ProdutoDto produtoDto, double preco) {
        this.mercadoProdutoId = mercadoProdutoId;
        this.mercadoDto = mercadoDto;
        this.produtoDto = produtoDto;
        this.preco = preco;
    }

    public MercadoProdutoDto() {
    }

    public void setMercadoProdutoId(int mercadoProdutoId) {
        this.mercadoProdutoId = mercadoProdutoId;
    }

    public void setMercadoDto(MercadoDto mercadoDto) {
        this.mercadoDto = mercadoDto;
    }

    public void setProdutoDto(ProdutoDto produtoDto) {
        this.produtoDto = produtoDto;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getMercadoProdutoId() {
        return mercadoProdutoId;
    }

    public MercadoDto getMercadoDto() {
        return mercadoDto;
    }

    public ProdutoDto getProdutoDto() {
        return produtoDto;
    }

    public double getPreco() {
        return preco;
    }
}
