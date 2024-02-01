data Pessoa = Pessoa
  { nome :: String
  , idade :: Int
  , altura :: Float
  } deriving (Show)

pessoa1 :: Pessoa
pessoa1 = Pessoa { nome = "Alice", idade = 30, altura = 1.65 }

nomePessoa1 :: String
nomePessoa1 = nome pessoa1