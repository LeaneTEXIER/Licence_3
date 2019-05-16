class Question{
  constructor(){
    this.question = "";
    this.rep1 = "";
    this.rep2 = "";
    this.rep3 = "";
    this.bonnereponse="";
    this.nbQuestions = 21;
    this.listQuestionsNotDid = [];
    for(var i=1; i<this.nbQuestions; i++){
      this.listQuestionsNotDid[i] = true;
    }
    this.sizeRep = 20;
  }

  getSizeRep(){
    return this.sizeRep;
  }

  getQuestion(){
    return this.question;
  }

  getrep1(){
    return this.rep1;
  }

  getrep2(){
    return this.rep2;
  }

  getrep3(){
    return this.rep3;
  }

  getBonneReponse(){
    return this.bonnereponse;
  }

  randomQuestion(){
    var i = Math.round(Math.random()*(this.nbQuestions-1))+1;
    //Si la question n'a pas encore été faite
    if (this.listQuestionsNotDid[i]==true){
      var q = "question"+i;
      this[q]();
      this.listQuestionsNotDid[i]=false;
    }
    else{
      this.randomQuestion();
    }
  }

  question1(){
    this.question = "Dans une année ordinaire, combien de mois comportent vingt-huit jours ?";
    this.rep1 = "1";
    this.rep2 = "2";
    this.rep3 = "12";
    this.bonnereponse = this.rep3;
	this.sizeRep = 20;
  }

  question2(){
    this.question = "x=5";
    this.rep1 = "9x=18";
    this.rep2 = "5x=25";
    this.rep3 = "6x=18";
    this.bonnereponse = this.rep2;
	this.sizeRep = 20;
  }

  question3(){
    this.question = "3 poissons sont dans un seau. l'un meurt. combien en reste-t-il ?";
    this.rep1 = "1";
    this.rep2 = "2";
    this.rep3 = "3";
    this.bonnereponse = this.rep3;
	this.sizeRep = 20;
  }

  question4(){
    this.question = "J’ai 4 frères et chacun de mes frères a 4 frères. Combien de frères sommes-nous ?";
    this.rep1 = "4";
    this.rep2 = "5";
    this.rep3 = "20";
    this.bonnereponse = this.rep2;
	this.sizeRep = 20;
  }

  question5(){
    this.question = "Quelle est la soeur de ta tante qui elle n'est pas ta tante ?";
    this.rep1 = "Ta mère";
    this.rep2 = "Ta cousine";
    this.rep3 = "Ta grand-mère";
    this.bonnereponse = this.rep1;
    this.sizeRep = 11;
  }

  question6(){
    this.question = "2 / 10 / 12 / 17 / 18 / 19 / ?   ";
    this.rep1 = "20";
    this.rep2 = "200";
    this.rep3 = "2";
    this.bonnereponse = this.rep2;
	this.sizeRep = 20;
  }

  question7(){
    this.question = "R / O / J / V / B / I / ?  ";
    this.rep1 = "V";
    this.rep2 = "R";
    this.rep3 = "A";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

  question8(){
    this.question = "David a 10 ans, son petit frère Franck a la moitié de son âge. Quand David sera 10 fois plus âgé, \nquel âge aura Franck ?";
    this.rep1 = "50";
    this.rep2 = "95";
    this.rep3 = "100";
    this.bonnereponse = this.rep2;
	this.sizeRep = 20;
  }

  question9(){
    this.question = "Une brique pèse 1 kg plus une demi-brique. Combien pèse une brique ?";
    this.rep1 = "2kg";
    this.rep2 = "1kg";
    this.rep3 = "1,5kg";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

  question10(){
    this.question = "Combien de fois peut-on soustraire 5 de 25 ?";
    this.rep1 = "1";
    this.rep2 = "5";
    this.rep3 = "4";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

  question11(){
    this.question = "Banane ca commence par un 'B', mais normalement ca commence par quelle lettre ? ";
    this.rep1 = "B";
    this.rep2 = "N";
    this.rep3 = "H";
    this.bonnereponse = this.rep2;
	this.sizeRep = 20;
  }

  question12(){
    this.question = "Un bucheron a sept filles, chacune de ces filles a un frère. Combien a-t-il d'enfants ?";
    this.rep1 = "7";
    this.rep2 = "14";
    this.rep3 = "8";
    this.bonnereponse = this.rep3;
	this.sizeRep = 20;
  }

  question13(){
    this.question = "Un escargot est dans un puits de 10 mètres. Il monte 3 mètres chaque jour et descend 2 mètres chaque nuit. \nEn combien de jours sera-t-il rendu en haut ?";
    this.rep1 = "8";
    this.rep2 = "9";
    this.rep3 = "10";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

  question14(){
    this.question = "Dans 4 ans, Noemy aura la moitié de l'âge de pierre. Aujourd'hui, Pierre a 14 ans. \nQuel est l'age actuel de Noemy ?";
    this.rep1 = "9";
    this.rep2 = "4";
    this.rep3 = "5";
    this.bonnereponse = this.rep3;
	this.sizeRep = 20;
  }

  question15(){
    this.question = "Un fermier a des poules et des lapins. En regardant tous les animaux, il voit 5 têtes et 16 pattes. \nCombien le fermier a-t-il de lapins ?";
    this.rep1 = "3";
    this.rep2 = "5";
    this.rep3 = "2";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

  question16(){
    this.question = "Combien de gouttes d'eau peut-on mettre dans un verre vide de 100 ml?";
    this.rep1 = "1";
    this.rep2 = "100";
    this.rep3 = "1000";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

  question17(){
    this.question = "Il y a 3 poussins : 2 devant un de ces poussins et 2 derrière. Combien y a-t-il de poussins en tout?";
    this.rep1 = "2";
    this.rep2 = "3";
    this.rep3 = "5";
    this.bonnereponse = this.rep2;
	this.sizeRep = 20;
  }

  question18(){
    this.question = "Un berger a cent moutons ; il dit qu'il va en donner quatre-vint-dix à son voisin, \ncombien lui en reste-t-il ?";
    this.rep1 = "10";
    this.rep2 = "90";
    this.rep3 = "100";
    this.bonnereponse = this.rep3;
	this.sizeRep = 20;
  }

  question19(){
    this.question = "Une course est organisée entre des villageois. Un villageois double le deuxième. \nQuel devient son classement ?";
    this.rep1 = "Premier";
    this.rep2 = "Deuxième";
    this.rep3 = "Troisième";
    this.bonnereponse = this.rep2;
    this.sizeRep = 15;
  }

  question20(){
    this.question = "Un père et un fils ont à eux deux 36 ans. Sachant que le père a 30 ans de plus que le fils, \nquel âge a le fils ?";
    this.rep1 = "6";
    this.rep2 = "5";
    this.rep3 = "3";
    this.bonnereponse = this.rep3;
	this.sizeRep = 20;
  }

  question21(){
    this.question = "Une bouteille de vin coûte vingt euros. Le vin coûte dix-neuf euros de plus que la bouteille. \nCombien coûte la bouteille ?";
    this.rep1 = "0,50€";
    this.rep2 = "1€";
    this.rep3 = "20€";
    this.bonnereponse = this.rep1;
	this.sizeRep = 20;
  }

}
