$(document).ready( function () {
	 var table = $('#productsTable').DataTable({
			"sAjaxSource": "/rest/products",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "name" },
				  { "mData": "price" },
				  { "mData": "stock" },
				  { "mData": "iva" },
				  { "mData": "enable",
			        	"render" :function(data, type, row){
			        	 let enable = row.enable;
			        	 if(enable){
			        		 return "Active"
			        	 }
			        	 return "Inactive"
			        	}  
			          },
				  { mRender: function (data, type, row) {
		                return '<button  class="text-warning" id="'+row.id+'"><span><i class="fas fa-pen"></i></span></button>'
		              },
		              className:"center"
			     }
			]
	 });
	 	 
	 $('#productsTable tbody').on( 'click', 'button', function () {
	        var actionId = this.id;  
	        
	        	$.get("/rest/product/"+actionId, function(product,status){
	        		$('#product-id').val(product.id)
	        		$('#edit-name').val(product.name)
	        		$('#edit-price').val(product.price)
	        		$('#edit-existences').val(product.stock)
	        		$('#edit-iva').val(product.iva)
	        		$("#edit-status-product").prop('checked',product.enable)
	        		 $("#ModalEditProduct").modal();
	        	});
	        	
	    } );
	
});
