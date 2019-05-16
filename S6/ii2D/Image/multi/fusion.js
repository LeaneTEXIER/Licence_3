MeanFuseMultiImagesTask=function(opt_options){}

MeanFuseMultiImagesTask.prototype.process_multi=function(imageDatas,fused) {
  var w=0;
  var l = imageDatas.length;
  for (var y=0;y<fused.height;y++) {
    for (var x=0;x<fused.width;x++) {
      fused.data[w]=0; fused.data[w+1]=0; fused.data[w+2]=0; fused.data[w+3]=0;
      for (idx in imageDatas) {
        for (var i=0; i<4; i++) {
          fused.data[w+i]+=imageDatas[idx].data[w+i]*(1/l);
        }
      }
      w+=4;
    }
  }
}

FirstMoreMultiImagesTask=function(opt_options){}

FirstMoreMultiImagesTask.prototype.process_multi=function(imageDatas,fused) {
  var w=0;
  var l = imageDatas.length;
  l++;
  for (var y=0;y<fused.height;y++) {
    for (var x=0;x<fused.width;x++) {
      fused.data[w]=0; fused.data[w+1]=0; fused.data[w+2]=0; fused.data[w+3]=0;
      for (idx in imageDatas) {
        for (var i=0; i<4; i++) {
          if(idx==0){
            // compte 2 fois l'image 1 pour lui donner plus de poids
            fused.data[w+i]+=(imageDatas[idx].data[w+i])*(1/l);
          }
          fused.data[w+i]+=imageDatas[idx].data[w+i]*(1/l);
        }
      }
      w+=4;
    }
  }
}

FuseIfRSecSupRFirstTask=function(opt_options){}

FuseIfRSecSupRFirstTask.prototype.process_multi=function(imageDatas,fused) {
  var w=0;
  for (var y=0;y<fused.height;y++) {
    for (var x=0;x<fused.width;x++) {
      fused.data[w]=0; fused.data[w+1]=0; fused.data[w+2]=0; fused.data[w+3]=0;
      if(imageDatas[1].data[w]>imageDatas[0].data[w]){
        for (var i=0; i<4; i++) {
          fused.data[w+i]+=imageDatas[0].data[w+i]*0.5;
          fused.data[w+i]+=imageDatas[1].data[w+i]*0.5;
        }
      }
      else{
        for (var i=0; i<4; i++) {
          fused.data[w+i]+=imageDatas[0].data[w+i];
        }
      }
      w+=4;
    }
  }
}
