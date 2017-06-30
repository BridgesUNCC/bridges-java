STYLE_PARAMS = -A2 -T4 -xn -xc -xV -C -S -N -xU  -Y -p -xg -H -y -xb

ALL_TARGETS = ds array element sle dle csle cdle mle tree bintree bst avl gral \
					gram  edge elvis linkvis bridges scomm color dformat bridges connect

ds:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/DataStruct.java
array:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/Array.java
element:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/Element.java
sle:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/SLelement.java
dle:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/DLelement.java
csle:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/CircSLelement.java
cdle:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/CircDLelement.java
mle:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/MLelement.java
tree:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/TreeElement.java
bintree:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/BinTreeElement.java
bst:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/BSTElement.java
avl:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/AVLTreeElement.java
gral:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/GraphAdjList.java
gram:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/GraphAdjMatrix.java
edge:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/Edge.java
elvis:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/ElementVisualizer.java
linkvis:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/LinkVisualizer.java
bridges:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/connect/Bridges.java
connect:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/connect/Connector.java
color:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/base/Color.java
dsrc:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/data_src_dependent/DataSource.java
dformat:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/connect/DataFormatter.java

# data source dependent
game:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/data_src_dependent/Game.java
gutenberg:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/data_src_dependent/GutenbergBook.java
shakespeare:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/data_src_dependent/Shakespeare.java
eq:
	astyle $(STYLE_PARAMS) ./src/main/java/edu/uncc/cs/bridges_v21/data_src_dependent/EarthquakeUSGS.java

all:
	make $(ALL_TARGETS)
