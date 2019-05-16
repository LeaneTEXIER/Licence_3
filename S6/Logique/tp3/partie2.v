Definition faux := forall (P : Prop), P.
Definition non (A : Prop) := forall (P : Prop), ((A -> faux) -> P) -> P.
Definition et (A B : Prop) := forall (P : Prop), (A -> B -> P) -> P.
Definition ou (A B : Prop) := forall (P : Prop), ((A -> P) -> (B -> P) -> P).
Definition existe (A : Type) (P : A -> Prop) := forall (Q : Prop), (forall a, P a -> Q) -> Q.
Definition egal (A : Type) (a a' : A) := forall (P : A -> Prop), P a -> P a'.

Lemma bottom_e (A : Prop) : faux -> A.
Proof.
intro f.
apply f.
Show Proof.
Qed.

Lemma non_intro (A : Prop) : (A -> faux) -> non A.
Proof.
intro af.
intro b.
intro afb.
apply afb.
exact af.
Show Proof.
Qed.


Lemma non_elim (A : Prop) : A -> non A -> faux.
Proof.
intros a na.
apply na.
intro af.
apply af.
exact a.
Show Proof.
Qed.

Lemma et_intro (A B : Prop) : A -> B -> et A B.
Proof.
intros a b.
intro c.
intros abc.
apply abc.
+ exact a.
+ exact b.
Show Proof.
Qed.

Lemma et_elim_g (A B : Prop) : et A B -> A.
Proof.
intro etab.
apply etab.
intros a b.
exact a.
Show Proof.
Qed.

Lemma et_elim_d (A B : Prop) : et A B -> B.
Proof.
intro etab.
apply etab.
intros a b.
exact b.
Show Proof.
Qed.

Lemma ou_intro_g (A B : Prop) : A -> ou A B.
Proof.
intro a.
intro c.
intros ac bc.
apply ac.
exact a.
Show Proof.
Qed.

Lemma ou_intro_d (A B : Prop) : B -> ou A B.
Proof.
intro b.
intro c.
intros ac bc.
apply bc.
exact b.
Show Proof.
Qed.

Lemma ou_elim (A B C : Prop) : ou A B -> (A -> C) -> (B -> C) -> C.
Proof.
intros ouab ac bc.
apply ouab.
- exact ac.
- exact bc.
Show Proof.
Qed.

Lemma existe_intro (A : Type) (P : A -> Prop) : forall x : A, P x -> existe A P.
Proof.
intros a pa.
intros Q G.
apply (G a pa).
Show Proof.
Qed.

Lemma existe_elim (A : Type) (P : A -> Prop) (Q : Prop) : existe A P -> (forall x : A, P x -> Q) -> Q.
Proof.
intros ap apxq.
apply ap.
apply apxq.
Show Proof.
Qed.

Lemma faux_false : faux <-> False.
Proof.
split.
- apply bottom_e.
- intro F.
  destruct F.
Show Proof.
Qed.

Lemma non_not (A : Prop) : non A <-> ~A.
Proof.
split.
- intro na.
  intro a.
  apply na.
  intro af.
  apply af.
  exact a.
- intro na.
  intro a.
  intro afa.
  apply afa.
  intro a2.
  apply faux_false.
  apply na.
  exact a2.
Show Proof.
Qed.

Lemma et_and (A B : Prop) : et A B <-> A /\ B.
Proof.
split.
- intro etab.
  apply etab.
  intros a b.
  split.
  + exact a.
  + exact b.
- intro aetb.
  destruct aetb as [a b].
  intro c.
  intro abc.
  apply abc.
  + exact a.
  + exact b.
Show Proof.
Qed.

Lemma ou_or (A B : Prop) : ou A B <-> A \/ B.
Proof.
split.
- intro ouab.
  apply ouab.
  + intro a.
    left.
    exact a.
  + intro b.
    right.
    exact b.
- intro aoub.
  intro c.
  intros ac bc.
  destruct aoub as [a|b].
  + apply ac.
    exact a.
  + apply bc.
    exact b.
Show Proof.
Qed.

Lemma existe_exists (A : Type) (P : A -> Prop) : existe A P <-> exists a, P a.
Proof.
split.
- intro ap.
  apply ap.
  intros a pa.
  exists a.
  exact pa.
- intro apa.
  intro Q.
  intro apaq.
  destruct apa as [a pa].
  apply (apaq a pa).
Show Proof.
Qed.

Lemma egal_eq (A : Type) (a a' : A) : egal _ a a' <-> a = a'.
Proof.
split.
- intro egaa'.
  apply egaa'.
  reflexivity.
- intro aega'.
  rewrite aega'.
  intro ap.
  intro apa'.
  exact apa'.
Show Proof.
Qed.