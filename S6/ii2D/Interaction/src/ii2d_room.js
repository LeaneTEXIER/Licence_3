var player = new Player();

class Room{
  constructor(){
    this.i=0
    this.questionRep = new Question();
    this.questionRep.randomQuestion();
    this.question = this.questionRep.getQuestion();
    this.doors = new DoorManager(this.questionRep.getrep1(),this.questionRep.getrep2(),this.questionRep.getrep3(),this.questionRep.getBonneReponse(),this.questionRep.getSizeRep());
    this.objects = new Objets();
  }

  draw(){
    ctx.clearRect(0,0,canvas.width,canvas.height);
    this.doors.draw();
    player.draw();
    if (indiceFound){
      this.printQuestion();
    }
    this.objects.draw();
  }

  printQuestion(){
    //Affiche question
    ctx.fillStyle = "black";
    ctx.font = "15pt Calibri,Geneva,Arial";
    var text = "Indice : "+this.question;
    var ind = text.indexOf("\n");
    //Si pas de \n
    if (ind==-1){
      ctx.fillText(text, 0, 480);
    }
    else{
      ctx.fillText(text.substring(0,ind), 0, 480);
      ctx.fillText(text.substring(ind+1,text.length), 0, 500);
    }
  }

  updateData() {
    this.draw();
  }

  selection(x,y){
    var bool=this.doors.closest(x,y);
    if(bool){
      this.setQuestion();
      player.addScore(100);
    }
    else if(bool!=null){
      player.takeHit();
    }
  }

  setQuestion(){
    this.i++
    this.questionRep.randomQuestion();
    this.question = this.questionRep.getQuestion();
    this.doors = new DoorManager(this.questionRep.getrep1(),this.questionRep.getrep2(),this.questionRep.getrep3(),this.questionRep.getBonneReponse(),this.questionRep.getSizeRep());
	this.objects=new Objets();
	indiceFound = false;
  }

  loop() {
    if(player.hp>0 && this.i<10){
      this.updateData();
      window.requestAnimationFrame(this.loop.bind(this));
    }
    else if(player.hp<=0){
      canvas.removeEventListener('mousedown',handleClick,false);
      this.updateData();
      ctx.clearRect(0,0,canvas.width,canvas.height);
      ctx.fillStyle = "black";
      ctx.font = "50pt Calibri,Geneva,Arial";
      var text1="You Died with : ";
      var text2=player.score+" points";
      ctx.fillText(text1, 200,250);
      ctx.fillText(text2, 200,350);
    }
    else{
      canvas.removeEventListener('mousedown',handleClick,false);
      this.updateData();
      ctx.clearRect(0,0,canvas.width,canvas.height);
      ctx.fillStyle = "black";
      ctx.font = "50pt Calibri,Geneva,Arial";
      var text1="You Win with : ";
      var text2=player.hp+" health points";
      var text3="and "+player.score+" points"
      ctx.fillText(text1, 200,200);
      ctx.fillText(text2, 200,300);
      ctx.fillText(text3, 200,400);
    }
  }

  start() {
    this.loop();
  }
}
