# Red River
Red River Project

This was a fun little project. I added the pagination, sorting and dynamic query items. Seemed like
if I started there I met all the requirements. 

This application is pretty basic - it would definitely need more security. I would probably use JWT.
Also some of the error checking and handling is basic - but I added some so you could see how I 
would do it. 

To run the app locally:

Install Docker (if not already installed)

I'm not sure what environment you all are on - I normally create a bash script for this stuff.

```
mvn package
docker build -t redriver/project .
docker-compose up
```

Then from Postman or Curl - or whatever you use:

```
GET localhost:8080/student (no parameters will get you the first 10 entries, sorted by lastname)
GET localhost:8080/student?lastName=something&firstName=something?page=0?size=10?sort=firstName
```
Or whatever parameters you want to use.

I thought about adding swagger and having it build a file - but I didn't want to go too overboard

```
GET localhost:8080/student/{id}

PUT localhost:8080/student - Passing a json representation of student with an ID - NO ID causes an exception.
POST localhost:8080/student - Passing a json representation of a student without an ID will create a new one.
```

I also set this up in AWS - so if you want to bypass the set up you can call it directly:

```
www.maslakowski.com/student 
```

I also pre-loaded some data on that server.

Let me know if you have any questions or comments. Thanks again for the opportunity.

Mark

