var pixels_similarity={}

pixels_similarity.MeanRGBSimilarityTask=function(dataset,opt_options) {
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.mean_rgb,
    pixel_metrics.rgb_edist,
    opt_options
  );
}

pixels_similarity.GridMeanRGBSimilarityTask=function(dataset,opt_options){
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.grid_mean_rgb,
    pixel_metrics.grid_rgb_edist,
    opt_options
  );
}
