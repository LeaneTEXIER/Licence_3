var blobs={}

blobs.Pixel8ConnectivityBlobs=function(opt_options) {
  this.metric = opt_options.metric;
  this.threshold=opt_options && opt_options.threshold ? opt_options.threshold : 0;
}

blobs.Pixel8ConnectivityBlobs.prototype.analyse_neighbours=function(in_imgData, x0, y0) {
  w0=((y0*in_imgData.width)+x0)<<2;
  var pixel0=[in_imgData.data[w0],in_imgData.data[w0+1],in_imgData.data[w0+2]];
  var local_connectivity=[]
  for (var dy=-1;dy<1;dy++) {
    if (0>(dy+y0) || (dy+y0)>=in_imgData.height) continue;
    for (var dx=-1;dx<(dy<0?2:1);dx++) {
      if (0>(dx+x0) || (dx+x0)>=in_imgData.width) continue;
      var w=((y0+dy)*in_imgData.width+x0+dx)<<2;
      if (!this.component[w>>2]) continue;
      var pixel=[in_imgData.data[w],in_imgData.data[w+1],in_imgData.data[w+2]]
      if (this.metric(pixel0,pixel) <= this.threshold) {
        var component=this.components[this.component[w>>2]];
        if (local_connectivity.indexOf(component)==-1) {
          local_connectivity.push(component);
        }
      }
    }
  }
  local_connectivity.sort(function(a,b){return a<b?-1:((a==b)?0:1)});
  return local_connectivity;
}

blobs.Pixel8ConnectivityBlobs.prototype.update_bbox=function(id,bbox,x,y,mean) {
  if (bbox.x0>x) {bbox.dx+=bbox.x0-x; bbox.x0=x;}
  if (bbox.y0>y) {bbox.dy+=bbox.y0-y; bbox.y0=y;}
  if (bbox.x0+bbox.dx<x) bbox.dx=x-bbox.x0;
  if (bbox.y0+bbox.dy<y) bbox.dy=y-bbox.y0;
  bbox.count++;
  for (i=0;i<3;i++)
    bbox.mean[i]=((bbox.count-1)*bbox.mean[i]+mean[i])/bbox.count;
  return bbox;
}

blobs.Pixel8ConnectivityBlobs.prototype.process=function(in_imgData,out_imgData) {
  var blobs_arr=[]
  this.component=[]; this.components=[];
  this.nb_components=0;
  var w=0;
  for (var y=0; y < in_imgData.height; y++) {
    for (var x=0; x < in_imgData.width; x++)
    {
      w=(y*in_imgData.width)+x;
      var neighbours_connectivity=this.analyse_neighbours(in_imgData, x, y);
      if (neighbours_connectivity.length==1) {
        this.component[w]=(neighbours_connectivity[0]);
      } else if (neighbours_connectivity.length>1) {
        for (var i=1;i<neighbours_connectivity.length;i++) {
          this.components[neighbours_connectivity[i]]=
              this.components[neighbours_connectivity[0]];
        }
        this.component[w]=(this.components[neighbours_connectivity[0]]);
      } else {
          this.components[this.nb_components]=this.nb_components;
          this.component[w]=(this.nb_components);
          this.nb_components++;
      }
    }
  }

  var w=0, w4=0;
  var components_bbox=[];
  for (var y=0; y < in_imgData.height; y++)
    for (var x=0; x < in_imgData.width; x++,w++,w4+=4)
    {
      this.component[w]=this.components[this.component[w]];
      if (components_bbox[this.component[w]])
        components_bbox[this.component[w]]=
          this.update_bbox(
            this.component[w],
            components_bbox[this.component[w]],
            x,y,
            [in_imgData.data[w4],in_imgData.data[w4+1],in_imgData.data[w4+2]]);
      else
        components_bbox[this.component[w]]={
                      x0:x,y0:y,dx:0,dy:0,
                      count:1,mean:[in_imgData.data[w4],in_imgData.data[w4+1],
                      in_imgData.data[w4+2]]};
    }
  if (out_imgData.data) {
    for (idx in components_bbox) {
    Tools.strokeBBox_on_imageData(out_imgData,components_bbox[idx],components_bbox[idx].mean);
    }
  }
  return components_bbox;
}
