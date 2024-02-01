raizes :: Float -> Float -> Float -> [Float]
raizes a b c | delta < 0 = []
             | delta == 0 = [(-b)/2*a]
             | delta > 0 = [((-b) + sqrt delta)/2*a, ((-b) - sqrt delta)/2*a]
    where
        delta = b^2 - 4*a*c