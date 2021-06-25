Online test using API : https://reqres.in/

There are 2 different scenarios available in the feature file :

POST : To Create new user, using scenario outline to save parameters. After created new user, the system will assert data inputed and response output is match.
GET : To get and verify user data. User id must input or change manually due to my limitations to create dynamic objects

Steps to run :

open the project
wait until dependency finish to load
open feature file at "src/test/java/features/PostGetMethod.feature"
right click in choosen scenario. note : dont forget to adjust the data (user id)

This project build with :

Java
Maven
Rest Assured
Cucumber

