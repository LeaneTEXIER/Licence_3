var canvas;
var ctx; // !!! context 2D (drawing)

var engine;


window.addEventListener("load",main);

/*function handleMouseDown(event) {
  // get the mouse relative to canvas
  mouseX = event.layerX-canvas.offsetLeft;
  mouseY = (event.layerY-canvas.offsetTop)-1.0;
  engine.particleManager.generatorList[0].min.setXY(mouseX, mouseY);
  engine.particleManager.generatorList[0].max.setXY(mouseX, mouseY);
}*/

function mouseDownFunction(event) {
  // get the mouse relative to canvas
  mouseX = event.layerX-canvas.offsetLeft;
  mouseY = (event.layerY-canvas.offsetTop)-1.0;
  mousex_old = mouseX;
  mousey_old = mouseY;
  engine.selection(mouseX, mouseY);
  canvas.addEventListener('mousemove', mouseMoveFunction, false);
}

function mouseMoveFunction(event) {
  // get the mouse relative to canvas
  mouseX = event.layerX-canvas.offsetLeft;
  mouseY = (event.layerY-canvas.offsetTop)-1.0;
  engine.move(mouseX-mousex_old, mouseY-mousey_old);
  mousex_old = mouseX;
  mousey_old = mouseY;
}

function mouseUpFunction(event){
  canvas.removeEventListener('mousemove', mouseMoveFunction, false);
}


function main() {
   	canvas=document.getElementById("canvas");
  	ctx=canvas.getContext("2d");
    ctx.lineWidth = "2";
  	/* Gère le mouvement du générateur de particules */
  	/*window.onmousedown = function(event) {
      	canvas.addEventListener('click',handleMouseDown);
      	canvas.addEventListener('mousemove', handleMouseDown, false);
  	}
  	window.onmouseup = function(event) {
      	canvas.removeEventListener('mousemove', handleMouseDown, false);
  	}*/
    window.addEventListener('mousedown',mouseDownFunction);
    window.addEventListener('mouseup', mouseUpFunction);
    engine=new Engine();
    /* Générateurs de particules */
    var gen1 = new GeneratorBox();
    gen1.min.setXY(100,100);
    gen1.max.setXY(100,100);
    var gen2 = new GeneratorBox();
    gen2.min.setXY(250,150);
    gen2.max.setXY(250,150);
    engine.particleManager.generatorList.push(gen1,gen2);
    /* Obstacles */
    var obs1 = new Circle(new Vector(100,100),50, 0);
    var obs2 = new Circle(new Vector(250,200),20, 0.5);
    var obs3 = new Segment(new Vector(150,300), new Vector(400,200), 1);
    var obs4 = new Segment(new Vector(0,200), new Vector(200,400), 0.4);
    engine.obstacleManager.all.push(obs1,obs2,obs3,obs4);
    //
    engine.start();
}

//window.addEventListener("load",scene_tp2);

function scene_tp2(){
    canvas=document.getElementById("canvas");
    ctx=canvas.getContext("2d");
    engine=new Engine();
    var gen1 = new GeneratorBox();
    gen1.min.setXY(50,200);
    gen1.max.setXY(50,200);
    gen1.minSpeed.setXY(-30,0);
    gen1.maxSpeed.setXY(30,-360);
    gen1.birthRate = 7;
    var gen2 = new GeneratorBox();
    gen2.min.setXY(150,300);
    gen2.max.setXY(150,300);
    gen2.minSpeed.setXY(0,0);
    gen2.maxSpeed.setXY(90,90);
    gen2.minTimeLeft = 20;
    gen2.maxTimeLeft = 50;
    gen2.birthRate = 20;
    engine.particleManager.generatorList.push(gen1,gen2);
    engine.start();
}
