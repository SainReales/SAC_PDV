$(document).ready( function () {
	 var table = $('#invoicesTable').DataTable({
					
			"sAjaxSource": "/rest/invoices",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
			      { "mData": "description" },
		          { "mData": "client.name" },
				  { "mData": "createdAt" },
				  { "mData": "user.username" },
				  { "mData": "total",
					  "render": function (data, type, row) {
			        		 var total = row.total;
			        		 return total.toFixed(4);
				           }  
				  },
				  { mRender: function (data, type, row) {
		                return '<a href="/invoice/details/'+row.id+'"><button class="text-warning"><span><i class="fas fa-info"></i></span></button> </a>'
		              },
		              className:"center"
			     }
			]
	 });
	 
	
});