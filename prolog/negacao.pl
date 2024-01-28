tipo(tidus, humano).
tipo(auron, humano).
tipo(seymour, humano).
tipo(kinoc, humano).

tipo(bomb, monstro).
tipo(one-eye, monstro).

vilao(seymour).
vilao(kinoc).

mesmoTipo(X, Y) :- tipo(X, A), tipo(Y, A), X \= Y.
ehAmigo(X, Y) :- mesmoTipo(X, Y), (not(vilao(X)), not(vilao(Y))); 
                (vilao(X); tipo(X, monstro) -> write('monstro')), (vilao(Y); tipo(Y, monstro) -> write('monstro')).