package com.example.ECommerce.model;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;


    public Cliente( int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente " + nome + ", cpf=" + cpf + ", id=" + id + ".";
    }

    public String getDocumento() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getDocumento'");
    }

    public void put(int nextClienteID, Cliente cliente) {
     
      throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    public Cliente get(int clienteId) {
      
      throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
