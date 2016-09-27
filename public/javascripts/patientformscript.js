/**
 * 
 */

$( document ).ready(function() {
	var form = $("#patientsform")
	$.ajax({ 
        type: "GET",
        dataType: "json",
        url: "/patientformjson",
        success: function(data){  
        	console.log(data);
           var page1 = data.pages[0];
           var question1 = page1.questions[0];
           var userdetails = question1.items;
           console.log(userdetails);
           for(var i=0;i<userdetails.length;i++){
        	   form.append(userdetails[i].title+"<input type='text' name="+userdetails[i].name+" placeholder="+userdetails[i].name+">");
        	   form.append("<br>");
           }
           
           var question2 = page1.questions[1];
           var radiobutton = question2.choices;
           var radioname = question2.name;
           for(var i=0;i<radiobutton.length;i++){
        	   form.append("<input type='radio' name="+radioname+" value="+radiobutton[i]+">"+radiobutton[i]+"");
           }
           form.append("<br>");
           form.append("<input type='button' value='submit' onclick='submitfunction()'>")
        }
    });
});


function submitfunction(){
	
	var form = $("#patientsformid");
	$.ajax({
		type : "GET",
		url : "/saveform",
		data : form.serialize(),
		success : function(data){
			document.write(data);
		}
	});
}