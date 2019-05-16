var pixels_features={};

/*
  pixels_features.mean_rgb_afactor_per_circle
  - computes RGB mean of all pixels considering A as a weight of RGB channels
  within opt_options.x0 .y0 .dx .dy
  - if opt_options missing partially,
      replace partially with defaults 0, 0, imageData.width, imageData.height
  - returns undefined if none available
*/
pixels_features.mean_rgb_afactor_per_circle=function(imageData,opt_options) {
  x0=opt_options&&opt_options.x0?opt_options.x0:0;
  y0=opt_options&&opt_options.y0?opt_options.y0:0;
  dx=opt_options&&opt_options.dx?opt_options.dx:imageData.width;
  dy=opt_options&&opt_options.dy?opt_options.dy:imageData.height;


  var mean=[];
  mean[0]=0; mean[1]=0; mean[2]=0;
  var pos=0; var count=0;
  var rayon;
  if (dy>dx){
    rayon = dx/2;
  }
  else{
    rayon = dy/2;
  }
  var centre_x = (dx/2)+x0;
  var centre_y = (dy/2)+y0;
  for (var y=y0;y<y0+dy;y++)
    for (var x=x0;x<x0+dx;x++) {
      if(Math.sqrt(Math.pow((x-centre_x),2)+Math.pow((y-centre_y),2))<=rayon){
        pos=(y*imageData.width+x)<<2;
        for (var i=0;i<3;i++) {
          mean[i]+=(imageData.data[pos+i]*imageData.data[pos+3]);
        }
        count++;
      }
    }
  if (count>0) {
    for (var i=0;i<3;i++) {
      mean[i]=Math.round(mean[i]/count);
    }
    return mean;
  }
  return undefined;
}
