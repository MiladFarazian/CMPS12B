#------------------------------------------------------------------------------
# Another Makefile with macros
#------------------------------------------------------------------------------
JAVASRC = Queens.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Queens
CLASSES = Queens.class
JARFILE = Queens
SUBMIT = submit cmps012b-pt.w19 $(ASSIGNMENT)
ASSIGNMENT = pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/$(ASSIGNMENT)/mfarazia

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)