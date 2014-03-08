LIBRARYDIR =../stanford-corenlp-full-2012-11-12
VERSIONNO =1.3.4
INPUT =input7
JAVAFLAGS = -d ../bin

ifeq ($(VERSIONNO),1.3.4)
LIBRARYDIR =../stanford-corenlp-full-2012-11-12
endif

ifeq ($(VERSIONNO),3.3.1)
LIBRARYDIR =../stanford-corenlp-full-2014-01-04
endif
 
compile:
	javac -cp $(LIBRARYDIR)/stanford-corenlp-$(VERSIONNO).jar:$(LIBRARYDIR)/stanford-corenlp-$(VERSIONNO)-models.jar:$(LIBRARYDIR)/xom.jar:$(LIBRARYDIR)/joda-time.jar:$(LIBRARYDIR)/jollyday.jar:$(LIBRARYDIR)/ejml-0.23.jar:. *.java $(JAVAFLAGS)

compile_debug:
	javac -g -cp $(LIBRARYDIR)/stanford-corenlp-$(VERSIONNO).jar:$(LIBRARYDIR)/stanford-corenlp-$(VERSIONNO)-models.jar:$(LIBRARYDIR)/xom.jar:$(LIBRARYDIR)/joda-time.jar:$(LIBRARYDIR)/jollyday.jar:$(LIBRARYDIR)/ejml-0.23.jar:. *.java $(JAVAFLAGS)

run:
	java -cp $(LIBRARYDIR)/stanford-corenlp-$(VERSIONNO).jar:$(LIBRARYDIR)/stanford-corenlp-$(VERSIONNO)-models.jar:$(LIBRARYDIR)/xom.jar:$(LIBRARYDIR)/joda-time.jar:$(LIBRARYDIR)/jollyday.jar:../bin:. -Xmx1g StanfordCoreNlpDemo  $(INPUT).txt $(INPUT).out $(INPUT).xml | tee log

