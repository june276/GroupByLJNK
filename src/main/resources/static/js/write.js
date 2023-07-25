$(function(){
    let index = 0;
    $("#btnInsert").click(function(){
        $("#files").append(`
            <div class="input-group mb-3">
            <input class="form-control col-xs-3" type="file" id="fileId" name="upfile${index}"/>
            <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
            </div>`);
        index++;

    });

    $("#writeBtn").click(function(){
        let frm = $("form[name='writeFrm']");
         if($("#fileId").length==0)
         {
                alert("첨부 파일이 없습니다.");
                $("#fileId").focus();
                return false;
         }
         else if($("#fileId").val()=="")
         {
                alert("첨부 파일이 없습니다.");
                $("#fileId").focus();
                return false;
         }else
         {
            frm.submit();
         }
    });


    $("#content").summernote({
        height:100
    });

});