/**
 *
 *
 * */


var randInt=function(a,b) {
	return Math.floor(Math.random()*(b-a)+a);
}

var setAttributes=function(v,lAttrib) {
  for(var k in lAttrib) {
    v[k]=lAttrib[k];
  }
}



class Engine {
  constructor() {
    this.particleManager = new ParticleManager();
		this.obstacleManager = new ObstacleManager();
    this.time = 0;
    this.deltaTime = 0.1;
		this.obstacleSelected = null;
  }

  draw() {
		ctx.clearRect(0,0,500,500);
		this.particleManager.draw();
		this.obstacleManager.draw();
  }

  updateData() {
		this.calculateGravity();
		this.calculateNewPosition();
		this.collision();
		this.particleManager.update();
  }

  loop() {
    this.time+=this.deltaTime;
    this.updateData();
    this.draw();
    window.requestAnimationFrame(this.loop.bind(this));
}

  start() {
    this.loop();
  }

	calculateNewPosition(){
		for(var i=0; i<this.particleManager.all.length; i++){
			if(this.particleManager.all[i].isAlive){
				this.particleManager.all[i].oldSpeed.setVectXY(this.particleManager.all[i].speed);
				this.particleManager.all[i].oldPosition.setVectXY(this.particleManager.all[i].position);
				this.particleManager.all[i].speed.add(Vector.mul(this.particleManager.all[i].strength, this.deltaTime));
				this.particleManager.all[i].position.add(Vector.mul(this.particleManager.all[i].speed, this.deltaTime));
			}
		}
	}

	calculateGravity(){
		for(var i=0; i<this.particleManager.all.length; i++){
			if(this.particleManager.all[i].isAlive){
				this.particleManager.all[i].strength = new Vector(0,9.81);
			}
		}
	}

	collision(){
		for(var i=0; i<this.particleManager.all.length; i++){
			if(this.particleManager.all[i].isAlive){
				for (var j=0; j<this.obstacleManager.all.length; j++){
					this.solveCollision(this.particleManager.all[i], this.obstacleManager.all[j]);
				}
			}
		}
	}

	solveCollision(une_particule, un_obstacle){
		var res = un_obstacle.intersect(une_particule.oldPosition, une_particule.position);
		if (res.isIntersect){
			this.impulse(une_particule, res.normal, res.position, un_obstacle.coeff);
		}
	}

	impulse(p, ncol, pcol, coeff){
		// position
		var pos = Vector.minus(pcol, p.position);
		var ps = (pos.x*ncol.x) + (pos.y*ncol.y);
		var h = Vector.mul(ncol, ps);
		var posi = Vector.mul(h, 1+coeff);
		var interm = Vector.add(p.oldPosition,posi);
		p.position.setVectXY(interm);
		// vitesse
		var ps = (p.speed.x*ncol.x) + (p.speed.y*ncol.y);
		var vnnew = Vector.mul(ncol, ps);
		var vncol = Vector.mul(vnnew, -coeff);
		var vtnew = Vector.minus(p.speed, vnnew);
		var vcol = Vector.add(vncol, vtnew);
		p.speed.setVectXY(vcol);
	}

	selection(x,y){
		this.obstacleSelected = this.obstacleManager.closest(new Vector(x,y));
		if (this.obstacleSelected!=null){
			this.obstacleSelected.color = "lime";
		}
	}

	move(x,y){
		if(this.obstacleSelected!=null){
			this.obstacleSelected.move(x,y);
		}
	}

}
