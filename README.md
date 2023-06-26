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
A etapa foi completamente concluída, construção da ATS, das tabelas e dos visitors feitas. O programa foi testado para as entradas disponíveis no [site do MiniJava](https://www.cambridge.org/resources/052182060X/) e por testes escritos manualmente pelos membros da equipe, para checar se os erros semânticos eram apontados. Nenhum erro foi encontrado usando esses arquivos como entrada. A maior dificuldade da equipe foi definir o visitor de TypeChecking, pois vários erros foram surgindo e para consertar demandava muito tempo de análise. Outra dificuldade foi saber exatamente como definir ações semânticas no gerador de parser. Durante essa fase, os dois membros da equipe trabalharam em conjunto em todas as etapas do processo.

### Geração do Código Intermediário (IR Tree)
A etapa foi completamente concluída, seguindo as etapas definidas pelo professor: Incorporamos as classes, comentamos todas as partes do código que chamavam a classe CodeGen e resolvemos todas as dependências, tirando a de spilling, porém essa não apresentou diferença no resultado final da etapa. Além disso, implementamos um IRVisitor para gerar o código IRTree, fizemos isso usando as classes disponibilizadas no framework do compilador MiniJava. O programa foi testado para todos os arquivos dispníveis no site do MiniJava, tirando o TreeVisitor.txt, pois o mesmo retorna uma excessão na fase do frontend (o próprio arquivo diz que uma excessão é retornada, portanto não faz sentido testar esse arquivo) e em todos os testes o debug funcionou, printando o processo de tradução. Nenhum erro foi encontrado nos testes. Nossas dificuldades durante a etapa foram: disponibilização de classes muito confusas, isto é, nos primeiros momentos estávamos perdidos sobre o que fazer e onde colocar cada classe, tendo que recorrer diversas vezes ao livro da disciplina e mesmo assim, muitas partes do código, principalmente o arquivo MipsFrame.java e até algumas funções da própria classe abstrata Frame não foram entendidas. Porém, o livro ajudou bastante esclarecendo o que significava cada classe e ajudou bastante na lógica de construção da árvore. Durante essa fase, os dois membros da equipe trabalharam em conjunto.  

### Seleção de Instruções
A etapa foi completamente concluída, com a implementação da árvore canônica, blocos e traços além da integração desses com a classe Codegen que também foi implementada nessa fase. O programa foi testado para algumas entradas fornecidas pelo site do framework e nesses testes nenhum erro foi encontrado. As principais dificuldades foram centradas no módulo CodeGen, pois apresentamos dificuldade em tratar as instruções e boa parte da árvore canônica foi fornecida pelo framework. Os três membros (Adição do Lucas nessa etapa) trabalharam em conjunto para a realização dessa fase. 