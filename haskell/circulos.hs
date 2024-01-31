area :: Float -> Float
area raio = (raio^2)*pi

perimetro :: Float -> Float
perimetro raio = 2*pi*raio

hipotenusa :: Float -> Float -> Float
hipotenusa a b = sqrt(a^2 + b^2)

diferencaAreaCirculos :: Float -> Float -> Float
diferencaAreaCirculos r1 r2 = abs (area r1 - area r2)

main :: IO()
main = do
    let r1 = 10.0
        r2 = 9.0
        resultado = diferencaAreaCirculos r1 r2
    putStrLn $ "A diferença de área entre os circulos é " ++ show resultado
