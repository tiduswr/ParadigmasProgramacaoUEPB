raizes :: Float -> Float -> Float -> Int
raizes a b c | delta > 0 = 2
             | delta == 0 = 1
             | delta < 0 = 0
    where 
        -- delta = b^2 - 4*a*c OU
        delta = b^2 - 4*a*c

heron :: Float -> Float -> Float -> Float
heron a b c = let s = (a+b+c)/2
              in sqrt(s*(s-a)*(s-b)*(s-c))