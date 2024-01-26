isPrime :: Integer -> Bool
isPrime x
  | x <= 1    = False
  -- \n -> x `mod` n /= 0 é uma função anonima
  | otherwise = all (\n -> x `mod` n /= 0) [2..isqrt x]
  where
    isqrt = floor . sqrt . fromIntegral

main :: IO ()
main = do
  putStrLn "Digite um número:"
  input <- getLine
  let x = read input :: Integer
  if isPrime x
    then putStrLn (show x ++ " é primo.")
    else putStrLn (show x ++ " não é primo.")