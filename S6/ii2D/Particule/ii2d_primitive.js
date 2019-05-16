/**
 *
 * Vector
 *
 *  */
class Vector {
  constructor(x,y) {
    this.x=x;
    this.y=y;
  }

  setRandInt(v1,v2){
    this.x = randInt(v1.x,v2.x);
    this.y = randInt(v1.y,v2.y);
    return this;
  }

  setXY(sx,sy){
    this.x = sx;
    this.y = sy;
    return this;
  }

  setVectXY(p){
    this.x = p.x;
    this.y = p.y;
    return this;
  }

  add(p){
    this.x += p.x;
    this.y += p.y;
    return this;
  }

  static add(p1, p2){
    return new Vector(p1.x+p2.x, p1.y+p2.y);
  }

  mul(n){
    this.x *= n;
    this.y *= n;
    return this;
  }

  static mul(p, n){
    return new Vector(p.x*n, p.y*n);
  }

  minus(p){
    this.x -= p.x;
    this.y -= p.y;
    return this;
  }

  static minus(p1, p2){
    return new Vector(p1.x-p2.x, p1.y-p2.y);
  }

  static milieu(p1, p2){
    return new Vector((p1.x+p2.x)/2, (p1.y+p2.y)/2);
  }

  normalize(){
    var l =  Math.pow(Math.pow((this.x),2) + Math.pow((this.y),2),0.5);
    this.x = (this.x / l);
    this.y = (this.y / l);
    return this;
  }


};
