package com.br.msf.dao;

import com.br.msf.model.Cidade;
import com.br.msf.model.Pais;
import com.br.msf.model.SituacaoDeSaude;
import com.br.msf.model.Voluntario;
import com.br.msf.repository.CidadeRepository;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VoluntarioDao {

    // Lista de voluntários cadastrados no sistema
    private List<Voluntario> voluntarios = new ArrayList<>();
    private CidadeRepository cidadeRepository;

    EntityManager entityManager = null;

    private Connection connection;

    // Adiciona um voluntário à lista de voluntários
    public boolean cadastrarVoluntario(Voluntario voluntario) {
        if (buscarVoluntarioPorPassaporte(voluntario.getPassaporte()) != null) {
            return false; // Já existe um voluntário com o mesmo número de passaporte
        }

        // Verifica se a idade está dentro do intervalo permitido (18 a 55 anos)
        int idade = Integer.parseInt(voluntario.getIdade());
        if (idade < 18 || idade > 55) {
            return false; // Idade inválida
        }

        if (voluntario.getSituacaoDeSaude() == null) {
            return false;
        }

        String situacaoDeSaude = voluntario.getSituacaoDeSaude().getSituacaoDeSaudeDeclarada();
        if (situacaoDeSaude.equals("Ruim") || situacaoDeSaude.equals("Bom") || situacaoDeSaude.equals("Ótimo")) {
            voluntarios.add(voluntario);
            return true;
        } else {
            return false;
        }


    }

    // Busca um voluntário na lista de voluntários pelo número do passaporte
    public Voluntario buscarVoluntarioPorPassaporte(String numeroPassaporte) {
        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getPassaporte().equals(numeroPassaporte)) {
                return voluntario;
            }
        }
        return null; // Não foi encontrado nenhum voluntário com o número de passaporte informado
    }

}
