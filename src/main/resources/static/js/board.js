let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    deleteById: function() {
        // 태그의 내용을 가져올 때는 text()를 사용
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function(response) {
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update: function() {
        // 게시글의 id를 가져옴 (태그의 value 값을 가져올 때는 val())
        let id = $("#id").val();

        // 입력받는 데이터를 가져옴
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function() {
            alert("수정이 완료되었습니다.");
            location.href = "/board/" + id;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();