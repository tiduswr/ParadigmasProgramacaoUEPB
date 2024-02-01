type Operation = (Int, (Int, Int))

opp :: Operation -> Int
opp z = if fst z == 1
        then fst (snd z) + snd (snd z)
        else if fst z == 2
            then fst (snd z) - snd (snd z)
            else 0

betterOppGuardas :: Operation -> Int
-- (+) funciona de forma prefixada e uncurry 
-- transforma snd z para ser usada no operador +
betterOppGuardas z | fst z == 1 = uncurry (+) (snd z) 
                   | fst z == 2 = uncurry (-) (snd z)

betterOppCasamentoPadroes :: Operation -> Int
betterOppCasamentoPadroes (1, (a, b)) = a + b
betterOppCasamentoPadroes (2, (a, b)) = a - b
betterOppCasamentoPadroes _ = 0