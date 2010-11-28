function destroy(){
	if (confirm('Are you sure?')) { 
		var f = document.createElement('form'); 
		f.style.display = 'none'; 
		this.parentNode.appendChild(f); 
		f.method = 'POST'; 
		f.action = this.href;
		f.submit(); 
	};
	return false;
}