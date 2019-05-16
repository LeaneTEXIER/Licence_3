var pixels_similarity={}

pixels_similarity.MeanRGBASimilarityTask=function(dataset,opt_options) {
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.mean_rgba,
    pixel_metrics.rgba_edist,
    opt_options
  );
}

pixels_similarity.GridMeanRGBASimilarityTask=function(dataset,opt_options){
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.grid_mean_rgba,
    pixel_metrics.grid_rgba_edist,
    opt_options
  );
}
