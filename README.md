# Compiladores

#### compilando um arquivo no javaCC:
```
javacc arquivo.jj
```
#### links:<br>
[MiniJava](https://www.cambridge.org/resources/052182060X/)<br>
[javaCC](https://javacc.github.io/javacc/)<br>
[pdf ensinando](https://www.cin.ufpe.br/~in1007/transparencias/MaterialApoio/javacc-tutorial.pdf)<br>

# Team 8

### Análise Léxica e Sintática
A etapa foi completamente concluída, apresentando a extração de tokens do programa e o teste sintático do programa. O programa foi testado para todas as entradas de exemplo do [site do MiniJava](https://www.cambridge.org/resources/052182060X/) que ficam na seção __*Sample MiniJava Programs*__. Nenhum erro foi encontrado usando esses arquivos como entrada. As maiores dificuldades durante a implementação dessa etapa foi resolver ambiguidades sem usar o "LOOKAHEAD", pois não sabíamos como fazer isso de outra forma. Durante essa fase, os dois membros da equipe trabalharam em conjunto tanto na especificação dos tokens, quanto na escrita da gramática para o funcionamento do parser.

### Árvore Sintática Abstrata e Análise Semântica 