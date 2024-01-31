-- distancia euclidiana entre dois pontos
distancia :: Float -> Float -> Float -> Float -> Float
distancia x1 x2 y1 y2 = sqrt ((x2-x1)^2 + (y2-y1)^2)

-- Estrutura de if e else
isPar :: Int -> Bool
isPar x = if mod x 2 == 0
    then True
    else False

-- Estrutura de Guardas, similar ao switch
-- Otherwise é usado caso não queira mais as outras possibilidades, similar ao default do switch
fatorial :: Int -> Int
fatorial n | n == 0  = 1
           | otherwise = n * fatorial (n-1)

fibonacci :: Int -> Int
fibonacci n | n == 0 = 1
            | n == 1 = 1
            | otherwise = fibonacci (n-1) + fibonacci(n-2)

charCase :: Char -> String
charCase ch | ch >= 'a' && ch <= 'z' = "Minusculo"
            | ch >= 'A' && ch <= 'Z' = "Maiusculo"
            | otherwise = "Desconhecido"

testeGuardas :: Int -> Int -> Int -> Int
testeGuardas a b c | a == 0 = b^2 + 3*c
                   | a == 1 = 2*c^2 - 3*c
                   | a == 2 = 3*c - b^2
                   | otherwise = 0