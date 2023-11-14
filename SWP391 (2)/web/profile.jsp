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
        <script>
function previewImage(input) {
    const avatarPreview = document.getElementById('avatarPreview');
    var oldImageValue = document.querySelector("input[name='oldimage']").value;

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            avatarPreview.src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    } else {
         avatarPreview.src=oldImageValue ;
        // Người dùng không chọn hình ảnh mới, giữ lại ảnh cũ
        // You can add code here to handle the case when no new image is selected.
    }
}

</script>

  <form action="UserServlet?action=editProfile" method="POST">
       <div class="container emp-profile" style=" margin-top:150px  ; margin-left: 336px;">
             <div class="row">
                <div class="col-md-4">
                   <div class="profile-img">
                        <img src="img/${sessionScope.userlogin.image}" alt="User cc"  id="avatarPreview"/>
                        <div class="file btn btn-lg btn-primary">
                                    Change Photo
                        <input type="file" id="newAvatar" name="image" onchange="previewImage(this)" />
                            <input type="hidden"  name="oldimage" value="${sessionScope.userlogin.image}" />
                        </div>
                    </div>
                        
                </div>
                    <div class="col-md-6">
                            <div class="ibox">
              x    <div class="ibox-body">
                                        <ul class="nav nav-tabs tabs-line">
                                             <li class="nav-item">
                                                  <a class="nav-link active" href="#tab-1" data-toggle="tab"><i class="ti-settings"></i> Hồ sơ</a>
                                            </li>
                                        </ul>
                                    </div>
                           </div>
                    </div>
               </div>               
         
                   <div class="tab-content">
                   <div class="tab-pane fade show active" id="tab-1">
                      
                              <input name="id" type="text" value="${sessionScope.userlogin.id}" hidden="">
                            <input name="role" type="text" value="${sessionScope.userlogin.roleid.name}" hidden="">
                            <input name="gender" type="text" value="${sessionScope.userlogin.gender}" hidden="">
                         <input name="password" type="password" value="${sessionScope.userlogin.password}" hidden="">
                           
                       <div class="form-group">
                                                    <label>Họ</label>
                                                    <input name="firstname" class="form-control" readonly="" type="text" value="${sessionScope.userlogin.firstname}" placeholder="First Name">
                                                </div>
                                                
                         <div class="form-group">
                                                    <label>Tên</label>
                                                    <input name="lastname" class="form-control" readonly="" type="text" value="${sessionScope.userlogin.lastname}" placeholder="Last Name">
                                                </div>  
                            <div class="form-group">
                                                    <label>Email</label>
                                                    <input name="email" class="form-control" readonly="" type="text" value="${sessionScope.userlogin.email}" placeholder="Email address">
                                                </div>
                            <div class="form-group">
                                                    <label>Địa chỉ</label>
                                                    <input name="address" class="form-control" type="text" value="${sessionScope.userlogin.address}" placeholder="Address">
                                                </div>
                            <div class="form-group">
                                                    <label>Ngày sinh</label>
                                                    <input name="birthday" class="form-control" type="text" value="${sessionScope.userlogin.birthday}" placeholder="Birthday">
                                                </div>    
                             <div class="form-group">
                                                    <label>Số điện thoại</label>
                                                    <input name="phone" class="form-control" readonly="" type="text" value="${sessionScope.userlogin.phone}" placeholder="Phone Number">
                                                </div>
                           
                                                <div class="form-group">
                                                  <input class="btn btn-default" type="submit"  />
                                               </div>
                    </div>
                </div>
                              
                                                <a href="changepassword.jsp">Thay đổi mật khẩu</a>
    </div>

  </form> 
                                                a>
<!--                      <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal"
              data-target="#ModalUP" onclick="getData('${sessionScope.userlogin.id}"><i class="fas fa-edit"></i></button>  
              <div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
             data-keyboard="false">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group  col-md-12">
                                <span class="thong-tin-thanh-toan">
                                    <h5>Chỉnh sửa thông tin nhân viên cơ bản</h5>
                                </span>
                            </div>
                        </div>

                        <form action="ChangPassword" method="POST" id="changepasswords">


                        </form>    -->

                       
  <script>
                            oTable = $('#sampleTable').dataTable();
                            $('#all').click(function (e) {
                                $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
                                e.stopImmediatePropagation();
                            });

                            function getData(i) {
                                $.ajax({
                                    type: 'Post',
                                    url: '${pageContext.request.contextPath}/LoadPassword',
                                    data: {
                                        id: i
                                    },
                                    success: function (data, textStatus, jqXHR) {
                                        $('#changepasswords').html(data);
                                    }
                                })
                            }
                        </script>
</body>
</html>



