-- Creating the Database
use Food_Adventure;

-- Creating the Collection called "userTesting"
db.createCollection("userTesting");

-- Creating the Collection called "couponTesting"
db.createCollection("couponTesting");

-- Populating the Collection of "userTesting"
db.userTesting.insertOne({"Name" : "Phuong", "Age" : 110, "Username" : "iLuvPanda"});
db.userTesting.insertOne({"Name" : "Kelly", "Age" : 24, "Username" : "Hall_of_Fame"});
db.userTesting.insertOne({"Name" : "Jimmy", "Age" : 23, "Username" : "Lanh_Party"});
db.userTesting.insertOne({"Name" : "Aingty", "Age" : 23, "Username" : "NullPointer"});
db.userTesting.insertOne({"Name" : "Bruce", "Age" : 35, "Username" : "hashttag_noParent"});
db.userTesting.insertOne({"Name" : "Vader", "Age" : 44, "Username" : "DaddyIsHere"});
db.userTesting.insertOne({"Name" : "Frank", "Age" : 45, "Username" : "RebelScrum"});
db.userTesting.insertOne({"Name" : "Smeagel", "Age" : 130, "Username" : "ImPrescious"});

-- Populating the Collection of "couponTesting"
db.couponTesting.insertOne({"Name" : "15% off Boba", Type : "Percentage Discount", Description : "Reduce the cost of a boba drink by 15%."});
db.couponTesting.insertOne({"Name" : "Get 1 Free Appetizer", Type : "Reward", Description : "Get a free appetizer."});
db.couponTesting.insertOne({"Name" : "Buy 1 Entree Get 1 Dessert", Type : "BOGO", Description : "Buy an entree and get one free dessert."});
db.couponTesting.insertOne({"Name" : "20% off entree", Type : "Percentage Discount", Description : "Percentage discount off an entree."})
db.couponTesting.insertOne({"Name" : "$5 off $30", Type : "Dollars Off", Description : "Spend at least $30 to get $5 off."});
db.couponTesting.insertOne({"Name" : "Double Experience for 2hrs", Type : "Game Experience", Description : "Gets double experience point for two hours."});

-- Useful MongoDB Command(S)
db.[collectionname].find(); -- AKA SELECT * From [collectionname]
