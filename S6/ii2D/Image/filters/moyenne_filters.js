ToMoyenneTask=function(opt_options) {
  this.size=opt_options.size;
  /* size doit etre impair*/
  if (this.size%2==0){
    this.size++;
  }
}

ToMoyenneTask.prototype.process=function(imageData) {
  var pixels = imageData.data;
  var p = new Array();
  for (var i = 0; i < imageData.height*imageData.width*4; i++){
      p[i]=255;
  }
  var r = Math.trunc(this.size/2);
  var w = imageData.width*4*r+4*r;
  for (var i = r; i < imageData.height-r; i++){
      for (var j = r; j < imageData.width-r; j++) {
        for (var k=0; k<4; k++){
          var val = 0;
          for (var l=-r; l<r+1; l++){
            for (var m=-r; m<r+1; m++){
              val += pixels[w+k+l*imageData.width*4+m*4];
            }
          }
          val /= this.size*this.size;
          p[w+k]=val;
        }
        w+=4;
      }
  }
  for(var n=0; n<imageData.height*imageData.width*4; n++){
    pixels[n] = p[n];
  }
}

PartialMoyenneTask=function(opt_options) {
  this.reg_x=opt_options.reg_x; this.reg_y=opt_options.reg_y;
  this.reg_w=opt_options.reg_w; this.reg_h=opt_options.reg_h;
  this.size=opt_options.size;
  /* size doit etre impair*/
  if (this.size%2==0){
    this.size++;
  }
}

PartialMoyenneTask.prototype.process=function(imageData) {
  var pixels = imageData.data;
  var p = new Array();
  for (var i = 0; i < imageData.height*imageData.width*4; i++){
      p[i]=pixels[i];
  }
  var r = Math.trunc(this.size/2);
  var w = imageData.width*4*r+4*r;
  for (var i = Math.max(r,this.reg_y); i < Math.min(imageData.height-r,this.reg_y+this.reg_h); i++){
      for (var j = Math.max(r,this.reg_x); j < Math.min(imageData.width-r,this.reg_x+this.reg_w); j++) {
        w=(i*imageData.width+j)<<2;
        for (var k=0; k<4; k++){
          var val = 0;
          for (var l=-r; l<r+1; l++){
            for (var m=-r; m<r+1; m++){
              val += pixels[w+k+l*imageData.width*4+m*4];
            }
          }
          val /= this.size*this.size;
          p[w+k]=val;
        }
        w+=4;
      }
  }
  for(var n=0; n<imageData.height*imageData.width*4; n++){
    pixels[n] = p[n];
  }
}
