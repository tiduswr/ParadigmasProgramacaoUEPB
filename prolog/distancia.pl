speed(ford, 100).
speed(volvo, 80).
time(ford, 20).
time(volvo, 24).

distance(X, Y) :- speed(X, VEL),
                  time(X, TIME),
                  Y is VEL * TIME.