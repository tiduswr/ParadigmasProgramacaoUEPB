import Data.Maybe (fromMaybe)

-- É possível acessar o ídice da lista com a sitaxe abaixo
-- minhaLista !! índice

-- head = index 0 da lista e tail = [...] - head
comprimento :: [Int] -> Int
comprimento [] = 0
comprimento (_:t) = 1 + comprimento t

soma :: [Int] -> Int
soma [] = 0
soma (h:t) = h + soma t

possuiChar :: [Char] -> Char -> Bool
possuiChar [] ch = False
possuiChar (h:t) ch | h == ch = True
                    | otherwise = possuiChar t ch

maior :: [Int] -> Int
maior [] = -1
maior (h:t) = if h >= maiorCauda then h else maiorCauda
    where
        maiorCauda = maior t

menor :: [Int] -> Int
menor [] = maxBound
menor (h:t) = if h <= menorCauda then h else menorCauda
    where
        menorCauda = menor t

multiplos :: Int -> [Int]
multiplos n = [n*x | x <- [1..10]]

type PrimoEntry = (Int, Bool)

ehPrimo :: Int -> Bool
ehPrimo n = length [x | x <- [1..n], n `mod` x == 0] == 2

ehPrimoList :: [Int] -> [PrimoEntry]
ehPrimoList [] = []
ehPrimoList (h:t) = (h, (ehPrimo h)):ehPrimoList(t)

ehPrimoListOtimizada :: [Int] -> [PrimoEntry]
ehPrimoListOtimizada = map (\ h -> (h, ehPrimo h))

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