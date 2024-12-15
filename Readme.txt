First import project in Eclipse and run as mentioned below.

/************/
Check if Folder Structure is as follows:-
/************/
      src/
       com/library/management/
         LibraryApp.java
         Item.java
         Book.java
         Magazine.java
         Library.java
         Member.java
         Transaction.java

/************/
To run LibraryApp.Java file [Configure the arguments first] =>
/************/

1. Right-click on the `LibraryApp.java` file in the `Package Explorer`.
2. Click on **Run As** -> **Java Application**.
3. To provide command-line arguments:
    - Go to `Run` -> `Run Configurations`.
    - Select your application (LibraryApp) and click on the `Arguments` tab.
    - Enter the arguments (e.g., `"My Library" 5`).
    - Click **Apply** and then **Run**.


/************/
Output will be displayed as follows=>
/************/

Initializing Library: Central Library
Items in Library:
Java Programming has been added to the library.
Tech Monthly has been added to the library.

Members Registered:
Alice has been registered as a member.
Bob has been registered as a member.

Alice borrowed: Java Programming
Alice returned: Java Programming

Library Status:
Library: Central Library
Items in Library:
Item [ID: 1, Title: Java Programming], Author: James Gosling
Item [ID: 2, Title: Tech Monthly], Issue Number: 45
Members Registered:
Alice
Bob