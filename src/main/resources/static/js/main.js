$(document).ready(function() {

	getProdctList();

	submitOrderForm()

});

function submitOrderForm() {

	$("#product-order-form").submit(
			function(event) {

				event.preventDefault();

				var data = $(this).serializeArray();
				var obj = [];

				$('#product-table > tbody  > tr').each(
						function() {

							var key1 = $(
									$(this).find('td:eq(5)').find("input")[0])
									.attr("name");
							var val1 = $(
									$(this).find('td:eq(5)').find("input")[0])
									.val();
							var key2 = $(
									$(this).find('td:eq(5)').find("input")[1])
									.attr("name");
							var val2 = $(
									$(this).find('td:eq(5)').find("input")[1])
									.val();

							if (key1 != undefined && val1 != undefined
									& key2 != undefined & val2 != undefined) {
								var o = {};
								o[key1] = val1;
								o[key2] = val2;
								obj.push(o);
							}

						});
				$.ajax({
					url : "/product/price/calculate",
					contentType : "application/json",
					type : "POST",
					dataType : 'json',
					data : JSON.stringify(obj),
					timeout : 600000,
					success : function(data) {
						$('#feedback').html("");
						setprice(data)
					},
					error : function(e) {
						var json = "<h4>Error Response</h4><pre>"
								+ e.responseJSON.message + "</pre>";
						$('#feedback').html(json);
					}
				});
			});
}
function getProdctList() {

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/product",
		data : {},
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			appendHtml(data);
		},
		error : function(e) {

		}
	});

}

function setprice(data) {
	$.each(data.items, function(key, value) {
		var td = $("#product-id-" + this.productId);
		$(td).find('td:last').html(this.price)
	});

	$("#total-price").html(data.totalPrice);
}

function appendHtml(data) {
	var tableBody = ""
	$
			.each(
					data,
					function(key, value) {
						tableBody += "<tr id='product-id-" + this.id
								+ "' data-id='" + this.id + "'>";
						tableBody += "<td>" + this.name + "</td>";
						tableBody += "<td>" + this.itemPrice + "</td>";
						tableBody += "<td>" + this.boxPrice + "</td>";
						tableBody += "<td>" + this.numberOfItems + "</td>";
						tableBody += "<td>" + this.numberOfItemsInBox + "</td>";
						tableBody += "<td><input type='number' value='0' name='numberOfItemOrder' min='0' step='1'> <input type='hidden' name='productId' value='"
								+ this.id + "' min='0' step='1'></td>";
						tableBody += "<td></td>";
						tableBody += "</tr>";
					});
	tableBody += "<tr><td colspan='6'>Total Price</td><td id='total-price'></td></tr>"
	$("#product-table > tbody").html(tableBody);
}