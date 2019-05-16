var pixel_metrics={};

/*
    pixel_metrics.gray_edist - computes euclidian distance between two gray pixels
*/
pixel_metrics.gray_edist=function(pixel_gray1, pixel_gray2) {
  var dist_fun=function(x,y){return x-y};
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixel_gray1,pixel_gray2,dist_fun);
}

/*
    pixel_metrics.gray_edist - computes euclidian distance between two grids
    containing in each cell an gray pixel
*/
pixel_metrics.grid_gray_edist=function(pixels_gray_grid1, pixels_gray_grid2) {
  var dist_fun=pixel_metrics.gray_edist;
  return generic_metrics.euclidian_distance_btw_feature_vectors(pixels_gray_grid1.cells,pixels_gray_grid2.cells,dist_fun);
}
