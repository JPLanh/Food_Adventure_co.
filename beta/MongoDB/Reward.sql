-- Creating the Collection called "Reward"
db.createCollection("Reward");

-- "Reward" Model
Reward:
{
    rewardUID           :   int,
    rewardName          :   String,
    rewardCost          :   int,
    rewardDescription   :   String
}

-- Populating "Reward" Collection
db.Reward.insertMany(
[
    {
        rewardUID           :   1,
        rewardName          :   "Free Any Dessert",
        rewardCost          :   20,
        rewardDescription   :   "Purchase any Dessert from any participating location for FREE!"
    },
    {
        rewardUID           :   2,
        rewardName          :   "15% off Boba",
        rewardCost          :   20,
        rewardDescription   :   "Get a 15% off Boba drink on any participating location."
    }
]);