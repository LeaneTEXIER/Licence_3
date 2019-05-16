echo "<html><body>"
count=1;
while read img ;
do 
echo "<img id='input${count}' src='$img'></img>"
count=$(( $count + 1 ))
done
echo "</body></html>"
