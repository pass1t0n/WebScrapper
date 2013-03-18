WebScrapper

How many times you wanted to visit couple of web sites get a piece of information from them and
do something with later For example: 
Visit a stock ranking web site see how analysts rank them and check if we are in bearish or bullish market.
The sample is doing that using only 2 files but you can do it with the whole sp 500.

Or a little use more elaborate that had with this code (well it is for my wife.. :) is: 
Get the list of the newest movies, rank them according to your favorite movie ranking web site (rotten tomatoes) 
and check if our favorite streaming web has them.

Note: The main point of the share is to give an easy start for any spring/hibernate web app. 
It is just I think that also examples needs to do something.

Spending your time going over this will show you how: 
1. Working with Spring MVC using annotations 
2. Using Spring templates 
3. Using XPath to scape html DOMs 
4. Annotate objects to work with Hibernate 
5. Using the OpenSessionInViewFilter pattern 
6. Using Ajax with JQuery to send the status to the UI
7. how to send mail

Just for the terminology sake Scrapping composed is composed out or 3 phases: 
Getting the page - fetting the html, cleaning it 
Getting the XPath values 
Exporting it to the desired format and maybe sending it/storing it..


As mentioned above the web app is fetching couple of stock analysts predictions regarding goog and lvs
creates a CSV file with the proper headlines and emails it using a gmail account

Configuration:
app.properties -
    app.mail.port = 587
    app.mail.host = smtp.gmail.com
    app.mail.userName = <Your Gmail User Account>
    app.mail.password = <You Gmail Password>

    app.mail.recipient = <Email Recipient>

jdbc.properties -
    jdbc.driverClassName= <JDBC driver for your DB> i.e com.mysql.jdbc.Driver
    jdbc.dialect=<Hibernate Dialect> You can find it here: http://www.javabeat.net/2013/02/list-of-hibernate-sql-dialects/
    jdbc.databaseurl=<DB Url>
    jdbc.username=<DB User Name>
    jdbc.password= <DB Password>

Build:
This is a basic Maven project so just go the project directory and execute mvn package.
Copy the created war from the target folder to your application server

Once the AppServer is up send a request to http://<Your Server>/BasicCrawler which will populate your DB.


Browse:
http://<Your Server>/BasicCrawler/
The user name and password dialog are there for you to extend in your web app. In this sample any username == password
will do the work.

Other Notes:
The code logs most of the things that happen on the server. In order to change it you can just modify the
log4j.properties file





All the rest is fairly documented.
If something does not make sense feel free to contact me.
