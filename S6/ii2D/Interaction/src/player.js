class Player{
  constructor(){
    this.hp=5;
    this.score=0;
  }

  takeHit(){
    this.hp--;
    this.score-=100;
  }

  addScore(x){
    this.score+=x;
  }

  removeScore(x){
    this.score-=x;
  }

  draw(){
    ctx.fillStyle = "black";
    ctx.font = "20pt Calibri,Geneva,Arial";
    ctx.fillText(this.hp, 0, 0);
    ctx.fillText(this.score,0,10);
  }
}
