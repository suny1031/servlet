<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<head>
<style type="text/css">
   .valid_info{
      display:block;
      color:red;
      font-size:0.5vw;
   }
</style>

</head>
<body>
   <h1>회원 가입 양식</h1>
     <form action="${context}/member/mailauth.do" method="post" id="frm_join">
        <table>
           <tr>
              <td>ID : </td>
              <td>
                   <input type="text" name="id" id="id" size="10" required/>
                 <button type="button" onclick="idCheck()">check</button>
                 <span class="valid_info" id="idCheck"></span>
              </td>
           </tr>
           <tr>
              <td>PASSWORD : </td>
              <td>
                   <input type="password" name="pw" id="pw" required/>
                   <span id="pw_confirm" class="valid_info"></span>
              </td>
           </tr>
           <tr>
              <td>휴대폰번호 : </td>
              <td>
                   <input type="tel" name="tell" required/>
              </td>
           </tr>
           <tr>
              <td>email : </td>
              <td>
                   <input type="email" name="email" required/>
              </td>
           </tr>
           <tr>
              <td>
                 <input type="submit" value="가입" />
                 <input type="reset" value="취소" />
              </td>
          </tr>
      </table>
   </form>
   <script type="text/javascript">
   let idCheckFlg = false;
   //아이디 체크를 했는지 확인하기 위한 flg
   
   let idCheck = ()=>{
      let userId = id.value;    
      //사용자가 입력한 아이디, 서버로 전송되어야하는 파라미터값
      
      let idCheck = document.querySelector('#idCheck');
      let headerObj = new Headers();
      
      headerObj.append('content-type',"application/x-www-form-urlencoded");
      if(userId){
          fetch("/member/idcheck.do",{
             method : "post",
             headers : headerObj,
             body : "userId="+userId
          }).then(response=> {
              if(response.ok){
                 return response.text();
              }
              throw new AsyncPageError(response.text());
           })
           .then((msg) => {
              if(msg == 'success'){
                 idCheckFlg = true;
                 idCheck.innerHTML = '사용 가능한 아이디 입니다.';
              }else{
                 idCheckFlg = false;
                 idCheck.innerHTML = '사용 불가능한 아이디 입니다.';
              }
           }).catch(error=>{
              error.alertMessage();
           })
        }else{
           alert("아이디를 입력하지 않으셨습니다.");
        }
    }

   
   
   document.querySelector('#frm_join').addEventListener('submit',(e) => {
      /* 요소의 아이디로 엘리먼트 객체 호출 가능(웹표준이 아님)  */   
      if(!idCheckFlg){
         alert("아이디 중복검사를 통과하지 못했습니다.");
         id.value = "";
         e.preventDefault();
      }
      
      let password = pw.value;
      let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
     
      if(!(regExp.test(password))){
         //form의 데이터 전송을 막음
         e.preventDefault();
         pw_confirm.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상이어야 합니다.';
/*       pw_confirm.style.color = 'red';
         pw_confirm.style.fontSize = '0.5vw'; */
         pw.value = '';
      }
   });
   
   </script>
   
</body>
</html>