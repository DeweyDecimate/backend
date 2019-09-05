# Omnibus Backend

Omnibus is a full stack CRUD book club application. Users can join a book club, create a book club, add a book to the club, and see other members of the club.

---

## Table of Contents

[Introduction](#introduction)<br />
[Vision](#vision)<br />
[Scope](#scope)<br />
[MVP](#mvp)<br />
[Stretch Goals](#stretch-goals)
[Dependencies](#dependencies)<br />
[Build](#build)<br />
[Database Schema](#database-schema)<br />
[Route Endpoints](#route-endpoints)<br />
[Work Schema](#work-flow)<br />
[Team Members](#Team-Members)<br />
[Acknowledgements](#acknowledgements)<br />

---

## Introduction

This is the backend to Omnibus and contains code for the server, database and route endpoints.

## Vision

The Omnibus backend will host all the endpoints that will do the following:

- create, read, update and delete a book club
- create, read, update and delete a book from a club
- create and read members of a club

## Scope

_IN_ - the Omnibus backend will

- provide a registration feature for users to create an account
- provide a login feature for returning users to sign back in
- provide a feature for a user to join a book club
- provide a feature for a user to add, update or remove a book club
- provide a feature for a user to add, update or remove a book to a book club
- display a list of the clubs
- display all the members of a club
- display books read in each club

_OUT_ - the Omnibus will not

- allow CRUD operations for users who are not logged in
- allow display information specific to a club such as the list of members or books for that club

## MVP

Users will be able to register for an account and sign in. They can explore other clubs, see who is in those clubs and see what they're reading. If they'd like, they can create a club and they can add a book to that club. They can send their friends a specific club ID, and their friends can join the same club.

## Stretch Goals

- club members can discuss the book they're reading

## Dependencies

Omnibus is a gradle Java application that utilizes a postgresql database and is hosted on AWS Elastic Beanstalk. If you're looking to run this on your machine, ensure that [postgresql](https://www.postgresql.org) is downloaded on your machine.

## Build

Clone this repo to a directory of your choice and open with your favorite editor. Before building and running in your editor, make sure that the application.properties has the correct information for your postgresql:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/PickANameForTheDB
spring.datasource.username=YourPostgresqlUsername
#spring.datasource.password=YourPostgresqlPassword
spring.jpa.hibernate.ddl-auto=create
```

Note that:

```
spring.jpa.hibernate.ddl-auto=create
```

can be changed to

```
spring.jpa.hibernate.ddl-auto=update
```

if you're looking to do some work, but don't want to drop and create the tables every time the application is run.

## Database Schema

![insert db Schema here](Omnibus-ERD.png)

## Route endpoints

When running this application locally, navigate to the localhost address that you have set in application.properties:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/PickANameForTheDB
```

In the example, the localhost address will be localhost:5432. Then add any of the following endpoints:

```
/
```

This is the splash page.

---

```
/signup
```

This is where a user can register for an account with Omnibus.

---

```
/login
```

This is where a user can log in with their credentials.

---

```
/logout
```

This is where a user can log out of Omnibus.

---

```
/clubs
```

This route allows a user to create a new club and will redirect the user to `/clubs/{randomId}` afterwards.

---

```
/clubs/{randomId}
```

This will take a user to a specific club profile where they can see the list of members and books.

---

```
/clubs/membership
```

This lets a user join a specific club. They are then redirected to the book club they joined.

---

```
/myprofile
```

This allows a user to see their profile.

---

```
/users/{id}
```

This allows a user to see other users' profile.

---

```
/users/
```

This adds a user and redirects to `/myprofile` afterwards.

---

```
/book
```

This is a post and delete route. It allows a user to add a book. They are then redirected back to the book club. Additionally, a book can be deleted from this end point.

## Work Flow

Our team utilized an agile process. User stories were written and assigned to team members who wanted to complete them. Each branch is a feature and is for a specific user story. When a feature is completed and pushed, someone who did not write the code will go over the code and merge the pull request. Features are first merged to the dev branch. Once everything is working and up to our satisfaction, dev is then merged into master.

## Team Members

[MarishaHoza](https://github.com/MarishaHoza), [perezm27](https://github.com/perezm27), [Bomi Bear](https://github.com/bomibear), [trevorjdobson](https://github.com/trevorjdobson)

## Acknowledgements

Generating the random string that comes after the club name is from [https://www.baeldung.com/java-random-string
](// https://www.baeldung.com/java-random-string
)
