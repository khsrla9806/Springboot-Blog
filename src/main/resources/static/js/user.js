let index = {
    init: function() {
        // btn-save 라는 id를 찾고, on(어떤 이벤트가 들어오면, 무엇을 할건지 결정)
        // on() 안에서 function()이 아닌 () => {}을 쓰는 이유는 this 값을 바인딩하기 위함이다.
        // 만약 function(){}을 사용해서 this 사용하면 그 this == window 객체를 가리킴
        $("#btn-save").on("click", () => { // btn-save 라는 id 를 갖는 아이템이 클릭되면 save() 메서드가 실행됨
            this.save();
        });

        $("#btn-login").on("click", () => {
            this.login();
        });
    },

    save: function() {
        // alert("user의 save함수가 호출됨");

        let data = { // id 값으로 username, password, email 로 들어온 값을 바인딩해서 저장
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // console.log(data);

        // ajax 통신을 해서 3개의 데이터를 json 으로 변경해서 insert 요청을 한다.
        // ajax 호출 시 default가 비동기 호출 (비동기 실행을 할 수 있다.)
        $.ajax({
            // 회원가입 수행을 요청
            // 너 어떤 방식으로 호출할거야?
            type: "POST",
            // 어느 URL 요청을 할래?
            url: "/api/user",
            // 위에서 받은 javascript 타입의 오브젝트를 json 타입 문자열로 변경해줌
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            // 응답이 오는 타입은 기본적으로 버퍼이기 때문에 String 이다.
            // 하지만 json 이라고 적어주면 응답되는 데이터가 json 타입 처럼 생겼는지 확인한 후 맞다면 javascript 오브젝트로 바꿔준다.
            dataType: "json"
        }).done(function(response) {
            // 정상이면 done 실행
            // console.log(response);
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            // 실패하면 fail 실행
            alert(JSON.stringify(error));
        });
    },

    login: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };

        $.ajax({
            type:"POST",
            url:"/api/user/login",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(response) {
            alert("로그인이 완료되었습니다.");
            location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init(); // index 를 바인딩하고 가지고 있다가 btn-save 에 클릭이 들어오면 init function 실행