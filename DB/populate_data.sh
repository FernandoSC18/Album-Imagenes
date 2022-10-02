##Script to populate databse     
touch populate_data_result.txt 

CURRENT_PATH=$(pwd)
echo 'START POPULATE PROCESS' > ./populate_data_result.txt
printf "START POPULATE PROCESS \\n"

printf "Inserting data ...\\n"
mysql -h localhost -P $DB_PORT -u $DB_USER_API -p$DB_USER_API_PASSWORD $DB_DATABASE < \
$CURRENT_PATH/populate_data.sql >> $CURRENT_PATH/populate_data_result.txt

echo 'END POPULATE PROCESS' >> ./populate_data_result.txt
printf "END POPULATE PROCESS \\n"
exit 0 