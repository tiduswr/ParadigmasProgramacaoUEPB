parente(carlos, joao).
parente(maria, joao).
parente(joao, lisa).
parente(sara, lisa).
parente(marcos, carlos).
parente(zefa, carlos).
parente(marcos, alberto).
parente(alberto, leon).
parente(juliana, marcos).

homem(carlos).
homem(joao).
homem(marcos).
homem(alberto).
mulher(zefa).
mulher(maria).
mulher(lisa).
mulher(sara).
mulher(juliana).

avo(X, Y) :- parente(X, A), parente(A, Y), homem(X).
avoh(X, Y) :- parente(X, A), parente(A, Y), mulher(X).
mae(X, Y) :- parente(X, Y), mulher(X).
pai(X, Y) :- parente(X, Y), homem(X).
irmao(X, Y) :- parente(A, X), parente(A, Y), X \= Y.
tio(X, Y) :- parente(A, Y), irmao(A, X), homem(X). 
tia(X, Y) :- parente(A, Y), irmao(A, X), homem(Y). 

avo_paterno(X, Y) :- parente(X, A), pai(A, Y), homem(X).
bisavo(X, Y) :- avo(X, A), parente(A, Y).

% Recursão
% ancestral(X, Y) :- parente(X, Y).
% ancestral(X, Y) :- parente(X, A), ancestral(A, Y).
ancestral(X, Y) :- parente(X, Y); (parente(X, A), ancestral(A, Y)).