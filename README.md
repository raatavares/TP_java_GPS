# Project Title

## Contents

- [Team](#team)
- [Vision and Scope](#vision-and-scope)
- [Requirements](#requirements)
    - [Use case diagram](#use-case-diagram)
    - [User stories and prototypes](#user-stories-and-prototypes)
- [Architecture and Design](#architecture-and-design)
    - [Domain Model](#domain-model)
- [Implementation](#implementation)
    - [Product Increment 1](#product-increment-1)
    - [Product Increment 2](#product-increment-2)
    - [Product Increment 3](#product-increment-3)
    - [Product Increment 4](#product-increment-4)

## Team

- Leonardo Silva - 2021135858
- Ricardo Tavares - 2021144652
- Daniel Bravo - 2021137795
- Mário Lourenço - 2021129732
- Sandra Perdigão - 2019102697

***

## Vision and Scope

#### Problem Statement

##### Project background

Atualmente, os Lares de Idosos enfrentam um desafio significativo: a disparidade entre o número de residentes idosos e a quantidade de funcionários disponíveis. Isto resulta em dificuldades na prestação adequada de cuidados individualizados a cada idoso. Ao longo dos anos, esta lacuna tem-se agravando devido ao envelhecimento da população e à crescente procura por serviços de cuidados a idosos, enquanto a oferta de profissionais de saúde e cuidadores não tem acompanhado este aumento. Isto resulta numa sobrecarga de trabalho para os funcionários, o que, por sua vez, pode levar a erros na administração de medicamentos, problemas na alimentação dos idosos e a não conformidade com dietas recomendadas. Sem um sistema de gestão adequado, o bem-estar dos idosos pode estar comprometido.

Nesse contexto, um software de gestão especializado para Lares de Idosos desempenha um papel vital. Além de melhorar a
gestão das necessidades dos idosos e a eficiência dos funcionários, ele também garante a administração precisa de
medicamentos e alimentos, proporcionando assim uma qualidade de vida mais elevada para os idosos e tranquilidade para os
seus entes queridos. Portanto, investir em tecnologia de gestão é essencial para enfrentar os desafios atuais e garantir
um futuro mais seguro e satisfatório para a população idosa nas instituições de cuidados.

_This section contains a summary of the problem that the project will solve. It should provide a brief history of the
problem and an explanation of how the organization justified the decision to build software to address it. This section
should cover the reasons why the problem exists, the organization's history with this problem, any previous projects
that were undertaken to try to address it, and the way that the decision to begin this project was reached._

##### Stakeholders

1. Lar de Idosos

- Departamento Clínico: Necessidade de um sistema de gestão que ajude a administrar eficazmente os cuidados de saúde aos utentes, incluindo registos médicos, administração de medicamentos e acompanhamento de condições de saúde.

- Departamento Culinario: Necessidades relacionadas à gestão de dietas e preferências alimentares dos utentes, bem como alergias alimentares dos utentes e planeamento de refeições individualizado.

- Departamento Financeiro: Necessidades relacionadas à gestão financeira do lar, incluindo informação da prestação de cada utente, controlo de custos, etc.

2. Familiares dos utentes

- Comunicação com o Lar de Idosos: Necessidade de acesso fácil a informações sobre o bem-estar e atendimento aos seus entes queridos, bem como uma linha de Comunicação direta com a equipa do lar.

3. Funcionários

- Gestão dos utentes: Necessidade de uma ferramenta que simplifique o acompanhamento das necessidades indiviuais dos utentes, alocação de tarefas e comunicação interna.

4. Utentes

- Melhores condições: Necessidade de um ambiente seguro, confortável e bem administrado, que inclua cuidados de saúde adequados, nutrição adequada, e atenção personalizada para melhorar a sua qualidade de vida.

_This is a bulleted list of the stakeholders. Each stakeholder may be referred to by name, title, or role ("support
group manager," "CTO," "senior manager"). The needs of each stakeholder are described in a few sentences.

##### Users

1. Lar de Idosos

- Departamento Clínico: Necessidade de um sistema para conter a informação dos medicamentos prescritos para os diferentes utentes, horário de administração, registo de incidentes médicos ou problemas de saúde dos utentes, para um acompanhamento adequado.

- Departamento Culinario: Necessidade de saberas quantidades e tipos de comida a serem preparados a cada dia, levando em consideração alergias alimentares e dietas específicas dos utentes.

- Departamento Financeiro: Necessidade de um sistema que controle as despesas da instituição, incluindo pagamento a funcionários, despesas com alimentação, e outros custos operacionais. Necessidade de registar os pagamentos recebidos por parte dos utentes/ familiares para garantir um registo preciso das finanças do lar.

2. Familiares dos utentes

- Necessidade de um sistema que forneça um chat ou estado do processo do utente, permitindo uma comunicação direta com a equipa do lar. Interesse em informações atualizadas sobre a condição e o bem-estar do utente, para tranquilidade e acompanhamento dos familiares.

3. Funcionários

- Necessidade de um registo que contenha informações sobre as medicações que cada utente precisa de tomar, garantido uma administração precisa. Interesse em que esteja disponível informação sobre cuidados específicos necessários para cada utente, assim como acesso ao horário de visistas dos utentes para coordenar as interações familiares e garantir o bem-estar dos idosos.

_This is a bulleted list of the users. As with the stakeholders, each user can either be referred to by name or role ("
support rep," "call quality auditor," "home web site user")however, if there are many users, it is usually inefficient
to try to name each one. The needs of each user are described._

***

#### Vision & Scope of the Solution

##### Vision statement

O objetivo deste projeto é revolucionar a Gestão de Lares de Idosos, para isso será necessário desenvolver um software bastante completo e complexo. Este software não só melhorará a qualidade de vida dos idosos residentes em lares, como também permitirá aos funcionários/utilizadores realizarem as suas tarefas mais acertadamente uma vez que será possivel aceder aos mais diversos dados relativos a cada idoso facilitando o seu trabalho.
Atendendo a estas necessidades críticas dos funcionários e idosos pretende-se ajudar a criar um ambiente mais acolhedor para os residentes de lares.

_The goal of the vision statement is to describe what the project is expected to accomplish. It should explain what the
purpose of the project is. This should be a compelling reason, a solid justification for spending time, money, and
resources on the project. The best time to write the vision statement is after talking to the stakeholders and users and
writing down their needs; by this time, a concrete understanding of the project should be starting to jell._

##### List of features

**Gestão de Medicamentos**

- Providenciar uma plataforma segura na qual os funcionários responsáveis pelos idosos possam verificar a medicação a ser tomada.
- Manter um registo detalhado dos horários da toma dos medicamentos.
- Alertas para os horários de administração de medicamentos.

**Gestão Dietética**

- Planos de refeições personalizados para os idosos tendo em conta as suas restrições dietéticas e alergias.

**Gestão de Pessoal**

- Manter uma base de dados com todos os funcionários do lar,contendo as suas funções e horários.
- Permitir que todos os funcionários tenham acesso aos registos das informações sobre os idosos

**Gestão de Perfis dos Residentes**

- Criar perfis para novos residentes, incluido alergias, preferências ao nivel da alimentação e informações pessoais.
- Armazenar na base de dados fotografias e documentação importante relacionados aos residentes.

**Alertas e Notificações**

- Envio de alertas aquando a realização de eventos críticos, aniversários, erros de medicação, entre outros.


_This section contains a list of features. A feature is as a cohesive area of the software that fulfills a specific need
by providing a set of services or capabilities. Any software package, in fact, any engineered product, can be broken
down into features. The project manager can choose the number of features in the vision and scope document by changing
the level of detail or granularity of each feature, and by combining multiple features into a single one. Sometimes
those features are small ("screw-top cap," "holds one liter of liquid"); sometimes they are big ("four-wheel drive," "
seats seven passengers"). It is useful to describe a product in about 10 features in the vision and scope document,
because this usually yields a level of complexity that most people reading it are comfortable with. Adding too many
features will overwhelm most readers._

_Each feature should be listed in a separate paragraph or bullet point. It should be given a name, followed by a
description of the functionality that it provides. This description does not need to be detailed; it can simply be a few
sentences that give a general explanation of the feature. However, if there is more information that a stakeholder or
project team member feels should be included, it is important to include that information. For example, it is sometimes
useful to include a use case (see Chapter 6), as long as it is written in such a way that all of the stakeholders can
read and understand it._

##### Features that will not be developed

- Não incluiremos a funcionalidade de processar pagamentos para funcionários, fornecedores, utentes.

- Não abordaremos a gestão de stock de alimentos.

- Não haverá integração com sistemas externos de saúde ou segurança social.

- Não iremos desenvolver um sistema de diálogo entre funcionários, familiares e utentes.



_Features are often left out of a project on purpose. When a feature is explicitly left out of the software, it should be
added to this section to tell the reader that a decision was made to exclude it. For example, one way to handle an
unrealistic deadline is by removing one or more features from the software, in which case the removed features should be
moved into this section. The reason these features should be moved rather than deleted from the document is that
otherwise, readers might assume that they were overlooked and bring them up in a review. This is especially important
during the review of the document because it allows everyone to agree on the exclusion of the feature (or object to it)._

##### Risk

1. Risco de violações de segurança de dados que podem comprometer informações sensíveis dos idosos.
2. Risco de resistência por parte dos funcionários e da equipa de cuidados aos idosos em relação à adoção da nova tecnologia.
3. Risco de dados incorretos ou imprecisos devido a erros de entrada por parte dos utilizadores autorizados.
4. Risco de falhas técnicas na aplicação que poderiam interromper o funcionamento.

_Risks are potential uncertainties or adverse events that can impact the achievement of objectives and require proactive
management to mitigate or minimize their negative effects._

##### Assumptions

1. A primeira versão da aplicação será um protótipo disponível como uma aplicação de desktop.
2. A aplicação não recolhe dados por si só, em vez disso, os utilizadores autorizados para cada secção inserem informações dos utentes, das finanças do lar, etc. 
3. Parte-se do pressuposto de que os utilizadores inserirão dados fiáveis e precisos apensar das verificações impostas pelo software. 
4. A aplicação vai ser desenvolvida para uma única instituição de apoio a idosos que contém 5 funcionários e 20 residentes, cada um com informações únicas. 
5. Supõe-se que os utilizadores serão treinados e orientações para usar a aplicação de forma eficaz.



_This is the list of assumptions that the stakeholders, users, or project team have made. The team should hold a
brainstorming session to come up with a list of assumptions. (See Chapter 3 for more information on assumptions.)_

***

## Requirements

#### Use Case Diagram

![Use case diagram](imgs/UML_use_case_example-800x707.png)

***

##### Use Case 1

- Actor: Actor x
- Description: Description of use case number 1
- Preconditions:
    - Precondition 1
    - Precondition 2
    - Without preconditions
- Postconditions:
    - Postcondition 1
    - Postcondition 2
    - Without postcondition
- Normal flow:
    - The user ...
    - The user ...
- Alternative flows:
    - The user ...
    - The user ...

***

##### Use Case 2

***

##### Use Case 3

***

#### User Stories and Prototypes

***

##### User Story 1

As ..., I want ..., so ...

###### Acceptance Criteria

```
An acceptance test should be written here
```

###### Prototype

A prototype of user story 1 should be here. You can see in (#use-case-diagram) how to import an image.

***

##### User Story 2

***

##### User Story 3

***

## Architecture and Design

#### Domain Model

A domain model should be here. You can see in (#use-case-diagram) how to import an image.

***

## Implementation

#### Product Increment 1

##### Sprint Goal

The sprint goal was ...

##### Planned vs Implemented

For this iteration we planned to implement the:

- Feature 1
- Feature 2

For this iteration we implemented the:

- Feature 1
- Feature 2

##### Sprint Retrospective

- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C

***

#### Product Increment 2

***

#### Product Increment 3

***

#### Product Increment 4

***
