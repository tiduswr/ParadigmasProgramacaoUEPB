import Data.Maybe (fromMaybe)

-- É possível acessar o ídice da lista com a sitaxe abaixo
-- minhaLista !! índice

-- head = index 0 da lista e tail = [...] - head
comp :: [Int] -> Int
comp [] = 0
comp (_:t) = 1 + comp t 

soma :: [Int] -> Int
soma [] = 0
soma (h:t) = h + soma t

possuiChar :: [Char] -> Char -> Bool
possuiChar [] ch = False
possuiChar (h:t) ch | h == ch = True
                  | otherwise = possuiChar t ch

maior :: [Int] -> Maybe Int
maior [] = Nothing
maior (h:t) = Just $ if h >= maiorCauda then h else maiorCauda
    where
        maiorCauda = fromMaybe h (maior t)

multiplos :: Int -> [Int]
multiplos n = [n*x | x <- [1..10]]

ehPrimo :: Int -> Bool
ehPrimo n = length [x | x <- [1..n], n `mod` x == 0] == 2

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