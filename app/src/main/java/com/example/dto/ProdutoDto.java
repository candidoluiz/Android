package com.example.dto;

public class ProdutoDto {

    private int produtoId;
    private String tipo;
    private String marca;
    private long codigoBarra;
    private String foto;
    private String medida;
    private String unMedida;

    public ProdutoDto(int produtoId, String tipo, String marca, long codigoBarra, String foto, String medida, String unMedida) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.marca = marca;
        this.codigoBarra = codigoBarra;
        this.foto = foto;
        this.medida = medida;
        this.unMedida = unMedida;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigoBarra(long codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }

    public ProdutoDto() {
    }

    public int getProdutoId() {
        return produtoId;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public long getCodigoBarra() {
        return codigoBarra;
    }

    public String getFoto() {
        return foto;
    }

    public String getMedida() {
        return medida;
    }

    public String getUnMedida() {
        return unMedida;
    }
}
