## Exemplo Introdutório:
```prolog
fala(maria, espanhol).
fala(joao, ingles).
fala(joao, espanhol).
fala(jose, ingles).
fala(jambo, ingles).

conversa(X, Y) :- fala(X, L), fala(Y, L), X \= Y.
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

aluno1(X, Y, A) :- ensina(X, A), aprende(Y, A).

aluno2(X, Y, A) :- ensina(X, A), !, aprende(Y, A).
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