let index = {
    init: function() {
        // btn-save 라는 id를 찾고, on(어떤 이벤트가 들어오면, 무엇을 할건지 결정)
        $("#btn-save").on("click", () => { // btn-save 라는 id 를 갖는 아이템이 클릭되면 save() 메서드가 실행됨
            this.save();
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

        $a.jax().done().fail(); // ajax 통신을 해서 3개의 데이터를 json 으로 변경해서 insert 요청을 한다.
    }
}

index.init(); // index 를 바인딩하고 가지고 있다가 btn-save 에 클릭이 들어오면 init function 실행