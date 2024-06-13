# Correlacionador

Este projeto é um programa Java para correlacionar codigos de um banco de dados, permitindo ao usuário ordenar dados por diferentes colunas e buscar informações específicas. O programa oferece um menu com quatro opções:

1. Ordenar código de natureza de rendimento (BubbleSort).
2. Ordenar código de serviço (SelectionSort).
3. Retornar código de natureza por código de serviço.
4. Encerrar o programa.

## Requisitos

- Java JDK 8 ou superior.
- Java JDBC (Necessario para conexão entre seu banco de dados em SQL com a IDE utilizada).
  
## Organização do Projeto

O projeto consiste nos seguintes arquivos:

- `Correlacionador.java`: O arquivo principal que contém o código Java para processar o banco de dados.

## Como Executar

### Preparação do Ambiente

1. **Instalar o JDK 8**: Baixe e instale o JDK 8 do site oficial da Oracle ou de um mirror confiável.
2. **Configurar as Variáveis de Ambiente**: Defina `JAVA_HOME` para o diretório de instalação do JDK 8 e adicione `%JAVA_HOME%\bin` ao `Path`.

### Compilação e Execução

1. Clone este repositório ou copie os arquivos para seu ambiente local.
2. Navegue até o diretório do projeto onde o arquivo `Correlacionador.java` está localizado.
3. Crie um banco de dados em MySQL através do arquivo que se encontra neste GitHub meu_banco_de_dados.
4. Coloque seu caminho para o banco de dados em SQL no mesmo diretório do arquivo `Correlacionador.java` e renomeie-o para `seu banco de dados`, ou ajuste o caminho do arquivo no código, informe usuario e senha correta do seu banco em SQL.
5. Compile o código:

    ```sh
    javac --release 8 Correlacionador.java.java
    ```

6. Execute o programa:

    ```sh
    java Correlacionador.java
    ```

### Usando o Programa

1. Após executar o programa, você verá um menu com quatro opções:

    ```plaintext
    Selecione uma opção:
    1. Ordenar código de natureza de rendimento (BubbleSort)
    2. Ordenar código de serviço (SelectionSort)
    3. Retornar código de natureza por código de serviço
    4. Encerrar o programa
    ```

2. Escolha uma opção digitando o número correspondente e pressione Enter.

### Funcionalidades

- **Ordenar código de natureza de rendimento (BubbleSort)**: Ordena o CSV pela primeira coluna usando o algoritmo BubbleSort e imprime o resultado.
- **Ordenar código de serviço (SelectionSort)**: Ordena o CSV pela segunda coluna usando o algoritmo SelectionSort e imprime o resultado.
- **Retornar código de natureza por código de serviço**: Solicita ao usuário um código de serviço, procura por ele na segunda coluna e retorna o código de natureza correspondente da primeira coluna. Se o código de serviço não for encontrado, informa que o código não existe.
- **Encerrar o programa**: Finaliza a execução do programa.

