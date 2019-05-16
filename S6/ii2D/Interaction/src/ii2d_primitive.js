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
    this.x=randInt(v1.x,v2.x);
    this.y=randInt(v1.y,v2.y);
  }

  setXY(x,y){
    this.x=x;
    this.y=y;
  }

  copy(v){
    this.x=v.x;
    this.y=v.y;
  }

  static add(v1,v2){
    var x=v1.x+v2.x;
    var y=v1.y+v2.y;
    return new Vector(x,y);
  }

  static soustract(v1,v2){
    var x=v1.x-v2.x;
    var y=v1.y-v2.y;
    return new Vector(x,y);
  }

  static mult(v1,n){
    var x=v1.x*n;
    var y=v1.y*n;
    return new Vector(x,y);
  }

  longueur(){
    return Math.sqrt(this.x*this.x+this.y*this.y);
  }
  
  // Longueur du vecteur
  static long(v){
    return Math.sqrt(v.x*v.x+v.y*v.y);
  }

  static pdtSca(u,v){
    return u.x*v.x+u.y*v.y;
  }

};
