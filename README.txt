There are two ways to run it:

1) inside eclipse run Java Application for the class ZipcodeSorter and set argument. 
   E.g the argument can be [90500,90600] [90700,90800] [90100,90200] [90300,90400] [90150,90660]

2) Build using maven
	mvn clean install
	
   Jar zpsorter-0.0.1-SNAPSHOT.jar will be created in maven repository. Copy it to a folder and cd to it in terminal,
   then run java -jar zpsorter-0.0.1-SNAPSHOT.jar [90500,90600] [90700,90800] [90100,90200] [90300,90400] [90150,90660]
   The result is displayed.

