-- Creating the Collection called "Member"
db.createCollection("Member");

-- "Member" Model
Member:
{
    dateJoined  :   String,
    initiated   :   Boolean
}

-- Populating "Member" Collection
db.Member.insertMany(
[
    {
        dateJoined  :   "01-23-2018",
        initiated   :   false
    },
    {
        dateJoined  :   "02-14-2019",
        initiated   :   true
    },
    {
        dateJoined  :   "10-25-2019",
        initiated   :   false
    }
]);