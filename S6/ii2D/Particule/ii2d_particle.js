/**
 *
 *
 *
 * */

class GeneratorBox {
  constructor() {
    this.nbBirth = 0;
    this.birthRate = 2;
    this.min = new Vector(0,0);
    this.max = new Vector(500,500);
    this.minTimeLeft = 50;
    this.maxTimeLeft = 200;
    this.minSpeed = new Vector(-10,-10);
    this.maxSpeed = new Vector(10,10);
  }

  initParticle(p) {
    p.position.setRandInt(this.min,this.max);
    p.color.r = randInt(0,255);
    p.color.g = randInt(0,255);
    p.color.b = randInt(0,255);
    p.color.t = 1;
    p.isAlive = true;
    p.lifeTime = randInt(this.minTimeLeft, this.maxTimeLeft);
    p.speed.setRandInt(this.minSpeed,this.maxSpeed);
  }
};



/**
 *
 *
 *
 *  */
class Particle {
  constructor() {
    this.position = new Vector(0,0);
    this.oldPosition = new Vector(0,0);
    this.color = {r:0,g:0,b:0,t:0};
    this.isAlive = false;
    this.lifeTime = 0;
    this.speed = new Vector(0,0);
    this.oldSpeed = new Vector(0,0);
    this.strength = new Vector(0,0);
  }

  draw() {
    ctx.fillRect(this.position.x, this.position.y, 5, 5);
    ctx.fillStyle = 'rgba('+this.color.r+','+this.color.g+','+this.color.b+','+this.color.t+')';
  }

};

/**
 *
 *
 *
 *
 * */


class ParticleManager {
  constructor() {
    /* Particules */
    this.all=[];
    this.nbAliveMax=5000;
    for(var i=0;i<this.nbAliveMax;++i) {
      this.all.push(new Particle());
    }
    /* Generator */
    this.generatorList = [];
  }

  update() {
    /*var j = parseInt(this.all.length/2);
   for(var i=0;i<j;++i) {
     this.generatorList[0].initParticle(this.all[i]);
   }
   for(var i=j;i<this.all.length;++i) {
     this.generatorList[1].initParticle(this.all[i]);
   }*/

    for(var i=0;i<this.generatorList.length;++i) {
      this.generatorList[i].nbBirth += this.generatorList[i].birthRate;
    }
    var k = 0;
    for(var j=0;j<this.all.length;++j) {
      if(this.all[j].isAlive){
        this.all[j].lifeTime--;
        /* Diminue l'opacité */
        if(this.all[j].lifeTime<=25){
          this.all[j].color.t -= 0.04;
        }
        if(this.all[j].lifeTime<=0){
          this.all[j].isAlive = false;
        }
      }
      else{
        while (k<this.generatorList.length && this.generatorList[k].nbBirth<1){
          k++;
        }
        if (k<this.generatorList.length && this.generatorList[k].nbBirth>=1){
          this.generatorList[k].initParticle(this.all[j]);
          this.generatorList[k].nbBirth--;
        }
      }
    }
  }

  draw() {
    for(var i=0;i<this.all.length;++i) {
      if (this.all[i].isAlive){
        this.all[i].draw();
      }
    }
  }
};
