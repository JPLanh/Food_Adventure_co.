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
        attribute   :   
        {
            Health      :   100,
            Strength    :   25,
            Stamina     :   20,
            Agility     :   20,
            Intellect   :   20,
            Dexterity   :   20
        }
    },
    {
        avatarUID   :   2, 
        avatarName  :   "Asian", 
        hairStyle   :   3,
        hairColor   :   3,
        eyeColor    :   2,
        furColor    :   2,
        animal      :   2,
        attribute   :   
        {
            Health      :   100,
            Strength    :   20,
            Stamina     :   25,
            Agility     :   20,
            Intellect   :   20,
            Dexterity   :   20
        }
    },
    {
        avatarUID   :   3, 
        avatarName  :   "Party", 
        hairStyle   :   4,
        hairColor   :   5,
        eyeColor    :   3,
        furColor    :   2,
        animal      :   3,
        attribute   :   
        {
            Health      :   100,
            Strength    :   20,
            Stamina     :   20,
            Agility     :   25,
            Intellect   :   20,
            Dexterity   :   20
        }
    },
    {
        avatarUID   :   4, 
        avatarName  :   "Famous", 
        hairStyle   :   1,
        hairColor   :   1,
        eyeColor    :   1,
        furColor    :   1,
        animal      :   1,
        attribute   :   
        {
            Health      :   100,
            Strength    :   20,
            Stamina     :   20,
            Agility     :   20,
            Intellect   :   25,
            Dexterity   :   20
        }
    }
]);