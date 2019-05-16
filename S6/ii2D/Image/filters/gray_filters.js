ToGrayTask=function(opt_options) { }

ToGrayTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  for (var i = 0; i < imageData.height; i++)
      for (var j = 0; j < imageData.width; j++) {
        var mean=(pixels[w+1]+pixels[w+2]+pixels[w+3])/3;
        pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        w+=4;
      }
}

PartialGrayTask=function(opt_options) {
  this.reg_x=opt_options.reg_x; this.reg_y=opt_options.reg_y;
  this.reg_w=opt_options.reg_w; this.reg_h=opt_options.reg_h;
}

PartialGrayTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  for (var i = this.reg_y; i < this.reg_y+this.reg_h; i++)
      for (var j = this.reg_x; j < this.reg_x+this.reg_w; j++) {
        var pos=(i*imageData.width+j)<<2;
        var mean=(pixels[pos+1]+pixels[pos+2]+pixels[pos+3])/3;
        pixels[pos]=mean; pixels[pos+1]=mean; pixels[pos+2]=mean;
      }
}

RandomPartialGrayTask=function(opt_options) {
  this.reg_x=opt_options.reg_x; this.reg_y=opt_options.reg_y;
  this.reg_w=opt_options.reg_w; this.reg_h=opt_options.reg_h;
  this.cvs_w=opt_options.cvs_w; this.cvs_h=opt_options.csv_h;
}

RandomPartialGrayTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  for (var i = this.reg_y; i < this.reg_y+this.reg_h; i++)
      for (var j = this.reg_x; j < this.reg_x+this.reg_w; j++) {
        var pos=(i*imageData.width+j)<<2;
        var mean=(pixels[pos+1]+pixels[pos+2]+pixels[pos+3])/3;
        pixels[pos]=mean; pixels[pos+1]=mean; pixels[pos+2]=mean;
      }
}

RandomPartialGrayTask.prototype.random_focus=function() {
    this.reg_y=Math.trunc(Math.random()*(this.cvs_h-this.reg_h));
    this.reg_x=Math.trunc(Math.random()*(this.cvs_w-this.reg_w));
}


RandomLevelGrayTask=function(opt_options) {
  // seuil possible de 45 à 75
  this.seuil=45+Math.random()*30;
}

RandomLevelGrayTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  for (var i = 0; i < imageData.height; i++)
      for (var j = 0; j < imageData.width; j++) {
        var mean=(pixels[w+1]+pixels[w+2]+pixels[w+3])/3;
        if(mean>((256*this.seuil)/100)){
          pixels[w]=255; pixels[w+1]=255; pixels[w+2]=255;
        }
        else{
          pixels[w]=0; pixels[w+1]=0; pixels[w+2]=0;
        }
        w+=4;
      }
}

ToGrayIfRInfGouBTask=function(opt_options) {}

ToGrayIfRInfGouBTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  for (var i = 0; i < imageData.height; i++)
      for (var j = 0; j < imageData.width; j++) {
        if(pixels[w]<pixels[w+1] || pixels[w]<pixels[w+2]){
          var mean=(pixels[w]+pixels[w+1]+pixels[w+2])/3;
          pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        }
        w+=4;
      }
}

ToGrayDifferentWeightTask=function(opt_options) {
  // poids du rouge (de 0.2 à 0.45)
  this.w_red=0.2+Math.random()*0.25;
  // poids du vert (de 0.2 à 0.45)
  this.w_green=0.2+Math.random()*0.25;
  // poids du bleu (de 0.1 à 0.6)
  this.w_blue = 1 - this.w_red - this.w_green;
}

ToGrayDifferentWeightTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  for (var i = 0; i < imageData.height; i++)
      for (var j = 0; j < imageData.width; j++) {
        var mean=(pixels[w]*this.w_red)+(pixels[w+1]*this.w_green)+(pixels[w+2]*this.w_blue);
        pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        w+=4;
      }
}

RInverseBTask=function(opt_options) {}

RInverseBTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  var r;
  for (var i = 0; i < imageData.height; i++)
      for (var j = 0; j < imageData.width; j++) {
        r = pixels[w];
        pixels[w]=pixels[w+2]; pixels[w+2]=r;
        w+=4;
      }
}

ToGrayExceptDistancePointXYTask=function(opt_options) {
  this.dist=opt_options.dist;
}

ToGrayExceptDistancePointXYTask.prototype.process=function(imageData) {
  var x = Math.round(Math.random()*imageData.width);
  var y = Math.round(Math.random()*imageData.height);
  var pixels=imageData.data;
  var w=0;
  var r;
  var distanceToPoint;
  for (var i = 0; i < imageData.height; i++){
      for (var j = 0; j < imageData.width; j++) {
        distanceToPoint = Math.sqrt(Math.pow((x-j),2)+Math.pow((y-i),2));
        if(distanceToPoint>this.dist){
          var mean=(pixels[w+1]+pixels[w+2]+pixels[w+3])/3;
          pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        }
        w+=4;
      }
    }
}

ToGrayExceptDistancePointXYMoveTask=function(opt_options) {
  this.dist=opt_options.dist;
  this.x=this.dist+Math.round(Math.random()*(opt_options.image.width-2*this.dist));
  this.y=this.dist+Math.round(Math.random()*(opt_options.image.height-2*this.dist));
  this.moveX=Math.random()+1;
  if(Math.round(Math.random())==0){
    this.moveX=-1*this.moveX;
  }
  this.moveY=Math.random()+1;
  if(Math.round(Math.random())==0){
    this.moveY=-1*this.moveY;
  }
}

ToGrayExceptDistancePointXYMoveTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  var r;
  var distanceToPoint;
  for (var i = 0; i < imageData.height; i++){
      for (var j = 0; j < imageData.width; j++) {
        distanceToPoint = Math.sqrt(Math.pow((this.x-j),2)+Math.pow((this.y-i),2));
        if(distanceToPoint>this.dist){
          var mean=(pixels[w+1]+pixels[w+2]+pixels[w+3])/3;
          pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        }
        w+=4;
      }
  }
  this.x+=this.moveX;
  this.y+=this.moveY;
  if(this.x<this.dist || this.x>(imageData.width-this.dist)){
    this.x-=this.moveX;
    this.moveX = -1*(this.moveX);
    this.x+=this.moveX;
  }
  if(this.y<this.dist || this.y>(imageData.height-this.dist)){
    this.y-=this.moveY;
    this.moveY = -1*(this.moveY);
    this.y+=this.moveY;
  }
}


Magnification2MoveTask=function(opt_options) {
  this.dist=opt_options.dist;
  this.x=this.dist+Math.round(Math.random()*(opt_options.image.width-2*this.dist));
  this.y=this.dist+Math.round(Math.random()*(opt_options.image.height-2*this.dist));
  this.moveX=Math.random()+1;
  if(Math.round(Math.random())==0){
    this.moveX=-1*this.moveX;
  }
  this.moveY=Math.random()+1;
  if(Math.round(Math.random())==0){
    this.moveY=-1*this.moveY;
  }
}

Magnification2MoveTask.prototype.process=function(imageData) {
  var pixels=imageData.data;
  var w=0;
  var r;
  var distanceToPoint;
  for (var i = 0; i < imageData.height; i++){
      for (var j = 0; j < imageData.width; j++) {
        distanceToPoint = Math.sqrt(Math.pow((this.x-j),2)+Math.pow((this.y-i),2));
        if(distanceToPoint>this.dist){
          var mean=(pixels[w+1]+pixels[w+2]+pixels[w+3])/3;
          pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        }
        w+=4;
      }
  }
  this.x+=this.moveX;
  this.y+=this.moveY;
  if(this.x<this.dist || this.x>(imageData.width-this.dist)){
    this.x-=this.moveX;
    this.moveX = -1*(this.moveX);
    this.x+=this.moveX;
  }
  if(this.y<this.dist || this.y>(imageData.height-this.dist)){
    this.y-=this.moveY;
    this.moveY = -1*(this.moveY);
    this.y+=this.moveY;
  }
}
