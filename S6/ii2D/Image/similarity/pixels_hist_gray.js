var pixels_similarity={}

pixels_similarity.MeanHistGraySimilarityTask=function(dataset,opt_options) {
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.mean_hist_gray,
    pixel_metrics.hist_gray_edist,
    opt_options
  );
}

pixels_similarity.GridMeanHistGraySimilarityTask=function(dataset,opt_options){
  this.__proto__.__proto__=new generic_similarity.SimilarityTask(
    dataset,
    pixels_features.grid_mean_hist_gray,
    pixel_metrics.grid_hist_gray_edist,
    opt_options
  );
}
