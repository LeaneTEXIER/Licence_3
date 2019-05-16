-- Question 1 --
alterne :: [a] -> [a]
alterne [] = []
alterne (x:[]) = [x]
alterne (x:y:ys) = (x:(alterne ys))

-- Question 2 --
combine :: (a -> b -> c) -> [a] -> [b] -> [c]
combine f [] _ = []
combine f _ [] = []
combine f (x:xs) (y:ys) = ((f x y):(combine f xs ys))

-- Question 3 --
pasPascal :: [Integer] -> [Integer]
pasPascal l@(x:xs) = [1]++(zipWith (+) l xs)++ [1]

-- Question 4 --
pascal :: [[Integer]]
pascal = iterate pasPascal [1]
