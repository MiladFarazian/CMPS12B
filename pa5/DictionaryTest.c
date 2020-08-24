//DictionaryTest.c

#include<stdio.h>
#include <stdlib.h>
#include<string.h>
#include<"Dictionary.h">

#define MAX_LEN 180

int main(int argc, char* argv[1]){
	Dictionary A = new Dictionary();
	char* k;
	char* v;
	char* number = {"1","2","3","4","5"};
	char* letter = {"M","i","l","a","d"};

	for(int i = 0; i < 5; i++){
		insert(A, number[i], letter[i]);
	}
}