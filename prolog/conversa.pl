fala(maria, espanhol).
fala(joao, ingles).
fala(joao, espanhol).
fala(jose, ingles).
fala(jambo, ingles).

conversa(X, Y) :- fala(X, L), fala(Y, L), X \= Y.