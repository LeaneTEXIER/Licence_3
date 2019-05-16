# multif
echo ""
echo "Tests de multif"
echo "------------------------------------------------------------------";
echo "multif de true et true: doit retourner true(donc 0)";
./multif true true;
echo "------------------------------------------------------------------";
echo "multif de true et false: doit retourner false(donc 1)";
./multif true false;
echo "------------------------------------------------------------------";
echo "multif de false et true: doit retourner false(donc 1)";
./multif false true;
echo "------------------------------------------------------------------";
echo "multif de false et false: doit retourner false(donc 1)";
./multif false false;
echo "------------------------------------------------------------------";
echo "multif de false, true et false: doit retourner false(donc 1)";
./multif false true false;
echo ""
echo "******************************************************************";
#race
echo "Test de la course"
echo "------------------------------------------------------------------";
./race
