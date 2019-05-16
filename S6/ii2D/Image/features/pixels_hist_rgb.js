var pixels_features={};

/*
  pixels_features.mean_hist_rgb
  - computes HistRGB mean of all pixels having A>0 within imageData
*/
pixels_features.mean_hist_rgb=function(imageData,opt_options) {
  return pixels_features.mean_hist_rgb_per_region(imageData,{x0:0,y0:0,dx:imageData.width,dy:imageData.height});
}

/*
  pixels_features.mean_hist_rgb_per_region
  - computes HistRGB mean of all pixels having A>0 within opt_options.x0 .y0 .dx .dy
  - if opt_options missing partially,
      replace partially with defaults 0, 0, imageData.width, imageData.height
  - returns undefined if none available
*/
pixels_features.mean_hist_rgb_per_region=function(imageData,opt_options) {
  x0=opt_options&&opt_options.x0?opt_options.x0:0;
  y0=opt_options&&opt_options.y0?opt_options.y0:0;
  dx=opt_options&&opt_options.dx?opt_options.dx:imageData.width;
  dy=opt_options&&opt_options.dy?opt_options.dy:imageData.height;

  var nb_bins = 8;
  var etendue = 256/nb_bins;
  var hist_rgb=[];
  for (var i=0; i<(nb_bins*3); i++){
    hist_rgb[i]=0;
  }
  var coul;
  var no_bin;
  var pos=0; var count=0;
  for (var y=y0;y<y0+dy;y++)
    for (var x=x0;x<x0+dx;x++) {
      pos=(y*imageData.width+x)<<2;
      if (imageData.data[pos+3]>0) {
        for (var i=0;i<3;i++) {
          coul = imageData.data[pos+i];
          no_bin =Math.trunc(coul/etendue);
          hist_rgb[no_bin+i]++;
        }
        count++;
      }
    }
    if (count>0) {
      return hist_rgb;
    }
  return undefined;
}

/*
  pixels_features.grid_mean_hist_rgb
  - computes HistRGB mean of all pixels having A>0 in all grid cells
  - grid params are defined as opt_options.nb_lines*opt_options.nb_columns
  - returns a generic_features.grid_descriptor (grid.cells - array)
*/
pixels_features.grid_mean_hist_rgb=function(imageData,opt_options) {
  return generic_features.grid_descriptor(imageData,pixels_features.mean_hist_rgb_per_region,opt_options);
}
