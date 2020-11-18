# RobinsonJDBC

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

RobinsonJDBC is a easy to customize, open source banking app that handles interaction through. With RobinsonJDBC you can:

  - Add, update and delete users and accounts as admin
  - create users, create account and delete account as user
  - create and use joint accounts with unlimited account holders
  - create and use multiple accounts per user
  - normal bank customer functions such as deposit, withdraw and viewing user and account details

# Before Running!

  - Download the sql file RobinsonJDBC github from the folder NadjahProjects and execute in your DBMS software of choice (we suggest DBeaver) 
  - edit database.properties file within RobinsonJDBC. Change username and password to your database username and password.

admin user name and password for the database:
-username = admin
-password = addminpass


### Run!
Select log in from main menu
Log in with admin credentials supplies above
select update user from admin menu
view the admin user id from the user list printed
enter admin user id
set the values of the admin user to whatever you like!
!!Be sure to write the new username and password down as there is no way to retrieve these values through the application!! You may view admin login credentials in your DBMS softawre as an entry in the users table wherre isAdmin value is set to true.

