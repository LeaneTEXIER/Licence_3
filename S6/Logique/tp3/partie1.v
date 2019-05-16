Lemma hilbertS (A B C : Prop) :  (A -> B -> C) -> (A -> B) -> A -> C.
Proof.
intros abc ab a.
apply abc.
- exact a.
- apply  ab.
exact a.
Show Proof.
Qed.

Definition hilbertS' (A B C : Prop) :  (A -> B -> C) -> (A -> B) -> A -> C :=
  fun f g x => f x (g x).

Lemma q2 (A B : Prop) :  A -> (B -> A).
Proof.
intros a b.
exact a.
Show Proof.
Qed.

Lemma q3 (A B : Prop) :  A -> (~A -> B).
Proof.
intros a na.
destruct na.
exact a.
Show Proof.
Qed.

Lemma q4 (A B C : Prop) :  (A -> B) -> ((B -> C) -> (A -> C)).
Proof.
intros ab bc a.
apply bc.
apply ab.
exact a.
Show Proof.
Qed.

Lemma q5 (A B : Prop) :  (A -> B) -> (~B -> ~A).
Proof.
intros ab nb.
intro a.
apply nb.
apply ab.
exact a.
Show Proof.
Qed.

Require Import Classical.

Lemma tiersexclus (A : Prop) : A \/ ~A.
Proof.
apply NNPP.
intro n_aouna.
apply n_aouna.
right.
intro a.
apply n_aouna.
left.
exact a.
Show Proof.
Qed.

Lemma bottom_c (A : Prop) : (~A -> False) -> A.
Proof.
apply NNPP.
Show Proof.
Qed.

Lemma q8 (A B : Prop) : (~B -> ~A) -> (A -> B).
Proof.
intros nbna a.
apply NNPP.
intro nb.
apply nbna.
- exact nb.
- exact a.
Show Proof.
Qed.

Lemma q9 (A : Prop) : ~~A <-> A.
Proof.
split.
- apply NNPP.
- intro a.
  intro na.
  apply na.
  exact a.
Show Proof.
Qed.

Lemma q10 (A : Prop) :  (A /\ ~A) <-> False.
Proof.
split.
- intro aouna.
  destruct aouna as [a na].
  apply na.
  exact a.
- intro f.
  destruct f.
Show Proof.
Qed.

Lemma q11 (A B : Prop) :  (A \/ B) <-> ~(~A /\ ~B).
Proof.
split.
- intro aoub.
  intro naetnb.
  destruct naetnb as [na nb].
  destruct aoub as [a|b].
   + apply na.
     exact a.
   + apply nb.
     exact b.
- intro n_naetnb.
  apply NNPP.
  intro n_aoub.
  apply n_naetnb.
  split.
  + intro a.
    apply n_aoub.
    left.
    exact a.
  + intro b.
    apply n_aoub.
    right.
    exact b.
Show Proof.
Qed.

Lemma q12 (A : Prop) :  ~A <-> (A -> False).
Proof.
split.
- intros na a.
  apply na.
  exact a.
- intro af.
  intro a.
  apply af.
  exact a.
Show Proof.
Qed.

Lemma q13 (A B : Prop) :  (A <-> B) <-> (A -> B) /\ (B -> A).
Proof.
split.
- intro ab.
  apply ab.
- intro abetba.
  destruct abetba as [ab ba].
  split.
  + exact ab.
  + exact ba.
Show Proof.
Qed.

Lemma q14 (A B C : Prop) :  (A /\ B -> C) <-> (A -> B -> C).
Proof.
split.
- intros aetbc a b.
  apply aetbc.
  split.
  + exact a.
  + exact b.
- intros abc ab.
  destruct ab as [a b].
  apply abc.
  + exact a.
  + exact b.
Show Proof.
Qed.

Lemma q15 (A B C : Prop) :  (C -> A)\/ (C -> B) <-> (C -> A \/ B).
Proof.
split.
- intros caoucb c.
  destruct caoucb as [ca|cb].
  + left.
    apply ca.
    exact c.
  + right.
    apply cb.
    exact c.
- intro caoub.
  apply NNPP.
  intro ncaoucb.
  apply ncaoucb.
  left.
  intro c.
  destruct caoub as [a|b].
  + exact c.
  + exact a.
  + apply NNPP.
    intro na.
    apply ncaoucb.
    right.
    intro c2.
    exact b.
Show Proof.
Qed.

Lemma q16 (X : Type) (A B : X -> Prop) : ((forall x, A x) \/ (forall x, B x)) -> forall x, A x \/ B x.
Proof.
intro xaxb.
destruct xaxb as [xa|xb].
- left.
  apply xa.
- right.
  apply xb.
Show Proof.
Qed.

Lemma q17 (X : Type) (A B : X -> Prop) : (exists x, A x /\ B x) -> ((exists x, A x) /\ (exists x, B x)).
Proof.
intro axbx.
destruct axbx as [x axbx].
destruct axbx as [ax bx].
split.
- exists x.
  exact ax.
- exists x.
  exact bx.
Show Proof.
Qed.

Lemma q18 (A B : Prop) : ~ (A /\ B) -> (~ A \/ ~ B).
Proof.
intro n_aetb.
apply q11.
intro nna_nnb.
destruct nna_nnb as [nna nnb].
apply NNPP in nna.
apply NNPP in nnb.
apply n_aetb.
now split.
Show Proof.
Qed.

Lemma q19 (X : Type) : forall (x1 x2 : X), x1 = x2 -> x2 = x1.
Proof.
intros x1 x2 x1x2.
rewrite x1x2.
reflexivity.
Show Proof.
Qed.

Lemma q20 (X : Type) : forall (x1 x2 x3 : X), x1 = x2 /\ x2 = x3 -> x1 = x3.
Proof.
intros x1 x2 x3 x1x2etx2x3.
destruct x1x2etx2x3 as [x1x2 x2x3].
rewrite x1x2.
exact x2x3.
Show Proof.
Qed.
