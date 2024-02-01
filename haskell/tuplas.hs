type Nome = String
type MediaNota = Float
type Aluno = (Nome, MediaNota)
type Turma = [Aluno]

approved :: Turma -> MediaNota -> [String]
approved turma nota_corte = [name | (name, nota) <- turma, nota >= nota_corte]

type Ponto = (Float, Float, Float)

distancia :: Ponto -> Ponto -> Float
distancia (x1, y1, z1) (x2, y2, z2) = sqrt(dx^2 + dy^2 + dz^2)
    where
        dx = abs (x1 - x2)
        dy = abs (y1 - y2)
        dz = abs (z1 - z2)