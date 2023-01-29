## Mabaya Home Exercise

This project is a spring boot application that enables sellers to create campaigns for
promoting their products.


### Requirements

Java 17

Maven 3.8.7

### DataBase
I used the cloud service of MongoDB Atlas
For your convenient you can download the [MongoDB Compass](https://www.mongodb.com/try/download/compass) and see the objects in the database.
To connect to the database please take the MongoDB uri from the `application.properties` file.


## Main Flow
For the main flow we can start the server and then go to the [swagger-ui](http://localhost:8080/swagger-ui/index.html#/).
In the swagger-ui you can invoke the api calls easily.
In the `ProductController` we have GET request that create 6 products in the `/createStub` path.
After that you can test all other calls as well.
