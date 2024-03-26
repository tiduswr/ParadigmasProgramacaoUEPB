idade(joao, 25).
idade(maria, 32).
idade(pedro, 40).
idade(ana, 55).
idade(marcelo, 72).

jovem(Pessoa) :-
    idade(Pessoa, Idade),
    not(Idade >= 50).