I=1
while [ $I -lt 253 ]
do
	sed 's/data.js/data'$I'.js/g' page.html > page$I.html
	I=$((I+1))
done
