
$(document).ready(function() {
    if ($('#poolError').val()=='cusipIdentifier') {
        document.getElementById("cusipIdentifier").focus();
    } else {
        document.getElementById("poolNumber").focus();
    }        
});