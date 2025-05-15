package org.example.model;

import org.example.utils.Data;

public abstract class Pessoa  {
    protected int id;
    protected String nome;
    protected String sexo;
    protected Data dataNascimento;

    public Pessoa(int id, String nome, String sexo, Data dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = new Data(dataNascimento);
    }

    public Pessoa(Pessoa p){
        this.id = p.id;
        this.nome = p.nome;
        this.sexo = p.sexo;
        this.dataNascimento = new Data(p.dataNascimento);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Id:").append(id);
        sb.append(", Nome:'").append(nome).append('\'');
        sb.append(", Sexo: ").append(sexo);
        sb.append(", Data de Nascimento: ").append(dataNascimento);
        return sb.toString();
    }
}
