-- Usando o conceito de funções parcialmente aplicadas

somaLista :: [Int] -> Int
somaLista = foldr (+) 0

concatenaStrings :: [String] -> String
concatenaStrings = foldr (++) ""

dobro :: Int -> Int
dobro = \x -> x * 2

dobrarLista :: [Int] -> Int
dobrarLista = foldr (\elemento acumulador -> elemento * 2 + acumulador) 0
