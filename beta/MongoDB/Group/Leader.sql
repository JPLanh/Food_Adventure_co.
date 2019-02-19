-- Creating the Collection called "Leader"
db.createCollection("Leader");

-- "Leader" Model
Leader:
{
    dateInitialized     :   String
}

-- Populating "Leader" Collection
db.Leader.insertOne(
    {
        dateInitialized     :   "12-12-2012"
    }
);