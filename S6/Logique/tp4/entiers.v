Inductive entier : Type :=
  | O : entier
  | S : entier -> entier.

Fixpoint plus (n m : entier) : entier :=
  match n with
    | O => m
    | S n' => S (plus n' m)
  end.

Theorem associativite (n m p : entier) : plus n (plus m p) = plus (plus n m) p.
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  reflexivity.
Show Proof.
Qed.

Lemma plus0 (n : entier) : plus n O = n.
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  reflexivity.
Show Proof.
Qed.

Lemma plusS (n p : entier) : plus n (S p) = S (plus n p).
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  reflexivity.
Show Proof.
Qed.

Theorem symetrie (n m : entier) : plus n m = plus m n.
Proof.
induction n.
- simpl.
  rewrite plus0.
  reflexivity.
- simpl.
  rewrite plusS.
  rewrite IHn.
  reflexivity.
Show Proof.
Qed.

Lemma egalS (n m : entier) : n = m <-> S n = S m.
Proof.
split.
- intro nm.
  rewrite nm.
  reflexivity.
- intro snsm.
  injection snsm.
  intro nm.
  exact nm.
Show Proof.
Qed.

Theorem simplification (a n m : entier) : plus a n = plus a m <-> n = m.
Proof.
split.
- induction a.
  + intro p0np0m.
    apply p0np0m.
  + intro psanpsam.
    injection psanpsam.
    exact IHa.
- intro nm.
  now rewrite nm.
Show Proof.
Qed.

Fixpoint mult (n m : entier) : entier :=
  match n with
    | O => O
    | S n' => plus m (mult n' m)
  end.

Lemma multO (n : entier) : mult n O = O.
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  reflexivity.
Show Proof.
Qed.

Lemma assoc2 (n m p : entier) : plus n (plus m p) = plus m (plus n p).
Proof.
rewrite associativite.
rewrite (symetrie n m).
rewrite <- associativite.
reflexivity.
Show Proof.
Qed.

Lemma multsn (n m : entier) : mult n (S m) = plus n (mult n m).
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  rewrite assoc2.
  reflexivity.
Show Proof.
Qed.

Theorem symetriemult (n m : entier) : mult n m = mult m n.
Proof.
induction m.
- simpl. 
  rewrite multO.
  reflexivity.
- simpl.
  rewrite multsn.
  rewrite IHm.
  reflexivity.
Show Proof.
Qed.

Theorem distributivite (n m p : entier) : mult (plus n m) p = plus (mult n p) (mult m p).
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  rewrite associativite.
  reflexivity.
Show Proof.
Qed.

Theorem associativitemult (n m p : entier) : mult n (mult m p) = mult (mult n m) p.
Proof.
induction n.
- reflexivity.
- simpl.
  rewrite IHn.
  rewrite <- distributivite.
  reflexivity.
Show Proof.
Qed.
