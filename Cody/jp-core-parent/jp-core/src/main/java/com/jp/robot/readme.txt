#PROBLEM STATEMENT

A company working in artificial intelligence domain is planning to create a Robot.
But before they do heavy investment in Robot research and development, 
they requested their technical team to do a small prototype and create a Robot with some basic features. 
Technical team decided to introduce following features in the Robot prototype:

#Power Operated:

Robot works on battery and can walk for 5 km per charge.

If remaining battery is less than 15%, a red light on Robot head should lit up indicating low battery.

#Handling physical objects:

Robot can carry any object not weighing more than 10 Kg.

For every Kilogram carried by Robot, 2% extra [in addition to walking discharge] battery will be consumed.

If the weight of the object is more than 10 Kg, Robot display [LED display on chest] will show message “Overweight”.

#Scanning:

Robot can scan any bar code and display it's price on Robot Display.

In case bar code is not clear enough for scanning, Robot display will show “Scan Failure”.

Technical team handed over these details to IT team to build a software for Robot. 
Please create a Robot application in Java for automating all the features listed above.

#Assumption:

Robot will accept integer weights.

Robot will calculate the task and required power and capacity up front and denies if it has 
lower power left than required.It wont complete the partial task.

Assuming 100% charge as complete charge.

Bar code is being random generated for even number and for odd number, I am assuming as failure.

#Calculation:

As Robot can walk 5Km/charge. It will run 50 meter per 1% charge.
