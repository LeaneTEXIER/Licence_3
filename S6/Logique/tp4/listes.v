Inductive liste : Type :=
 | nil : liste
 | C : nat -> liste -> liste.

Fixpoint longueur (l : liste) : nat := 
  match l with 
    | nil => O 
    | C _ l2 => 1 + longueur l2 
  end.

Compute (longueur nil).

Compute (longueur (C 1 (C 1 (C 2 nil)))).

Fixpoint concat (l l2 : liste) : liste := 
  match l with 
    | nil => l2
    | C e l' => C e (concat l' l2) 
  end.

Compute (concat nil nil).

Compute (concat (C 1 (C 2 nil)) (C 3 (C 4 (C 5 nil)))).

Theorem long (l m : liste) : longueur(concat l m) = longueur l + longueur m.
Proof.
induction l, m.
- reflexivity.
- reflexivity.
- simpl.
  rewrite IHl.
  reflexivity.
- simpl.
  rewrite IHl.
  reflexivity.
Show Proof.
Qed.

Fixpoint ajoutqueue (e : nat) (l : liste): liste := 
  match l with 
    | nil => C e nil
    | C e' l' => C e' (ajoutqueue e l') 
  end.

Compute (ajoutqueue 5 nil).

Compute (ajoutqueue 5 (C 1 (C 2 nil))).

Theorem lgajout (x : nat) (l : liste) : longueur(ajoutqueue x l) = 1 + longueur l.
Proof.
induction l,x.
- reflexivity.
- reflexivity.
- simpl.
  rewrite IHl.
  reflexivity.
- simpl.
  rewrite IHl.
  reflexivity.
Show Proof.
Qed.