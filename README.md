# cs157a
Main project 

## Setting up MySQL on a Mac
* Install homebrew -> [brew.sh](http://brew.sh)
* Open terminal and type `brew search mysql`
* Brew will show the mysql options
* type `brew install mysql` and if you want to intall workbench `brew install Caskroom/cask/mysqlworkbench`
* brew may prompt you to run some setup commands before the above will work

## Run MySQL on a Mac
* Open terminal and type `mysqld` this starts the server.
* Open a new terminal window and type `mysql -u root` to access the running server
* root is the default user in mysql and can be changed

## MySQL Connector
Added to the repo is the library file needed to connect MySQL
The jar file needs to be added to the project library
The Folder "example sql connection" hold a java file that has an example connection

## Use the CreateTables.sql file
* Open a terminal and run the command `mysql -u root -p < CreateTables.sql`
* Login to mysql with root `mysql -u root -p`