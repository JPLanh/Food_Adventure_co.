-- Creating the Collection called "Group"
db.createCollection("Group");

-- "Group" Model
Group:
{
    groupUID    :   int,
    groupName   :   String,
    groupCode   :   String

}

-- Populating "Group" Collection
db.Group.insertMany(
[
    {
        groupUID    :   1,
        groupName   :   "Food-Guild",
        groupCode   :   "123456"
    },
    {
        groupUID    :   2,
        groupName   :   "Boba4Life",
        groupCode   :   "123456"
    },
    {
        groupUID    :   3,
        groupName   :   "Sweettooth",
        groupCode   :   "123456"
    }
]);