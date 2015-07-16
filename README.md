# colabdario

This is a Maven Project running in a GlassFish server.

1) Clonning the project:
Go to the desired directory and then:
"git clone https://github.com/felipepo/colabdario"

2) DataBase:
Install PostgreSQL:
https://wiki.postgresql.org/wiki/Apt

3) To run the sql script you need to connect to the database with the user postgres: run the command "sudo su postgres" or "sudo -i -u postgres"
Then type your system password.
After that run "psql".
Now you have to run the sql script with the \i command.
"\i <directory>"
Where directory is the directory where the scipt is stored.
For example, in my computer it's
"\i /home/felipe/projects/colabdario/colabdario.sql"
Alternatively, you can connect to the database and run the script using PgAdmin III.

4) After that the easiest way to build the project is importing the project directory to NetBeans and running it.
Make sure that you have GlassFish installed and integrated on NetBeans.
