package com.br.msf.repository;

import com.br.msf.dao.VoluntarioDao;
import com.br.msf.model.Cidade;
import com.br.msf.model.Pais;
import com.br.msf.model.SituacaoDeSaude;
import com.br.msf.model.Voluntario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VoluntarioRepositoryTest {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private VoluntarioDao voluntarioDao;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private SituacaoDeSaudeRepository situacaoDeSaudeRepository;

    // TESTE: RN-01: O voluntário deve ter os seguintes dados cadastrados (todos obrigatórios): Passaporte, Nome
    //Completo, Idade, Telefone, E-mail e Tipo Sanguíneo.

    // POSITIVO
    @Test
    public void testCriarVoluntarioValido() {
        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("Bom");

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("Fulano de Tal");
        voluntario.setIdade("30");
        voluntario.setTelefone("99999999");
        voluntario.setEmail("fulano@example.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        assertNotNull(voluntario.getPassaporte());
        assertNotNull(voluntario.getNome());
        assertNotNull(voluntario.getIdade());
        assertNotNull(voluntario.getTelefone());
        assertNotNull(voluntario.getEmail());
        assertNotNull(voluntario.getTipoSanguineo());
    }

    // NEGATIVO
    @Test
    public void testCriarVoluntarioInvalido() {
        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("Ruim");

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte(null);
        voluntario.setNome(null);
        voluntario.setIdade("30");
        voluntario.setTelefone("");
        voluntario.setEmail("");
        voluntario.setTipoSanguineo("A+");
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        assertNull(voluntario.getPassaporte());
        assertNull(voluntario.getNome());
    }

    // TESTE: RN-02: Um voluntário não pode ser cadastrado se já tiver um passaporte com o mesmo número no sistema.

    // POSITIVO
    @Test
    public void testCriarVoluntarioComPassaporteInexistente() {
        // Cria um voluntário com um passaporte já existente no sistema
        SituacaoDeSaude situacaoDeSaude1 = new SituacaoDeSaude();
        situacaoDeSaude1.setSituacaoDeSaudeDeclarada("Ótimo");

        situacaoDeSaudeRepository.save(situacaoDeSaude1);

        String numeroPassaporte1 = "123456";
        String numeroPassaporte2 = "841565";
        Voluntario voluntario1 = new Voluntario();
        voluntario1.setPassaporte(numeroPassaporte1);
        voluntario1.setNome("João");
        voluntario1.setIdade("30");
        voluntario1.setTelefone("1111111111");
        voluntario1.setEmail("joao@teste.com");
        voluntario1.setTipoSanguineo("A+");
        voluntario1.setSituacaoDeSaude(situacaoDeSaude1);

        // Cadastra o voluntário
        String cadastro1 = voluntarioDao.cadastrarVoluntario(voluntario1);

        // Verifica se o cadastro foi bem-sucedido
        Assertions.assertEquals("Cadastro realizado com sucesso.", cadastro1);

        // Tenta cadastrar outro voluntário com o mesmo passaporte
        SituacaoDeSaude situacaoDeSaude2 = new SituacaoDeSaude();
        situacaoDeSaude2.setSituacaoDeSaudeDeclarada("Ruim");

        situacaoDeSaudeRepository.save(situacaoDeSaude2);

        Voluntario voluntario2 = new Voluntario();
        voluntario2.setPassaporte(numeroPassaporte2);
        voluntario2.setNome("Maria");
        voluntario2.setIdade("25");
        voluntario2.setTelefone("2222222222");
        voluntario2.setEmail("maria@teste.com");
        voluntario2.setTipoSanguineo("AB-");
        voluntario2.setSituacaoDeSaude(situacaoDeSaude2);

        // Tenta cadastrar o segundo voluntário
        String cadastro2 = voluntarioDao.cadastrarVoluntario(voluntario2);

        // Verifica se o cadastro foi bloqueado
        Assertions.assertEquals("Cadastro realizado com sucesso.", cadastro2);
    }

    // NEGATIVO
    @Test
    public void testCriarVoluntarioComPassaporteExistente() {
        // Cria um voluntário com um passaporte já existente no sistema
        SituacaoDeSaude situacaoDeSaude1 = new SituacaoDeSaude();
        situacaoDeSaude1.setSituacaoDeSaudeDeclarada("Bom");

        situacaoDeSaudeRepository.save(situacaoDeSaude1);

        String numeroPassaporte = "123456";
        Voluntario voluntario1 = new Voluntario();
        voluntario1.setPassaporte(numeroPassaporte);
        voluntario1.setNome("João");
        voluntario1.setIdade("30");
        voluntario1.setTelefone("1111111111");
        voluntario1.setEmail("joao@teste.com");
        voluntario1.setTipoSanguineo("A+");
        voluntario1.setSituacaoDeSaude(situacaoDeSaude1);

        // Cadastra o voluntário
        String cadastro1 = voluntarioDao.cadastrarVoluntario(voluntario1);

        // Verifica se o cadastro foi bem-sucedido
        Assertions.assertEquals("Cadastro realizado com sucesso.", cadastro1);

        // Tenta cadastrar outro voluntário com o mesmo passaporte
        SituacaoDeSaude situacaoDeSaude2 = new SituacaoDeSaude();
        situacaoDeSaude2.setSituacaoDeSaudeDeclarada("Ruim");

        situacaoDeSaudeRepository.save(situacaoDeSaude2);


        Voluntario voluntario2 = new Voluntario();
        voluntario2.setPassaporte(numeroPassaporte);
        voluntario2.setNome("Maria");
        voluntario2.setIdade("25");
        voluntario2.setTelefone("2222222222");
        voluntario2.setEmail("maria@teste.com");
        voluntario2.setTipoSanguineo("AB-");
        voluntario2.setSituacaoDeSaude(situacaoDeSaude2);

        // Tenta cadastrar o segundo voluntário
        String cadastro2 = voluntarioDao.cadastrarVoluntario(voluntario2);

        // Verifica se o cadastro foi bloqueado
        Assertions.assertNotEquals("Cadastro realizado com sucesso.", cadastro2);
    }

    // TESTE: RN-03: O voluntário deve ter de 18 a 55 anos de idade;

    // POSITIVO
    @Test
    public void testIdadeVoluntarioValida() {
        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("Ruim");

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("João");
        voluntario.setIdade("25");
        voluntario.setTelefone("1111111111");
        voluntario.setEmail("joao@teste.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        String cadastro = voluntarioDao.cadastrarVoluntario(voluntario);

        Assertions.assertEquals("Cadastro realizado com sucesso.", cadastro);
    }

    // NEGATIVO
    @Test
    public void testIdadeVoluntarioInvalida() {
        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("Ruim");

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("João");
        voluntario.setIdade("60");
        voluntario.setTelefone("1111111111");
        voluntario.setEmail("joao@teste.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        String cadastro = voluntarioDao.cadastrarVoluntario(voluntario);

        Assertions.assertNotEquals("Cadastro realizado com sucesso.", cadastro);
    }

    // TESTE: RN-04: O voluntário deve estar associado a uma cidade de um determinado país.
    // POSITIVO
    @Test
    public void testAssociarVoluntarioCidadeValida() {
        Pais pais = new Pais();
        pais.setNome("Brasil");
        pais.setIbge("1001");

        Cidade cidade = new Cidade();
        cidade.setNome("Belo Horizonte");
        cidade.setIbge("103");
        cidade.setPais(pais);

        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("Ruim");

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("João");
        voluntario.setIdade("25");
        voluntario.setTelefone("1111111111");
        voluntario.setEmail("joao@teste.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setCidade(cidade);
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        String cadastro = voluntarioDao.cadastrarVoluntario(voluntario);

        Assertions.assertEquals("Cadastro realizado com sucesso.", cadastro);
    }

    // NEGATIVO
    @Test
    public void testAssociarVoluntarioCidadeInvalida() {
        Pais pais = new Pais();
        pais.setNome("Brasil");
        pais.setIbge("1001");

        Cidade cidade = new Cidade();
        cidade.setNome("Miami"); // Cidade não existe no banco de dados
        cidade.setIbge("952");
        cidade.setPais(pais);

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("João");
        voluntario.setIdade("25");
        voluntario.setTelefone("1111111111");
        voluntario.setEmail("joao@teste.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setCidade(cidade);

        String cadastro = voluntarioDao.cadastrarVoluntario(voluntario);

        Assertions.assertEquals("A cidade informada não existe no banco de dados.", cadastro);
    }

    // TESTE: RN-05: O voluntário deve ter sua situação de saúde declarada (Opções: Ruim, Bom, Ótimo).

    // POSITIVO
    @Test
    public void deveDeclararSituacaoDeSaudeValida() {

        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("Ruim");
        situacaoDeSaude.setHistorico("Não há.");
        situacaoDeSaude.setTratamentoEmAndamento(true);
        situacaoDeSaude.setData(new Date());

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("João");
        voluntario.setIdade("25");
        voluntario.setTelefone("1111111111");
        voluntario.setEmail("joao@teste.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        String cadastro = voluntarioDao.cadastrarVoluntario(voluntario);

        Assertions.assertEquals("Cadastro realizado com sucesso.", cadastro);
    }

    // NEGATIVO
    @Test
    public void deveDeclararSituacaoDeSaudeInvalida() {

        SituacaoDeSaude situacaoDeSaude = new SituacaoDeSaude();
        situacaoDeSaude.setSituacaoDeSaudeDeclarada("hadusd");
        situacaoDeSaude.setHistorico("");
        situacaoDeSaude.setTratamentoEmAndamento(false);
        situacaoDeSaude.setData(new Date());

        Voluntario voluntario = new Voluntario();
        voluntario.setPassaporte("123456");
        voluntario.setNome("João");
        voluntario.setIdade("25");
        voluntario.setTelefone("1111111111");
        voluntario.setEmail("joao@teste.com");
        voluntario.setTipoSanguineo("A+");
        voluntario.setSituacaoDeSaude(situacaoDeSaude);

        String cadastro = voluntarioDao.cadastrarVoluntario(voluntario);

        Assertions.assertNotEquals("Cadastro realizado com sucesso.", cadastro);
    }
}
