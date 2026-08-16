# 📚 Digital Library Management System

## Requirements

- Java 25 or Higher
- Maven 1.4+
- VSCode or Intellij

## How To Usage

```bash
# clone project from github
git clone git@github.com:qewr1324/digital-library-management-system.git

# open folder in your ide
cd ./digital-library-management-system

# VSCode
code ./digital-library-management-system

# or IntelliJ
idea ./digital-library-management-system

# run project
mvn build

# ...
```

## Project Structure

```text
digital-library-management-system
├─ README.md
├─ pom.xml
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ ir
   │  │     └─ nas
   │  │        ├─ Main.java
   │  │        ├─ enums
   │  │        │  └─ StockStatus.java
   │  │        ├─ exception
   │  │        │  └─ db
   │  │        │     └─ DBConnectionException.java
   │  │        ├─ model
   │  │        │  ├─ Author.java
   │  │        │  ├─ BaseModel.java
   │  │        │  ├─ Book.java
   │  │        │  ├─ Category.java
   │  │        │  ├─ Profile.java
   │  │        │  └─ embeddable
   │  │        │     └─ Address.java
   │  │        └─ util
   │  │           ├─ ColorCMD.java
   │  │           └─ HibernateUtil.java
   │  └─ resources
   │     └─ META-INF
   │        └─ persistence.xml
   └─ test
      └─ java
```

---
>
> # Week 22 – HW 22Digital Library Management System
>
> ## Scenario and Domain Model
>
> ### You need to design a digital library that includes the following entities
>
> 1. Book
>
> - id
> - title
> - isbn
> - publicationYear
> - price
> - stockStatus (Enum with values IN_STOCK, OUT_OF_STOCK, COMING_SOON)
> - publisherAddress (a Value Object containing city, street, postalCode, which must be stored as Embeddable in the same Book table)
> Note: The stockStatus value must be stored in the database as a readable string
>
> 2. Author
>
> - id
> - name
> - birthDate
> - profile
>
> 3. Profile
>
> - id
> - bio
> - website
> - author
>
> 4. Category
>
> - id
> - name
> - books
>
> Relationships Between Entities
>
> - Many-to-Many between Book and Author (A book can have multiple authors, and an author can write multiple books.)
> - One-to-One between Author and Profile (Each author has exactly one profile, and each profile belongs to one author.)
> - One-to-Many between Category and Book (Each category can have multiple books, but each book belongs to only one
> category.)
>
> ## Technical Requirements and Implementation
>
> 1. Use of Design Patterns
>
> - a) Singleton Pattern
> Use the Singleton pattern to manage the EntityManagerFactory (or a utility class like HibernateUtil) so that only one instance is created throughout the application's execution, avoiding unnecessary recreation of heavy infrastructure.
> - b) Builder Pattern
> Implement the Book and Author classes using the Builder pattern to make constructing objects with multiple fields more readable and maintainable.
> - c) Factory Method Pattern
> Create a Repository Factory that uses the Factory Method pattern to build the appropriate repository based on the entity type.
>
> 2. Persistence Layer and Transaction Management
>
> - Use a utility class like HibernateUtil to manage the EntityManager and transactions.
> - Design helper methods to execute code within transactions to avoid duplicated transaction
>
> 3. Repository Layer
> For each entity, create a separate Repository that provides the following methods:
>
> - save
> - findById
> - update
> - delete
>
> 4. Cascade, Orphan Removal, and FetchType
>
> - In the Category → Book relationship, use CascadeType.PERSIST so that when a category is saved, its associated books are also saved.
> - In the Author ↔ Profile relationship, use CascadeType.ALL and orphanRemoval = true so that deleting an author also deletes their profile, and if a profile is removed from its author, it is deleted as an orphan.
> - For the Book ↔ Author relationship (Many-to-Many), set the owner side on Book and use mappedBy on the Author side.
> - Set the fetch type (FetchType) for the Category → Book relationship to LAZY. In the repository's findById method, provide a way to prevent LazyInitializationException (e.g., by initializing the collection beforehand or using JOIN FETCH).
>
> 5. Main Class

> In the Main class, perform the following operations:
>
> 1. Create at least 3 books with different authors and categories, and save them to the database.
> 2. Retrieve one of the books by its ID.
> 3. Change some information of the retrieved book (e.g., price or stock status).
> 4. Save the changes.
> 5. Delete one of the books.
> 6. Display the final database state by printing all books and authors.
>
> Test 1: testSaveAndFindBook()
> Purpose: Test the save and findById methods of the BookRepository.
>
> Steps
>
> 1. Create a new Book object using the Builder pattern.
> 2. Save the book.
> 3. Retrieve the book by its ID.
> 4. Assert that the retrieved book is not null and its title matches the original title.
> 5. Verify that the book's publisherAddress is correctly stored and retrieved.
>
> Test 2: testCategoryCascadePersist()
> Purpose: Test the CascadeType.PERSIST behavior between Category and Book.
>
> Steps
>
> 1. Create a new Category object.
> 2. Create at least two new Book objects using the Builder pattern.
> 3. Add the books to the category's books collection.
> 4. Save only the Category object.
> 5. Retrieve the category by its ID.
> 6. Assert that the retrieved category is not null.
>
> 7. Verify that the category contains the books that were added before saving.
> 8. Verify that the books were automatically persisted to the database through CascadeType.PERSIST.
>
---
