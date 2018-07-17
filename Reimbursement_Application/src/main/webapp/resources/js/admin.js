let tickets = [];
let user = {};
let users = [];
document.addEventListener("DOMContentLoaded", redirect, false);
window.onload = function(){
	displayName();
	getPendingTickets();
	document.getElementById('pending-tickets').addEventListener('click', getPendingTickets);
	document.getElementById('view-tickets').addEventListener('click', getTickets);
	document.getElementById('view-approved-tickets').addEventListener('click', getApprovedTickets);
	document.getElementById('view-denied-tickets').addEventListener('click', getDeniedTickets);
	document.getElementById('view-all-employees').addEventListener('click', getUsers);
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

function getPendingTickets(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			tickets = JSON.parse(xhttp.responseText);
			displayTicketsToAudit(tickets);
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-pending-tickets.do', true);
	xhttp.send();
}

function getTickets(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			tickets = JSON.parse(xhttp.responseText);
			displayTickets(tickets);
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-tickets.do', true);
	xhttp.send();
}

function getUsers(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			users = JSON.parse(xhttp.responseText);
			displayUsers(users);
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-users.do', true);
	xhttp.send();
}

function getApprovedTickets(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			tickets = JSON.parse(xhttp.responseText);
			displayTickets(tickets);
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-approved-tickets.do', true);
	xhttp.send();
}

function getDeniedTickets(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			tickets = JSON.parse(xhttp.responseText);
			displayTickets(tickets);
		}
	}
	xhttp.open("GET", 'http://localhost:9001/Reimbursement_Application/get-denied-tickets.do', true);
	xhttp.send();
}

function displayUsers(users){
	let mainBody = document.getElementById("displayTable");
	let td = '<div class="table"><table class="table-hover w-100 d-block d-md-table">';
	let th = '<thead><tr><th>ID</th><th>Username</th><th>Password</th><th>First Name</th>'+
		'<th>Last Name</th><th>Email</th><th>Status</th>';
	let status = ['EMPLOYEE', 'MANAGER'];
	let tbody = '<tbody>';
	for(let i = 0; i < users.length; i++){
		tr = '<tr>';
		tr += '<td>' + users[i].employeeID + '</td>';
		tr += '<td>' + users[i].username + '</td>';
		tr += '<td> **** </td>';
		tr += '<td>' + users[i].firstName + '</td>';
		tr += '<td>' + users[i].lastName + '</td>';
		tr += '<td>' + users[i].email + '</td>';
		tr += '<td>' + status[users[i].status - 1] + '</td>';
		tr += '</tr>';
		tbody += tr;
	}
	tbody += '</tbody></table></div>'
	
	mainBody.innerHTML = td+th+tbody;
}

function displayTickets(tickets){
	let mainBody = document.getElementById("displayTable");
	let td = '<div class="table"><table class="table-hover w-100 d-block d-md-table">';
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

function displayTicketsToAudit(tickets){
	let mainBody = document.getElementById("displayTable");
	let td = '<div class="table"><table class="table-hover w-100 d-block d-md-table">';
	let th = '<thead><tr><th>Select</th><th>ID</th><th>Amount</th><th>Submitted</th><th>Resolved</th>'+
		'<th>Description</th><th>Image</th><th>Requester</th><th>Resolver</th>'+
		'<th>Status</th><th>Type</th>';
	let status = ['PENDING', 'APPROVED', 'DENIED'];
	let type = ['LODGING', 'TRAVEL', 'FOOD', 'OTHER'];
	let tbody = '<tbody>';
	for(let i = 0; i < tickets.length; i++){
		tr = '<tr>';
		tr += '<td><input type="checkbox" name="ticket" value="' + tickets[i].id + '" /></td>';
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
	tbody += '</tbody></table></div>';
	tbody += '<div class="shift-right"><input type="submit" name="choice" form="audit-form" value="Approve" class="btn btn-primary btn-sm" />'
		 + '  <input type="submit" name="choice" form="audit-form" value="Deny" class="btn btn-primary btn-sm" /></div>'
	
	mainBody.innerHTML = td+th+tbody;
}