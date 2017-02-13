echo clean and execute tests first
call ./gradlew clean check -Dspring.profiles.active=test,h2_db
cd frontend
cd marketplace
dir
echo build angular app with prod-profile
call ./ng build -prod
cd ..
cd ..
dir
echo build spring boot and copy angular app files to resource folder
call ./gradlew build -Dspring.profiles.active=prod,h2,copy
echo end
pause