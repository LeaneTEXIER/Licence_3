class Player{
  constructor(){
    this.hp=3;
    this.score=0;
  }

  addScore(x){
    this.score+=x;
  }

  removeScore(x){
    this.score-=x;
  }

  addALife(){
    this.hp++;
  }

  takeHit(){
    this.hp--;
    this.score-=100;
  }

  isAlive(){
    if(this.life<=0){
      return false;
    }
    else{
      return true;
    }
  }

  draw(){
    ctx.fillStyle="white";
    ctx.fillRect(0,0,110,30);
    ctx.beginPath();
    ctx.strokeStyle="black";
    ctx.lineWidth=2;
    ctx.rect(0,0,120,65);
    ctx.stroke();
    ctx.fillStyle = "black";
    ctx.font = "15pt Calibri,Geneva,Arial";
    ctx.fillText(this.hp, 5,25);
    ctx.fillText("hp", 90,25);
    ctx.fillText(this.score,5,55);
    ctx.fillText("pts",90,55);
  }
}
