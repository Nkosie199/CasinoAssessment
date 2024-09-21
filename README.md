Welcome to the Casino!

Casino is a Java web application which utilises the Spring Boot framework to meet functional requirements. It consists of an H2 database with 20 pre-loaded player and transaction entries respectively.

To run:

1. Build using the command "mvn clean install"
2. Start using the command "mvn spring-boot:run"

After running, you can expect to see the program on http://localhost:8000 of your preferred browser.

The home page contains links to navigate to the players and transactions pages respectively.
The players page contains a list of all players in the database. You can select a player by clicking on their username.
The transaction page contains a list of all player transactions in the database.

The player page contains the information of the selected player including a table of their results from the database.
The page page allows you to play a betting game which showcases the capabilities of the Casino API (http://localhost:8000/casino).
To play:

1. Enter the amount you wish to wager in the amount field
2. Click submit to place your bet.
3. Refresh the page to view the outcome of your bets in the results table.
   Please note: You can press F12 to view your console and see what is happening on the back-end when you click submit

I have added a word document called 'Casino Requirements Checklist' in the 'src/main/resources' directory.
In this document I have highlighted the working functional requirements of the application in green.

To test each functional requirement of the API, I have added curl commands in the 'bin' directory.
You can copy-paste each curl command into the terminal to see the output of each get/post request as per the functional requiremnts specified.

Enjoy!

Nkosi Gumede (nkosigumede@yahoo.com)

To deploy:
heroku run bash --app casino6
>> curl -O https://archive.apache.org/dist/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz
>> file apache-maven-3.8.5-bin.tar.gz
>> tar -xzvf apache-maven-3.8.5-bin.tar.gz
>> export PATH=$PATH:/app/apache-maven-3.8.5/bin
>> mvn clean install
>> mvn spring-boot:run

To check logs:
heroku logs --app=casino6 --tail