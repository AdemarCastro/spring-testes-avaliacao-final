package com.br.msf.dao;

import com.br.msf.model.Cidade;
import com.br.msf.model.Pais;
import com.br.msf.model.Voluntario;
import com.br.msf.repository.CidadeRepository;
import com.br.msf.repository.PaisRepository;
import com.br.msf.repository.VoluntarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoluntarioDao {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private PaisRepository paisRepository;

    // Adiciona um voluntário à lista de voluntários
    public String cadastrarVoluntario(Voluntario voluntario) {

        // Vamos verificar se foi informado a Cidade e o País
        if (voluntario.getCidade() != null && voluntario.getCidade().getPais() != null) {
            Cidade cidade = voluntario.getCidade();
            Pais pais = voluntario.getCidade().getPais();

            Cidade cidadeEncontrada = cidadeRepository.buscarCidade(cidade.getNome(), cidade.getIbge());

            if (cidadeEncontrada == null) {
                return "A cidade informada não existe no banco de dados.";
            }
        
            Pais paisEncontrado = paisRepository.buscarPais(pais.getNome(), pais.getIbge());
        
            if (paisEncontrado == null) {
                return "O país informado não existe no banco de dados.";
            }

        }

        if (voluntarioRepository.findOneByPassaporte(voluntario.getPassaporte()) != null) {
            return "Já existe um voluntário com o mesmo número de passaporte.";
        }

        // Verifica se a idade está dentro do intervalo permitido (18 a 55 anos)
        int idade = Integer.parseInt(voluntario.getIdade());
        if (idade < 18 || idade > 55) {
            return "Idade inválida. A idade deve estar entre 18 e 55 anos.";
        }

        if (voluntario.getSituacaoDeSaude() == null) {
            return "A situação de saúde não foi informada.";
        }

        String situacaoDeSaude = voluntario.getSituacaoDeSaude().getSituacaoDeSaudeDeclarada();
        
        if (!situacaoDeSaude.equals("Ruim") && !situacaoDeSaude.equals("Bom") && !situacaoDeSaude.equals("Ótimo")) {
            return "Situação de saúde inválida. Opções válidas: Ruim, Bom, Ótimo.";
        }

        voluntarioRepository.save(voluntario);
        return "Cadastro realizado com sucesso.";

    }

}
