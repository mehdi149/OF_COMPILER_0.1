#!/bin/bash
declare -i steprule
steprule=100
resultfile=entries_compression_result
file_rcf=$1
num_rule=$2
file_update=$3
num_update=$4
declare -i support
if [ "$num_rule" -le 1000 ]
	then
	support=$(($num_rule/10))
elif [ "$num_rule" -gt 1000 ]
	then
	support=$(($num_rule/27))
fi
declare -i update
update=10
Excution=-1
test=$support
while [ "$Excution" -ne 0 ]
	do
	echo "rule : $rule ; threshold : $test ; update : $update"
	java -jar ofcomfpiler.jar ./result/$resultfile $file_rcf $file_update $test 1 $num_rule $num_update
	#echo  "java -jar compiler.jar ./result/$resultfile ./input/benchmark_$rule.rcf ./input/benchmark_up_$update.rcf $test $rule	$update"
	Excution=$?		
	test=$(($test+1))
	if [ $test -ge $(($support+10)) ]  
		then
		break
	fi
	echo "Excution result is $Excution"
done
rm -rf *~
