# Sistema de Estacionamento em Java

Bem-vindo ao meu repositório do **Sistema de Estacionamento em Java**! 🚀 Este projeto foi desenvolvido como uma aplicação prática de conceitos de Matemática Discreta e Lógica de Programação, utilizando a linguagem Java.

## Sobre

O objetivo deste projeto é demonstrar a implementação de um sistema de estacionamento via linha de comando (CLI) que integra diferentes funcionalidades:

1.  **Identificação de Estado**: Reconhece o estado de origem de um veículo com base em sua placa padrão Mercosul.
2.  **Cálculo de Tarifa**: Calcula o valor a ser pago pelo estacionamento com base em regras de tempo e taxas variáveis.
3.  **Análise Combinatória**: Aplica o princípio fundamental da contagem para determinar o número de placas possíveis.

Ele serve como um recurso educacional para ilustrar como a lógica matemática pode ser traduzida em um software funcional.

## Funcionalidades

### 1. Identificação de Estado pela Placa
- O usuário insere uma placa no formato Mercosul (`LLLNLNN`).
- O sistema valida o formato da placa usando Expressões Regulares (Regex).
- Com base nos três primeiros caracteres, o sistema identifica se a placa pertence a um dos seguintes estados: **Paraíba, Rio de Janeiro ou Piauí**.

### 2. Cálculo de Tarifa de Estacionamento
- O sistema solicita os horários de entrada e saída no formato `HH:MM`.
- O cálculo do valor é feito com base nas seguintes regras:
  - **Tolerância**: Isenção de taxa para permanência de até 15 minutos.
  - **Taxa Inicial**: Um valor fixo para as 3 primeiras horas.
  - **Taxa Adicional**: Após 3 horas, é cobrado um valor adicional a cada 20 minutos de permanência. Este valor é calculado dinamicamente.

### 3. Análise Combinatória
- O programa exibe automaticamente o número total de combinações de placas possíveis no formato `LLLNLNN` para um estado e para os três estados mapeados no sistema.

## Conceitos Aplicados

### Lógica e Matemática Discreta
- **Relações**: Mapeamento entre o prefixo da placa e o estado correspondente.
- **Análise Combinatória**: Uso do princípio multiplicativo para calcular o espaço amostral de placas.
- **Lógica Proposicional**: Estruturas condicionais (`if/else`) para aplicar as regras de negócio.

### Estruturas de Programação
- **Manipulação de Tempo**: Uso das classes `LocalTime` e `Duration` do Java para cálculos precisos de tempo.
- **Expressões Regulares (Regex)**: Para validação eficiente do formato da placa.
- **Funções e Modularização**: Divisão do código em métodos com responsabilidades únicas (`processarPlaca`, `calcularValorEstacionamento`, etc.).

## Ferramentas e Tecnologias

- **Java 17 ou superior**: Linguagem de programação utilizada.
- **IDE recomendada**: [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/).
