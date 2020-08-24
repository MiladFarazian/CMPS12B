# Makefile for hello.c with macros

NAME = lab3
FLAGS   = -std=c99 -Wall
SOURCES = README Makefile $(CSOURCES)
CSOURCES = FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.w19 $(NAME)

all: $(EXEBIN)

$ (EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(CSOURCES)
	gcc -c $(FLAGS) $(CSOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/$(NAME)/mfarazia

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
