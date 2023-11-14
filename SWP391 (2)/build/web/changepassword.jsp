<%-- 
    Document   : LeaveApplication
    Created on : May 28, 2023, 11:36:10 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS -->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" type="text/css" href="./css/profile.css">
        <!-- Font-icon CSS -->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>


        <style>
            body {
                font-family: 'Roboto', sans-serif;
            }

            h1 {
                text-align: center;
            }

            h6 {
                text-align: center;
            }

            .form-container {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-container label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .form-container input[type="text"] {
                width: 100%;
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-container .form-group {
                margin-bottom: 5px;
            }

            .form-container .btn-container {
                text-align: center;
                margin-top: 20px;
            }

            .form-container .btn-container input[type="submit"] {
                padding: 5px 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
        </style>
        <style>
            .btn-check-status {
                background-color: #4CAF50; /* Màu nền */
                color: white; /* Màu chữ */
                padding: 5px 10px; /* Kích thước nút */
                border: none; /* Loại bỏ viền */
                border-radius: 5px; /* Bo tròn góc */
                cursor: pointer; /* Hiệu ứng con trỏ */
                font-size: 14px; /* Cỡ chữ */
            }

            .btn-check-status:hover {
                background-color: #45a049; /* Màu nền khi hover */
            }
        </style>
        <jsp:include page="viewprofilepage.jsp"/>
    </head>
    <body>

    </div>
    <div class="container emp-profile" style=" margin-top:150px  ; margin-left: 336px;">
        <form method="post">
            <div class="row">
                <div class="col-md-4">
                   <div class="profile-img">
   
  
        
   
                           </form>

</div>
<script>
function changeUserImage(event) {
    const fileInput = event.target;
    const userImage = document.getElementById('userImage');

    if (fileInput.files && fileInput.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            userImage.src = e.target.result;

            // Cập nhật hình ảnh người dùng trên máy chủ ở đây nếu cần.
            // Sử dụng Ajax hoặc gửi dữ liệu hình ảnh lên máy chủ.

            // Ví dụ:
            // const formData = new FormData();
            // formData.append('newImage', fileInput.files[0]);
            // fetch('uploadImage.php', {
            //     method: 'POST',
            //     body: formData
            // })
            // .then(response => response.json())
            // .then(data => {
            //     console.log('Image uploaded:', data);
            // })
            // .catch(error => {
            //     console.error('Error uploading image:', error);
            // });
        };

        reader.readAsDataURL(fileInput.files[0]);
    }
}
</script>

                </div>
                <div class="col-md-6">
                    <div class="ibox">
                                <div class="ibox-body">
                                    <ul class="nav nav-tabs tabs-line">
                                        <li class="nav-item">
                                            <a class="nav-link active" href="#tab-1" data-toggle="tab"><i class="ti-settings"></i> Hồ sơ</a>
                                        </li>
                                    </ul>
              <div class="tab-content">
                   <div class="tab-pane fade show active" id="tab-1">
                       <form action="UserServlet?action=changepassword" method="POST">
                         <input name="id" type="text" value="${sessionScope.userlogin.id}" hidden="">
                              <div class="form-group">
                                                    <label>Mật khẩu cũ</label>
                                                    <input name="password" class="form-control" type="password" placeholder="Password">
                                                </div>
                                <c:if test="${requestScope.EDIT_ERROR != null}">
                                                    <div class="error-message color-red">${requestScope.EDIT_ERROR.notMatchPassword}</div>
                                                </c:if>
                                         <div class="form-group">
                                                    <label>Mật khẩu mới</label>
                                                    <input name="newPassword" class="form-control" type="password" placeholder="New Password">
                                                </div>     
                                                     <div class="form-group">
                                                    <label>Nhập lại mật khẩu</label>
                                                    <input name="confirm" class="form-control" type="password" placeholder="Confirm">
                                                </div>
                                                <c:if test="${requestScope.EDIT_SUCCESS != null}">
                                                    <div class="error-message color-green">${requestScope.EDIT_SUCCESS}</div>
                                                </c:if>
                                                <div class="form-group">
                                                  <input class="btn btn-default" type="submit"  />
                                                </div>
                       </form>

                        </div>
                    </div>
                </div>
            </div>          
    </div>

</body>
</html>