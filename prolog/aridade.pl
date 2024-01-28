%Aridade 3
parente(marcos, maria, enzo).
parente(marcos, maria, vitoria).

%Aridade 2
amigo(enzo, vitor).
amigo(vitoria, isabel).

%Aridade 1
homem(enzo).
homem(vitor).
homem(marcos).
mulher(maria).
mulher(vitoria).
mulher(isabel).

irmaos(X,Y) :- parente(A, B, X), parente(A, B, Y), X \= Y.
casados(X, Y) :- parente(X, Y, _).