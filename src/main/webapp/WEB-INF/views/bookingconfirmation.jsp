<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking confirmation</title>
  <style> 
        .GFG {  background-color: Cornsilk;  border: 2px solid RosyBrown; color: RosyBrown;  padding: 5px 10px;  text-align: center; 
display: inline-block;  font-size: 20px; margin: 10px 30px;   cursor: pointer; 
 } 
 
 </style> 
</head>
<body>
<h1>Your booking is confirmed</h1>
<h1>Cab is on the way</h1>

Your cab will arrive at :${booking.startTime}
<br>
Booking id :${booking.bookingId}
<br>
Start location :${booking.startLocation}
<br>
End location :${booking.endLocation}
<br><br>
Cab number:${booking.cab.cabNumber}
<BR>
Cab Name: ${booking.cab.cabModelName}
<br><br>
<h1>Please pay :${booking.cab.fare} rupees</h1>

<button class="GFG" 
    onclick="window.location.href = 'http://localhost:8081/cba/';">   Home  </button> 
</body>
</html>