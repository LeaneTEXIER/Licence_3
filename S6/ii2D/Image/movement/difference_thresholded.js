var diff={}

diff.ThresholdedDifferenceImage=function(opt_options) {
  this.seuil = opt_options.seuil;

  this.difference = document.createElement("canvas");
  this.previous = document.createElement("canvas");

  this.width=opt_options.width;
  this.height=opt_options.height;

  this.difference.width=this.width; this.difference.height=this.height;
  this.previous.width=this.width; this.previous.height=this.height;

  this.difference_ctxt = this.difference.getContext("2d");
  this.previous_ctxt = this.previous.getContext("2d");
}

diff.ThresholdedDifferenceImage.prototype.set_first_frame_imgData=function(imgData) {
  this.previous_ctxt.putImageData(imgData,0,0);
}

diff.ThresholdedDifferenceImage.prototype.set_first_frame_from_eltId=function(eltId) {
  var elt=document.getElementById(eltId);
  var cvs=document.createElement("canvas");
  cvs.width=elt.width; cvs.height=elt.height;
  var ctxt=cvs.getContext("2d");
  ctxt.drawImage(elt,0,0);
  this.set_first_frame_imgData(ctxt.getImageData(0,0,cvs.width,cvs.height));
}

diff.ThresholdedDifferenceImage.prototype.process=function(in_imgData, out_imgData) {
  var previous_imgData = this.previous_ctxt.
                            getImageData(0, 0, this.width, this.height);
  var w=0;
  for (var x=0; x<this.width ; x++)
    for (var y=0; y<this.height ; y++) {
      for (var i=0; i<3; i++) {
        out_imgData.data[w+i] =
        Math.abs(in_imgData.data[w+i] - previous_imgData.data[w+i]);
      }

      if(out_imgData.data[w]!=0 || out_imgData.data[w+1]!=0 || out_imgData.data[w+2]!=0){
        if(Math.abs((in_imgData.data[w]+in_imgData.data[w+1]+in_imgData.data[w+2])/3)>this.seuil){
          for (var i=0; i<3; i++) {
            out_imgData.data[w+i]=255;
          }
        }
        else{
          for (var i=0; i<3; i++) {
            out_imgData.data[w+i]=0;
          }
        }
      }
      out_imgData.data[w+3] = 255;
      w+=4;
    }
  this.previous_ctxt.putImageData(in_imgData,0,0);
}
