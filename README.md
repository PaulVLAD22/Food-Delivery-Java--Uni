# Food-Delivery-Java--Uni
<h4>Collections Used:</h4>

<li><b>ArrayList</b> for keeping hold of Users, Drivers, Admins, Products</li>
<li><b>Set</b> for keeping hold of Locals</li>
<li><b>Map</b> for keeping hold of an order's Product<->Qunatity</li>

<h3>Models Folder:</h3>
accont:
Account-> User
       -> Driver
       -> Admin

local:
Local, Menu, Product

order:
Order

Company 
Coordinate (x,y)

<h3>Services:</h3>
Basic Service (available to anyone who just ran the app) -> UserService (Users)
                                                         -> DriverService (Drivers)
                                                         -> Admin Service (Admins)
