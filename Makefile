STYLE_PARAMS = -A2 -T4 -xn -xc -xV -C -S -N -xU  -Y -p -xg -H -y -xb

ALL_TARGETS = ds array element sle dle csle cdle mle tree bintree bst avl gral \
					gram  edge elvis linkvis bridges scomm color dformat bridges connect

JAVA_SRC = ./src/main/java/bridges
all:
	cd  $(JAVA_SRC)/base; astyle $(STYLE_PARAMS) *.java;
	cd  $(JAVA_SRC)/connect; astyle $(STYLE_PARAMS) *.java;
	cd  $(JAVA_SRC)/data_src_dependent; astyle $(STYLE_PARAMS) *.java;
	cd  $(JAVA_SRC)/validation; astyle $(STYLE_PARAMS) *.java;

clean:
	cd  $(JAVA_SRC)/base; rm -f *.orig
	cd  $(JAVA_SRC)/connect; rm -f *.orig
	cd  $(JAVA_SRC)/data_src_dependent; rm -f *.orig
	cd  $(JAVA_SRC)/validation; rm -f *.orig


