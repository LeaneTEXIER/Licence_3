set PARCELLES;
set LEGUMES;

/* Paramètres concernants les légumes */
param rendement{LEGUMES} >= 0; /* tonne/parcelle/an */
param prix_vente{LEGUMES} >= 0; /* €/kg  */
param besoin_richesse{LEGUMES} >= 0; /* besoin par an */
param apport_richesse{LEGUMES} >= 0; /* apport en richesse par an */

/* Paramètre concernant les parcelles */
param richesse_initiale{PARCELLES} >= 0;

/* Variable permettant de savoir quel légume a été affecté suivant la parcelle */
var est_affecte{LEGUMES,PARCELLES} binary;

/* Contrainte pour savoir si un légume peut être cultivé sur la parcelle */
subject to peut_contenir{p in PARCELLES, l in LEGUMES}:
  (richesse_initiale[p]-besoin_richesse[l]*est_affecte[l,p]) >= 0;

/* Contrainte indiquant qu'une parcelle peut contenir au maximum un légume */
subject to exactement_un_legume_par_parcelle {p in PARCELLES} :
  sum {l in LEGUMES} est_affecte[l,p] <= 1;

/* Contrainte indiquant que chaque légume doit être cultivé au moins une fois */
subject to minimum_un_legume {l in LEGUMES} :
  sum {p in PARCELLES} est_affecte[l,p] >= 1;

/* Maximisation du profit */
maximize profit :
  sum {l in LEGUMES, p in PARCELLES} (est_affecte[l,p]*prix_vente[l]*1000*rendement[l]);

/* Données connues */
data;
set PARCELLES:= A B C D E F G;
set LEGUMES:= fruits feuilles racines gousses engraisVerts;

param:        rendement     prix_vente     besoin_richesse     apport_richesse:=
fruits            2.7           4.3             20.2                0
feuilles          2.6           1.2             10.1                0
racines           3.4           2.7             5.5                 0
gousses           0.5           3.3              0                  3.5
engraisVerts      0              0               0                  25.8;

param: richesse_initiale:=
A               43.2
B               12.1
C               20.8
D               16.8
E               5.7
F               16.3
G               15.1;

/* Résolution */
option solver gurobi;
solve;
display est_affecte, profit;
