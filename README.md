# 11228 - Transportation System (UVA Online Judge) - Grupo G

Este repositório contém a solução em **Java** para o problema [11228 - Transportation System](https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=2169) do UVA Online Judge.

## Sobre o Problema

O objetivo é projetar um sistema de transporte para o país de *Graphland* conectando todas as cidades com o menor custo possível (Árvore Geradora Mínima - MST). A regra principal divide as conexões com base num limiar $R$:
* **Estradas:** Conectam cidades pertencentes ao mesmo estado (distância <= R).
* **Ferrovias:** Conectam cidades de estados diferentes (distância > R).

O programa deve calcular e imprimir:
1. O número de estados formados.
2. O custo total mínimo das estradas (arredondado).
3. O custo total mínimo das ferrovias (arredondado).

## Abordagem e Implementação

A solução foi desenvolvida utilizando o **Algoritmo de Kruskal** devido à sua natureza iterativa e baseada em ordenação, que se alinha perfeitamente às regras do problema:

1. **Modelação em Grafo Completo:** Calculamos a distância Euclidiana entre todos os pares de cidades possíveis.
2. **Algoritmo de Kruskal (MST):** Processamos as arestas da menor para a maior distância. Isso garante que as estradas (distâncias curtas) sejam construídas antes das ferrovias (distâncias longas).
3. **Union-Find (Disjoint Sets):** Estrutura de dados altamente otimizada (*Path Compression* e *Union by Rank*) utilizada para gerir os grupos de cidades ("estados") e impedir a formação de ciclos na rede de transportes.
4. **Fast I/O:** Implementação de um `FastScanner` customizado com `BufferedReader` e `StringTokenizer` para garantir leitura rápida e evitar *Time Limit Exceeded (TLE)* na plataforma de juiz online.

## Como Executar

### Pré-requisitos
* [Java Development Kit (JDK)] (versão 8 ou superior).

### Passo a Passo

1. **Clone o repositório ou descarregue os ficheiros:**
   Certifique-se de ter o ficheiro principal guardado como `Main.java`.

2. **Prepare a entrada de dados:**
   Crie um ficheiro chamado `input.txt` na mesma pasta e cole os casos de teste.

3. **Compile o código-fonte:**
   Abra o terminal na pasta do projeto e execute o comando: javac Main.java
