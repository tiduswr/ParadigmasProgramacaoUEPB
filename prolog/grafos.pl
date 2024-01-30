aresta(a, b, 2).
aresta(a, d, 7).
aresta(a, c, 3).
aresta(c, d, 1).
aresta(d, b, 5).

caminho(X, Y, P) :- aresta(X, Y, P).
caminho(X, Y, P) :- aresta(X, A, P1),
                    caminho(A, Y, P2),
                    P is P1 + P2.