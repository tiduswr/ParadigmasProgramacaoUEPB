fatorial(0, 1) :- !. % ! previne o backtracking
fatorial(X, R) :- X > 0, NEXT is X-1, fatorial(NEXT, R2), R is X * R2.

fibonacci(0, 0) :- !.
fibonacci(1, 1) :- !.
fibonacci(N, R) :- 
    N > 1,
    N1 is N - 1,
    N2 is N - 2,
    fibonacci(N1, R1),
    fibonacci(N2, R2),
    R is R1 + R2.