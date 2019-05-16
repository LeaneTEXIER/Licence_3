#TEXIER Léane et ZENATI Lamine

## Race (`./race`)
La course entre 10 fils peut être lancé dans le terminal, après un make (ou make race), avec la commande `./race`. Le résultat de la course ainsi que l'affichage d'un message du fils quand il est à la moitié est alors affiché.          


## Tests 
Les tests de multif et de race sont lancés avec la commande `make test`.


## Observations (`./observe`)
Pour faire des observations, lancer `./observe` dans un terminal, puis faire les manipulations (`kill`, `ps -a`) dans un autre.

* Lorsque l'on tue un processus fils, celui-ci sera réceptionné directement par le `wait()` du père, et affichera son PID.

* Lorsqu'on tue le processus père celui-ci s'arrete en affichant "Complété" dans le terminal.

On attend maintenant une entrée clavier avant de faire les `wait()`:

* Quand on tue un processus, et qu'on affiche la liste des processus avec `ps -a`
  on remarque des `<defunct>` à coté des processus fils, ils sont considérés comme des processus "zombi" car l'un d'eux s'est arreté sans etre receptionné par un `wait()` de son père.
