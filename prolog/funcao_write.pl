animal(gato).
animal(cachorro).
animal(pato).

sons(gato, miau).
sons(cachorro, auau).
sons(pato, quack).

emitir_som(X) :-
    animal(X),
    (   X = gato -> write('O gato faz: miau')
    ;   X = cachorro -> write('O cachorro faz: auau')
    ;   X = pato -> write('O pato faz: quack')
    ;   write('Animal desconhecido')
    ).
