import Text.XHtml (base)
-- Sucessivas subtrações para calculo de módulo
resto :: Int -> Int -> Int
resto a b | a < b = a
          | a == b = 0
          | otherwise = resto (a-b) b
