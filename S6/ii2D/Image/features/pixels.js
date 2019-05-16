var pixels_features={};

/*
  pixels_features.mean_rgb
  - computes RGB mean of all pixels having A>0 within imageData
*/
pixels_features.mean_rgb=function(imageData,opt_options) {
  return pixels_features.mean_rgb_per_region(imageData,{x0:0,y0:0,dx:imageData.width,dy:imageData.height});
}

/*
  pixels_features.mean_rgb_per_region
  - computes RGB mean of all pixels having A>0 within opt_options.x0 .y0 .dx .dy
  - if opt_options missing partially,
      replace partially with defaults 0, 0, imageData.width, imageData.height
  - returns undefined if none available
*/
pixels_features.mean_rgb_per_region=function(imageData,opt_options) {
  x0=opt_options&&opt_options.x0?opt_options.x0:0;
  y0=opt_options&&opt_options.y0?opt_options.y0:0;
  dx=opt_options&&opt_options.dx?opt_options.dx:imageData.width;
  dy=opt_options&&opt_options.dy?opt_options.dy:imageData.height;


  var mean=[];
  mean[0]=0; mean[1]=0; mean[2]=0;
  var pos=0; var count=0;
  for (var y=y0;y<y0+dy;y++) {
    for (var x=x0;x<x0+dx;x++) {
      pos=(y*imageData.width+x)<<2;
      if (imageData.data[pos+3]>0) {
        for (var i=0;i<3;i++) {
          mean[i]+=imageData.data[pos+i];
        }
        count++;
      }
    }
  }
  if (count>0) {
    console.log("count: "+count);
    for (var i=0;i<3;i++) {
      mean[i]=Math.round(mean[i]/count);
    }
    return mean;
  } else {
    console.log("no positive Alpha, no rgb mean");
  }
  return undefined;
}

/*
  pixels_features.grid_mean_rgb
  - computes RGB mean of all pixels having A>0 in all grid cells
  - grid params are defined as opt_options.nb_lines*opt_options.nb_columns
  - returns a generic_features.grid_descriptor (grid.cells - array)
*/
pixels_features.grid_mean_rgb=function(imageData,opt_options) {
  return generic_features.grid_descriptor(imageData,pixels_features.mean_rgb_per_region,opt_options);
}

GetRandomRGBAPixelTask=function(opt_options) {
  this.output=opt_options.output;
}

GetRandomRGBAPixelTask.prototype.process=function(imageData) {
  var x = Math.round(Math.random()*imageData.width);
  var y = Math.round(Math.random()*imageData.height);
  var pos=(y*imageData.width+x)<<2;
  var r=imageData.data[pos];
  var g=imageData.data[pos+1];
  var b=imageData.data[pos+2];
  var a=imageData.data[pos+3];

  this.output.innerHTML=x+"x"+y+" : ";
  this.output.innerHTML+="<font color='red'>"+r+"</font> | ";
  this.output.innerHTML+="<font color='green'>"+g+"</font> | ";
  this.output.innerHTML+="<font color='blue'>"+b+"</font> | ";
  this.output.innerHTML+="<font color='gray'>"+a+"</font>";
}

GetMouseRGBAPixelTask=function(opt_options) {
  this.output=opt_options.output;
}

GetMouseRGBAPixelTask.prototype.set_position= function(x,y){
  this.x = x;
  this.y = y;
}

GetMouseRGBAPixelTask.prototype.process=function(imageData) {
  var pos=(this.y*imageData.width+this.x)<<2;
  var r=imageData.data[pos];
  var g=imageData.data[pos+1];
  var b=imageData.data[pos+2];
  var a=imageData.data[pos+3];

  this.output.innerHTML=this.x+"x"+this.y+" : ";
  this.output.innerHTML+="<font color='red'>"+r+"</font> | ";
  this.output.innerHTML+="<font color='green'>"+g+"</font> | ";
  this.output.innerHTML+="<font color='blue'>"+b+"</font> | ";
  this.output.innerHTML+="<font color='gray'>"+a+"</font>";
}

GetMeanPixelTask=function(opt_options){
  this.output=opt_options.output;
}

GetMeanPixelTask.prototype.process=function(imageData) {
  var pixels = imageData.data;
  var moyR = 0;
  var moyG = 0;
  var moyB = 0;
  var moyA = 0;
  var w = 0;
  for(var i=0; i<imageData.width; i++){
    for(var j=0; j<imageData.height; j++){
      moyR += pixels[w++];
      moyG += pixels[w++];
      moyB += pixels[w++];
      moyA += pixels[w++];
    }
  }
  w /= 4;
  moyR /= w;
  moyG /= w;
  moyB /= w;
  moyA /= w;
  this.output.innerHTML="Moyenne : ";
  this.output.innerHTML+="<font color='red'>"+Math.round(moyR)+"</font> | ";
  this.output.innerHTML+="<font color='green'>"+Math.round(moyG)+"</font> | ";
  this.output.innerHTML+="<font color='blue'>"+Math.round(moyB)+"</font> | ";
  this.output.innerHTML+="<font color='gray'>"+Math.round(moyA)+"</font>";
}

/*
  pixels_features.mean_rgb_afactor_per_region
  - computes RGB mean of all pixels considering A as a weight of RGB channels
  within opt_options.x0 .y0 .dx .dy
  - if opt_options missing partially,
      replace partially with defaults 0, 0, imageData.width, imageData.height
  - returns undefined if none available
*/
pixels_features.mean_rgb_afactor_per_region=function(imageData,opt_options) {
  x0=opt_options&&opt_options.x0?opt_options.x0:0;
  y0=opt_options&&opt_options.y0?opt_options.y0:0;
  dx=opt_options&&opt_options.dx?opt_options.dx:imageData.width;
  dy=opt_options&&opt_options.dy?opt_options.dy:imageData.height;


  var mean=[];
  mean[0]=0; mean[1]=0; mean[2]=0;
  var pos=0; var count=0;
  for (var y=y0;y<y0+dy;y++)
    for (var x=x0;x<x0+dx;x++) {
      pos=(y*imageData.width+x)<<2;
      for (var i=0;i<3;i++) {
        mean[i]+=(imageData.data[pos+i]*imageData.data[pos+3]);
      }
      count++;
    }
  if (count>0) {
    for (var i=0;i<3;i++) {
      mean[i]=Math.round(mean[i]/count);
    }
    return mean;
  }
  return undefined;
}
