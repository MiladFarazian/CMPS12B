#------------------------------------------------------------------------------
# Another Makefile with macros
#------------------------------------------------------------------------------
JAVASRC = Search.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Search
CLASSES = Search.class
JARFILE = Search
SUBMIT = submit cmps012b-pt.w19 pa1

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
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa1/mfarazia

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)