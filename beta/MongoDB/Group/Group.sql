-- Creating the Collection called "Group"
db.createCollection("Group");

-- "Group" Model
Group:
{
    GroupUID     :   int, 
    GroupName    :   String, 
    password    :   String,
    firstName   :   String,
    lastName    :   String,
    email       :   String,
    phone       :   String,
    dateOfBirth :   String
}

-- Populating "Group" Collection
db.Group.insertMany(
[
    {