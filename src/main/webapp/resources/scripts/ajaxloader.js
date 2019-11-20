jQuery(document).ready(function() {
		window.location.href = '#/';
});

function startError() {
	stopLoad();
	PF('error').show();
}

function stopError() {
	stopLoad();
	PF('error').hide()
}

function startLoad() {
	stopError();
	PF('statusDialog').show();
}

function stopLoad() {
	PF('statusDialog').hide()
}

function startStatus() {
	document.getElementById("loading-list").style.display = "block";
}

function endStatus() {
	document.getElementById("loading-list").style.display = "none";
}