comprimento([], 0) :- !.
comprimento([_ | TAIL], LENGTH) :- comprimento(TAIL, L), LENGTH is L + 1.

insertFirst(LISTA, E, [E | LISTA]).
insertLast([], E, [E]) :- !.
insertLast([H | T], E, [H | R]) :- insertLast(T, E, R).

insertIn(L, E, 0, [E | L]) :- !.
insertIn([H | T], E, N, R) :- 
    N > 0,
    N2 is N - 1,
    insertIn(T, E, N2, R2),
    R = [H | R2].

find([X | _], 0, X) :- !.
find([_ | TAIL], N, X) :- N1 is N-1, find(TAIL, N1, X).

sum([], R) :- R is 0, !.
sum([H | T], R) :- sum(T, R2), R is R2 + H.

replace([], _, _, []) :- !.
replace([BUSCA | T1], BUSCA, SUBSTITUTO, [SUBSTITUTO | T2]) :- !, replace(T1, BUSCA, SUBSTITUTO, T2).
replace([H | T], BUSCA, SUBSTITUTO, [H | T2]) :- replace(T, BUSCA, SUBSTITUTO, T2).