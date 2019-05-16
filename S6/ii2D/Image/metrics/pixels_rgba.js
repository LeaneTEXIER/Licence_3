var pixel_metrics={};

/*
    pixel_metrics.rgb_edist - computes euclidian distance between two rgb pixels
*/
pixel_metrics.rgba_edist=function(pixel_rgba1, pixel_rgba2) {
  var dist_fun=function(x,y){return x-y};
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixel_rgba1,pixel_rgba2,dist_fun);
}

/*
    pixel_metrics.rgb_edist - computes euclidian distance between two grids
    containing in each cell an rgb pixel
*/
pixel_metrics.grid_rgba_edist=function(pixels_rgba_grid1, pixels_rgba_grid2) {
  var dist_fun=pixel_metrics.rgba_edist;
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixels_rgba_grid1.cells,pixels_rgba_grid2.cells,dist_fun);
}
