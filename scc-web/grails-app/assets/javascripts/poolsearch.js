
$(document).ready(function() {
/*$( "#searchBtn" ).click(function() 
    {  
        var cusip = $( "#cusipIdentifier" ).val()
        var poolnumber = $( "#poolNumber" ).val()
        if (cusip.indexOf(" ")>-1) {
            alert ("Spaces are not allowed in CUSIP identifier");
            document.getElementById("cusipIdentifier").focus();
            return false;
        } else if (poolnumber.indexOf(" ")>-1) {
            alert ("Spaces are not allowed in Pool Number");
            document.getElementById("poolNumber").focus();
            return false;
        } else {
            
            }
    });*/
    
    
    if ($('#poolError').val()=='cusipIdentifier') {
        document.getElementById("cusipIdentifier").focus();
    } else {
        document.getElementById("poolNumber").focus();
    }        
});