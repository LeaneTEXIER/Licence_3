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
  this.output=opt_options.output;
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
          var mean=(pixels[w+1]+pixels[w+2]+pixels[w+3])/3;
          pixels[w]=mean; pixels[w+1]=mean; pixels[w+2]=mean;
        }
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

ToGrayExceptDistancePointXY=function(opt_options) {
  this.dist=opt_options.dist;
  this.x=opt_options.x;
  this.y=opt_options.y;
}

ToGrayExceptDistancePointXY.prototype.process=function(imageData) {
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
}
