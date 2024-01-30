speed(ford, 100).
speed(volvo, 80).
time(ford, 20).
time(volvo, 24).

distance(X, Y) :- speed(X, VEL),
                  time(X, TIME),
                  Y is VEL * TIME.

% point(name, X, Y)
point(a, 5, 5).
point(b, 7, 5).
point(c, 2, 3).
point(d, 1, 1).
point(e, 6, 5).
point(f, 12, 7).
point(g, 8, 4).
point(h, 5, 6).
point(i, 10, 9).

distanceBetweenPoints(O, D, R) :- point(O, Xo, Yo), point(D, Xd, Yd), R is 
    sqrt((Xd - Xo)^2 + (Yd - Yo)^2).

% Exemplo de uso
% distanceBetweenPoints(a, Point, Res), Res < 5.