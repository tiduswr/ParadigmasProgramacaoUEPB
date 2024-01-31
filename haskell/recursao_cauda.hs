potencia2 :: Int -> Int
potencia2 n | n == 0 = 1
            | n > 0 = 2 * potencia2 (n-1)

potencia2Cauda :: Int -> Int -> Int
potencia2Cauda n acumulado | n == 0 = acumulado
                           | n > 0 = potencia2Cauda (n-1) (2*acumulado)

fatorial :: Int -> Int -> Int
fatorial n acc | n == 0 = acc
               | n > 0 = fatorial (n-1) (n*acc)

fibonacci :: Int -> Int -> Int -> Int
fibonacci n a1 a2 | n == 0 = a1
                  | n == 1 = a2
                  | n > 1 = fibonacci (n-1) a2 (a1 + a2)