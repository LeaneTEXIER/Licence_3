
class Door{
  constructor(txt, bonneRep, size){
    this.position=new Vector(0,0);
    this.color={r:0,g:0,b:0,a:1};
    this.height=250;
    this.width=150;
    this.text=new Proposal(txt,size);
    this.handle=new Handle();
    this.solution=bonneRep;
  }

  getHandle(){
    return this.handle;
  }

  setSolution(s){
    this.solution=s;
  }

  isSolution(){
    return this.solution;
  }

  setText(t){
    this.text=t;
  }

  setHandle(h){
    this.handle=h;
  }

  setPosition(x,y){
    this.position.x=x;
    this.position.y=y;
  }

  setHeight(h){
    this.height=h;
  }

  setWidth(w){
    this.width=w;
  }

  draw() {
    ctx.fillStyle='rgba('+this.color.r+','+this.color.g+','+this.color.b+','+this.color.a+')';
    ctx.fillRect(this.position.x,this.position.y,this.width,this.height);
    ctx.beginPath();
    ctx.strokeStyle="black";
    ctx.lineWidth=4;
    ctx.rect(this.position.x,this.position.y,this.width,this.height);
    ctx.stroke();
    this.handle.draw();
    this.text.draw();
  }

  open(){
    if(this.isSolution()){
      return true;
    }
    else{
      return false;
    }
  }

}

class DoorManager{
  constructor(rep1,rep2,rep3,bonnerep,size){
    this.all=[]
    this.nbDoors=3;
    if (rep1==bonnerep){
      this.all[0] = new Door(rep1,true,size);
    }
    else{
      this.all[0] = new Door(rep1,false,size);
    }
    if (rep2==bonnerep){
      this.all[1] = new Door(rep2,true,size);
    }
    else{
      this.all[1] = new Door(rep2,false,size);
    }
    if (rep3==bonnerep){
      this.all[2] = new Door(rep3,true,size);
    }
    else{
      this.all[2] = new Door(rep3,false,size);
    }
    for (var i=0; i<this.nbDoors; i++){
      this.all[i].setPosition((100+this.all[i].height*i),100);
      this.all[i].color.r = Math.round(Math.random()*200);
      this.all[i].color.g = Math.round(Math.random()*200);
      this.all[i].color.b = Math.round(Math.random()*200);
      this.all[i].handle.setCenter(new Vector (this.all[i].position.x+this.all[i].width-20,this.all[i].position.y+(this.all[i].height/2)));
      this.all[i].handle.color.r = Math.round(201+Math.random()*54);
      this.all[i].handle.color.g = Math.round(201+Math.random()*54);
      this.all[i].handle.color.b = Math.round(201+Math.random()*54);
      this.all[i].text.setPosition(this.all[i].position.x+25,this.all[i].position.y+25);
    }
  }

  draw(){
    for (var i=0; i<this.nbDoors; i++){
      this.all[i].draw();
    }
  }

  closest(x,y){
    for (var i=0; i<this.nbDoors; i++){
      var bool=this.all[i].getHandle().isSelected(x,y);
      if (bool){
        return this.all[i].open();
      }
    }
    return null;
  }
}

class Handle{
  constructor(){
    this.center=new Vector(0,0);
    this.radius=10;
    this.color={r:0,g:0,b:0};
  }

  setCenter(c){
    this.center=c;
  }

  setRadius(r){
    this.radius=r;
  }

  draw(){
    ctx.beginPath();
    ctx.fillStyle='rgb('+this.color.r+','+this.color.g+','+this.color.b+')';
    ctx.lineWidth=3;
    ctx.arc(this.center.x,this.center.y,this.radius,0,2*Math.PI);
    ctx.fill();
    ctx.strokeStyle="black";
    ctx.stroke();
  }

  isSelected(x,y){
    var distanceCentre = Math.pow(Math.pow((x - this.center.x),2) + Math.pow((y - this.center.y),2),0.5);
    if(distanceCentre<=this.radius){
      return  true;
    }
    else{
      return false;
    }
  }
}

class Proposal{
  constructor(txt,size){
    this.txt = txt;
    this.position = new Vector(0,0);
    this.height=50;
    this.width=100;
    this.sizeTxt = size;
  }

  setTexte(txt){
    this.txt = txt;
  }

  setPosition(x,y){
    this.position.x = x;
    this.position.y = y;
  }

  setHeight(h){
    this.height=h;
  }

  setWidth(w){
    this.width=w;
  }

  draw(){
    ctx.fillStyle="white";
    ctx.fillRect(this.position.x,this.position.y,this.width,this.height);
    ctx.beginPath();
    ctx.strokeStyle="black";
    ctx.lineWidth=2;
    ctx.rect(this.position.x,this.position.y,this.width,this.height);
    ctx.stroke();
    ctx.fillStyle = "black";
    ctx.font = this.sizeTxt+"pt Calibri,Geneva,Arial";
    ctx.fillText(this.txt, this.position.x+10, this.position.y+35);
  }
}
