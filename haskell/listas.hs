-- head = index 0 da lista e tail = [...] - head
comp :: [Int] -> Int
comp [] = 0
comp (h:t) = 1 + comp t 

soma :: [Int] -> Int
soma [] = 0
soma (h:t) = h + soma t

insertFirst :: [Int] -> Int -> [Int]
insertFirst lista x = x:lista

insertIn :: [Int] -> Int -> Int -> [Int]
insertIn [] _ x = [x]
insertIn lista 0 x = x:lista
insertIn (h:t) i x = h : insertIn t (i-1) x

qsort :: [Int] -> [Int]
qsort [] = []
qsort (pivo:t) = qsort [y | y <- t, y < pivo]
              ++ [pivo]
              ++ qsort [y | y <- t, y >= pivo]