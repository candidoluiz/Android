package com.example.dto;

public class MercadoDto {

    private int mercadoId;
    private String nome;
    private String rua;
    private String bairro;
    private String numero;
    //private InputStream imagem;
    private String foto;
    private String cidade;
    private String uf;

    public MercadoDto(){};


    public MercadoDto(int mercadoId, String nome, String rua, String bairro, String numero, String foto, String cidade, String uf) {
        this.mercadoId = mercadoId;
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.foto = foto;
        this.cidade = cidade;
        this.uf = uf;
    }

    public MercadoDto(String cidade) {
        this.cidade = cidade;
    }

    public int getMercadoId() {
        return mercadoId;
    }

    public void setMercadoId(int mercadoId) {
        this.mercadoId = mercadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
