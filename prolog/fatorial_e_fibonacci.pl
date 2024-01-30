fatorial(0, 1) :- !. % ! previne o backtracking
fatorial(X, R) :- X > 0, NEXT is X-1, fatorial(NEXT, R2), R is X * R2.

fibonacci(N, R) :- (N =< 1 -> R is N; fibonacci(N-1, R1), fibonacci(N-2, R2), R is R1 + R2).