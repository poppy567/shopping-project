/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function currentDiv(n) {
  showDivs(slideIndex = n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
     dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
  }
  x[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " w3-opacity-off";
}

function bigImg(x) {
    x.style.height = "75%";
    x.style.width = "75%";
}

function normalImg(x) {
    x.style.height = "70%";
    x.style.width = "70%";
}

function autotab(current,to)
{
    if (current.getAttribute && current.value.length==current.getAttribute("maxlength")) 
    {
        to.focus() 
    }
}

function formatBankNo (BankNo){
    if (BankNo.value == "") return;
    var account = new String (BankNo.value);
    account = account.substring(0,19);
    if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}") == null){
        if (account.match (".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" +
        ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null){
            var accountNumeric = accountChar = "", i;
            for (i=0;i<account.length;i++){
                accountChar = account.substr (i,1);
                if (!isNaN (accountChar) && (accountChar != " ")) accountNumeric = accountNumeric + accountChar;
            }
            account = "";
            for (i=0;i<accountNumeric.length;i++){    
                if (i == 4) account = account + " "; 
                if (i == 8) account = account + " ";
                if (i == 12) account = account + " ";
                account = account + accountNumeric.substr (i,1)
            }
        }
    }
    else
    {
        account = " " + account.substring (1,5) + " " + account.substring (6,10) + " " + account.substring (14,18) + "-" + account.substring(18,25);
    }
    if (account != BankNo.value) BankNo.value = account;
}


/*function onlyNum(){
    if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}
var checkfn, checkmn,checkln,checkstateheck,checkcity,checkholder;

function checkfn(fn){ 
var name = document.getElementById('first_name'); 
//var name = document.getElementById(zm.); 
var pattern=/^[a-zA-Z]*$/; 
var nameErr = document.getElementById('fnameErr');
if(!pattern.test(name.value)){ 
//alert("only letters are permitted！");
nameErr.innerHTML="wrong";
nameErr.className="error";
checkfn=0;
return false; 
}
else{ 
     nameErr.innerHTML='correct';
     nameErr.className="success"; 
checkfn=1;
     return true; 
     } 

} 

function checkmn(mn){ 
var name = document.getElementById('middle_name'); 
//var name = document.getElementById(zm.); 
var pattern=/^[a-zA-Z]*$/; 
var nameErr = document.getElementById('mnameErr');
if(!pattern.test(name.value)){ 
//alert("only letters are permitted！");
nameErr.innerHTML="wrong";
nameErr.className="error";
checkmn=0;
return false; 
}
else{ 
     nameErr.innerHTML='correct';
     nameErr.className="success"; 
     checkmn=1;
     return true; 
     } 

} 

function checkln(ln){ 
var name = document.getElementById('last_name'); 
//var name = document.getElementById(zm.); 
var pattern=/^[a-zA-Z]*$/; 
var nameErr = document.getElementById('lnameErr');
if(!pattern.test(name.value)){ 
//alert("only letters are permitted！");
nameErr.innerHTML="wrong";
nameErr.className="error";
checkln=0;
return false; 
}
else{ 
     nameErr.innerHTML='correct';
     nameErr.className="success"; 
     checkln=1;
     return true; 
     } 

} 

function checkcity(city){ 
var name = document.getElementById('city'); 
//var name = document.getElementById(zm.); 
var pattern=/^[a-zA-Z]*$/; 
var nameErr = document.getElementById('cityErr');
if(!pattern.test(name.value)){ 
//alert("only letters are permitted！");
nameErr.innerHTML="wrong";
nameErr.className="error";
checkcity=0;
return false; 
}
else{ 
     nameErr.innerHTML='correct';
     nameErr.className="success"; 
     checkcity=1;
     return true; 
     } 

} 

function checkstate(state){ 
var name = document.getElementById('state'); 
//var name = document.getElementById(zm.); 
var pattern=/^[a-zA-Z]*$/; 
var nameErr = document.getElementById('stateErr');
if(!pattern.test(name.value)){ 
//alert("only letters are permitted！");
nameErr.innerHTML="wrong";
nameErr.className="error";
checkstate=0;
return false; 
}
else{ 
     nameErr.innerHTML='correct';
     nameErr.className="success"; 
     checkstate=1;
     return true; 
     } 

} 

function checkholder(holder){ 
var name = document.getElementById('cardholder'); 
//var name = document.getElementById(zm.); 
var pattern=/^[a-zA-Z]*$/; 
var nameErr = document.getElementById('holderErr');
if(!pattern.test(name.value)){ 
//alert("only letters are permitted！");
nameErr.innerHTML="wrong";
nameErr.className="error";
checkholder=0;
return false; 
}
else{ 
     nameErr.innerHTML='correct';
     nameErr.className="success"; 
     checkholder=1;
     return true; 
     } 

} 

function handlesubmit(){
  if(checkfn+checkmn+checkln+checkstateheck+checkcity+checkholder!=6){
        error.innerHTML = "Please check the input";
        error.className = "error active";
        event.preventDefault();
    }
    else{
      window.location.replace("index.html");
    }
}*/
function check(){
    var pattern=/^[a-zA-Z]*$/; 
    if (!pattern.test(document.orderform.first_name.value))    {        
        alert("Wrong firstname");        
        return false;    
        }
    if (!pattern.test(document.orderform.last_name.value))    {        
        alert("Wrong last tname");        
        return false;    
        }
    if (!pattern.test(document.orderform.city.value))    {        
        alert("Wrong city");        
        return false;    
        }
    if (!pattern.test(document.orderform.state.value))    {        
        alert("Wrong state");        
        return false;    
        }
    if (!pattern.test(document.orderform.cardholder.value))    {        
        alert("Wrong Card Holder Name");        
        return false;    
        }
    return true;
}

