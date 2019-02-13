-- Creating the Collection called "Gear"
db.createCollection("Gear");

-- "Gear" Model
Gear:
{
    gearUID             :   int, 
    gearName            :   String,
    attributeModifier   :   Map<String, int>,
    spriteID            :   int
}

-- Populating "Gear" Collection
db.Gear.insertMany(
[
    {
        gearUID             :   1,
        gearName            :   "Dev's Helm",
        attributeModifier   : 
        {
            Health      :   100,
            Strength    :   100,
            Stamina     :   100,
            Agility     :   100,
            Intellect   :   100,
            Dexterity   :   100
        }
    },
    {
        gearUID             :   2,
        gearName            :   "Dev's Body",
        attributeModifier   : 
        {
            Health      :   100,
            Strength    :   100,
            Stamina     :   100,
            Agility     :   100,
            Intellect   :   100,
            Dexterity   :   100
        }
    },
    {
        gearUID             :   3,
        gearName            :   "Dev's Pant",
        attributeModifier   : 
        {
            Health      :   100,
            Strength    :   100,
            Stamina     :   100,
            Agility     :   100,
            Intellect   :   100,
            Dexterity   :   100
        }
    },
    {
        gearUID             :   4,
        gearName            :   "Dev's Boot",
        attributeModifier   : 
        {
            Health      :   100,
            Strength    :   100,
            Stamina     :   100,
            Agility     :   100,
            Intellect   :   100,
            Dexterity   :   100
        }
    },
    {
        gearUID             :   5,
        gearName            :   "Dev's Glove",
        attributeModifier   : 
        {
            Health      :   100,
            Strength    :   100,
            Stamina     :   100,
            Agility     :   100,
            Intellect   :   100,
            Dexterity   :   100
        }
    },
    {
        gearUID             :   6,
        gearName            :   "Dev's Weapon",
        attributeModifier   : 
        {
            Health      :   100,
            Strength    :   100,
            Stamina     :   100,
            Agility     :   100,
            Intellect   :   100,
            Dexterity   :   100
        }
    }
]);
