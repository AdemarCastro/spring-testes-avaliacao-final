package com.br.msf.dto;

import com.br.msf.model.Cidade;
import com.br.msf.repository.PaisRepository;

public class CidadeInputDto {

    private String ibge;
    private String nome;
    private String pais;

    public Cidade build(PaisRepository paisRepository){

        Cidade cidade = new Cidade();
        cidade.setIbge(this.ibge);
        cidade.setNome(this.nome);
        cidade.setPais(paisRepository.findByNomeIgnoreCase(this.pais).get(0));

        return cidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
