$("#subBtn").click(function() {
	if(($("#num_show").val() - 1) <= 1) {
		$("#num_show").val(1);
		$("#zongjia").text(parseInt($("#danjia").text()));
	} else {
		$("#num_show").val($("#num_show").val() - 1);
		$("#zongjia").text(parseInt($("#zongjia").text())-parseInt($("#danjia").text()));
	}
})

$("#addBtn").click(function() {
	$("#num_show").val(parseInt($("#num_show").val()) + 1);
	$("#zongjia").text(parseFloat($("#zongjia").text())+parseFloat($("#danjia").text()));
})

function btnClickOpertion(id) {

}