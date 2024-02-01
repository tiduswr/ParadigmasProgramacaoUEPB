safeDivide :: Integer -> Integer -> Maybe Integer
safeDivide _ 0 = Nothing
-- É possível alterar a ordem div x y para x div y usando crases:
safeDivide x y = Just (x `div` y)

-- Exemplo de uso
main :: IO ()
main = do
  putStrLn "Digite dois números:"
  input1 <- getLine
  input2 <- getLine
  let num1 = read input1 :: Integer
      num2 = read input2 :: Integer
  case safeDivide num1 num2 of
    Just result -> putStrLn ("Resultado: " ++ show result)
    Nothing     -> putStrLn "Divisão por zero!"