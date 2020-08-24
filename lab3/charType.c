#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>

#define MAX_STRING_LENGTH 255

void extract_chars(char* s, char* a, char* d, char* p, char* w){
	int i = 0;
	int ai = 0;
	int di = 0;
	int pi = 0;
	int wi = 0;

	while(i < strlen(s)){
		if(isalpha(s[i])){
			a[ai] = s[i];
			ai++;
		}else if(isdigit(s[i])){
			d[di] = s[i];
			di++;
		}else if(ispunct(s[i])){
			p[pi] = s[i];
			pi++;
		}else if(isspace(s[i])){
			w[wi] = s[i];
			wi++;
		}
		i++;

	}
	a[ai] = '\0';
	d[di] = '\0';
	p[pi] = '\0';
	w[wi] = '\0';
}

int main(int argc, char* argv[]){
	int lineNumber = 1;
	FILE* in;
	FILE* out;
	char* s;
	char* a;
	char* d;
	char* p;
	char* w;

	if(argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	in = fopen(argv[1], "r");
	if(in==NULL){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	out = fopen(argv[2], "w");
	if(out==NULL){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	s = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	a = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	d = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	p = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	w = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	assert( s != NULL && a !=NULL && d != NULL && p != NULL && w!= NULL );

	while(fgets(s,MAX_STRING_LENGTH , in) != NULL){
		extract_chars(s, a, d, p, w);
		fprintf(out, "line  %d contains:\n", lineNumber);
		fprintf(out, "%d alphabetic number: %s\n", (int) strlen(a), a);
		fprintf(out, "%d numeric characters: %s\n", (int) strlen(d), d);
		fprintf(out, "%d punctuation characters: %s\n", (int) strlen(p), p);
		fprintf(out, "%d whitespace characters: %s\n", (int) strlen(w),  w);
		lineNumber++;
	}

	fclose(in);
	fclose(out);
	free(s);
	free(a);
	free(d);
	free(p);
	free(w);
}

