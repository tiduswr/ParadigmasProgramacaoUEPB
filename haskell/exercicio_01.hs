-- Sucessivas subtrações para calculo de módulo
restoRec :: Int -> Int -> Int
restoRec a b | a < b = a
          | a == b = 0
          | otherwise = restoRec (a-b) b

multRec :: Int -> Int -> Int
multRec x n | x == 1 = n
            | x > 1 = n + multRec(x-1) n

-- O Algoritmo de Euclides é um método eficiente 
-- para encontrar o MDC de dois números inteiros. 
-- A ideia é usar a propriedade de que o MDC de 
-- dois números não muda se o menor for subtraído 
-- do maior. O algoritmo continua fazendo isso 
-- até que ambos os números se tornem iguais, e o valor comum é o MDC.
mdcEuclides :: Int -> Int -> Int
mdcEuclides x y | x > y = mdcEuclides (x-y) y
        | x < y = mdcEuclides y x
        | x == y = x