echo "******************************************************************";
echo "tail sans paramètre : ";
tail mtail.c;
echo "------------------------------------------------------------------";
echo "mon mtail sans paramètre : ";
./mtail mtail.c;
echo "------------------------------------------------------------------";
echo "difference entre les 2 tails sans paramètre: "
tail mtail.c > tailtest.txt;
./mtail mtail.c > mtailtest.txt;
diff -s tailtest.txt mtailtest.txt
echo "******************************************************************";
echo "tail avec 5 : ";
tail -5 mtail.c;
echo "------------------------------------------------------------------";
echo "mon mtail avec 5 : ";
./mtail -n 5 mtail.c;
echo "------------------------------------------------------------------";
echo "difference entre les 2 tails de taille 5: "
tail -5 mtail.c > tailtest.txt;
./mtail -n 5 mtail.c > mtailtest.txt;
diff -s tailtest.txt mtailtest.txt
echo "******************************************************************";
