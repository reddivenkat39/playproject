/**
 * 
 */

function signup(){
	var x = document.getElementById("unameid").value;
		if(x.length>10){
			console.log("reaching");
		$("#resultid").html( "<p>username should be less than 10 charachters</p>");
		}
	var form = $("#homepagelogin");
	var url = "/persistdata";
	$.ajax({
		type : "GET",
		url : url,
		data : form.serialize(),
		success : function(data){
			document.write(data);
			console.log(data);
		}
	});
		
}

function signin(){
	
	var form = $("#homepagelogin");
	var url = "/signin";
	$.ajax({
		type : "GET",
		url : url,
		data : form.serialize(),
		success : function(data){
			document.write(data);
		}
	});
}