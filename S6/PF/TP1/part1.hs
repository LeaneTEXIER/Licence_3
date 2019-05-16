-- Question 1 --
somme' n = sum [1..n]

-- Question 3 --
sommeDeXaY :: Int -> Int -> Int
sommeDeXaY x y = sum[x..y]

sommeDeXaY' :: Int -> Int -> Int
sommeDeXaY' x y = if y < x then 0
                  else x + sommeDeXaY' (x+1) y

-- Question 4 --
somme :: [Int] -> Int
somme [] = 0
somme (x:xs) = x + somme xs

-- Question 5 --
last' :: [a] -> a
last' xs = xs !! (length xs - 1)

init' :: [a] -> [a]
init' xs = take (length xs -1) xs

-- Question 6 --
charAt :: [a] -> Int -> a
charAt (x:_) 0 = x
charAt (_:xs) y = charAt xs (y-1)

plusPlus :: [a] -> [a] -> [a]
plusPlus [] ys = ys
plusPlus (x:xs) ys = (x:plusPlus xs ys)

concat' :: [[a]] -> [a]
concat' (x:[]) = x
concat' (x:(y:ys)) =  concat' ((plusPlus x y):ys)

map' :: (a -> b) -> [a] -> [b]
map' f [] = []
map' f (x:xs) = ((f x):(map' f xs))

-- Question 7 --
-- x represente une fonction qui prend un entier n en paramètre et renvoie l'élement
-- de l à l'indice n. (si n > length l, renvoie une erreur) --

-- Question 8 --
longueur :: [a] -> Int
longueur xs= somme (map (\x -> 1) xs)

-- Question 9 --
recursivIterate :: (a->a) -> a -> Int -> [a]
recursivIterate f x 0 = [x]
recursivIterate f x n = (x:(recursivIterate f (f x) (n-1)))

iterate2 :: (a->a) -> a -> Int -> [a]
iterate2 f x n = take (n+1) (iterate f x)

-- Question 10 --
listNumbers :: Int -> [Int]
listNumbers n= recursivIterate (\x -> x+1) 0 n
