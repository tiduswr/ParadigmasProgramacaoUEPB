## Funções Simples
```haskell
polinomio :: Int -> Int
polinomio x = x*x + 10*x + 2

quadrado :: Int -> Int
quadrado n = n*n

soma :: Float -> Float -> Float
soma a b = a + b

duplica :: Float -> Float
duplica a = a * 2
```

## Verificação condicional
```haskell
verificarNumero :: Int -> String
verificarNumero n =
    if n == 0
        then "O número é igual a zero"
    else if n > 0
        then "O número é maior do que zero"
    else
        "O número é menor do que zero"

verificarNumero :: Int -> String
verificarNumero 0 = "O número é igual a zero"
verificarNumero n
    | n > 0     = "O número é maior do que zero"
    | otherwise = "O número é menor do que zero"
```

## Declarações locais
```haskell

{--
Essa função em Haskell implementa a fórmula de Heron para calcular a área 
de um triângulo dado os comprimentos dos seus lados. A fórmula de Heron é 
uma maneira de encontrar a área de um triângulo conhecendo os comprimentos 
dos três lados a, b, c
--}
heron :: Float -> Float -> Float -> Float
heron a b c = let s = (a+b+c)/2
              in sqrt(s*(s-a)*(s-b)*(s-c))

-- Função para calcular o IMC e fornecer uma descrição do estado de saúde
calcularIMC :: Float -> Float -> String
calcularIMC peso altura
    | imc <= 18.5 = "Abaixo do peso"
    | imc <= 24.9 = "Peso normal"
    | imc <= 29.9 = "Sobrepeso"
    | otherwise   = "Obesidade"
    where
        imc = peso / (altura * altura)

```

## Loops
```haskell
fatorial :: Int -> Int
fatorial 0 = 1
fatorial n = n * fatorial (n - 1)

comprimento :: String -> Int
comprimento [] = 0
comprimento (_:t) = 1 + comprimento t

soma :: [Int] -> Int
soma [] = 0
soma (h:t) = h + soma t
```