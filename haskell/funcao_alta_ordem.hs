dobra :: Int -> Int
dobra x = x + 2

quadrado :: Int -> Int
quadrado x = x * x

mapTo :: [Int] -> (Int -> Int) -> [Int]
mapTo [] _ = []
mapTo (h:t) fn = fn h : mapTo t fn

pares :: Int -> Bool
pares x = mod x 2 == 0

impares :: Int -> Bool
impares x = mod x 2 == 1

filtro :: [Int] -> (Int -> Bool) -> [Int]
filtro [] _ = []
filtro (h:t) fn = if fn h
    then h : filtro t fn
    else filtro t fn

maior :: Int -> Int -> Bool
maior a b = a > b

menor :: Int -> Int -> Bool
menor a b = not (maior a b)

buscaLista :: (Int -> Int -> Bool) -> [Int] -> Int
buscaLista _ [] = -1
buscaLista _ [n] = n
buscaLista fn (h:t) = if fn h x then h else x
    where
        x = buscaLista fn t

numeros :: [Int]
numeros = [1, 5, 10, 15, 20]

-- (< 10) funciona como contrutor de função, é uma função parcialmente aplicada
-- Filtrar números menores que 10
numerosMenoresQue10 :: [Int]
numerosMenoresQue10 = filter (< 10) numeros

-- Adicionar 10 a cada número
numerosMais10 :: [Int]
numerosMais10 = map (+ 10) numeros