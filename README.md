Design Docs Link : https://docs.google.com/document/d/10CR7oLxPBFXlvMsYdba7HQOHHRZsBlmyOOyVyMRkiJk/edit?usp=sharing


# caseStudy_1

Case Study
Problems 
// Time 
//
// Check for class array
3d Array 
Rows as floors
breadth and length
Each floor will have 2 entries and 2 exits
Classes :

Interface Display;
ParkingLot:
  CheckAvailability Method
  
Vehicle Interface
   Vehicle Details
   Entry TimeStamp
   Payment Status
   Type Vehicle 
   
Class Floor :
   SlotAvailability Method;
   Level
   Entry Method
   Exit Method:
   Checking FreeSpace form FreeSpace class
   
Interface Payment:
   Credit
   Cash
   TimeStamps during Exit
   
Class ArrangeVehicles
   Using Queues for different type
   Dequeuing
   Enqueuing

Interface Customer Info Portal
   Type of Vehicle
   Whether Space Vacant ( Checking from FreeSpace Class )
   Payment ( connecting to Payment class ) 
   Not collecting money at exit ( connecting exit class )
