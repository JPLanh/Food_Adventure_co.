-- Creating the Collection called "Gear"
db.createCollection("Gear");

-- "Gear" Model
Gear:
{
    gearUID             :   int, 
    attributeModifier   :   Map<String, int>,
    spriteID            :   int
}