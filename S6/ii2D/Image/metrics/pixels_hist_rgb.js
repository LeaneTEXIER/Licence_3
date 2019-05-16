var pixel_metrics={};

/*
    pixel_metrics.hist_rgb_edist - computes euclidian distance between two hist_rgb pixels
*/
pixel_metrics.hist_rgb_edist=function(pixel_hist_rgb1, pixel_hist_rgb2) {
  var dist_fun=function(x,y){return x-y};
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixel_hist_rgb1,pixel_hist_rgb2,dist_fun);
}

/*
    pixel_metrics.hist_rgb_edist - computes euclidian distance between two grids
    containing in each cell an hist_rgb pixel
*/
pixel_metrics.grid_hist_rgb_edist=function(pixels_hist_rgb_grid1, pixels_hist_rgb_grid2) {
  var dist_fun=pixel_metrics.hist_rgb_edist;
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixels_hist_rgb_grid1.cells,pixels_hist_rgb_grid2.cells,dist_fun);
}
