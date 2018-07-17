/Reimbursement_Application/document.addEventListener("DOMContentLoaded", redirect, false);

function redirect(){
	if((window.location.href != 'http://localhost:9001/Reimbursement_Application/create-ticket.do') &&
			(window.location.href != 'http://localhost:9001/Reimbursement_Application/create-employee.do')){
		var f = document.getElementById('redirect');
		f.submit();
	}
}