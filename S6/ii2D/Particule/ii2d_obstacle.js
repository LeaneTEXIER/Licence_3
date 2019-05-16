/**
 *
 * Circle
 *
 *  */
class Circle {
  constructor(centre,radius, coeff) {
    this.centre = new Vector(centre.x, centre.y);
    this.old_centre = new Vector(centre.x, centre.y);
    this.radius = radius;
    this.coeff = coeff;
    this.color = "red";
  }

  draw() {
    ctx.beginPath();
    ctx.strokeStyle = this.color;
    ctx.arc(this.centre.x, this.centre.y, this.radius, 0, 2*Math.PI, false);
    ctx.stroke();
  }

  intersect(old_pos, new_pos){
    var distanceOld = Math.pow(Math.pow((old_pos.x - this.centre.x),2) + Math.pow((old_pos.y - this.centre.y),2),0.5);
    var distanceNew = Math.pow(Math.pow((new_pos.x - this.centre.x),2) + Math.pow((new_pos.y - this.centre.y),2),0.5);
    var oldInCircle;
    var newInCircle;
    // Savoir si l'ancienne position est dans le cercle
    if (distanceOld <= this.radius){
      oldInCircle = true;
    }else{
      oldInCircle = false;
    }
    // Savoir si la nouvelle position est dans le cercle
    if (distanceNew <= this.radius){
      newInCircle = true;
    }else{
      newInCircle = false;
    }
    // Si les 2 sont dedans ou dehors
    if (oldInCircle==newInCircle){
      return {isIntersect : false};
    }
    else{
      var pos = Vector.milieu(old_pos, new_pos);
      var norm = Vector.minus(pos,this.centre);
      norm.normalize();
      return {isIntersect : true, normal : norm, position : pos};
    }
  }

  distance(un_point){
    var distanceCentre = Math.pow(Math.pow((un_point.x - this.centre.x),2) + Math.pow((un_point.y - this.centre.y),2),0.5);
    var distanceContour;
    if (distanceCentre<=this.radius){
      distanceContour = this.radius-distanceCentre;
    }
    else{
      distanceContour = distanceCentre-this.radius;
    }
    return distanceContour;
  }

  move(x,y){
    this.old_centre.setVectXY(this.centre);
    this.centre.add(new Vector(x,y));
  }
}

/**
 *
 * Segment
 *
 *  */
class Segment {
  constructor(a, b, coeff) {
    this.a = new Vector(a.x, a.y);
    this.b = new Vector(b.x, b.y);
    this.old_a = new Vector(a.x, a.y);
    this.old_b = new Vector(b.x, b.y);
    this.coeff = coeff;
    this.color = "red";
    this.zone = null;
  }

  draw() {
    ctx.beginPath();
    ctx.strokeStyle = this.color;
    ctx.moveTo(this.a.x, this.a.y);
    ctx.lineTo(this.b.x, this.b.y);
    ctx.stroke();
  }

  intersect(old_pos, new_pos){
    // norm de [a,b]
    var norm = new Vector(-(this.b.y-this.a.y), (this.b.x-this.a.x));
    norm.normalize();
    // signe de old_pos par rapport à [a,b]
    var vect = Vector.minus(old_pos, this.a);
    var ps = (vect.x*norm.x) + (vect.y*norm.y);
    var sign_old = (ps>0) ? 1 : -1;
    // signe de new_pos par rapport à [a,b]
    vect = Vector.minus(new_pos, this.a);
    ps = (vect.x*norm.x) + (vect.y*norm.y);
    var sign_new = (ps>0) ? 1 : -1;
    //norm de [old_pos, new_pos]
    var norm2 = new Vector(-(new_pos.y-old_pos.y), (new_pos.x-old_pos.x));
    norm2.normalize();
    // signe de a par rapport à [old_pos, new_pos]
    vect = Vector.minus(this.a, old_pos);
    ps = (vect.x*norm2.x) + (vect.y*norm2.y);
    var sign_a = (ps>0) ? 1 : -1;
    // signe de b par rapport à [old_pos, new_pos]
    vect = Vector.minus(this.b, old_pos);
    ps = (vect.x*norm2.x) + (vect.y*norm2.y);
    var sign_b = (ps>0) ? 1 : -1;
    //intersection?
    if ((sign_new!=sign_old) && (sign_a!=sign_b)){
      var pos = Vector.milieu(old_pos, new_pos);
      return {isIntersect : true, normal : norm, position : pos};
    }
    else{
      return {isIntersect: false};
    }
  }

  distance(m){
    var distance;
    //zone a ?
    var ma = new Vector(m.x-this.a.x, m.y-this.a.y);
    var ab = new Vector(this.a.x-this.b.x, this.a.y-this.b.y);
    var psabma = (ab.x * ma.x) + (ab.y * ma.y);
    if (psabma>0){
      // si dans zone a
      this.zone = "b";
      distance = Math.pow(Math.pow((m.x - this.a.x),2) + Math.pow((m.y - this.a.y),2),0.5);
      return distance;
    }
    //zone b ?
    var mb = new Vector(m.x-this.b.x, m.y-this.b.y);
    var ba = new Vector(this.b.x-this.a.x, this.b.y-this.a.y);
    var psbamb = (ba.x * mb.x) + (ba.y * mb.y);
    if (psbamb>0){
      // si dans zone b
      this.zone = "b";
      distance = Math.pow(Math.pow((m.x - this.b.x),2) + Math.pow((m.y - this.b.y),2),0.5);
      return distance;
    }
    // zone line
    var norm = new Vector(-(this.b.y-this.a.y), (this.b.x-this.a.x));
    norm.normalize();
    distance = Math.abs((ma.x * norm.x) + (ma.y * norm.y));
    if(psabma>-1500){
      this.zone = "a";
    }
    else if(psbamb>-1500){
      this.zone = "b";
    }
    else{
      this.zone = "line";
    }
    return distance;
  }

  move(x,y){
    if(this.zone=="a"){
      this.old_a.setVectXY(this.a);
      this.a.add(new Vector(x,y));
    }
    else if(this.zone=="b"){
      this.old_b.setVectXY(this.b);
      this.b.add(new Vector(x,y));
    }
    else{
      this.old_a.setVectXY(this.a);
      this.a.add(new Vector(x,y));
      this.old_b.setVectXY(this.b);
      this.b.add(new Vector(x,y));
    }
  }
}

/**
 *
 * Obstacle Manager
 *
 *  */
 class ObstacleManager {
   constructor() {
     this.all = [];
   }

   draw() {
     for(var i=0;i<this.all.length;++i) {
       this.all[i].draw();
     }
   }

   closest(un_point){
     var obstacle = null;
     var distance = 20;
     for (var i=0; i<this.all.length; i++){
       if (this.all[i].distance(un_point)<distance){
         obstacle = this.all[i];
         distance = obstacle.distance(un_point);
       }
       this.all[i].color = "red";
     }
     return obstacle;
   }
 }
