/**Main application menu javascript*/

function highlight(obj){
	
	var new_icon;
	console.log("object id= "+obj.id);
	var selected = document.getElementById(obj.id);
	switch(selected.id){
		case "mn-item-manuscripts":
		new_icon = "resc/icons/menu-icons/inactive/ico_manuscripts.png";
		console.log(new_icon);
		break;
		case 'mn-item-authors':
		new_icon = "resc/icons/menu-icons/inactive/ico_authors.png";
		console.log(new_icon);
		break;
		case 'mn-item-reviewers':
		new_icon = "resc/icons/menu-icons/inactive/ico_reviewers.png";
		console.log(new_icon);
		break;
		case 'mn-item-journals':
		new_icon = "resc/icons/menu-icons/inactive/ico_journals.png";
		console.log(new_icon);
		break;
		default:
		console.log("default");
	}
	var child = selected.getElementsByTagName('img')[0];
	child.src = new_icon;
	obj.style.backgroundColor = "#cccccc";
	
	}
	
	function reset(obj){
	var new_icon;
	//console.log(obj.id);
	var selected = document.getElementById(obj.id);
	//console.log("Selected = "+selected);
	switch(selected.id){
		case 'mn-item-manuscripts':
		new_icon = "resc/icons/menu-icons/active/ico_manuscripts.png";
		console.log(new_icon);
		break;
		case 'mn-item-authors':
		new_icon = "resc/icons/menu-icons/active/ico_authors.png";
		console.log(new_icon);
		break;
		case  'mn-item-reviewers':
		new_icon = "resc/icons/menu-icons/active/ico_reviewers.png";
		console.log(new_icon);
		break;
		case  'mn-item-journals':
		new_icon = "resc/icons/menu-icons/active/ico_journals.png";
		console.log(new_icon);
		break;
		default:
		console.log("default");
	}
	//console.log("First chile in selection: "+child);
	var child = selected.getElementsByTagName('img')[0];
	//console.log(new_icon);
	child.src = new_icon;
	obj.style.backgroundColor = "white";
	}

	function select(obj){
	var style = obj.getAttribute('style');
	console.log(style);
	console.log("selected class changed");
	}

