echo "Start Entry Point Script in $API_WORKDIR/$API_DIRNAME"  

cd $API_WORKDIR/$API_DIRNAME
echo "Cleaning Project..."
sh ./gradlew clean 
echo "Building Project..."
sh ./gradlew build 

#Put up spring application with war file 
java -jar $API_WORKDIR/$API_DIRNAME/build/libs/*release.war

echo "End Entry Point Script" 