-- Creating the Collection called "User"
db.createCollection("User");

-- "User" Model
User:
{
    userUID     :   int, 
    userName    :   String, 
    password    :   String,
    firstName   :   String,
    lastName    :   String,
    email       :   String,
    phone       :   String,
    dateOfBirth :   String
}

-- Populating "User" Collection
db.User.insertMany(
[
    {
        userUID     :   1, 
        userName    :   "iLuvPanda", 
        password    :   "*******",
        firstName   :   "Phuong",
        lastName    :   "Huynh",
        email       :   "Phuong.Huynh@csulb.edu",
        phone       :   "714-221-2112",
        dateOfBirth :   "09-28-1997"
    },
    {
        userUID     :   2, 
        userName    :   "NULLPoint", 
        password    :   "*******",
        firstName   :   "Aingty",
        lastName    :   "Eung",
        email       :   "Aingty.Eung@csulb.edu",
        phone       :   "562-233-2252",
        dateOfBirth :   "04-01-1995"
    },
    {
        userUID     :   3, 
        userName    :   "Lanh_Party", 
        password    :   "*******",
        firstName   :   "Jimmy",
        lastName    :   "Lanh",
        email       :   "Jimmy.Lanh@csulb.edu",
        phone       :   "626-998-3172",
        dateOfBirth :   "03-18-1995"
    },
    {
        userUID     :   4, 
        userName    :   "Hall_of_Fame", 
        password    :   "*******",
        firstName   :   "Kelly",
        lastName    :   "Hall",
        email       :   "Kelly.Hall@csulb.edu",
        phone       :   "123-456-7891",
        dateOfBirth :   "05-22-1996"
    }
]);