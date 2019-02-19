-- Creating the Collection called "Battle"
db.createCollection("Battle");

-- "Battle" Model
Battle:
{
    battleUID           :   int,
    groupVictoryUID     :   String,
    date                :   String
}

-- Populating "Battle" Collection
db.Battle.insertMany(
[
    {
        battleUID           :   1,
        groupVictoryUID     :   2,
        date                :   "03-15-2019"
    },
    {
        battleUID           :   2,
        groupVictoryUID     :   1,
        date                :   "11-23-2018"
    },
    {
        battleUID           :   3,
        groupVictoryUID     :   1,
        date                :   "03-03-2019"
    },
    {
        battleUID           :   4,
        groupVictoryUID     :   3,
        date                :   "06-18-2017"
    }
]);