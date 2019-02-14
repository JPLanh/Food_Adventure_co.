-- Creating the Collection called "Message"
db.createCollection("Message");

-- "Message" Model
Message:
{
    dateSent    :   String, 
    receiver    :   String,
    sender      :   String,
    subject     :   String,
    message     :   String
}

-- Populating "Message" Collection
db.Message.insertMany(
[
    {
        dateSent    :   "01/13/19",
        receiver    :   "iLuvPanda",
        sender      :   "Hall_of_Fame",
        subject     :   "Wanna go bro?",
        message     :   "Challenge me to a tournament!!"
    },
    {
        dateSent    :   "02/14/19",
        receiver    :   "Lanh_Party",
        sender      :   "NULLPoint",
        subject     :   "Going Out For A Drink",
        message     :   "Are we going to get boba tonight? I need to get that limited gear."
    },
    {
        dateSent    :   "12/25/18",
        receiver    :   "Hall_of_Fame",
        sender      :   "Lanh_Party",
        subject     :   "Merry Christmas!!!!",
        message     :   "And a HAPPY NEW YEAR!!!!"
    },
    {
        dateSent    :   "10/31/18",
        receiver    :   "NULLPoint",
        sender      :   "iLuvPanda",
        subject     :   "BOOO!!",
        message     :   "There is a coupon for a two for one dessert. Are you free to join us?"
    }
]);