var pixel_metrics={};

/*
    pixel_metrics.hist_gray_edist - computes euclidian distance between two hist_gray pixels
*/
pixel_metrics.hist_gray_edist=function(pixel_hist_gray1, pixel_hist_gray2) {
  var dist_fun=function(x,y){return x-y};
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixel_hist_gray1,pixel_hist_gray2,dist_fun);
}

/*
    pixel_metrics.hist_gray_edist - computes euclidian distance between two grids
    containing in each cell an hist_gray pixel
*/
pixel_metrics.grid_hist_gray_edist=function(pixels_hist_gray_grid1, pixels_hist_gray_grid2) {
  var dist_fun=pixel_metrics.hist_gray_edist;
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixels_hist_gray_grid1.cells,pixels_hist_gray_grid2.cells,dist_fun);
}
