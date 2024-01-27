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

avo(X, Y) :- parente(X, A), parente(A, Y).
mae(X, Y) :- parente(X, Y), mulher(X).
pai(X, Y) :- parente(X, Y), homem(X).
irmao(X, Y) :- parente(A, X), parente(A, Y), X \= Y.
tio(X, Y) :- parente(A, Y), irmao(A, X), homem(X). 
tia(X, Y) :- parente(A, Y), irmao(A, X), homem(Y). 