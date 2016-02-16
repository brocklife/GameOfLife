mvn install:install-file -Dfile=./lib/muskel-core-1.0.0.jar -DgroupId=it.reactive.muskel \
-DartifactId=muskel-core -Dversion=1.0.0 -Dpackaging=jar\

mvn install:install-file -Dfile=./lib/skandium-1.0.0.jar -DgroupId=cl.niclabs \
-DartifactId=skandium -Dversion=1.0.0 -Dpackaging=jar

mvn install
