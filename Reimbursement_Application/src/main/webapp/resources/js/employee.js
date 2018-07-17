let tickets = [];
let user = {};
document.addEventListener("DOMContentLoaded", redirect, false);
window.onload = function(){
	displayName();
	document.getElementById("view-ticket").addEventListener('click', getTickets);
}

function redirect(){
	if(window.location.href != 'http://localhost:9001/Reimbursement_Application/redirect-home.do'){
		var f = document.getElementById('redirect');
		f.submit();
	}
}

function displayName(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			user = JSON.parse(xhttp.responseText);
			document.getElementById("Welcome").innerHTML = "Welcome back, "+user.firstName+"!";
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-user.do', true);
	xhttp.send();
}

function getTickets(){
	console.log("going");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			tickets = JSON.parse(xhttp.responseText);
			displayTickets(tickets);
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-user-tickets.do', true);
	xhttp.send();
}

function displayTickets(tickets){
	let mainBody = document.getElementById("displayTable");
	let td = '<div class="table"><table class="table-hover  w-100 d-block d-md-table">';
	let th = '<thead><tr><th>ID</th><th>Amount</th><th>Submitted</th><th>Resolved</th>'+
		'<th>Description</th><th>Image</th><th>Requester</th><th>Resolver</th>'+
		'<th>Status</th><th>Type</th>';
	let status = ['PENDING', 'APPROVED', 'DENIED'];
	let type = ['LODGING', 'TRAVEL', 'FOOD', 'OTHER'];
	let tbody = '<tbody>';
	for(let i = 0; i < tickets.length; i++){
		tr = '<tr>';
		tr += '<td>' + tickets[i].id + '</td>';
		tr += '<td>' + tickets[i].amount + '</td>';
		tr += '<td>' + new Date(tickets[i].submitted) + '</td>';
		tr += '<td>' + (tickets[i].resolved === null ? 'undefined' : new Date(tickets[i].resolved)) + '</td>';
		tr += '<td>' + tickets[i].description + '</td>';
		tr += '<td>' + tickets[i].image + '</td>';
		tr += '<td>' + tickets[i].requester_id + '</td>';
		tr += '<td>' + tickets[i].handler_id + '</td>';
		tr += '<td>' + status[tickets[i].status - 1] + '</td>';
		tr += '<td>' + type[tickets[i].type - 1] + '</td>';
		tr += '</tr>';
		tbody += tr;
	}
	tbody += '</tbody></table></div>'
	
	mainBody.innerHTML = td+th+tbody;
}