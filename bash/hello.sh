####!/bin/bash
#### https://devhints.io/bash#functions

name="John"
echo ${name}

###    IF    ###
ENV1=dev2
if [[ "dev1" == ${ENV1} ]] || [[ "dev2" == ${ENV1} ]]; then
  echo "its dev"
else
  echo "its not dev"
fi

if [[ -z "$string" ]]; then
  echo "String is empty"
elif [[ -n "$string" ]]; then
  echo "String is not empty"
fi
###    IF    ###

###    FUNC    ####
func(){
    echo func $1
}
func par
###    FUNC    ####

echo "I'm in $(pwd)"

###    LOOPS    ###
for i in $(pwd)/.*; do
  echo $i
done

for i in {1..5}; do
    echo "Welcome $i"
done
###    LOOPS    ###

###    SLICE    ###
A=( a b c d e f )
echo "${A[@]:1:2}"    # slice b c
echo "${A[0]}"        # a
echo "${A[@]:1}"      # slice to the end of the array starting from b
###    SLICE    ###