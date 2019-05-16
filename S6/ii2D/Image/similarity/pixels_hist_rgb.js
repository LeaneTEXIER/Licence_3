var pixels_similarity={}

pixels_similarity.MeanHistRGBSimilarityTask=function(dataset,opt_options) {
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.mean_hist_rgb,
    pixel_metrics.hist_rgb_edist,
    opt_options
  );
}

pixels_similarity.GridMeanHistRGBSimilarityTask=function(dataset,opt_options){
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.grid_mean_hist_rgb,
    pixel_metrics.grid_hist_rgb_edist,
    opt_options
  );
}
