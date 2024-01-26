raizes :: Float -> Float -> Float -> Int
raizes a b c | delta a b c > 0 = 2
             | delta a b c == 0 = 1
             | delta a b c < 0 = 0
    where 
        -- delta = b^2 - 4*a*c OU
        delta aa bb cc = bb^2 - 4*aa*cc

heron :: Float -> Float -> Float -> Float
heron a b c = let s = (a+b+c)/2
              in sqrt(s*(s-a)*(s-b)*(s-c))