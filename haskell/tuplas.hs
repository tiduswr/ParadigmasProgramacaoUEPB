type Aluno = (String, Float)
type Turma = [Aluno]

approved :: Turma -> Float -> [String]
approved turma nota_corte = [name | (name, nota) <- turma, nota >= nota_corte]