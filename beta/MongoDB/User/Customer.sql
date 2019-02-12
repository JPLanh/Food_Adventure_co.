-- Creating the Collection called "Customer"
db.createCollection("Customer");

-- "Customer" Model
Customer:
{
    userUID         :   int, 
    loyaltyPoint    :   int,
    tierLevel       :   int,
    address         :   String
}

-- Populating "Customer" Collection
db.Customer.insertMany
(
    [
        {
            userUID         :   1, 
            loyaltyPoint    :   30,
            tierLevel       :   2,
            address         : "1244 N Blvd Drive Fountain Valley, CA 90555"
        },
        {
            userUID         :   2, 
            loyaltyPoint    :   100,
            tierLevel       :   3,
            address         : "1049 E West Long Beach, CA 90808"
        },
        {
            userUID         :   3, 
            loyaltyPoint    :   500,
            tierLevel       :   5,
            address         : "35154 N South Blvd Torrance, CA 78046"
        },
        {
            userUID         :   4, 
            loyaltyPoint    :   200,
            tierLevel       :   2,
            address         : "420 Blaze It, CA 80707"
        },
    ]
);