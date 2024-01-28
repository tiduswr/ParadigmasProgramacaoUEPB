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