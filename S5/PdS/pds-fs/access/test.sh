#ENOENT
./maccess -wv timoleon

#ENAMETOOLONG
./maccess -wv qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqsssssssssdqqqqqqqqqqqqqqqqqqq

#ENOTDIR
./maccess -vw ./Makefile/blabla

#EACCES
mkdir interdit
touch interdit/timoleon
chmod -wxr interdit
./maccess -wvxr ./interdit/timoleon
chmod +wxr interdit
rm ./interdit/timoleon
rm -d interdit
