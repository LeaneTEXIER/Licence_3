Require Import Bool List   Coq.Sorting.Permutation Coq.Sorting.Sorted.
Set Implicit Arguments.

(** L'objet de ce TP est de spécifier et de démontrer l'algorithme de tri par
   insertion. Pour cela, nous allons utiliser plusieurs bibliothèques de la
   librairie standard de Coq. Les bibliothèques que nous allons utilisées
   définissent plusieurs propriétés à l'aide de prédicats inductifs. Nous
   utiliserons notamment des prédicats qui définissent ce que signifie pour deux
   listes d'être une permutation l'une de l'autre, ou encore ce que signifie
   pour une liste d'être triée. *)

Section tri.
  (** Nous fixons le type A des données sur lesquelles nous allons
  travailler.Nous allons trier des listes de A. *)
  Variable A:Set.

  (** Nous supposons disposer d'un algorithme de comparaison ordre tel que ordre
     x y = True signifie que x est inférieur ou égal à y. *)
  Variable ordre: A -> A -> bool.

  (** L'algorithme ordre induit une relation d'ordre *)
  Definition ordre_rel x y := ordre x y = true.

  (** La relation ordre_rel est transitive. *)

  Variable ordre_rel_transitive : forall x y z :A, ordre_rel x y -> ordre_rel y z -> ordre_rel x z.

  (** Elle est également réflexive *)

  Variable ordre_rel_reflexive : forall x:A, ordre_rel x x.

  (** Et enfin elle est totale *)

  Variable ordre_rel_total:
    forall x y, ordre_rel x y \/ ordre_rel y x.

  (** Il est intéressant de noter que nous n'avons pas requis que la relation
  soit irréflexive. Il est possible que x soit plus petit que y et également
  que y soit plus petit que x. Ainsi ordre_rel n'est pas un ordre, mais plutôt
  ce que l'on appelle un "pré-ordre". *)


  (** Le fait que ordre_rel soit total implique que lorsque ordre a b vaut
  false, alors on a forcément ordre_rel b a = true, c'est-à-dire une
  démonstration de ordre_rel b a. Démontrez le lemme suivant qui établit cette
  propriété qui nous sera utile dans la suite.*)

  Lemma ordre_rel_false: 
    forall a b, ordre a b = false -> ordre_rel b a.
    Proof.
    intros a a' aa'false.
    destruct (ordre_rel_total a' a) as [orda'a|ordaa'].
    trivial.
    unfold ordre_rel in *.
    rewrite aa'false in ordaa'.
    discriminate.
    Qed.


  (** Écrivez la spécification d'une fonction de tri. Il s'agit d'une fonction
  qui prenant comme argument une fonction tri renvoie une propriété qui si elle
  est vraie (ou plutôt démontrable) nous certifie que tri est bien un algorithme
  de tri.

Pour cela vous allez utiliser la relation Permutation de type list A -> list A
-> Prop qui relie deux listes lorsqu'elles sont une permutation l'une de
l'autre. Vous utiliserez aussi le prédicat Sorted de type list A -> Prop qui
spécifie si la liste passée en argument est triée.*)

(** verifier que liste donnée et celle en retour de tri sont une permutation 
    et que la liste en retour de tri soit triée *)

  Definition tri_spec (tri: list A -> list A) : Prop := 
  forall l: list A, Sorted ordre_rel (tri l) /\ Permutation l (tri l).

  (** Nous commençons par définir l'algorithme d'insertion. Il insert un élément
  a dans une liste l. Cette insertion a lieu avant le premier élément b de l qui
  soit plus grand que a au sens de ordre_rel. *)


  Fixpoint insertion a l :=
    match l with
    | nil => a::nil
    | b::l =>
      if ordre a b
      then a :: (b :: l)
      else b:: (insertion a l)
    end.


  (** Nous allons maintenant démontrer que si l est une liste ordonnée alors
  insertion a l produit également une liste ordonnée. Afin de conduire cette
  démonstration, nous devons comprendre un peu plus en détail la définition du
  prédicat Sorted de la librairie Coq.Sorting.Sorted. Ce prédicat est en fait
  basé sur la relation (HdRel ordre_rel) définie inductivement de la manière
  suivante :


  Inductive HdRel a : list A -> Prop :=
  | HdRel_nil : HdRel a []
  | HdRel_cons b l : ordre_rel a b -> HdRel a (b :: l).

   HdRel ordre_rel a l signifie que si l a un premier élément, alors a est plus
   petit que cet élément pour ordre_rel.

   Les démonstrations de HdRel ordre_rel a l sont construites à partir des deux
   règles d'inférences déclarées dans la définitions :

   1. HdRel_nil qui permet de déduire immédiatement que HdRel ordre_rel a l est
   vrai dès que l est la liste vide.

   2. HdRel_cons qui permet de déduire que si a est plus petit que b (pour
   ordre_rel) alors nous avons HdRel ordre_rel a (b::l).

   Démontrer le lemme suivant qui spécifie que si a est plus petit que
   l'éventuel premier élément de l et que si a est plus petit que b, alors a est
   plus petit que le premier élément de insertion b l.

   Vous pourrez utiliser la tactique constructor qui permet de choisir
   automatiquement une façon démontrer un prédicat inductif. Vous pourrez
   l'utiliser plutôt que d'employer apply HdRel_nil ou apply HdRel_cons.

   Enfin, lorsque vous avez une hypothèse H de type HdRel a l, utiliser la
   tactique inversion H; subst vous permettra de discriminer les cas à partir
   desquels H a pu être construite.
   *)

  (** Au cours des démonstrations qui vons suivre vous aurez souvent à
  discriminer des cas suivant la valeur que prend une expression de la forme
  ordre a b. Pour cela nous définissons une tactique spécifique case_ordre à
  laquelle vous pourrez faire appel dès que vous aurez besoin de faire une telle
  distinction. *)

  Ltac case_ordre_aux :=
    match goal with
    | [ |- context[if ordre ?A ?B then _ else _]] =>
      case_eq (ordre A B); intro
    end.

  Ltac case_ordre := repeat case_ordre_aux.


  Lemma insertion_hdrel:
        forall l a b, HdRel ordre_rel a l -> ordre_rel a b -> HdRel ordre_rel a (insertion b l).
      Proof.
      intros l a a' hoal ordaa'.
      destruct l; simpl.
      - now constructor.
      - case_ordre.
        + now constructor.
        + constructor.
        now inversion hoal; subst.
      Qed.

  
  (** Vous allez maintenant démontrer que lorsqu'une liste est triée y insérer
  un élément produit une liste triée. N'oubliez pas d'utiliser les lemmes
  précédants.

 Au cours de la démonstration, il vous faudra démontrer qu'une liste est triée.
 Pour cela il sera nécessaire de comprendre la définition du prédicat (Sorted
 ordre_rel). Sa définition est la suivante :

Inductive Sorted : list A -> Prop :=
| Sorted_nil : Sorted []
| Sorted_cons a l : Sorted l -> HdRel a l -> Sorted (a :: l).

 Sorted_nil permet de démontrer que la liste vide est triée. Sorted_cons a l
 montre que si l est trée et que a est plus petit que l'éventuel premier élément
 de l alors a :: l est triée. Comme pour le lemme précédent n'hésitez pas à
 recourir à la tactique constructor plutôt que d'utiliser explicitement les
 constructeurs Sorted_nil et Sorted_cons. N'oubliez pas également de vous servir
 de tactiques de la forme inversion H; subst. *)


  Lemma insertion_preserve_sorted:
    forall l a, Sorted ordre_rel l -> Sorted ordre_rel (insertion a l).
  Proof.
  intros l a sol.
  induction l; simpl.
  - now constructor.
  - case_ordre.
    + constructor.
      * trivial.
      * now constructor.
    + inversion sol; subst.
      constructor.
      * now apply IHl.
      * apply insertion_hdrel; trivial.
        now apply ordre_rel_false.
 Qed.

  (** Nous définissons maintenant le tri par insertion en utilisant la fonction
  insertion. *)

  Fixpoint tri_insertion l :=
    match l with
    | nil => nil
    | a::l => insertion a (tri_insertion l)
    end.

 (** Démontrez que tri_insertion a toujours pour résultat une liste triée. *)

  Lemma tri_insertion_sorted :
    forall l, Sorted ordre_rel (tri_insertion l).
  Proof.
  intro l.
  induction l; simpl.
  - now constructor.
  - now apply insertion_preserve_sorted.
  Qed.

  (** Nous allons maintenant chercher à démontrer que le tri par insertion
  produit une permutation de la liste qui lui est passée en argument. Pour cela
  nous allons devoir utiliser des propriété des permutations.

  Tout d'abord la théorème de la bibliothèque standard

   Permutation_refl : forall l : list A, Permutation l l

  qui établit que pour tout liste l, l est une permutation d'elle-même.

  Ensuite

perm_trans : forall l l' l'' : list A, Permutation l l' -> Permutation l' l'' -> Permutation l l''

 qui établit que si l est une permutation de l' et l' une permutation de l'' alors l est une permutation de l''. En général, lorsque l'on veut démontrer que l1 est une permutation de l3 et démontrant que l1 est une démonstration de l2 puis que l2 est une permutation de l3, il convient d'utiliser la tactique suivante :
 apply perm_trans with (l':= l2).


 Finalement nous avons les propriétés suivantes :

 perm_skip : forall (x:A) (l l':list A), Permutation l l' -> Permutation (x::l) (x::l')

 perm_nil : Permutation nil nil

 perm_swap : forall (x y:A) (l:list A) : Permutation (y::x::l) (x::y::l)

 En guise d'exercice démontrer que les lemmes suivants (vous pourrez les utiliser dans les démonstration concernant le tri par insertion).
   *)

  Lemma permutation_cons_cons:
    forall (a b:A) (l1 l2:list A),
       Permutation l1 l2 -> Permutation (a :: b :: l1) (a :: b :: l2).
  Proof.
  intros a a' la la' plala'.
  constructor.
  now constructor.
  Qed.

  Lemma permutation_skip_swap:
    forall (a b c:A) (l:list A),
      Permutation (a :: b :: c :: l) (a :: c :: b :: l).
  Proof.
  intros a a' a'' la.
  constructor.
  constructor.
  Qed.

  Lemma permutation_rotation_right:
        forall (a b c:A) (l:list A),
          Permutation (a :: b :: c :: l) (c :: a :: b :: l).
  Proof.
  intros a a' a'' la.
  apply perm_trans with (l':=a::a''::a'::la).
  - apply permutation_skip_swap.
  - constructor.
  Qed.

  Lemma permutation_rotation_left:
        forall (a b c:A) (l:list A),
          Permutation (a :: b :: c :: l) (b :: c :: a :: l).
  Proof.
  intros a a' a'' la.
  apply perm_trans with (l':=a'::a::a''::la).
  - apply perm_swap.
  - apply permutation_skip_swap. 
  Qed.

  (** Démontrez le lemme suivant. *)

  Lemma insertion_permutation:
    forall l a, Permutation (a::l) (insertion a l).
  Proof.
  intros la a.
  induction la; simpl.
  - trivial.
  - case_ordre.
    + trivial.
    + apply perm_trans with (l':=a0::a::la).
      * constructor.
      * now constructor.
 Qed.

  Lemma insertion_permutation_inv:
    forall l l', Permutation l l' -> forall a, Permutation (insertion a l) (insertion a l').
  Proof.
  intros la la' plala' a.
  apply perm_trans with (l':=a::la).
  - symmetry.
    apply insertion_permutation.
  - rewrite plala'.
    apply insertion_permutation. 
  Qed.

  Lemma tri_insertion_permutation:
    forall l, Permutation l (tri_insertion l).
  Proof.
  intro la.
  induction la; simpl.
  - trivial.
  - apply perm_trans with (l':=a::(tri_insertion la)). 
    + now constructor.
    + apply insertion_permutation.
  Qed.

  Lemma tri_insertion_correct:
    tri_spec tri_insertion.
  Proof.
  constructor.
  - apply tri_insertion_sorted.
  - apply tri_insertion_permutation.
  Qed.
