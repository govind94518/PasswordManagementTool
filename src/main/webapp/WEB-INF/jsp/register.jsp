<!DOCTYPE html>
<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>Fullscreen Overlay Navigation | CodingNepal</title>
      <link rel="stylesheet" href="style.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
          <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
          <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
          <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
          <link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">
          <script src="${pageContext.request.contextPath}/js/menu.js" type="text/javascript"></script>
   </head>
   <body>
      <input type="checkbox" id="active">
      <label for="active" class="menu-btn"><i class="fas fa-bars"></i></label>
      <div class="">
         <ul>
                <form name="student" action="createAccount" >
                   <li><a   > groupName:</a><input type="text" name="groupName"> </li>
                    <li><a  >     userName:</a> <input type="text" name="userName"></li>
                    <li><a   >                  url:</a> <input type="text" name="url"></li>
                    <li><a >       password: </a> <input type="text" name="password"></li>
                    <li>    <a >    <input type="submit" value="Add Student"> </a> </li>
                <br>
                 </form>
         </ul>
  <!--    </div>
      <div class="content">
        <div class="title">
              Successful Entered Welcome
         </div>
         <p>
          Click on Menu option
         </p>
      </div> -->
   </body>
</html>