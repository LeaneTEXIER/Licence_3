import Graphics.Gloss

-- main = animate (InWindow "Dragon" (500, 500) (0, 0)) white (dragonAnime (50,250) (450,250))
-- dragonAnime a b t = Line (dragon a b !! (round t `mod` 20))

-- Question 5 --
pointAintercaler :: Point -> Point -> Point
pointAintercaler (x1,y1) (x2,y2) = ((x1+x2)/2+(y2-y1)/2, (y1+y2)/2+(x1-x2)/2)

-- Question 6 --
pasDragon :: Path -> Path
pasDragon [] = []
pasDragon [x1]= [x1]
pasDragon [x1,x2]= [x1, (pointAintercaler x1 x2), x2]
pasDragon (x1:x2:x3:xs)= (x1:(pointAintercaler x1 x2):x2:(pointAintercaler x3 x2):(pasDragon (x3:xs)))

-- Question 7 --
dragon :: Point -> Point -> [Path]
dragon x1 x2 = iterate pasDragon [x1,x2]

-- Question 8 --
dragonOrdre :: Point -> Point -> Int -> Path
dragonOrdre p1 p2 n =  (take (n+1) (dragon p1 p2)) !! n

-- Question 9 --
main = animate (InWindow "Dragon" (500, 500) (0, 0)) white (dragonAnime (50,250) (450,250))
dragonAnime a b t = Line (dragonOrdre a b (round t `mod` 20))
