# Projeto de Aprendizagem do Curso de Spring e Testes

## Documento Oficial do Projeto

<img src="imgs/DOC-AVALIACAO.png" width="70%" alt="Gif mostrando a execução do projeto.">

## Descrição do Projeto

Este projeto tem como objetivo implementar um sistema de cadastro para ajuda humanitária da organização "Médicos Sem Fronteiras" (MSF). A MSF é uma organização que apoia a saúde pública em países que enfrentam crises decorrentes de guerras e outras situações políticas adversas. Através do fornecimento de estrutura para atendimento médico, com hospitais de campanha e profissionais de saúde voluntários, como médicos e enfermeiros, a organização presta assistência nessas regiões.

O cadastro para se tornar um voluntário da MSF é realizado através do site da organização. Quando ocorre uma solicitação de ajuda internacional, os profissionais são enviados até determinada cidade de um país e prestam atendimento médico de forma voluntária à população local. Para se cadastrar como voluntário, é necessário fornecer alguns dados e atender a alguns critérios.

## Regras de Negócio

1. O voluntário deve ter os seguintes dados cadastrados (todos obrigatórios): Passaporte, Nome Completo, Idade, Telefone, E-mail e Tipo Sanguíneo.
2. Um voluntário não pode ser cadastrado se já tiver um passaporte com o mesmo número no sistema.
3. O voluntário deve ter de 18 a 55 anos de idade.
4. O voluntário deve estar associado a uma cidade de um determinado país.
5. O voluntário deve ter sua situação de saúde declarada (Opções: Ruim, Bom, Ótimo).

## Entidades do Sistema

Para simplificar a modelagem de dados, o sistema possui as seguintes entidades:

- Voluntário: Representa um profissional de saúde interessado em se tornar voluntário da MSF.
- Situação de Saúde: Enumeração com as opções de situação de saúde do voluntário (Ruim, Bom, Ótimo).
- Cidade: Representa uma cidade associada a um determinado país.
- País: Representa um país onde a MSF atua.

## Implementação

O projeto será desenvolvido utilizando o Framework Spring, incluindo as seguintes tecnologias:

- Spring Boot: Para configuração e inicialização da aplicação.
- Spring MVC: Para desenvolvimento dos controladores (Controllers) que irão gerenciar as requisições HTTP.
- Spring Data JPA: Para implementar os repositórios de acesso aos dados do banco de dados.

Serão criadas classes Model, Repository e Controller para as entidades do sistema, bem como classes de teste utilizando JUnit e Spring para garantir o correto funcionamento das funcionalidades implementadas.

## Testes

Os testes serão realizados para garantir o correto funcionamento das regras de negócio e das funcionalidades implementadas no sistema. Serão considerados, pelo menos, 2 cenários (positivo e negativo) para cada regra de negócio. Os testes serão organizados em classes específicas para cada entidade do sistema: Voluntário, Cidade e País.

## Instruções para Teste

Para executar os testes automatizados, foram criadas classes de teste para cada entidade do sistema. Essas classes podem ser encontradas no pacote de testes correspondente a cada entidade. Os testes foram organizados da seguinte forma:

### Voluntário

- Testes de Repository:
    - Cenário Positivo: Verifica se é possível cadastrar um novo voluntário no sistema sem conflito de passaportes.
    - Cenário Negativo: Verifica se o sistema impede o cadastro de um voluntário com o mesmo passaporte de outro já cadastrado.

- Testes de Controller:
    - Cenário Positivo: Verifica se o cadastro de um novo voluntário através do Controller é realizado com sucesso.
    - Cenário Negativo: Verifica se o Controller trata corretamente o cadastro de um voluntário com passaporte já existente.

### Cidade

- Testes de Repository:
    - Cenário Positivo: Verifica se é possível cadastrar uma nova cidade no sistema.
    - Cenário Negativo: Verifica se o sistema impede o cadastro de uma cidade com informações inválidas.

- Testes de Controller:
    - Cenário Positivo: Verifica se o cadastro de uma nova cidade através do Controller é realizado com sucesso.
    - Cenário Negativo: Verifica se o Controller trata corretamente o cadastro de uma cidade com informações inválidas.

### País

- Testes de Repository:
    - Cenário Positivo: Verifica se é possível cadastrar um novo país no sistema.
    - Cenário Negativo: Verifica se o sistema impede o cadastro de um país com informações inválidas.

- Testes de Controller:
    - Cenário Positivo: Verifica se o cadastro de um novo país através do Controller é realizado com sucesso.
    - Cenário Negativo: Verifica se o Controller trata corretamente o cadastro de um país com informações inválidas.

## Execução dos Testes

Os testes automatizados foram executados e as evidências estão disponíveis no arquivo "testes.png" na pasta raiz do projeto.

## Misc

Dados para criar um novo voluntário:

```json
{
    "passaporteId": "BB0002",
    "nomeCompleto": "Ademar Castro",
    "idade": 19,
    "telefone": "+55 (92) 90000-0000",
    "email": "ademar@gmail.com",
    "tipoSanguineo": "O-",
    "situacaoSaude": "RUIM",
    "cidade": {
        "id": 2
    }
}
```

### Conclusão 🎈

O projeto demonstrou a importância dos testes automatizados para garantir a qualidade e a confiabilidade de uma aplicação e como o ecossistema Spring pode ser utilizado para simplificar o desenvolvimento e a implementação desses testes.
