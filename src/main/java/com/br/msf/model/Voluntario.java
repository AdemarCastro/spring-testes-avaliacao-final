package com.br.msf.model;

import javax.persistence.*;

@Entity
public class Voluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String passaporte;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String idade;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tipoSanguineo;

    @ManyToOne
    private Cidade cidade;

    @OneToOne
    private SituacaoDeSaude situacaoDeSaude;

    public SituacaoDeSaude getSituacaoDeSaude() {
        return situacaoDeSaude;
    }

    public void setSituacaoDeSaude(SituacaoDeSaude situacaoDeSaude) {
        this.situacaoDeSaude = situacaoDeSaude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanquineo) {
        this.tipoSanguineo = tipoSanquineo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "id=" + id +
                ", passaporte='" + passaporte + '\'' +
                ", nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", tipoSanquineo='" + tipoSanguineo + '\'' +
                ", cidade=" + cidade +
                '}';
    }
}
