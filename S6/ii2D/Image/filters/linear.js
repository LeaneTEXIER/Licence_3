var linear_filters={}

linear_filters.identity=function(imgData,x0,y0,region_width,region_height) {
  var w=((y0+Math.round(region_height/2))*imgData.width+(x0+Math.round(region_width/2)))<<2;
  return [imgData.data[w],imgData.data[w+1],imgData.data[w+2],imgData.data[w+3]];
}
