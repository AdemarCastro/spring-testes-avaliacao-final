package com.br.msf.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SituacaoDeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String situacaoDeSaudeDeclarada;

    @Column(nullable = true)
    private Boolean tratamentoEmAndamento;

    @Column(nullable = true)
    private String historico;

    @Column(nullable = true)
    private Date data;

    @OneToOne(mappedBy = "situacaoDeSaude")
    private Voluntario voluntario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSituacaoDeSaudeDeclarada() {
        return situacaoDeSaudeDeclarada;
    }

    public void setSituacaoDeSaudeDeclarada(String situacaoDeSaudeDeclarada) {
        this.situacaoDeSaudeDeclarada = situacaoDeSaudeDeclarada;
    }

    public Boolean getTratamentoEmAndamento() {
        return tratamentoEmAndamento;
    }

    public void setTratamentoEmAndamento(Boolean tratamentoEmAndamento) {
        this.tratamentoEmAndamento = tratamentoEmAndamento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SituacaoDeSaude{" +
                "id=" + id +
                ", situacaoDeSaudeDeclarada=" + situacaoDeSaudeDeclarada +
                ", tratamentoEmAndamento=" + tratamentoEmAndamento +
                ", historico=" + historico +
                ", data=" + data +
                '}';
    }
}
