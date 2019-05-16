var morpho_croix_effects={}

morpho_croix_effects.DilatationCroixWindow = function(opt_options) {
  this.window_width=opt_options.window_width;
  this.window_height=opt_options.window_height;
}

morpho_croix_effects.DilatationCroixWindow.prototype.process=function(in_imgData,out_imgData) {
  return generic_effect.apply_region_filter(
      in_imgData,out_imgData,this.window_width,this.window_height,
      morpho_croix_filters.max_from_region
    );
}

morpho_croix_effects.ErosionCroixWindow = function(opt_options) {
  this.window_width=opt_options.window_width;
  this.window_height=opt_options.window_height;
}

morpho_croix_effects.ErosionCroixWindow.prototype.process=function(in_imgData,out_imgData) {
  generic_effect.apply_region_filter(
      in_imgData,out_imgData,this.window_width,this.window_height,
      morpho_croix_filters.min_from_region);
}

morpho_croix_effects.DilatationCroixBlueWindow = function(opt_options) {
  this.window_width=opt_options.window_width;
  this.window_height=opt_options.window_height;
}

morpho_croix_effects.DilatationCroixBlueWindow.prototype.process=function(in_imgData,out_imgData) {
  return generic_effect.apply_region_filter(
      in_imgData,out_imgData,this.window_width,this.window_height,
      morpho_croix_filters.max_blue_from_region
    );
}

morpho_croix_effects.ErosionCroixBlueWindow = function(opt_options) {
  this.window_width=opt_options.window_width;
  this.window_height=opt_options.window_height;
}

morpho_croix_effects.ErosionCroixBlueWindow.prototype.process=function(in_imgData,out_imgData) {
  generic_effect.apply_region_filter(
      in_imgData,out_imgData,this.window_width,this.window_height,
      morpho_croix_filters.min_blue_from_region);
}
