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