var indiceFound = false;
class Objets{
  constructor(){
    this.objets = [];
    this.createCircles();
    this.nbQuestion = 0;
    this.chooseCircleQuestion();
    this.hideBonusAndMalus();
  }

  createCircles(){
    //Création cercles et changement radius
    for(var i=0;i<32;i++){
      this.objets[i]=new Cercle();
      this.objets[i].setRadius(25+Math.random()*15);
    }
    //Cercles au dessus
    for(var i=0;i<9;i++){
      this.objets[i].setCenter(81*i+170,42+Math.random()*20);
    }
    //Cercles cotés
    for(var i=9;i<12;i++){
      this.objets[i].setCenter(42+Math.random()*15,85*(i-9)+135);
    }
    for(var i=12;i<15;i++){
      this.objets[i].setCenter(292+Math.random()*15,85*(i-12)+135);
    }
    for(var i=15;i<18;i++){
      this.objets[i].setCenter(542+Math.random()*15,85*(i-15)+135);
    }
    for(var i=18;i<21;i++){
      this.objets[i].setCenter(792+Math.random()*15,85*(i-18)+135);
    }
    //Cercles en dessous
    for(var i=21;i<32;i++){
      this.objets[i].setCenter(81*(i-21)+40,400+Math.random()*20);
    }
  }

  chooseCircleQuestion(){
    //Cache l'indice dans un rond
    this.nbQuestion = Math.round(Math.random()*(this.objets.length-1));
    this.objets[this.nbQuestion].doActionQuestion();
  }

  hideBonusAndMalus(){
    for(var i=0; i<this.objets.length; i++){
      if (i!=this.nbQuestion){
        //Choisi un nb entre 0 et 20
        var j = Math.round(Math.random()*20);
        //Si de 0 à 9 //soit 1 chance sur 10 => add points
        if (j>=0 && j<10){
          this.objets[i].doActionAddPoints();
        }
        //Si 10 //soit 1 chance sur 21 => add life
        else if(j==10){
          this.objets[i].doActionAddLife();
        }
        //Si de 11 à 20 //soit 1 chance sur 10 => remove points
        else if (j>10 && j<21){
          this.objets[i].doActionRemovePoints();
        }
      }
    }
  }

  draw(){
    for(var i=0;i<this.objets.length;i++){
      this.objets[i].draw();
    }

  }
}

var randInt=function(a,b) {
	return Math.floor(Math.random()*(b-a)+a);
}

class Cercle{
  constructor(){
    this.center = new Vector(0,0);
    this.radius = 0;
    this.color = {r:randInt(0,255),g:randInt(0,255),b:randInt(0,255),a:1};
    this.opacity = 1;
    this.action = "";
    this.isVisible = true;
  }

  itsVisible(){
    return this.isVisible;
  }

  setCenter(x,y){
    this.center.x =x;
    this.center.y =y;
  }

  setRadius(r){
    this.radius = r;
  }

  setOpacity(o){
    this.opacity = o;
  }
  getOpacity(){
    return this.opacity;
  }

  draw(){
    ctx.beginPath();
    ctx.arc(this.center.x, this.center.y, this.radius, 0, 2 * Math.PI);
    ctx.fillStyle = 'rgba('+this.color.r+','+this.color.g+','+this.color.b+','+this.opacity+')';
    ctx.fill();
  }

  distance(point){
      var distanceCentre = Math.pow(Math.pow((point.x - this.center.x),2) + Math.pow((point.y - this.center.y),2),0.5);
      return distanceCentre;
  }

 collision(point){
     if(this.distance(point)>this.radius){
       return false;
     }
     else{
       return true;
     }
  }

  doActionQuestion(){
    this.action = "question";
  }

  doActionAddPoints(){
    this.action = "add points";
  }

  doActionRemovePoints(){
    this.action = "remove points";
  }

  doActionAddLife(){
    this.action = "add life";
  }

  doAction(){
    this.isVisible = false;
    if (this.action=="add points"){
      //Add points entre 30 et 60
      var i = Math.round(Math.random()*30+30);
      player.addScore(i);
    }
    else if (this.action=="remove points"){
      //Remove points entre 30 et 60
      var i = Math.round(Math.random()*30+30);
      player.removeScore(i);
    }
    else if (this.action=="add life"){
      //Add a life
      player.addALife();
    }
    else if (this.action=="question"){
      indiceFound = true;
    }
  }


}
