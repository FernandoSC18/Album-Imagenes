##Create Script to populate databse and delete data exists   
## $1 parameter can be a database name 
printf "Starter \\n"

SQL='populate_data.sql'
CURRENT_PATH=$(pwd)
touch SQL
echo "/* THIS FILE IS AUTOGENERATE WHITH create_sql_populate.sh */" > ./$SQL 
echo "" >> ./$SQL
 
if [ ! -z "$1" ]
then
    echo "/* Select database */" >> ./$SQL 
    echo "USE $1;" >> ./$SQL
    echo "" >> ./$SQL
    echo "" >> ./$SQL
fi

##Create Albums Query from folder ImagesExampleLoad
#Truncate tables 
echo "delete from \`files\`;" >> ./$SQL
echo "delete from \`image\`;" >> ./$SQL
echo "delete from \`album\`;" >> ./$SQL
echo "" >> ./$SQL

Albums=("Animales" "Coches" "Comidas" "Memes" "Naturaleza" "Videojuegos")
printf "Creating Inserts \\n"

#create Albums
countId=1
for album in "${Albums[@]}" 
do
    echo "INSERT INTO \`album\` (album_id,album_name,album_description,created_date,updated_date) " >> ./$SQL 
    echo "VALUES ($countId, '$album','Descripcion de $album',SYSDATE(),null);" >> ./$SQL     
    ((countId=countId+1))
done
echo "" >> ./$SQL

#Process repet for populate table whit same data until get high row data for 10400 records, run 243 times
for iterator in {1..243}
do 
    printf "$iterator of 243. "
    ##Create INSERTS TO [Animales]  ImagesExampleLoad
    for filename in $CURRENT_PATH/ImagesExampleLoad/Animales/*; do
        filename=$(basename -- "$filename")
        extension="${filename##*.}"
        filename="${filename%.*}"
        mimeType=$(file -b --mime-type $CURRENT_PATH/ImagesExampleLoad/Animales/$filename.$extension)
        fileSize=$(stat -c%s $CURRENT_PATH/ImagesExampleLoad/Animales/$filename.$extension)

        echo "INSERT INTO \`files\` (file_name,file_description,file_type,file_mime_type,file_size,file_data,created_date,updated_date) " >> ./$SQL 
        echo "VALUES ('$filename','Descripcion de $filename','$extension','$mimeType',$fileSize,'data:$mimeType;base64,$( base64 -w 0 $CURRENT_PATH/ImagesExampleLoad/Animales/$filename.$extension )',SYSDATE(),null);" >> ./$SQL 

        echo "INSERT INTO \`image\` (image_name,image_description,created_date,updated_date,album_id,file_id) " >> ./$SQL 
        echo "VALUES ('$iterator _ $filename','Descripcion de $iterator _ $filename',SYSDATE(),null,1, LAST_INSERT_ID());" >> ./$SQL 
    done 
    
    ##Create INSERTS TO [Coches]  ImagesExampleLoad
    for filename in $CURRENT_PATH/ImagesExampleLoad/Coches/*; do
        filename=$(basename -- "$filename")
        extension="${filename##*.}"
        filename="${filename%.*}"
        mimeType=$(file -b --mime-type $CURRENT_PATH/ImagesExampleLoad/Coches/$filename.$extension)
        fileSize=$(stat -c%s $CURRENT_PATH/ImagesExampleLoad/Coches/$filename.$extension)

        echo "INSERT INTO \`files\` (file_name,file_description,file_type,file_mime_type,file_size,file_data,created_date,updated_date) " >> ./$SQL 
        echo "VALUES ('$filename','Descripcion de $filename','$extension','$mimeType',$fileSize,'data:$mimeType;base64,$( base64 -w 0 $CURRENT_PATH/ImagesExampleLoad/Coches/$filename.$extension )',SYSDATE(),null);" >> ./$SQL 

        echo "INSERT INTO \`image\` (image_name,image_description,created_date,updated_date,album_id,file_id) " >> ./$SQL 
        echo "VALUES ('$iterator _ $filename','Descripcion de $iterator _ $filename',SYSDATE(),null,2, LAST_INSERT_ID());" >> ./$SQL 
    done 
    
    ##Create INSERTS TO [Comidas]  ImagesExampleLoad
    for filename in $CURRENT_PATH/ImagesExampleLoad/Comidas/*; do
        filename=$(basename -- "$filename")
        extension="${filename##*.}"
        filename="${filename%.*}"
        mimeType=$(file -b --mime-type $CURRENT_PATH/ImagesExampleLoad/Comidas/$filename.$extension)
        fileSize=$(stat -c%s $CURRENT_PATH/ImagesExampleLoad/Comidas/$filename.$extension)

        echo "INSERT INTO \`files\` (file_name,file_description,file_type,file_mime_type,file_size,file_data,created_date,updated_date) " >> ./$SQL 
        echo "VALUES ('$filename','Descripcion de $filename','$extension','$mimeType',$fileSize,'data:$mimeType;base64,$( base64 -w 0 $CURRENT_PATH/ImagesExampleLoad/Comidas/$filename.$extension )',SYSDATE(),null);" >> ./$SQL 

        echo "INSERT INTO \`image\` (image_name,image_description,created_date,updated_date,album_id,file_id) " >> ./$SQL 
        echo "VALUES ('$iterator _ $filename','Descripcion de $iterator _ $filename',SYSDATE(),null,3, LAST_INSERT_ID());" >> ./$SQL 
    done 
    
    ##Create INSERTS TO [Memes]  ImagesExampleLoad
    for filename in $CURRENT_PATH/ImagesExampleLoad/Memes/*; do
        filename=$(basename -- "$filename")
        extension="${filename##*.}"
        filename="${filename%.*}"
        mimeType=$(file -b --mime-type $CURRENT_PATH/ImagesExampleLoad/Memes/$filename.$extension)
        fileSize=$(stat -c%s $CURRENT_PATH/ImagesExampleLoad/Memes/$filename.$extension)

        echo "INSERT INTO \`files\` (file_name,file_description,file_type,file_mime_type,file_size,file_data,created_date,updated_date) " >> ./$SQL 
        echo "VALUES ('$filename','Descripcion de $filename','$extension','$mimeType',$fileSize,'data:$mimeType;base64,$( base64 -w 0 $CURRENT_PATH/ImagesExampleLoad/Memes/$filename.$extension )',SYSDATE(),null);" >> ./$SQL 

        echo "INSERT INTO \`image\` (image_name,image_description,created_date,updated_date,album_id,file_id) " >> ./$SQL 
        echo "VALUES ('$iterator _ $filename','Descripcion de $iterator _ $filename',SYSDATE(),null,4, LAST_INSERT_ID());" >> ./$SQL 
    done 
    
    ##Create INSERTS TO [Naturaleza]  ImagesExampleLoad
    for filename in $CURRENT_PATH/ImagesExampleLoad/Naturaleza/*; do
        filename=$(basename -- "$filename")
        extension="${filename##*.}"
        filename="${filename%.*}"
        mimeType=$(file -b --mime-type $CURRENT_PATH/ImagesExampleLoad/Naturaleza/$filename.$extension)
        fileSize=$(stat -c%s $CURRENT_PATH/ImagesExampleLoad/Naturaleza/$filename.$extension)

        echo "INSERT INTO \`files\` (file_name,file_description,file_type,file_mime_type,file_size,file_data,created_date,updated_date) " >> ./$SQL 
        echo "VALUES ('$filename','Descripcion de $filename','$extension','$mimeType',$fileSize,'data:$mimeType;base64,$( base64 -w 0 $CURRENT_PATH/ImagesExampleLoad/Naturaleza/$filename.$extension )',SYSDATE(),null);" >> ./$SQL 

        echo "INSERT INTO \`image\` (image_name,image_description,created_date,updated_date,album_id,file_id) " >> ./$SQL 
        echo "VALUES ('$iterator _ $filename','Descripcion de $iterator _ $filename',SYSDATE(),null,5, LAST_INSERT_ID());" >> ./$SQL 
    done 
    
    ##Create INSERTS TO [Videojuegos]  ImagesExampleLoad
    for filename in $CURRENT_PATH/ImagesExampleLoad/Videojuegos/*; do
        filename=$(basename -- "$filename")
        extension="${filename##*.}"
        filename="${filename%.*}"
        mimeType=$(file -b --mime-type $CURRENT_PATH/ImagesExampleLoad/Videojuegos/$filename.$extension)
        fileSize=$(stat -c%s $CURRENT_PATH/ImagesExampleLoad/Videojuegos/$filename.$extension)

        echo "INSERT INTO \`files\` (file_name,file_description,file_type,file_mime_type,file_size,file_data,created_date,updated_date) " >> ./$SQL 
        echo "VALUES ('$filename','Descripcion de $filename','$extension','$mimeType',$fileSize,'data:$mimeType;base64,$( base64 -w 0 $CURRENT_PATH/ImagesExampleLoad/Videojuegos/$filename.$extension )',SYSDATE(),null);" >> ./$SQL 

        echo "INSERT INTO \`image\` (image_name,image_description,created_date,updated_date,album_id,file_id) " >> ./$SQL 
        echo "VALUES ('$iterator _ $filename','Descripcion de $iterator _ $filename',SYSDATE(),null,6, LAST_INSERT_ID());" >> ./$SQL 
    done 

  
done

printf "\\n\\n Finish creation SQL \\n"