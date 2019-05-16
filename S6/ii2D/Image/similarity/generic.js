var generic_similarity={}

generic_similarity.SimilarityTask=
  function(dataset, descriptor_func,similarity_metric_func, opt_options){

  this.dataset=dataset;

  this.descriptor_func=descriptor_func;
  this.desc_opt_options=opt_options.desc_opt_options;

  this.dataset_descriptors=[];
  for (var idx in this.dataset.imageDatas) {
    this.dataset_descriptors[idx]=
      this.descriptor_func(this.dataset.imageDatas[idx],this.desc_opt_options);
    console.log(this.dataset_descriptors[idx]);
  }

  this.similarity_metric_func=similarity_metric_func;
  this.similarity_metric_opt_options=opt_options.similarity_metric_opt_options;
}

generic_similarity.SimilarityTask.prototype.process_descriptor=function(in_descriptor)
{
  var sim=[],order=[];
  for (var idx in this.dataset_descriptors) {
    sim[idx]=this.similarity_metric_func(
      in_descriptor,this.dataset_descriptors[idx],this.metric_opt_options);
    order[idx]=idx;

  }
  for (var idx1 in order)
    for (var idx2 in order)
      if (sim[order[idx1]]<sim[order[idx2]])
      { aux=order[idx1]; order[idx1]=order[idx2]; order[idx2]=aux; }

  var res=[];
  for (var idx in order) res[idx]={idx:order[idx],sim:sim[order[idx]]}
  return res;
}

generic_similarity.SimilarityTask.prototype.process=function(in_imageData){
  var in_descriptor=this.descriptor_func(in_imageData,this.desc_opt_options);
  return this.process_descriptor(in_descriptor);
}


generic_similarity.SimilarityTask.prototype.get_dataset_descriptor=function(idx) {
  return this.dataset_descriptors[idx];
}
