## Uso no linux:
```shell
# ; para todas as possibilidades
swipl -s caminho.ps

shell(comand)

trace # / notrace / nodebug

halt. #sair

consult('file.pl'). #carregar arquivo

```

## Exemplo Introdutório:
```prolog
fala(maria, espanhol).
fala(joao, ingles).
fala(joao, espanhol).
fala(jose, ingles).
fala(jambo, ingles).

conversa(P1, P2) :- fala(P1, L), fala(P2, L), P1 \= P2.
```

## Exemplo Operador corte:
Obs: Use trace (notrace / nodebug) para mostrar o backtracking
```prolog
ensina(joao, algoritmos).
ensina(joao, prolog).
ensina(bruno, redes).
aprende(maria, redes).
aprende(carlos, prolog).
aprende(mario, prolog).
aprende(jose, algoritmos).
aprende(mandela, algoritmos).

aluno1(PROFESSOR, ALUNO, DISCIPLINA) :- ensina(PROFESSOR, DISCIPLINA), aprende(ALUNO, DISCIPLINA).

aluno2(PROFESSOR, ALUNO, DISCIPLINA) :- ensina(PROFESSOR, DISCIPLINA), !, aprende(ALUNO, DISCIPLINA).
```
## Operadores Lógicos
```
X > Y  | X é maior do que Y
X < Y  | X é menor do que Y 
X >= Y | X é maior ou igual a Y
X =< Y | X é menor ou igual a Y 
X == Y | X é igual a Y
X = Y  | X unifica com Y 
X \= Y | X é diferente de Y
\+ X   | X é negado 
not(X) | X é negado
```

## Exemplo Negação:
```prolog
idade(joao, 25).
idade(maria, 32).
idade(pedro, 40).
idade(ana, 55).
idade(marcelo, 72).

jovem(Pessoa) :-
    idade(Pessoa, Idade),
    not(Idade >= 50).
```
## Operadores Aritméticos
```
+   | adição
-   | subtração
*   | multiplicação
/   | divisão
//  | divisão inteira
mod | resto divisão inteira |
**  | potência
is  | atribuição
```

## Exemplo Aritmética 1 com funcao Write:
```prolog
% nl é newLine
% O lado esquerdo da atribuição 'is' precisa não ter sido declarada
% o lado direito só pode ser processado entre variáveis ja declaradas
% então Sum is Sum + 10 não é válido
calculoSimples :- 
    B is 17,
    C is 10,
    A is B / 17 + C,
    write('O resultado do cálculo é: '), write(A), nl.

somar(A, B) :- 
    RESULT is A + B,
    write('O resultado do cálculo é: '), write(RESULT), nl.

% o operador = apenas guarda o lado direito da atribuição
% dentro da variavel do lado esquerdo
concatenar(A, B) :-
    RESULT = A + B,
    write('O resultado é: '), write(RESULT), nl.
``` 

## Exemplo Aritmética 2:
```prolog
fatorial(0, 1) :- !. % ! previne o backtracking
fatorial(X, R) :- X > 0, NEXT is X-1, fatorial(NEXT, R2), R is X * R2.
```