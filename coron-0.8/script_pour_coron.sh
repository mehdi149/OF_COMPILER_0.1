#/!/bin/bash
## example of running the script ./script.sh Myexample/useCase1.rcf 3
./core03_leco.sh $1 $2 -names -order -alg:dtouch -method:snow -ext -xml -of:Myexample/tmp.xml
xmllint --format Myexample/tmp.xml >> Myexample/output.xml
