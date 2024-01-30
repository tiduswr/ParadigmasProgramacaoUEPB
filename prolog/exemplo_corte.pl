ensina(joao, algoritmos).
ensina(joao, prolog).
ensina(bruno, redes).
aprende(maria, redes).
aprende(carlos, prolog).
aprende(jose, algoritmos).

aluno1(X, Y, A) :- ensina(X, A), aprende(Y, A).

aluno2(X, Y, A) :- ensina(X, A), !, aprende(Y, A).