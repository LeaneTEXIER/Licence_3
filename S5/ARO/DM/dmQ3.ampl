set PARCELLES;
set LEGUMES circular;
/* Paramètre concernant les années */
param N integer>=0;
set ANNEES:= 1 .. N;
set ANNEES_DONT_0:= 0 .. N;

/* Paramètres concernants les légumes */
param rendement{LEGUMES} >= 0; /* tonne/parcelle/an */
param prix_vente{LEGUMES} >= 0; /* €/kg  */
param besoin_richesse{LEGUMES} >= 0; /* besoin par an */
param apport_richesse{LEGUMES} >= 0; /* apport en richesse par an */

/* Paramètre concernant les parcelles */
param richesse_initiale{PARCELLES} >= 0;

/* Paramètre concernant l'ajout d'engrais initial aux parcelles */
param kilos_engrais >=0;

/* Variable permettant de savoir quel légume a été affecté suivant la parcelle */
var est_affecte{LEGUMES, PARCELLES, ANNEES} binary;
/* Variable du minimum des richesses */
var minimum_richesse >= 0;
/* Variable permettant de savoir la richesse de la parcelle en fin d'année*/
var richesse_parcelle_annee{ANNEES_DONT_0,PARCELLES} >=0;
/* Variable permettant de savoir le nombre de kilos d'engrais ajouter à chaque parcelle */
var ajout_engrais{PARCELLES} integer>= 0;

/* Contrainte pour savoir si un légume peut être cultivé sur la parcelle */
subject to peut_contenir{p in PARCELLES, l in LEGUMES, a in ANNEES}:
  (richesse_parcelle_annee[a-1,p]-besoin_richesse[l]*est_affecte[l,p,a]) >= 0;

/* Contrainte indiquant qu'une parcelle contient un unique légume */
subject to exactement_un_legume_par_parcelle {p in PARCELLES,a in ANNEES} :
  sum {l in LEGUMES} est_affecte[l,p,a] = 1;

/* Contrainte indiquant que chaque légume doit être cultivé au moins une fois */
subject to minimum_un_legume {l in LEGUMES,a in ANNEES} :
  sum {p in PARCELLES} est_affecte[l,p,a] >= 1;

/* Ajout maximal de kilos_engrais de kilos d'engrais sur les parcelles */
subject to ajout_engrais_parcelle :
  sum{p in PARCELLES} ajout_engrais[p] <= kilos_engrais;

/* Calcul de la richesse au début (avant les premières plantations) après l'apport en engrais*/
subject to richesse_annee_zero{p in PARCELLES}:
  richesse_parcelle_annee[0,p] = richesse_initiale[p]+(ajout_engrais[p]*0.01);

/*Calcul de la richesse du sol en fin d'année à partir de la première année de plantation*/
subject to richesse_annee{p in PARCELLES , a in ANNEES} :
	richesse_parcelle_annee[a,p] = sum{l in LEGUMES}(est_affecte[l,p,a]*(apport_richesse[l]-besoin_richesse[l]))+richesse_parcelle_annee[a-1,p];

/* Calcul du minimum des richesses à la fin des N années */
subject to est_minimum_richesse{p in PARCELLES} :
  minimum_richesse <= richesse_parcelle_annee[N,p];

/* Verification que cela fait bien un cycle*/
subject to cycle{l in LEGUMES, p in PARCELLES , a in 1..N-1}:
  est_affecte[l,p,a+1] = est_affecte[prev(l),p,a];

/* Maximisation du minimum des richesses */
maximize minimum_richesse_derniere_annee :
	 minimum_richesse;


/* Données connues */
data;
set PARCELLES:= A B C D E F G;
set LEGUMES := fruits feuilles racines gousses engraisVerts;
param N:= 6;
param kilos_engrais:=500;

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

option solver gurobi;
solve;
display richesse_parcelle_annee, minimum_richesse;
