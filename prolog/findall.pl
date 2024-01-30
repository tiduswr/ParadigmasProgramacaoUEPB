% Tomando a base de dados abaixo, que contém as coordenadas de algumas pessoas (a, b, c...) 
% em um mapa bidimensional. Imagine que uma delas (a) tenha uma bomba, cujo raio de ação 
% é de D metros. Como poderíamos escrever um programa que encontre uma lista de pessoas 
% que poderiam ser atingidas pela bomba portada pela pessoa “a”?

position(a, 5, 5).
position(b, 7, 5).
position(c, 2, 3).
position(d, 1, 1).
position(e, 6, 5).
position(f, 12, 7).
position(g, 8, 4).
position(h, 5, 6).
position(i, 10, 9).

distance(P1, P2, DX) :- position(P1, X1, Y1), position(P2, X2, Y2), DX is sqrt((X2 - X1)^2 + (Y2 - Y1)^2).
around(P, D, LST) :- findall(X, (distance(P,X,DX), DX =< D, P \= X), LST).