-- Creating the Collection called "Avatar"
db.createCollection("Avatar");

-- "Avatar" Model
Avatar:
{
    avatarUID   :   int, 
    avatarName  :   String, 
    hairStyle   :   int,
    hairColor   :   int,
    eyeColor    :   int,
    furColor    :   int,
    animal      :   int,
    attribute   :   Map<String, int>
}

-- Populating "Avatar" Collection
db.Avatar.insertMany(
[
    {
        avatarUID   :   1, 
        avatarName  :   "Panda", 
        hairStyle   :   2,
        hairColor   :   3,
        eyeColor    :   2,
        furColor    :   1,
        animal      :   1,
        attribute   :   Map<String, int>
    },
    {
        avatarUID   :   2, 
        avatarName  :   "Asian", 
        hairStyle   :   3,
        hairColor   :   3,
        eyeColor    :   2,
        furColor    :   2,
        animal      :   2,
        attribute   :   Map<String, int>
    },
    {
        avatarUID   :   3, 
        avatarName  :   "Party", 
        hairStyle   :   4,
        hairColor   :   5,
        eyeColor    :   3,
        furColor    :   2,
        animal      :   3,
        attribute   :   Map<String, int>
    },
    {
        avatarUID   :   4, 
        avatarName  :   "Famous", 
        hairStyle   :   1,
        hairColor   :   1,
        eyeColor    :   1,
        furColor    :   1,
        animal      :   1,
        attribute   :   Map<String, int>
    }
]);