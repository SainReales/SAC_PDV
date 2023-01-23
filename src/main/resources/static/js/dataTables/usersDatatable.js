$(document).ready( function () {
	 var table = $('#usersTable').DataTable({
			"sAjaxSource": "/rest/users",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "username" },
		          { "mData": "enable",
		        	"render" :function(data, type, row){
		        	 let enable = row.enable;
		        	 if(enable){
		        		 return "Active"
		        	 }
		        	 return "Inactive"
		        	}  
		          },
		          { "mData": "roles",
		        	 "render": function (data, type, row) {
		        		 var roles = row.roles;
		        		 let cadena = "";
		        		 roles.forEach( function(valor, indice, array) {
		        	
		        			    var aux = valor.authority;
		        			     cadena += aux.substring(5, aux.length)+" ";
		        			});
			                return cadena;
			           }
		        },
		        { mRender: function (data, type, row) {
	                return '<button  class="text-warning" id="'+row.id+'"><span><i class="fas fa-pen"></i></span></button>'
	              },
	              className:"center"
		     }
			]
	 });
	 	 
	 $('#usersTable tbody').on( 'click', 'button', function () {
	        var actionId = this.id;  
	        var actionClass = this.className;
	        
	        
	        if(actionClass == "text-danger") {
	        var url = '/deleteUser/'+actionId;
	        $('#BtnDeleteUser').attr("href",url);
	        $("#ModalDeleteUser").modal();
	        }else{
	        	$.get("/rest/users/"+actionId, function(user,status){
	        		$('#user-id').val(user.id)
	        		$('#edit-username').val(user.username)  		
	        		let checked  = user.enable;
	        		$('#edit-status').prop('checked',checked);
	        		
	        		let roleUser  = false;
	        		let roleAdmin = false;
	        		user.roles.forEach(function(value,indice,array){
	        			if(value.authority === "ROLE_ADMIN"){
	        				roleAdmin = true;
	        			}
	        			if(value.authority === "ROLE_USER"){
	        				roleUser = true;
	        			}
	        		});
	        		$('#edit-role_admin').prop('checked',roleAdmin);
	        		$('#edit-role_user').prop('checked',roleUser);
	        		 $("#ModalEditUser").modal();
	        	});
	        	
	        }
	    } );
	
});