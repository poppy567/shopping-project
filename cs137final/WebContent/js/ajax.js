/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getPlace (zip)
{
  if (zip=="")
  {
      document.getElementById("city").innerHTML="";
      document.getElementById("state").innerHTML="";
      return;
  }
  if (window.XMLHttpRequest)
  {  // IE7+, Firefox, Chrome, Opera, Safari
     var xhr = new XMLHttpRequest();
  }
  else
  {  // IE5, IE6
     var xhr = new ActiveXObject ("Microsoft.XMLHTTP");
  }

  // Register the embedded handler function
  // This function will be called when the server returns
  // (the "callback" function)
  xhr.onreadystatechange = function ()
  { // 4 means finished, and 200 means okay.
    if (xhr.readyState == 4 && xhr.status == 200)
    { // Data should look like "Fairfax, Virginia"
      var result = xhr.responseText;
      var place = result.split (',');
      if (document.getElementById ("city").value == "")
        document.getElementById ("city").value = place[0];
      if (document.getElementById ("state").value == "")
        document.getElementById ("state").value = place[1];
    } 
  } 
  // Call the response software component
  xhr.open ("GET", "/public_html/tpl/getCityState.php?zip=" + zip, true);
  xhr.send ();
  //xhr.open ("POST", "getCityState.php", true);
  //xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
  //xhr.send ("zip="+zip);  
}

function getShippingFee (sf)
{
  //console.log(sf);
  if (window.XMLHttpRequest)
  {  // IE7+, Firefox, Chrome, Opera, Safari
     var xhr = new XMLHttpRequest();
  }
  else
  {  // IE5, IE6
     var xhr = new ActiveXObject ("Microsoft.XMLHTTP");
  }

  // Register the embedded handler function
  // This function will be called when the server returns
  // (the "callback" function)
  xhr.onreadystatechange = function ()
  { // 4 means finished, and 200 means okay.
    if (xhr.readyState == 4 && xhr.status == 200)
    { // Data should look like "Fairfax, Virginia"
      var result = xhr.responseText;
      document.getElementById("ship_fee").innerHTML=result;
      console.log("in if");
    } 
  } 
  // Call the response software component
//console.log("start open");
  xhr.open ("GET", "/public_html/tpl/getShippingFee.php?sf=" + sf, true);
  //console.log("finish open");
  xhr.send ();
  //xhr.open ("POST", "getCityState.php", true);
  //xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
  //xhr.send ("zip="+zip);  
}