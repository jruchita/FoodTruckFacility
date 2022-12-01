# FoodTruckFacility
-----------------
Resource Path : /getFoodTrucks
Method :  GET
Response :  Returns list of food trucks present in the database

To apply filter use format:  /getFoodTrucks?filter=location=abcd&applicantname=xyz
------------------------------
Resource Path: /getNearestTruck
Method :  POST
Body format: 
{
   "location": "(37.794331003246846, -122.39581105302317)"
}
Response : Returns nearest food truck from give location
------------------------------------------
Resource Path: /insertFoodTruck
Method :  PUT
Body format: 
{
   "objectid" : 1234,
   "applicant" : "test"
}
Response : Inserts the record in DB



