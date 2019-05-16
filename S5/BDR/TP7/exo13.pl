schema13([a,b,c,d,e,f,g,h,i,j]).

fds13([ [[a,b],[c]], [[a],[d,e]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).

d1([ [a,b,c],[a,d,e],[b,f],[f,g,h],[d,i,j] ]).
d2([ [a,b,c,d,e],[b,f,g,h],[d,i,j] ]).
d3([ [a,b,c,d],[d,e],[b,f],[f,g,h],[d,i,j] ]).

answer13a1 :- schema13(R), fds13(F), d1(D), fpd(R,F,D).
answer13a2 :- schema13(R), fds13(F), d2(D), fpd(R,F,D).
answer13a3 :- schema13(R), fds13(F), d3(D), fpd(R,F,D).
answer13b1 :- schema13(R), fds13(F), d1(D), ljd(R,F,D).
answer13b2 :- schema13(R), fds13(F), d2(D), ljd(R,F,D).
answer13b3 :- schema13(R), fds13(F), d3(D), ljd(R,F,D).

/*
?- answer13a1.
Considering [a,b]-->[c]
Xplus=[c,a,e,b,f,g,h,d,i,j]
Considering [a]-->[d,e]
Xplus=[a,e,d,i,j]
Considering [b]-->[f]
Xplus=[b,f,g,h]
Considering [f]-->[g,h]
Xplus=[f,g,h]
Considering [d]-->[i,j]
Xplus=[d,i,j]
true .

?- answer13a2.
Considering [a,b]-->[c]
Xplus=[a,c,e,b,f,g,h,d,i,j]
Considering [a]-->[d,e]
Xplus=[a,e,d,i,j]
Considering [b]-->[f]
Xplus=[b,f,g,h]
Considering [f]-->[g,h]
Xplus=[f,g,h]
Considering [d]-->[i,j]
Xplus=[d,i,j]
true .

?- answer13a3.
Considering [a,b]-->[c]
Xplus=[a,c,b,f,g,h,d,i,j]
Considering [a]-->[d,e]
Xplus=[a,d,i,j]
false.

?- answer13b1.
[[a,1],[a,2],[a,3],[a,4],[a,5],[a,6],[a,7],[a,8],[a,9],[a,10]]
[[a,1],[b,2,2],[b,2,3],[a,4],[a,5],[b,2,6],[b,2,7],[b,2,8],[a,9],[a,10]]
[[b,3,1],[a,2],[b,3,3],[b,3,4],[b,3,5],[a,6],[a,7],[a,8],[b,3,9],[b,3,10]]
[[b,4,1],[b,4,2],[b,4,3],[b,4,4],[b,4,5],[a,6],[a,7],[a,8],[b,4,9],[b,4,10]]
[[b,5,1],[b,5,2],[b,5,3],[a,4],[b,5,5],[b,5,6],[b,5,7],[b,5,8],[a,9],[a,10]]

true .

?- answer13b2.
[[a,1],[a,2],[a,3],[a,4],[a,5],[a,6],[a,7],[a,8],[a,9],[a,10]]
[[b,2,1],[a,2],[b,2,3],[b,2,4],[b,2,5],[a,6],[a,7],[a,8],[b,2,9],[b,2,10]]
[[b,3,1],[b,3,2],[b,3,3],[a,4],[b,3,5],[b,3,6],[b,3,7],[b,3,8],[a,9],[a,10]]

true .

?- answer13b3.
[[a,1],[a,2],[a,3],[a,4],[b,1,5],[a,6],[a,7],[a,8],[a,9],[a,10]]
[[b,2,1],[b,2,2],[b,2,3],[a,4],[a,5],[b,2,6],[b,2,7],[b,2,8],[a,9],[a,10]]
[[b,3,1],[a,2],[b,3,3],[b,3,4],[b,3,5],[a,6],[a,7],[a,8],[b,3,9],[b,3,10]]
[[b,4,1],[b,4,2],[b,4,3],[b,4,4],[b,4,5],[a,6],[a,7],[a,8],[b,4,9],[b,4,10]]
[[b,5,1],[b,5,2],[b,5,3],[a,4],[b,5,5],[b,5,6],[b,5,7],[b,5,8],[a,9],[a,10]]

false.



=> The i and the ii have the dependency preservation property and the lossless join property.
The iii hasn't the dependency preservation property and the lossless join property.
*/
