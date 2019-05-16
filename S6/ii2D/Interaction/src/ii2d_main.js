var canvas;
var ctx; // !!! context 2D (drawing)

var engine;
var objets;


window.addEventListener("load",main);

function main() {
   	canvas=document.getElementById("canvas");
  	ctx=canvas.getContext("2d");
    canvas.addEventListener('mousedown',click,false);
    scene();
    engine.start();
    canvas.addEventListener('mousedown',handleClick,false);
}

function scene(){
  engine=new Room();
}

var mousex_old;
var mousey_old;
function click(event){
  mousex_old = event.layerX-canvas.offsetLeft;
  mousey_old = (event.layerY-canvas.offsetTop)-1.0;
  engine.selection(mousex_old,mousey_old);
}

function handleClick(event) {
  // get the mouse relative to canvas

   mouseX = event.layerX-canvas.offsetLeft;
   mouseY = (event.layerY-canvas.offsetTop)-1.0;

   var mouse = new Vector(mouseX,mouseY);
   //console.log(mouse);
   for (var i=0;i<engine.objects.objets.length;i++){
     if(engine.objects.objets[i].collision(mouse) && engine.objects.objets[i].itsVisible()){
       engine.objects.objets[i].setOpacity(0);
       ctx.clearRect(0, 0,this.width, this.height);
       engine.objects.objets[i].doAction();
     }
   }
   for(var i=0;i<engine.objects.objets.length;i++){
     engine.objects.objets[i].draw();
   }
  }
