-- Creating the Database
use Food_Adventure;

-- Creating the Collection called "userTesting"
db.createCollection("userTesting")

-- Populating the Collection of "userTesting"
db.userTesting.insertOne({"Name" : "Kelly", "Age" : 22, "Username" : "Hall_of_Fame"});
db.userTesting.insertOne({"Name" : "Jimmy", "Age" : 23, "Username" : "Lanh_Party"});
db.userTesting.insertOne({"Name" : "Aingty", "Age" : 23, "Username" : "NullPointer"});
db.userTesting.insertOne({"Name" : "Bruce", "Age" : 35, "Username" : "hasttag_noParent"});
db.userTesting.insertOne({"Name" : "Vader", "Age" : 44, "Username" : "DaddyIsHere"});
db.userTesting.insertOne({"Name" : "Frank", "Age" : 45, "Username" : "RebelScrum"});
db.userTesting.insertOne({"Name" : "Smeagel", "Age" : 130, "Username" : "ImPrescious"});

-- Useful MongoDB Command(S)
db.[collectionname].find() -- AKA SELECT * From [collectionname]
