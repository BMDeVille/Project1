document.addEventListener("DOMContentLoaded", getProfile, true);
let user = {}
function getProfile(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			user = JSON.parse(xhttp.responseText);
			displayProfile(user);
			document.getElementById("Welcome").innerHTML = user.firstName + "'s Profile";
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-user.do', true);
	xhttp.send();
}

function displayProfile(user){
	let mainBody = document.getElementById("displayProfile");
	let top = '<div class="center">';
	let d1 = '<dl class="profile">';
	d1 += '<dt>ID</dt> <dd>' + user.employeeID + '</dd>';
	d1 += '<dt>Username</dt> <dd>' + user.username + '</dd>';
	d1 += '<dt> First Name</dt> <dd>' + user.firstName + '</dd>';
	d1 += '<dt> Last Name</dt> <dd>' + user.lastName + '</dd>';
	d1 += '<dt> Email</dt> <dd>' + user.email + '</dd>';
	d1 += '</d1>';
	
	top += d1 + '</div>';
	
	mainBody.innerHTML = top;
}