$(function(){
   $("[name='pageRows']").change(function(){
    var form = $("[name='frmPageRows']");
    form.attr("method","POST");
    form.attr("action","pageRows");
    form.submit();
   });
});

function btnRec(){

}



